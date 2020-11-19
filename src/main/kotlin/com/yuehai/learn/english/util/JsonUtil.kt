package com.yuehai.learn.english.util

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

object JsonUtil {

    fun readJsonFile(filePath: String): String? {
        try {
            val reader = InputStreamReader(FileInputStream(File(filePath)), "utf-8")
            var ch: Int
            val sb = StringBuffer()
            ch = reader.read()
            do {
                sb.append(ch.toChar())
                ch = reader.read()
            } while (ch != -1)
            reader.close()
            return sb.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}