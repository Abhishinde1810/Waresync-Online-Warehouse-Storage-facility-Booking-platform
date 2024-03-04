package com.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@CrossOrigin(origins = "http://localhost:3000")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//    	String requestToken = request.getHeader("Authorization");
//
//
//        String username = null;
//
//        String token = null;
//
//        if (requestToken != null && requestToken.startsWith("Bearer")) {
//            token = requestToken.substring(7);
//            try {
//                username = this.jwtTokenHelper.getUsernameFromToken(token);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } 
//        else {
//            System.out.println("Wrong bearer "+requestToken);
//        }
//        // once we get the token now validate
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//            if (this.jwtTokenHelper.validateToken(token, userDetails)) {
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else {
//                System.out.println("Invalid JWT Token");
//            }
//        } 
//        else {
//            System.out.println("Username is null or context is not null "+token);
//        }
//
//        filterChain.doFilter(request, response);
 //   }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/api/login")) {
            filterChain.doFilter(request, response);
        } else {
            String requestToken = request.getHeader("Authorization");
            System.out.println(requestToken);
            String username = null;
            String token = null;

            if (requestToken != null) {
                token = requestToken.substring(7);
                try {
                    username = this.jwtTokenHelper.getUsernameFromToken(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
            else {
                System.out.println("Wrong bearer " + requestToken);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (this.jwtTokenHelper.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    System.out.println("Invalid JWT Token");
                }
            } 
            else {
                System.out.println("Username is null or context is not null ");
            }

            filterChain.doFilter(request, response);
        }
    }
  }
