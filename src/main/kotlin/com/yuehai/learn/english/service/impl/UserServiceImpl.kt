package com.yuehai.learn.english.service.impl

import ResultUtil
import com.yuehai.learn.english.bean.*
import com.yuehai.learn.english.common.Constants
import com.yuehai.learn.english.common.ErrorResult
import com.yuehai.learn.english.entity.UserEntity
import com.yuehai.learn.english.exception.MyException
import com.yuehai.learn.english.mapper.UserMapper
import com.yuehai.learn.english.mapper.UserRoleMapper
import com.yuehai.learn.english.security.JwtUtil
import com.yuehai.learn.english.service.UserService
import com.yuehai.learn.english.util.StringUtils
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var userRoleMapper: UserRoleMapper

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun login(phone: String?, password: String?): ResultBean {
        val resultBean = checkUserInfo(phone, password)
        if (resultBean != null) return resultBean
        try {
            SecurityContextHolder.getContext().authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(phone, String(Base64.decodeBase64(password))))
            val userBean = findUserByPhone(phone!!)
            if (userBean != null) {
                var role: RoleBean? = null
                userBean.id?.let { id ->
                    userRoleMapper.selectRoleByUserId(id)?.let { entity ->
                        role = RoleBean(entity)
                    }
                }
                return ResultUtil.success(LoginBean(getAccessToken(phone), getRefreshToken(phone), role, userBean), "登录成功")
            } else {
                throw UsernameNotFoundException("")
            }
        } catch (e: Exception) {
            when (e) {
                is UsernameNotFoundException -> throw MyException(ErrorResult.USER_NOT_FOUND)
                is BadCredentialsException -> throw MyException(ErrorResult.PASSWORD_ERROR)
                is LockedException -> throw MyException(ErrorResult.USER_LOCKED)
                is DisabledException -> throw MyException(ErrorResult.USER_DISABLED)
                else -> throw MyException(ErrorResult.ERROR)
            }
        }
    }

    private fun getAccessToken(sub: String): TokenBean {
        val expiration = jwtUtil.tokenExpiration()
        return TokenBean(jwtUtil.generateToken(sub, "accessToken", expiration), expiration)
    }

    private fun getRefreshToken(sub: String): TokenBean {
        val expiration = jwtUtil.refreshTokenExpiration()
        return TokenBean(jwtUtil.generateToken(sub, "refreshToken", expiration), expiration)
    }

    override fun token(refreshToken: String): ResultBean {
        if (jwtUtil.isTokenExpired(refreshToken)) {
            throw MyException(ErrorResult.LOGIN_INFO_EXPIRED)
        } else {
            val gentType = jwtUtil.getGentTypeFromToken(refreshToken)
            if(gentType !="refreshToken") throw MyException(ErrorResult.ERROR)
            val sub = jwtUtil.getSubFromToken(refreshToken)
            return ResultUtil.success(TokensBean(getAccessToken(sub), getRefreshToken(sub)))
        }
    }

    private fun findUserByPhone(phone: String): UserBean? {
        val entity = userMapper.selectUserByPhone(phone) ?: return null
        return UserBean(entity)
    }

    private fun setRole(id: Long, roleCode: Int): Int {
        val roleEntity = userRoleMapper.selectRoleByUserId(id)
        return if (roleEntity == null) {
            userRoleMapper.insertUserRole(id, roleCode)
        } else {
            userRoleMapper.updateUserRole(id, roleCode)
        }
    }

    override fun register(phone: String?, password: String?): ResultBean {
        val resultBean = checkUserInfo(phone, password)
        if (resultBean != null) return resultBean
        //检查是否已注册过
        if (findUserByPhone(phone!!) != null) {
            throw MyException(ErrorResult.PHONE_EXIST)
        }
        val encodedPassword = this.passwordEncoder().encode(String(Base64.decodeBase64(password)))
        val userEntity = UserEntity(phone, encodedPassword)
        var res = userMapper.insertUser(userEntity)
        val id = userEntity.id
        if (res > 0 && id != null) {//用户表插入新用户成功后，为该用户设置默认游客角色 --> GUEST
            res = setRole(id, Constants.GUEST)
        }
        return if (res > 0) ResultUtil.success(id, "注册成功") else ResultUtil.fail("注册失败")
    }

    private fun checkUserInfo(phone: String?, password: String?): ResultBean? {
        if (phone.isNullOrEmpty()) {
            return ResultUtil.fail("手机号码不能为空")
        }
        if (!StringUtils.isChinaPhoneLegal(phone)) {
            return ResultUtil.fail("手机号格式错误")
        }
        if (password.isNullOrEmpty()) {
            return ResultUtil.fail("密码不能为空")
        }
        if (!StringUtils.isPassword(password)) {
            return ResultUtil.fail("密码格式错误【6-16位数字或字母】")
        }
        return null
    }
}