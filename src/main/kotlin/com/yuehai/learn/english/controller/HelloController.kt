package com.yuehai.learn.english.controller

import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("api/v1/hello")
class HelloController : BaseController() {
    @ApiOperation("hello 返回日期")
    @RequestMapping("date")
    fun hello() = "hello ${Date()}"
}