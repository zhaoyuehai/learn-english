package com.yuehai.learn.english.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class WordExtraBean(@ApiModelProperty("单词主键id") val id: Long?, @ApiModelProperty("单词主键id") val wordId: Long, @ApiModelProperty("contentEN") val contentEN: String?, @ApiModelProperty("contentCN") val contentCN: String?)