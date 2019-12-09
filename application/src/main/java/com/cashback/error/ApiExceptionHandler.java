package com.cashback.error;

import com.cashback.common.exception.BusinessException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApiExceptionHandler {

    private static final String NO_MESSAGE_AVAILABLE = "No message available";
    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);
    private final MessageSource apiErrorMessageSource;

    public ApiExceptionHandler(MessageSource apiErrorMessageSource) {
        this.apiErrorMessageSource = apiErrorMessageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerNotValidException(MethodArgumentNotValidException exception,
                                                                  Locale locale) {

        final Stream<ObjectError> errors = exception.getBindingResult().getAllErrors().stream();

        List<ErrorResponse.ApiError> apiErrors = errors
                .map(ObjectError::getDefaultMessage)
                .map(code -> toApiError(code, locale))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidFormatException(InvalidFormatException exception,
                                                                       Locale locale) {

        final String errorCode = "generic-1";
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final ErrorResponse errorResponse = ErrorResponse.of(status, toApiError(errorCode, locale, exception.getValue()));

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidFormatException(ConstraintViolationException exception,
                                                                       Locale locale) {

        final String errorCode = "generic-1";
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> erros = exception.getConstraintViolations().parallelStream().map(x -> x.getMessage()).collect(Collectors.toList());
        final ErrorResponse errorResponse = ErrorResponse.of(status, toApiError(errorCode, locale, erros));

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(BusinessException exception, Locale locale) {

        final String errorCode = exception.getCode();
        final HttpStatus status = exception.getStatus();
        final ErrorResponse errorResponse = ErrorResponse.of(status, toApiError(errorCode, locale));

        return ResponseEntity.status(status).body(errorResponse);
    }

    public ErrorResponse.ApiError toApiError(String code, Locale locale, Object... args) {

        String message;
        try {

            message = apiErrorMessageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException exception) {
            LOG.error("Could not find any message for {} code under {} locale", code, locale);
            message = NO_MESSAGE_AVAILABLE;
        }

        return new ErrorResponse.ApiError(code, message);
    }
}
