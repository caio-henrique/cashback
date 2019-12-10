package com.cashback.common.exception;

import org.springframework.http.HttpStatus;

public class GenericBusinessException extends BusinessException {

    public GenericBusinessException(String code) {
        super(code, HttpStatus.BAD_REQUEST);
    }
}
