package com.yuehai.learn.english.controller

import ResultUtil
import com.yuehai.learn.english.common.ErrorResult
import com.yuehai.learn.english.exception.MyException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ModelAttribute
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

abstract class BaseController {
    protected lateinit var request: HttpServletRequest
    protected lateinit var response: HttpServletResponse

    @ModelAttribute
    fun setReqAndRes(request: HttpServletRequest, response: HttpServletResponse) {
        this.request = request
        this.response = response
    }

    @ExceptionHandler
    fun exceptionHandler(exception: Exception): Any {
        return when (exception) {
            is MyException -> {
                ResultUtil.fail(exception.errorResult)
            }
            is AccessDeniedException -> {
                ResultUtil.fail(ErrorResult.ACCESS_DENIED)
            }
            else -> {
                ResultUtil.fail(exception.message)
            }
        }
    }
}