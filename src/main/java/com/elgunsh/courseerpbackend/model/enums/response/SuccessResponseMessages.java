package com.elgunsh.courseerpbackend.model.enums.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SuccessResponseMessages implements ResponseMessages{
    SUCCESS("Success","Successful status", HttpStatus.OK);

    String key;
    String message;
    HttpStatus status;

    @Override
    public String key() {
        return key;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return status;
    }
}
