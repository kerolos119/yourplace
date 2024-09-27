package com.example.yourplace.service;

import com.example.yourplace.model.TokenInfo;
import com.example.yourplace.utils.JwUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

@Component
public class JwtFilterService extends OncePerRequestFilter {
 @Autowired
    JwUtils jwUtils;
 @Autowired
    CustomUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        final String authorizationsHeader=request.getHeader("Authorization");
        if(authorizationsHeader != null && authorizationsHeader.startsWith("Bearer")){

           String token= authorizationsHeader.substring(7);
           if (!jwUtils.isValid(token)){
               throw new AuthenticationException("invalid token");
           }
           TokenInfo tokenInfo= jwUtils.extractInfo(token);
           if (!userDetailsService.isValid(tokenInfo)){
               throw new AuthenticationException("invalid");
           }
        }
        filterChain.doFilter(request,response);
    }
}
