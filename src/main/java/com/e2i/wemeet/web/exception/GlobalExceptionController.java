package com.e2i.wemeet.web.exception;

import com.e2i.wemeet.web.exception.internal.DeserializeException;
import com.e2i.wemeet.web.exception.internal.SerializeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionController {

    private static final String ERROR_LOG_FORMAT = "ERROR::UNEXPECTED -> Error Class : {}, Message : {}, StackTrace : {}";
    private static final String SERIALIZE_ERROR_LOG_FORMAT = "ERROR::SERIALIZE -> Error Class : {}, Message : {}, StackTrace : {}";

    @ExceptionHandler({SerializeException.class, DeserializeException.class})
    public String handleSerializeException(final SerializeException e) {
        log.info(SERIALIZE_ERROR_LOG_FORMAT, e.getClass().getName(), e.getMessage(), e.getStackTrace());

        return "redirect:/v1/web/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleAuthorizationException(final Exception e) {
        log.info(ERROR_LOG_FORMAT, e.getClass().getName(), e.getMessage(), e.getStackTrace());

        return "redirect:/v1/web/error";
    }
}
