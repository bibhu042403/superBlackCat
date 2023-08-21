package com.pareeksha.blackcat.avenger.facade;

import com.pareeksha.blackcat.avenger.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ImageFacade {
    @Autowired
    ImageService imageService;

    public ResponseEntity<byte[]> processImage(String imageUrl, int width, int height, int targetSizeKB)  {
        return imageService.processImage(imageUrl, width, height, targetSizeKB);
    }

}
