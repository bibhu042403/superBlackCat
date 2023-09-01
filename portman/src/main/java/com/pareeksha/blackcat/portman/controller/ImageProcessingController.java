package com.pareeksha.blackcat.portman.controller;

import com.pareeksha.blackcat.avenger.facade.ImageFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/img")
@Slf4j
public class ImageProcessingController {

    @Autowired
    ImageFacade imageFacade;
    @PostMapping("/resize")
    public ResponseEntity<byte[]> resizeAndCompressImage( @RequestParam("file") MultipartFile file,
                                                         @RequestParam("width") int width,
                                                         @RequestParam("height") int height,
                                                         @RequestParam("targetSizeKB") int targetSizeKB) {

            // Specify the file:// protocol for local file URLs
//            if (!imageUrl.startsWith("file://")) {
//                imageUrl = "file://" + imageUrl;
//            }
          return imageFacade.processImage(file, width, height, targetSizeKB);
    }

}
