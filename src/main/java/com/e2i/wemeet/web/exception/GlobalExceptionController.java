package com.e2i.wemeet.web.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionController {

    private static final String ERROR_LOG_FORMAT = "Error Class : {}, Error Code : {}, Message : {}";

    @ExceptionHandler(Exception.class)
    public String handleAuthorizationException(
        final Exception e) {
        log.info(ERROR_LOG_FORMAT, e.getClass().getSimpleName(), 500,
            e.getMessage());

        return "redirect:/v1/web/error";
    }

    public record ErrorResponse(int code, String message) {

    }
}
