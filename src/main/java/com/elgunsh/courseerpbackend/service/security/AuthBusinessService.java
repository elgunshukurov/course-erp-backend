package com.elgunsh.courseerpbackend.service.security;

import com.elgunsh.courseerpbackend.model.base.BaseResponse;
import com.elgunsh.courseerpbackend.model.payload.auth.LoginPayload;
import com.elgunsh.courseerpbackend.model.payload.auth.RefreshTokenPayload;
import com.elgunsh.courseerpbackend.model.response.auth.LoginResponse;

public interface AuthBusinessService {

    LoginResponse login(LoginPayload loginPayload);

   LoginResponse refresh(RefreshTokenPayload refreshTokenPayload);

    void logout();

    void setAuthentication(String email);
}
