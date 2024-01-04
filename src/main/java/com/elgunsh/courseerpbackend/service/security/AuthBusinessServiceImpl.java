package com.elgunsh.courseerpbackend.service.security;

import com.elgunsh.courseerpbackend.model.dto.RefreshTokenDto;
import com.elgunsh.courseerpbackend.model.mybatis.user.User;
import com.elgunsh.courseerpbackend.model.payload.auth.LoginPayload;
import com.elgunsh.courseerpbackend.model.payload.auth.RefreshTokenPayload;
import com.elgunsh.courseerpbackend.model.response.auth.LoginResponse;
import com.elgunsh.courseerpbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static com.elgunsh.courseerpbackend.constants.TokenConstants.EMAIL_KEY;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthBusinessServiceImpl implements AuthBusinessService{

    private final AuthenticationManager authenticationManager;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final UserService userService;
    @Override
    public LoginResponse login(LoginPayload loginPayload) {

        authenticate(loginPayload);

        return prepareLoginResponse(loginPayload.getEmail(), loginPayload.isRememberMe());
    }

    @Override
    public LoginResponse refresh(RefreshTokenPayload refreshTokenPayload) {

        String email = refreshTokenManager.read(refreshTokenPayload.getRefreshToken()).get(EMAIL_KEY, String.class);

        return prepareLoginResponse(email, refreshTokenPayload.isRememberMe());
    }

    //private util methods
    private void authenticate(LoginPayload payload) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            payload.getEmail(),
                            payload.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

    }

    private LoginResponse prepareLoginResponse(String email, boolean rememberMe) {

        User user = userService.getByEmail(email);

        return LoginResponse
                .builder()
                .accessToken(accessTokenManager.generate(user))
                .refreshToken(refreshTokenManager.generate(
                        RefreshTokenDto
                                .builder()
                                .user(user)
                                .rememberMe(rememberMe)
                                .build()
                ))
                .build();
    }

}
