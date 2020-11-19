package com.yuehai.learn.english.controller

import ResultUtil
import com.yuehai.learn.english.bean.ResultBean
import com.yuehai.learn.english.bean.WordBean
import com.yuehai.learn.english.bean.WordExtraBean
import com.yuehai.learn.english.bean.WordMarkBean
import com.yuehai.learn.english.common.ErrorResult
import com.yuehai.learn.english.security.JwtUtil
import com.yuehai.learn.english.service.WordService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/word")
class WordController : BaseController() {
    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var wordService: WordService

    @ApiOperation("添加单词", notes = "")
    @ApiImplicitParam(name = "wordBean", value = "可以只传英文", paramType = "body", dataType = "WordBean", required = true)
    @PostMapping
    fun insertWord(@RequestBody wordBean: WordBean) = wordService.insertWord(wordBean)

    @ApiOperation("编辑单词", notes = "")
    @ApiImplicitParam(name = "wordBean", value = "id必须传", paramType = "body", dataType = "WordBean", required = true)
    @PutMapping
    fun updateWord(@RequestBody wordBean: WordBean) = wordService.updateWord(wordBean)

    @ApiOperation("删除单词", notes = "")
    @ApiImplicitParam(name = "id", value = "id", paramType = "path", dataType = "Int", required = true)
    @DeleteMapping("{id}")
    fun deleteWord(@PathVariable("id") id: Long) = wordService.deleteWord(id)

    @ApiOperation("删除单词", notes = "")
    @ApiImplicitParam(name = "ids", value = "ids", paramType = "body", required = true)
    @PutMapping("delete")
    fun deleteWords(@RequestBody ids: LongArray) = wordService.deleteWords(ids)

    @ApiOperation("获取单词列表", notes = "pageNum和pageSize为必传项")
    @ApiImplicitParams(
            ApiImplicitParam(name = "pageNum", value = "第几页，从1开始", paramType = "path", dataType = "String", required = true),
            ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "path", dataType = "String", required = true),
            ApiImplicitParam(name = "date", value = "日期条件【格式：yyyy-MM】", paramType = "path", dataType = "String", required = false)
    )
    @GetMapping("words/{pageNum}/{pageSize}/{date}")
    fun words(@PathVariable pageNum: Int, @PathVariable pageSize: Int, @PathVariable(required = false) date: String?) = wordService.selectWord(date, pageNum, pageSize)

    @ApiOperation("随机获取单词", notes = "count可以不传，默认1")
    @ApiImplicitParam(name = "count", value = "返回单词数量", paramType = "query", dataType = "Integer", required = false)
    @GetMapping("random")
    fun words(@ApiParam("count", required = false, defaultValue = "1") count: Int?, @ApiParam("date", required = false) monthDate: String?) = wordService.randomWords(count, monthDate)

    @ApiOperation("标记单词状态", notes = "markUp => true:++,false:--")
    @PostMapping("mark")
    fun markWord(@RequestBody wordMarkBean: WordMarkBean): ResultBean {
        val token = jwtUtil.getTokenFromRequest(request)
        return if (token != null) {
            val userPhone = jwtUtil.getSubFromToken(token)
            wordService.markWord(userPhone, wordMarkBean)
        } else {
            ResultUtil.fail(ErrorResult.LOGIN_INFO_ERROR)
        }
    }

    @GetMapping("marks/{pageNum}/{pageSize}")
    fun wordMarks(@PathVariable pageNum: Int, @PathVariable pageSize: Int): ResultBean {
        val token = jwtUtil.getTokenFromRequest(request)
        return if (token != null) {
            val userPhone = jwtUtil.getSubFromToken(token)
            wordService.selectWordMarks(userPhone, pageNum, pageSize)
        } else {
            ResultUtil.fail(ErrorResult.LOGIN_INFO_ERROR)
        }
    }

    @ApiOperation("派生扩展单词", notes = "")
    @PostMapping("expand")
    fun insertExpandWord(@RequestBody wordExtraBean: WordExtraBean) = wordService.insertExtraWord(wordExtraBean)

    @ApiOperation("获取派生扩展单词", notes = "")
    @ApiImplicitParam(name = "id", value = "id", paramType = "path", dataType = "Integer", required = true)
    @GetMapping("expand/{id}")
    fun expandWords(@PathVariable("id") id: Long) = wordService.selectExtraWords(id)
}