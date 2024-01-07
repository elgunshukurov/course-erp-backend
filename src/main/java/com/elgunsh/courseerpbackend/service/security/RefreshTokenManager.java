package com.elgunsh.courseerpbackend.service.security;

import com.elgunsh.courseerpbackend.model.dto.RefreshTokenDto;
import com.elgunsh.courseerpbackend.model.mybatis.user.User;
import com.elgunsh.courseerpbackend.model.property.security.SecurityProperties;
import com.elgunsh.courseerpbackend.service.base.TokenGenerator;
import com.elgunsh.courseerpbackend.service.base.TokenReader;
import com.elgunsh.courseerpbackend.service.getters.EmailGetter;
import com.elgunsh.courseerpbackend.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.elgunsh.courseerpbackend.constants.TokenConstants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenManager implements TokenGenerator<RefreshTokenDto>,
        TokenReader<Claims>, EmailGetter {

    private final SecurityProperties securityProperties;

    @Override
    public String generate(RefreshTokenDto obj) {

        final User user = obj.getUser();
        boolean rememberMe = obj.isRememberMe();

        Claims claims = Jwts.claims();
        claims.put(EMAIL_KEY, user.getEmail());
        claims.put(TOKEN_TYPE, REFRESH_TOKEN);

        Date now = new Date();
        Date exp = new Date(now.getTime() + securityProperties.getJwt().getRefreshTokenValidityTime(rememberMe));

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(now)
                .setExpiration(exp)
                .addClaims(claims)
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public Claims read(String token) {
        Claims tokenData = Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String tokenType = tokenData.get(TOKEN_TYPE, String.class);

        if (!tokenType.equals(REFRESH_TOKEN)) {
            throw new RuntimeException("Refresh token type is invalid!");
        }

        return tokenData;
    }

    @Override
    public String getEmailFromToken(String token) {
        Claims claims = read(token);
        return claims.get(EMAIL_KEY, String.class);
    }
}
