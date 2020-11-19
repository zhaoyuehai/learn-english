package com.yuehai.learn.english.mapper

import com.yuehai.learn.english.entity.RoleEntity
import com.yuehai.learn.english.entity.UserEntity
import org.apache.ibatis.annotations.Param

interface UserRoleMapper {
    fun selectRoleByUserId(id: Long): RoleEntity?
    fun insertUserRole(@Param("userId") id: Long,@Param("roleCode") roleCode: Int): Int
    fun updateUserRole(@Param("userId") id: Long, roleCode: Int): Int
}
