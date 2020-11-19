package com.yuehai.learn.english.bean

import com.yuehai.learn.english.entity.UserEntity
import io.swagger.annotations.ApiModel

@ApiModel("用户信息")
data class UserBean(val id: Long?, val userName: String?, var phone: String?, var email: String?, var avatar: String?) {
    constructor(entity: UserEntity) : this(entity.id, entity.userName, entity.phone, entity.email, entity.avatar)
}
