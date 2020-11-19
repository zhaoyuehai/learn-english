package com.yuehai.learn.english.service

import com.yuehai.learn.english.bean.ResultBean


interface UserService {
    fun login(phone: String?, password: String?): ResultBean
    fun register(phone: String?, password: String?): ResultBean
    fun token(refreshToken: String): ResultBean
}
