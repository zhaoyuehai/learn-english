package com.yuehai.learn.english.exception

import com.yuehai.learn.english.common.ErrorResult

class MyException(val errorResult: ErrorResult) : RuntimeException()