package com.mt.spring_file_upload.response;


import org.springframework.web.multipart.MultipartFile;

public class FileResponse {


    private String file ;
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String Message(String file){
        return file;
    }

}
