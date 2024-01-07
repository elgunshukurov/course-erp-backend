package com.elgunsh.courseerpbackend.exception;

import com.elgunsh.courseerpbackend.model.base.BaseResponse;
import com.elgunsh.courseerpbackend.model.enums.response.ResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static com.elgunsh.courseerpbackend.model.enums.response.ErrorResponseMessages.UNEXPECTED;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException{

    ResponseMessages responseMessages;

    public static BaseException unexpected() {
        return new BaseException(UNEXPECTED);
    }

    //todo: fix dynamic error message
    @Override
    public String getMessage() {
        return responseMessages.message();
    }
}
