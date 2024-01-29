package com.water.can.WaterCanal.controller;

import com.water.can.WaterCanal.Common.CommonUtil;
import com.water.can.WaterCanal.bean.FileRequest;
import com.water.can.WaterCanal.service.FileService.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class FileUploadController {

    private static final String folderPath = "/Users/haseebarittek005/Desktop/Save_files_database";

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestBody FileRequest fileRequest) {
        try {
            fileService.saveFile(fileRequest);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<String> readImage(@PathVariable String fileName) {
        try {
            // Read the content of the image file
            byte[] imageBytes = fileService.readImageFromFile(folderPath, fileName);

            // Return the image content in the response
            return ResponseEntity.ok().body(CommonUtil.convertImageToBase64(imageBytes));
        } catch (IOException e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Handle the exception, e.g., file not found
            return ResponseEntity.notFound().build();
        }
    }
}