package com.legendary.coffeeShop.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legendary.coffeeShop.dao.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME));
        claims.put(SecurityConstants.CLAIM_ROLES, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .compact();
        prepareResponse(res, user, token);
        res.addHeader(SecurityConstants.HEADER_STRING, prepareToken(token));
    }

    private String prepareToken(String token) {
        return SecurityConstants.TOKEN_PREFIX + " " + token;
    }

    private void prepareResponse(HttpServletResponse response, org.springframework.security.core.userdetails.User user, String token) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put(SecurityConstants.USERNAME_KEY, user.getUsername());
            jsonResponse.put(SecurityConstants.TOKEN_KEY, prepareToken(token));

            response.getWriter().write(objectMapper.writeValueAsString(jsonResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
