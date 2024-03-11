package com.mt.spring_file_upload.request;

import com.mt.spring_file_upload.model.entity.FileUploadEntity;

import java.io.File;
import java.io.Serializable;

public class FileUploadRequest implements Serializable {
    private File filename;

    public File getFilename() {
        return filename;
    }

    public void setFilename(File filename) {
        this.filename = filename;
    }


    public FileUploadEntity toEntity(){
        FileUploadEntity fileUpload = new FileUploadEntity();
        fileUpload.setFilename(this.filename);
        return fileUpload;
    }
}
