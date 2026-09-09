package com.example.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // HS256 要求密钥至少 256 位（32 字节），这里用 32 个字符以上的固定密钥
    private static final SecretKey KEY = Keys.hmacShaKeyFor(
            "itheima12345678901234567890123456".getBytes(StandardCharsets.UTF_8));

    private static final Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .signWith(KEY, SignatureAlgorithm.HS256) // 指定算法和密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY) // 用同一个密钥校验
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
