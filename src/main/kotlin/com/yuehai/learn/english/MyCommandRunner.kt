package com.yuehai.learn.english

import SystemUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class MyCommandRunner : CommandLineRunner {

    @Value("\${server.port}")
    var port: String = ""

    @Value("\${config.auto.print-host-url}")
    private var printHostUrl = false

    @Value("\${config.auto.open-browser}")
    private var openBrowser = false

    override fun run(vararg args: String?) {
        if (printHostUrl || openBrowser)
            SystemUtil.getIP()?.hostAddress?.let {
                val url = "http://$it${if (port != "80") ":$port" else ""}"
                if (printHostUrl) {
                    //打印服务地址
                    val sb = StringBuffer("[")
                    for (i in 0 until url.length + 10) {
                        sb.append("-")
                    }
                    val sbStr = sb.append("]").toString()
                    println(sbStr)
                    println(sbStr)
                    println("[---- $url ----]")
                    println(sbStr)
                    println(sbStr)
                }
                if (openBrowser) {
                    //打开浏览器
                    try {
                        Runtime.getRuntime().exec("cmd /c start $url")
                    } catch (e: Exception) {
                    }
                }
            }
    }
}
