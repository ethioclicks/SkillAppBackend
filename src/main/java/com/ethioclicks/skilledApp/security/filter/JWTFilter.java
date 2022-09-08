package com.ethioclicks.skilledApp.security.filter;

import com.ethioclicks.skilledApp.security.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeaderValue = httpServletRequest.getHeader("Authorization");
        if(authorizationHeaderValue!=null && authorizationHeaderValue.startsWith("Bearer ")){

            String token = authorizationHeaderValue.substring(7);
            try {
                String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(usernameFromToken);

            if(jwtTokenUtil.validateToken(token,userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            }catch (IllegalArgumentException e){
                logger.error("Exception in the response writer");
            }catch (ExpiredJwtException e){
                logger.error("Exception in the response writer");
            }catch (Exception e){
                logger.error("Exception in the response writer");
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

}
