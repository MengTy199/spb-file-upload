package com.mt.spring_file_upload.model.entity;


import com.mt.spring_file_upload.infrastructure.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.io.File;
import java.util.Objects;

@Entity(name = "file_upload")
public class FileUploadEntity extends BaseEntity<Long> {
    @Column(name = "file_name", length = 30)
    private File filename;

    public void setFilename(File filename) {
        this.filename = filename;
    }

    public File getFilename() {
        return filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FileUploadEntity that = (FileUploadEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
