package com.yuehai.learn.english.controller

import ResultUtil
import com.yuehai.learn.english.bean.ResultBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.util.*

@RestController
@RequestMapping("api/v1/file")
class FileController : BaseController() {

    @Value("\${upload-file-path}")
    private lateinit var uploadFilePath: String

    @PostMapping("upload")
    fun upload(@RequestParam("file") file: MultipartFile): ResultBean {
        if (file.isEmpty) return ResultUtil.fail("文件为空")
        val suffixName = file.originalFilename?.let {
            it.substring(it.lastIndexOf("."))
        }
        if (suffixName.isNullOrEmpty()) return ResultUtil.fail("没有找到文件后缀")
        val dest = File(uploadFilePath + UUID.randomUUID().toString() + suffixName)
        if (!dest.parentFile.exists()) dest.parentFile.mkdirs()
        try {
            file.transferTo(dest)
            return ResultUtil.success(dest.absolutePath, "上传成功")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ResultUtil.fail("上传失败")
    }
}