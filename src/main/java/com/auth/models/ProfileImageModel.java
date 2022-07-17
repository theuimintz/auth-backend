package com.auth.models;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table (name = "profile_images")
public class ProfileImageModel {

    // region Properties


    @Id
    @GeneratedValue ( strategy = GenerationType.SEQUENCE )
    private Long id;

    public Long getId() {
        return id;
    }


    @Column (nullable = false, unique = true)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Column (nullable = false)
    private MediaType type;

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }


    @Column (nullable = false)
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }


    // endregion

    // region Construction

    public ProfileImageModel() {}

    public ProfileImageModel(Long userId) {
        this.userId = userId;
    }   

    public ProfileImageModel(Long userId, MediaType type, byte[] bytes) {
        this(userId);
        this.type = type;
        this.bytes = bytes;
    }

    public ProfileImageModel(Long userId, MultipartFile file) throws IOException {
        this(userId, MediaType.parseMediaType(file.getContentType()), file.getBytes());
    }

    // endregion
}

