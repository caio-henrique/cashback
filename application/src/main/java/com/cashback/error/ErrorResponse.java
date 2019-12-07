package com.cashback.error;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ErrorResponse {

    private final int statusCode;
    private final List<ApiError> errors;

    private ErrorResponse(int statusCode, List<ApiError> errors) {
        this.statusCode = statusCode;
        this.errors = errors;
    }

    public static ErrorResponse of(HttpStatus status, List<ApiError> errors) {
        return new ErrorResponse(status.hashCode(), errors);
    }

    public static ErrorResponse of(HttpStatus status, ApiError errors) {
        return new ErrorResponse(status.value(), Collections.singletonList(errors));
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static class ApiError {
        private final String code;
        private final String message;

        public ApiError(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
