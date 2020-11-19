package com.yuehai.learn.english.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringBootVersion
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.Contact
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket

/**
 * https://blog.csdn.net/lilinhai548/article/details/107394670
 */
@Configuration
@EnableOpenApi
class Swagger2Config {
    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     */
    @Value("\${swagger.restApi.enabled}")
    private var enable = true

    @Value("\${swagger.restApi.version}")
    private lateinit var version: String

    @Bean
    fun creteRestApi(): Docket = Docket(DocumentationType.OAS_30)
            .pathMapping("/")
            .enable(enable)// 定义是否开启swagger，false为关闭，可以通过变量控制
            .apiInfo(apiInfo()) // 将api的元信息设置为包含在json ResourceListing响应中。
            .select()// 选择哪些接口作为swagger的doc发布
            .apis(RequestHandlerSelectors.basePackage("com.yuehai.learn.english.controller"))
            .paths(PathSelectors.any())
            .build()
            .protocols(setOf("https", "http"))// 支持的通讯协议集合
            .securityContexts(
                    listOf(SecurityContext
                            .builder()
                            .securityReferences(
                                    listOf(SecurityReference("BASE_TOKEN", arrayOf(AuthorizationScope("global", ""))))
                            )
                            .build())
            )// 授权信息全局应用
            .securitySchemes(listOf(ApiKey("BASE_TOKEN", "token", "pass")))  // 授权信息设置，必要的header token等认证信息

    private fun apiInfo() = ApiInfoBuilder()
            .title("learn-english接口")
            .description("desc")
            .termsOfServiceUrl("http://10.10.15.200/zhaoyuehai/learn-english")
            .contact(Contact("zhaoyuehai", null, "zhaoyuehai5282@163.com"))
            .version("API Version: " + version + ", SpringBoot Version:${SpringBootVersion.getVersion()}")
            .build()

    companion object {
        val SWAGGER_AUTH_WHITE_LIST = arrayOf(
                // -- swagger ui
//                "/doc.html",
                "/v3/api-docs",
                "/swagger**/**",
                "/webjars/**"
        )
    }
}