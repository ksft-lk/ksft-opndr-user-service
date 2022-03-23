package com.kochasoft.opendoor.userservice.util;

import org.springframework.http.MediaType;

public enum MediaTypeMap {
    JPG(MediaType.IMAGE_JPEG_VALUE),
    JPEG(MediaType.IMAGE_JPEG_VALUE),
    PNG(MediaType.IMAGE_PNG_VALUE),
    PDF(MediaType.APPLICATION_PDF_VALUE),
    GIF(MediaType.IMAGE_GIF_VALUE);

    

    private MediaTypeMap(String mediaType) {
        this.setMediaType(mediaType);
    }

    public String getMediaType() {
        return mediaType;
    }

    private void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    private String mediaType;
    
}
