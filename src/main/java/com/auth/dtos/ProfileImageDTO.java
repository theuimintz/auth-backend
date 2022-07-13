package com.auth.dtos;

import org.springframework.http.MediaType;

public class ProfileImageDTO {
    
    public Long id;

    public Long getId() { return id; }

    public Long userId;

    public Long getUserId() { return userId; }

    public MediaType imageType;

    public MediaType getImageType() {
        return this.imageType;
    }
    public void setImageType(MediaType mt) {
        imageType = mt;
    }


    public byte[] imageBytes;

    public byte[] getImageBytes() { return imageBytes; }
    public void setImageBytes(byte[] imageBytes) { this.imageBytes = imageBytes; }


    public ProfileImageDTO(Long id, Long userId, byte[] imageBytes, MediaType imageType) {
        this.id = id;
        this.imageType = imageType;
        this.userId = userId;
        this.imageBytes = imageBytes;
    }
}
