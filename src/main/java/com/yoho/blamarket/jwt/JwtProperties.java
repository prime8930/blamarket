package com.yoho.blamarket.jwt;

/**
 * JWT 기본 설정값
 */
public class JwtProperties {
    public static final long EXPIRATION_TIME = 60000000000L; // 100분
    public static final String HEADER_STRING = "JWT-AUTHENTICATION";
    public static final String TOKEN_PREFIX = "Bearer ";
}