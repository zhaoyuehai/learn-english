package com.yuehai.learn.english.service.impl

import com.yuehai.learn.english.mapper.UserMapper
import com.yuehai.learn.english.mapper.UserRoleMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var userRoleMapper: UserRoleMapper

    @Throws(AuthenticationException::class)
    override fun loadUserByUsername(username: String?): UserDetails? {
        if (username == null) throw UsernameNotFoundException("userName or phone isNullOrEmpty!")
        val user = userMapper.selectUserByPhone(username)
                ?: throw UsernameNotFoundException("$username is not registered!")
        if (user.status == 2) throw LockedException("User unavailable!")
        if (user.status == 3) throw DisabledException("User disabled!")
        val id = user.id ?: throw DisabledException("User disabled!")
        val authorities = mutableListOf<SimpleGrantedAuthority>()
        userRoleMapper.selectRoleByUserId(id)?.let {
            authorities.add(SimpleGrantedAuthority(it.name))
        }
        return User(user.phone, user.password, authorities)
    }
}