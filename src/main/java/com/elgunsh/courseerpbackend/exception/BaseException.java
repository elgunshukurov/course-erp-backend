package com.elgunsh.courseerpbackend.exception;

import com.elgunsh.courseerpbackend.exception.types.NotFoundExceptionType;
import com.elgunsh.courseerpbackend.model.base.BaseResponse;
import com.elgunsh.courseerpbackend.model.enums.response.ResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

import static com.elgunsh.courseerpbackend.model.enums.response.ErrorResponseMessages.NOT_FOUND;
import static com.elgunsh.courseerpbackend.model.enums.response.ErrorResponseMessages.UNEXPECTED;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException{

    ResponseMessages responseMessages;
    NotFoundExceptionType notFoundExceptionData;

    public static BaseException unexpected() {
        return BaseException.builder()
                .responseMessages(UNEXPECTED)
                .build();
    }

    public static BaseException notFound(String target, Map<String, Object> fields) {
        return BaseException.builder()
                .responseMessages(NOT_FOUND)
                .notFoundExceptionData(NotFoundExceptionType.of(target, fields))
                .build();
    }

    //todo: fix dynamic error message
    @Override
    public String getMessage() {
        return responseMessages.message();
    }
}
