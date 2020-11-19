package com.yuehai.learn.english.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("返回结果")
data class ResultBean(
        @ApiModelProperty("code")
        var code: String,
        @ApiModelProperty("message")
        val message: String,
        @ApiModelProperty("serviceVersion")
        val serviceVersion: String,
        @ApiModelProperty("data")
        val data: Any? = null
)