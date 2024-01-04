package com.elgunsh.courseerpbackend.filters;

import com.elgunsh.courseerpbackend.model.mybatis.user.User;
import com.elgunsh.courseerpbackend.service.security.AccessTokenManager;
import com.elgunsh.courseerpbackend.service.user.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final AccessTokenManager accessTokenManager;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");

        System.out.println(bearerToken);

        if (Objects.nonNull(bearerToken) && bearerToken.startsWith("Bearer")) {
            final String token = bearerToken.split(" ")[1].trim();

            Claims claims = accessTokenManager.read(token);
            String email = claims.get("email", String.class);

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            "",
                            userDetails.getAuthorities()
                    )
            );

            System.out.println(token);

        }



        filterChain.doFilter(request, response);
//
//        System.out.println("after doFilter" + count++);

    }
}
