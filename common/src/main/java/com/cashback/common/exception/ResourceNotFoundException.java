package com.cashback.common.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException() {
        super("resource-not-found-1", HttpStatus.NOT_FOUND);
    }
}
