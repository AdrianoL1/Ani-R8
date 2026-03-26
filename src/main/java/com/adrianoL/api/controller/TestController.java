package com.adrianoL.api.controller;

import com.adrianoL.api.dto.UploadFileResponseDTO;
import com.adrianoL.infrastructure.service.ImageStorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;

@AllArgsConstructor
@RestController
@RequestMapping("/api/files")
public class TestController {

    private final ImageStorageService service;

    @PostMapping("/uploadFile")
    public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = service.generateFileName(file.getOriginalFilename());
        service.save(file);

        return new UploadFileResponseDTO(fileName, file.getContentType(), file.getSize());
    }

    @GetMapping(value = "/{filename}", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<Resource> GetImage(@PathVariable String filename){
        var img = service.retrieve(filename);

        return ResponseEntity.status(HttpStatus.OK).body(img);
    }
}
