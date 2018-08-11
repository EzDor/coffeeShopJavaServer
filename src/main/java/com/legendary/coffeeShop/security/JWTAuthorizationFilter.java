package com.legendary.coffeeShop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String header = servletRequest.getHeader(SecurityConstants.HEADER_STRING);
        if (isHeaderValid(header)) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(servletRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isHeaderValid(String header) {
        return header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            // parse the token.
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET.getBytes())
                    .parseClaimsJws(prepareToken(token))
                    .getBody();

            String user = claims.getSubject();
            String role = claims.get(SecurityConstants.CLAIM_ROLES, String.class);

            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, authorityList);
            }
        }
        return null;
    }

    private String prepareToken(String token) {
        return token.replace(SecurityConstants.TOKEN_PREFIX, "");
    }
}
