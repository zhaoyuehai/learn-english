package com.yuehai.learn.english.entity

data class WordEntity(val id: Long?,
                      val contentEN: String?,
                      val contentCN: String?,
                      val createTime: String? = null,
                      val updateTime: String? = null,
                      val sortBy: Long? = null,
                      val type: Int? = null)