package com.hw.taskmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.BindException;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler {

    @Data
    @AllArgsConstructor
    private static class Error {
        private int code;
        private String message;
    }

    @ResponseBody
    @ExceptionHandler(value = {NotFoundException.class})
    ResponseEntity<Error> handleNotFoundException(RuntimeException e) {
        return createResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value = {BadRequestException.class})
    ResponseEntity<Error> handleBadRequestException(RuntimeException e) {
        return createResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class})
    protected ResponseEntity<Error> handleClientError(Exception e) {
        return createResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = {RuntimeException.class})
    ResponseEntity<Error> handleUnknownException(RuntimeException e, WebRequest request) {
        log.error("Unknown exception. Path: {} Params: {} Exception: {}", request.toString(), request.getParameterMap().entrySet(), e);
        return createResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Error> createResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(new Error(status.value(), message), status);
    }
}