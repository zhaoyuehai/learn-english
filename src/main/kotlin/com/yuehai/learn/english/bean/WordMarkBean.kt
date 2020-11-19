package com.yuehai.learn.english.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class WordMarkBean(
        @ApiModelProperty("wordId") val wordId: Long,
        @ApiModelProperty("markUp") val markUp: Boolean)