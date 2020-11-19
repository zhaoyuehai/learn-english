package com.yuehai.learn.english.security

import org.springframework.http.HttpStatus
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class UnAuthorizedEntryPoint : AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        if (authException is InsufficientAuthenticationException) {
            response?.status = HttpStatus.UNAUTHORIZED.value()
        }
    }
}