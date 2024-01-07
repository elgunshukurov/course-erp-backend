package com.elgunsh.courseerpbackend.filters;

import com.elgunsh.courseerpbackend.service.getters.EmailGetter;
import com.elgunsh.courseerpbackend.service.security.AccessTokenManager;
import com.elgunsh.courseerpbackend.service.security.AuthBusinessService;
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
import java.util.Objects;

import static com.elgunsh.courseerpbackend.constants.TokenConstants.EMAIL_KEY;
import static com.elgunsh.courseerpbackend.constants.TokenConstants.TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter implements EmailGetter {
    private final AccessTokenManager accessTokenManager;
    private final AuthBusinessService authBusinessService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader(AUTHORIZATION);

        if (Objects.nonNull(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            final String token = bearerToken.split(" ")[1].trim();

            authBusinessService.setAuthentication(getEmailFromToken(token));
        }



        filterChain.doFilter(request, response);

    }

    @Override
    public String getEmailFromToken(String token) {
        Claims claims = accessTokenManager.read(token);
        return claims.get(EMAIL_KEY, String.class);
    }
}
