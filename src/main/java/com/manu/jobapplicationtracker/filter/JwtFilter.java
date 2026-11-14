package com.manu.jobapplicationtracker.filter;

import com.manu.jobapplicationtracker.exceptions.JwtExpiredException;
import com.manu.jobapplicationtracker.service.UserDetailServiceImpl;
import com.manu.jobapplicationtracker.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailServiceImpl userDetailService;
    JwtFilter(JwtUtils utils, UserDetailServiceImpl service){
        this.userDetailService = service;
        this.jwtUtils = utils;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header!=null && !header.isBlank() && header.startsWith("Bearer")){
            String jwt = header.substring(7);
            try {
                if(jwtUtils.isJwtValid(jwt)){
                 UserDetails userDetail =  userDetailService.loadUserByUsername(jwtUtils.getSubject(jwt));
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (JwtExpiredException e) {
                throw new RuntimeException("Jwt expired");
            }
        }
        filterChain.doFilter(request,response);

    }
}
