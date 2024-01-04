package com.elgunsh.courseerpbackend.model.payload.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginPayload {

    String email;
    String password;
    boolean rememberMe;

}
