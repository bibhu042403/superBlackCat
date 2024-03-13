package com.pareeksha.blackcat.avenger.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
@Slf4j
public class ImageService {


    public ResponseEntity<byte[]> processImage(MultipartFile file, int width, int height, int targetSizeKB)  {

        try{
            // Load the original image
            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            // Resize the image
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            resizedImage.createGraphics().drawImage(originalImage, 0, 0, width, height, null);

            // Create an ImageWriter for JPEG format
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
            ImageWriter writer = writers.next();

            float value = 0.8f;
            ByteArrayOutputStream baos = compress(writer,resizedImage, value);


            // This will call compress function recursive till image size become less than or equally to the required

            while(baos.size() / 1024 > targetSizeKB) {
                if(value < 0.2)
                    break;
                value = value - 0.1f;
                baos = compress(writer,resizedImage, value);
            }
            // Create HttpHeaders to set response headers
            HttpHeaders headers = new HttpHeaders();

            // Set the Content-Disposition header to make the file downloadable
            headers.setContentDispositionFormData("attachment", "resized_image.jpg");

            // Return the resized and compressed image as a downloadable file
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(baos.toByteArray());
        } catch (IOException e) {
            // Handle exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ByteArrayOutputStream compress(ImageWriter writer, BufferedImage resizedImage, float value) throws IOException {

        ImageWriteParam params = writer.getDefaultWriteParam();
        params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        params.setCompressionQuality(value); // Adjust this value for desired quality

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
