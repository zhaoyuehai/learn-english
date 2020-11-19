package com.yuehai.learn.english.mapper

import com.yuehai.learn.english.entity.UserEntity

interface UserMapper {
    fun selectUserByPhone(phone: String): UserEntity?
    fun insertUser(userEntity: UserEntity): Int
}
