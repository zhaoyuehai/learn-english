package com.yuehai.learn.english.controller

import com.yuehai.learn.english.service.UserService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/user")
class UserController : BaseController() {

    @Autowired
    private lateinit var userService: UserService

    @ApiOperation("注册", notes = "注册新用户，手机号唯一")
    @ApiImplicitParams(
            ApiImplicitParam(name = "phone", value = "手机号", paramType = "form", dataType = "String", required = true),
            ApiImplicitParam(name = "password", value = "密码，需要Base64进行编码", paramType = "form", dataType = "String", required = true)
    )
    @PostMapping("register")
    fun register(@RequestParam("phone") phone: String, @RequestParam("password") password: String) = userService.register(phone, password)

    @ApiOperation("登录", notes = "")
    @ApiImplicitParams(
            ApiImplicitParam(name = "phone", value = "手机号", paramType = "form", dataType = "String", required = true),
            ApiImplicitParam(name = "password", value = "密码，需要Base64进行编码", paramType = "form", dataType = "String", required = true)
    )
    @PostMapping("login")
    fun login(@RequestParam("phone") phone: String, @RequestParam("password") password: String) = userService.login(phone, password)

    @ApiOperation("刷新token", notes = "")
    @PostMapping("token")
    fun token(@RequestParam("refreshToken") refreshToken: String) = userService.token(refreshToken)
}