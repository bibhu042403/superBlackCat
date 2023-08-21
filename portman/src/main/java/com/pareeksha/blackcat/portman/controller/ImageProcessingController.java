package com.pareeksha.blackcat.portman.controller;

import com.pareeksha.blackcat.avenger.facade.ImageFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/img")
@Slf4j
public class ImageProcessingController {

    @Autowired
    ImageFacade imageFacade;
    @GetMapping("/resize")
    public ResponseEntity<byte[]> resizeAndCompressImage(@RequestParam("url") String imageUrl,
                                                         @RequestParam("width") int width,
                                                         @RequestParam("height") int height,
                                                         @RequestParam("targetSizeKB") int targetSizeKB) {

            // Specify the file:// protocol for local file URLs
            if (!imageUrl.startsWith("file://")) {
                imageUrl = "file://" + imageUrl;
            }
          return imageFacade.processImage(imageUrl, width, height, targetSizeKB);
    }

}
