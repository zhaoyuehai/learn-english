package com.yuehai.learn.english.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = jwtUtil.getTokenFromRequest(request)
        if (token != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(jwtUtil.getSubFromToken(token))
            if (jwtUtil.validateToken(token, userDetails.username)) {
                SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities).apply {
                    details = WebAuthenticationDetailsSource().buildDetails(request)
                }
            }
        }
        filterChain.doFilter(request, response)
    }
}