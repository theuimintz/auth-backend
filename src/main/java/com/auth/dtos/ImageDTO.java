package com.auth.dtos;

import java.io.IOException;

import javax.persistence.Embeddable;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;


@Embeddable
public class ImageDTO {

    public MediaType type;
    public byte[] bytes;

    public ImageDTO(MultipartFile file) throws IOException {
        type = MediaType.parseMediaType(file.getContentType());
        bytes = file.getBytes();
    }

    public ImageDTO() {
        type = MediaType.ALL;
        bytes = new byte[0];
    }
    
}
