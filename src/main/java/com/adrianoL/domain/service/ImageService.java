package com.adrianoL.domain.service;

import com.adrianoL.domain.exception.ImageNotFoundException;
import com.adrianoL.domain.exception.StorageException;
import com.adrianoL.domain.model.Image;
import com.adrianoL.domain.repository.ImageRepository;
import com.adrianoL.infrastructure.service.ImageStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageStorageService imageStorageService;

    public Image getImageDataOrException(Long id){
        return imageRepository.findById(id).orElseThrow(
                () -> new ImageNotFoundException(id)
        );
    }

    public Image getImageEntityOrException(Image image){
        try{
            return imageRepository.findById(image.getId()).orElseThrow(
                    () -> new ImageNotFoundException(image.getId())
            );
        }catch (NullPointerException e){
            throw new StorageException("Couldn't locate file!", e);
        }
    }

    public ResponseEntity<Resource> getImage(Image image) {
        Image storedImg = getImageEntityOrException(image);
        MediaType mediaType = MediaType.parseMediaType(image.getContentType());
        Resource imageResource = imageStorageService.retrieve(storedImg.getFilename());

        return ResponseEntity.ok().contentType(mediaType).body(imageResource);
    }

    @Transactional
    public Image save(Image image, MultipartFile multipartFile) {
        String newImageName = imageStorageService.generateFileName(image.getFilename());
        image.setFilename(newImageName);

        image = imageRepository.save(image);
        imageRepository.flush();

        imageStorageService.save(multipartFile, newImageName);
        return image;
    }

    @Transactional
    public void delete(Image image){
        var img = getImageEntityOrException(image);
        imageRepository.delete(img);
        imageStorageService.delete(img.getFilename());
    }
}
