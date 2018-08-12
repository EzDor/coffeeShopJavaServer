package com.legendary.coffeeShop.security;

class SecurityConstants {
    static final String SECRET = "SecretKeyToGenJWTs";
    static final long EXPIRATION_TIME = 864_000_000; // 10 days
    static final String HEADER_STRING = "Authorization";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String SIGN_UP_URL = "/users/signUp";
    static final String CLAIM_ROLES = "roles";
    static final String ADMIN_ROLE = "ROLE_ADMIN";
    static final String USERNAME_KEY = "username";
    static final String TOKEN_KEY = "token";
    static final String IS_ADMIN_KEY = "isAdmin";

}
