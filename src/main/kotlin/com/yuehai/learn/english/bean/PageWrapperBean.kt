package com.yuehai.learn.english.bean

data class PageWrapperBean<T>(val total: Long, val pages: Int, val dataList: List<T>)