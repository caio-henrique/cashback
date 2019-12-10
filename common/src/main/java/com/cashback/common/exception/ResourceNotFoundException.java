package com.cashback.common.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException() {
        super("error-resource-not-found", HttpStatus.NOT_FOUND);
    }
}
