package com.yuehai.learn.english.security

import com.yuehai.learn.english.config.Swagger2Config
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * https://www.cnblogs.com/hackyo/p/8004928.html
 * http://www.jianshu.com/p/6307c89fe3fa
 * http://www.jianshu.com/p/4468a2fff879
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用方法级别的权限认证
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    companion object {
        val AUTH_WHITE_LIST = arrayOf(
                "/",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/**/*.json",
                "/**/*.ico",
                "/**/*.png",
                "/**/*.mp4",
                //项目内api
                "/api/v1/hello/**",
                "/api/v1/user/login",
                "/api/v1/user/register",
                "/api/v1/user/token"
        )
    }

    @Autowired
    private lateinit var jwtAuthenticationFilter: JwtAuthenticationFilter

    @Autowired
    private lateinit var unAuthorizedEntryPoint: UnAuthorizedEntryPoint

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    private var passwordEncoder = BCryptPasswordEncoder()


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity) {
        http.cors()//允许跨域
                .and()
                .csrf().disable()//使用JWT，不需要csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//基于Token，不需要session
                .and()
                .authorizeRequests()
                .antMatchers(*AUTH_WHITE_LIST, *Swagger2Config.SWAGGER_AUTH_WHITE_LIST)
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .headers().cacheControl()//禁用缓存
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        http.exceptionHandling().authenticationEntryPoint(unAuthorizedEntryPoint)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }

}