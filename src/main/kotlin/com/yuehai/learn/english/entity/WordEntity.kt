package com.yuehai.learn.english.entity

data class WordEntity(val id: Long?, val contentEN: String?, val contentCN: String?, val createTime: String?, val updateTime: String?, val sortBy: Long?, val type: Int?) {
    constructor(contentEN: String?, contentCN: String?, id: Long? = null, createTime: String? = null) : this(id, contentEN, contentCN, createTime, null, null, null)
}