package com.adrianoL.infrastructure.service;

import com.adrianoL.domain.exception.StorageException;
import com.adrianoL.domain.service.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class ImageStorageService implements StorageService {

    @Value("${file.upload-dir}")
    private Path storagePath;

    public ImageStorageService(){

    }

    private final List<String> allowedTypes = List.of("image/jpeg", "image/png", "image/webp");

    @Override
    public void save(MultipartFile multipartFile, String filename) {
        try{
            try {
                Files.createDirectories(storagePath.normalize().toAbsolutePath());
            }catch (IOException e){
                throw new StorageException("Couldn't create directory.");
            }

            String contentType = multipartFile.getContentType();

            if(!allowedTypes.contains(contentType) || filename.contains("..")){
                throw new StorageException("Invalid file type!");
            }

            Files.copy(multipartFile.getInputStream(), getFilePath(filename), StandardCopyOption.REPLACE_EXISTING);

        }catch (IOException e){
            throw new StorageException("Couldn't save file. Please try again.", e);
        }
    }

    @Override
    public Resource retrieve(String filename) {
        try {
            Path file = getFilePath(filename);
            return new UrlResource(file.toUri());
        }catch (Exception e){
            throw new StorageException("Couldn't locate file: " + filename, e);
        }
    }

    @Override
    public void delete(String filename) {
        try {
            Path file = getFilePath(filename);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new StorageException("File doesn't exists!", e);
        }
    }

    private Path getFilePath(String fileName) {
        return storagePath.resolve(fileName);
    }
}
