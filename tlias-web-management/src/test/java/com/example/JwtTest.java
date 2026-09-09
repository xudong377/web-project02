package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    // HS256 要求密钥至少 256 位（32 字节），这里用 32 个字符
    SecretKey key = Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes(StandardCharsets.UTF_8));
    /**
     * 生成jwt令牌
     */
    @Test
    public void test() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("username", "admin");
        dataMap.put("id", "1");



        String jwt = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256) // 指定加密算法和密钥
                .addClaims(dataMap)                       // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 过期时间
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解析Jwt令牌
     */
    @Test
    public void testParseJwt(){

        String token="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJ1c2VybmFtZSI6ImFkbWluIiwiZXhwIjoxNzg4Nzc4NzI4fQ.dAILlrNQqK-r5SiGQJjTpW3oApZOBSCf5hQo8zgFjEA";
        Claims claims = Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
