package com.elgunsh.courseerpbackend.model.payload.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenPayload {

    String refreshToken;
    boolean rememberMe;

}
