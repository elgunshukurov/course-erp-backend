package com.elgunsh.courseerpbackend.model.base;


import com.elgunsh.courseerpbackend.exception.BaseException;
import com.elgunsh.courseerpbackend.exception.types.NotFoundExceptionType;
import com.elgunsh.courseerpbackend.model.enums.response.ResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;

import static com.elgunsh.courseerpbackend.model.enums.response.ErrorResponseMessages.NOT_FOUND;
import static com.elgunsh.courseerpbackend.model.enums.response.SuccessResponseMessages.SUCCESS;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PROTECTED)
public class BaseResponse<T> {

    HttpStatus status;
    Meta meta;
    T data;

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK)
                .meta(Meta.of(SUCCESS))
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    public static BaseResponse<?> error(BaseException exception) {
        return BaseResponse.builder()
                .meta(Meta.of(exception))
                .status(exception.getResponseMessages().status())
                .data(null)
                .build();
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    public final static class Meta {

        String key;
        String message;

        public static Meta of(String key, String message) {
            return Meta.builder()
                    .key(key)
                    .message(message)
                    .build();
        }

        public static Meta of(ResponseMessages responseMessages) {
            return Meta.of(responseMessages.key(), responseMessages.message());
        }

        public static Meta of(BaseException ex) {
            ResponseMessages responseMessages = ex.getResponseMessages();

            if (responseMessages.equals(NOT_FOUND)) {
                NotFoundExceptionType notFoundExceptionData = ex.getNotFoundExceptionData();

                return of(
                        String.format(responseMessages.key(), notFoundExceptionData.getTarget())
                        , String.format(responseMessages.message(), notFoundExceptionData.getFields().toString())
                );
            }

            return of(responseMessages);
        }

    }

}
