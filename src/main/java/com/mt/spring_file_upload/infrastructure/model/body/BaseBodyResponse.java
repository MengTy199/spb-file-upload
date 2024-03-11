package com.mt.spring_file_upload.infrastructure.model.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class BaseBodyResponse implements Serializable {
    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private  BodyResponse body;

    private  StatusResponse status;


    public boolean isSuccess() {
        return success;
}

    public BodyResponse getBody() {
        return body;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setBody(BodyResponse body) {
        this.body = body;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public static ResponseEntity<Object> failed(String message, HttpStatusCode httpStatus){
        BaseBodyResponse response = new BaseBodyResponse();
        StatusResponse status = new StatusResponse();
        status.setCode((short) httpStatus.value());
        status.setMessage(message);

        response.setSuccess(false);
        response.setStatus(status);

        return ResponseEntity.status(httpStatus).body(response);
    }
}
