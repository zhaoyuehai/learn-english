package com.yuehai.learn.english.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class WordBean(@ApiModelProperty("单词主键id") val id: Long, @ApiModelProperty("contentEN") val contentEN: String?, @ApiModelProperty("contentCN") val contentCN: String?, @ApiModelProperty("createTime") val createTime: String?)