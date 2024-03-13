package com.pareeksha.blackcat.avenger.facade;

import com.pareeksha.blackcat.avenger.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageFacade {
    @Autowired
    ImageService imageService;

    public ResponseEntity<byte[]> processImage(MultipartFile file, int width, int height, int targetSizeKB)  {
        return imageService.processImage(file, width, height, targetSizeKB);
    }

}
