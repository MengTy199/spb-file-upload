package com.mt.spring_file_upload.util;

import com.mt.spring_file_upload.exception.BedRequestException;
import com.mt.spring_file_upload.exception.InternalServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public final class FileUtil {
    public  static  void saveMultipartFile(MultipartFile file, String path){

        if(file.getSize() <= 0) throw  new BedRequestException("No File Provided");

        String filename = UUID.randomUUID().toString();

        String sourceFilename = !Objects.requireNonNull(file.getOriginalFilename()).isEmpty() ||  !Objects.requireNonNull(file.getOriginalFilename()).isBlank() ? file.getOriginalFilename() : file.getName();

        String extension = sourceFilename.contains(".")? sourceFilename.substring(sourceFilename.lastIndexOf(".")): "";

        Path pth = !path.isEmpty() || !path.isBlank() ? Paths.get(path) :  Paths.get("./");

        System.out.println(pth);

        try{
            if(Files.notExists(pth))
                Files.createDirectories(pth);
            file.transferTo(pth.resolve(filename + extension));
        }catch (Exception ex){
            throw  new InternalServerErrorException(ex.getMessage());
        }
    }
}
