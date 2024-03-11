package com.mt.spring_file_upload.repository;

import com.mt.spring_file_upload.model.entity.FileUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUploadEntity, Long>  {
}
