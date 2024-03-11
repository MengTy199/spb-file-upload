package com.mt.spring_file_upload.service;


import com.mt.spring_file_upload.exception.InternalServerErrorException;
import com.mt.spring_file_upload.exception.NotFoundException;
import com.mt.spring_file_upload.model.entity.FileUploadEntity;
import com.mt.spring_file_upload.repository.FileUploadRepository;
import com.mt.spring_file_upload.request.FileUploadRequest;
import com.mt.spring_file_upload.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class StorageService implements Serializable {

    private final FileUploadRepository fileUpload;
    private  static final  String FILE_PATH = System.getProperty("user.dir") + "/files";

    public StorageService(FileUploadRepository fileUpload) {
        this.fileUpload = fileUpload;
    }

    public  void upload( MultipartFile file) {
//        FileUploadEntity entity = request.toEntity();
        FileUtil.saveMultipartFile(file, FILE_PATH);
    }

    public  void batchUpload(List<MultipartFile> files) {
        for (MultipartFile file:
             files) {
            FileUtil.saveMultipartFile(file, FILE_PATH);
        }

    }

    public  void loadFile(String filename, HttpServletResponse response){
        try{
            Path p = Paths.get(this.FILE_PATH).resolve(filename);

            Resource resource = new UrlResource(p.toUri());//resource we can convert to url

            if(!resource.exists() || !resource.isReadable()){
                throw  new NotFoundException("File not found!");
            }

            response.setHeader(HttpHeaders.CONTENT_TYPE, Files.probeContentType(p)); //get type

            response.setHeader(HttpHeaders.CONTENT_LENGTH, "" + Files.size(p));

            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline;filename = " + " \"" + filename + "\""); //attachment for download - inline for view only
            FileCopyUtils.copy(resource.getInputStream(), response.getOutputStream());

        }catch (Exception ex){
            if(ex instanceof  NotFoundException) throw new NotFoundException(ex.getMessage());
            throw  new InternalServerErrorException(ex.getMessage());
        }


    }
}
