package com.adrianoL.domain.service.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.UUID;

public interface StorageService {

    void save(MultipartFile multipartFile, String filename);

    Resource retrieve(String filename);

    void delete(String filename);

    default String generateFileName(String originalName){
        return UUID.randomUUID() + "_" + originalName ;
    }
}
