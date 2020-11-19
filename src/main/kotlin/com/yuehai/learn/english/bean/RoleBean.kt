package com.yuehai.learn.english.bean

import com.yuehai.learn.english.entity.RoleEntity

data class RoleBean(val code: Int, val name: String) {
    constructor(role: RoleEntity) : this(role.code,role.name)
}