package com.mt.spring_file_upload.infrastructure.model.body;

public class StatusResponse {
    private short code;
    private String message;

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}
