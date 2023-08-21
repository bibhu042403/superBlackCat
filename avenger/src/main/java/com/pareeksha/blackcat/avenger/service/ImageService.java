package com.pareeksha.blackcat.avenger.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

@Service
@Slf4j
public class ImageService {


    public ResponseEntity<byte[]> processImage(String imageUrl, int width, int height, int targetSizeKB)  {

        try{
            // Load the original image
            BufferedImage originalImage = ImageIO.read(new URL(imageUrl));

            // Resize the image
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            resizedImage.createGraphics().drawImage(originalImage, 0, 0, width, height, null);

            // Create an ImageWriter for JPEG format
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
            ImageWriter writer = writers.next();

            // Configure compression parameters
            ImageWriteParam params = writer.getDefaultWriteParam();
            params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            params.setCompressionQuality(0.3f); // Adjust this value for desired quality

            // Convert the resized image to a byte array with compression
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
            writer.setOutput(ios);
            writer.write(null, new IIOImage(resizedImage, null, null), params);

            // Close streams
            ios.close();
            baos.close();

            // Check if the image is below the target size
            if (baos.size() / 1024 <= targetSizeKB) {
                // Create HttpHeaders to set response headers
                HttpHeaders headers = new HttpHeaders();

                // Set the Content-Disposition header to make the file downloadable
                headers.setContentDispositionFormData("attachment", "resized_image.jpg");

                // Return the resized and compressed image as a downloadable file
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(baos.toByteArray());
            } else {
                // If the image is still above the target size, you may need to adjust compression parameters
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        } catch (IOException e) {
            // Handle exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ByteArrayOutputStream compress(ImageWriter writer, ImageWriteParam params,BufferedImage resizedImage) throws IOException {
        // Convert the resized image to a byte array with compression
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
        writer.setOutput(ios);
        writer.write(null, new IIOImage(resizedImage, null, null), params);

        // Close streams
        ios.close();
        baos.close();

        return baos;
    }

}
