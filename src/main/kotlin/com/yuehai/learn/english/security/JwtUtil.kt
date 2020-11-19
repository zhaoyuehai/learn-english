package com.yuehai.learn.english.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtUtil {

    @Value("\${jwt.tokenHeader}")
    private lateinit var tokenHeader: String

    @Value("\${jwt.header}")
    private lateinit var header: String

    //密钥
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.expiration.token}")
    private var tokenExpiration = 28800L
    fun tokenExpiration() = System.currentTimeMillis() + tokenExpiration * 1000

    @Value("\${jwt.expiration.refreshToken}")
    private var refreshTokenExpiration = 604800L
    fun refreshTokenExpiration() = System.currentTimeMillis() + refreshTokenExpiration * 1000

    /**
     * 生成令牌
     * @param sub 信息
     * @return [token,refreshToken]
     */
    fun generateToken(sub: String, gentType: String, expiration: Long): String = Jwts.builder()
            .setClaims(mapOf(Pair(Claims.SUBJECT, sub), Pair("gent_type", gentType)))
            .setExpiration(Date(expiration))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()

    fun getGentTypeFromToken(token: String): Any? = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body["gent_type"]

    fun getSubFromToken(token: String): String = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body.subject

    fun getTokenFromRequest(request: HttpServletRequest): String? {
        val authorization = request.getHeader(header)
        return if (authorization != null && authorization.startsWith(tokenHeader)) authorization.substring(tokenHeader.length) else null
    }

    fun getExpirationFromToken(token: String): Date = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
            .expiration

    /**
     * 验证令牌
     */
    fun validateToken(token: String, sub: String): Boolean = getSubFromToken(token) == sub && !isTokenExpired(token)

    /**
     * 令牌是否过期
     */
    fun isTokenExpired(token: String): Boolean = getExpirationFromToken(token).before(Date())
}