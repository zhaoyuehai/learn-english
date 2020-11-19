package com.yuehai.learn.english.entity

import java.util.*

data class UserEntity(val id: Long?, val phone: String, val password: String, val userName: String?, val status: Int?, val email: String?, val avatar: String?, val createTime: Date?, val updateTime: Date?) {
    constructor(phone: String, password: String) : this(null, phone, password, null, null, null, null, null, null)
}
