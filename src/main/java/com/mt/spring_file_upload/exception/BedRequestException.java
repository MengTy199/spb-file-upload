package com.mt.spring_file_upload.exception;

import com.mt.spring_file_upload.infrastructure.exception.BaseException;

public class BedRequestException extends BaseException {

    public BedRequestException(String message) {
        super(message);
    }
}
