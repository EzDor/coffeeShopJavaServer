package com.legendary.coffeeShop.security;

class SecurityConstants {
    static final String SECRET = "SecretKeyToGenJWTs";
    static final long EXPIRATION_TIME = 86_400_0000; // 10 days
    static final String HEADER_STRING = "Authorization";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String SIGN_UP_URL = "/users/signUp";
    static final String CLAIM_ROLES = "roles";
    static final String USERNAME_KEY = "username";
    static final String TOKEN_KEY = "token";

}
