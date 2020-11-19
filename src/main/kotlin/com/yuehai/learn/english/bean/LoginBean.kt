package com.yuehai.learn.english.bean

data class LoginBean(override val accessToken: TokenBean, override val refreshToken: TokenBean, val role: RoleBean?, val userBean: UserBean) : TokensBean(accessToken, refreshToken)