package com.e2i.wemeet.web.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionController {

    private static final String ERROR_LOG_FORMAT = "Error Class : {}, Error Code : {}, Message : {}";

    private static final String UNAUTHORIZED_LOG_FORMAT = "UnAuthorized Error Class : {}, Error Code : {}, Message : {}";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationException(
        final Exception e) {

        log.info(UNAUTHORIZED_LOG_FORMAT, e.getClass().getSimpleName(), 500,
            e.getMessage());
        return new ResponseEntity<>(
            new ErrorResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    public record ErrorResponse(int code, String message) {

    }
}
