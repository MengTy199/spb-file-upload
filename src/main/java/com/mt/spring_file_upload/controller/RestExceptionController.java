package com.mt.spring_file_upload.controller;

import com.mt.spring_file_upload.exception.BedRequestException;
import com.mt.spring_file_upload.exception.ConflictException;
import com.mt.spring_file_upload.exception.InternalServerErrorException;
import com.mt.spring_file_upload.exception.NotFoundException;
import com.mt.spring_file_upload.infrastructure.model.body.BaseBodyResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return BaseBodyResponse.failed(ex.getMessage(), statusCode);
    }

    @ExceptionHandler(value = {Exception.class, InternalServerErrorException.class})
    public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException ex) {
        return BaseBodyResponse.failed(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return BaseBodyResponse.failed(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BedRequestException.class)
    public ResponseEntity<Object> handleBedRequestException(BedRequestException ex) {
        return BaseBodyResponse.failed(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( value = ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException ex) {
        return BaseBodyResponse.failed(ex.getMessage(), HttpStatus.CONFLICT);
    }


}
