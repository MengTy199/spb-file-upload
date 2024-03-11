package com.mt.spring_file_upload.controller.frontend;


import com.mt.spring_file_upload.constant.RestURIConstant;
import com.mt.spring_file_upload.infrastructure.model.body.BaseBodyResponse;
import com.mt.spring_file_upload.model.entity.FileUploadEntity;
import com.mt.spring_file_upload.request.FileUploadRequest;
import com.mt.spring_file_upload.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Frontend Upload File Controller ", description = "Controller for admin manage File Upolad ")
@RestController
@RequestMapping(RestURIConstant.FILE)
public class FileController {
    private final StorageService storageService;

    @Autowired
    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Operation(summary = "Endpoint for admin create a upload", description = "Admin can creating a upload by using this endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class), mediaType = "application/json")),
                    @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class), mediaType = "application/json"))
            })
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> fileUpload(@RequestPart MultipartFile file) throws Exception {
        this.storageService.upload(file);
        return ResponseEntity.ok("done");
    }
    @Operation(summary = "Endpoint for admin create a upload", description = "Admin can creating a upload by using this endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class), mediaType = "application/json")),
                    @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class), mediaType = "application/json"))
            })
    @PostMapping(value = "/batch-upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> batchUpload(@RequestPart List<MultipartFile> files) throws Exception {
        this.storageService.batchUpload(files);
        return ResponseEntity.ok("done");
    }


    @GetMapping("/load/{filename}")
    public void loadFile(@PathVariable String filename, HttpServletResponse response){

       this.storageService.loadFile(filename, response);
    }
}


//note video 202