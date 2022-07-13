package com.auth.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.http.MediaType;

@Entity
@Table ( name = "profileImages")
public class ProfileImageModel {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.SEQUENCE )
    public Long id;

    public Long getId() { return id; }

    @Column ( nullable = false, unique = true )
    public Long userId;

    public Long getUserId() { return userId; }

    @Column ( nullable = false )
    public MediaType imageType;

    public MediaType getImageType() {
        return this.imageType;
    }
    public void setImageType(MediaType mt) {
        imageType = mt;
    }


    @Column ( nullable = false )
    public byte[] imageBytes;

    public byte[] getImageBytes() { return imageBytes; }
    public void setImageBytes(byte[] imageBytes) { this.imageBytes = imageBytes; }

    
    public ProfileImageModel() {
        imageType = MediaType.ALL;
        imageBytes = new byte[0];
    }

    public ProfileImageModel(Long userId, byte[] imageBytes, MediaType imageType) {
        this.imageType = imageType;
        this.userId = userId;
        this.imageBytes = imageBytes;
    }
}
