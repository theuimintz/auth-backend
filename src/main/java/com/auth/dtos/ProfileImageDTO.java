package com.auth.dtos;

import java.io.IOException;

import javax.persistence.Embeddable;

import com.auth.models.ProfileImageModel;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;


@Embeddable
public class ProfileImageDTO {

    private Long id;

    public Long getId() {
        return id;
    }


    private Long userId;

    public Long getUserId() {
        return userId;
    }


    private MediaType type;

    public MediaType getType() {
        return type;
    }


    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }


    public ProfileImageDTO(ProfileImageModel image) {
        this.id = image.getId();
        this.userId = image.getUserId();
        this.type = image.getType();
        this.bytes = image.getBytes();
    }

    public ProfileImageDTO() {
        type = MediaType.ALL;
        bytes = new byte[0];
    }
    
}
