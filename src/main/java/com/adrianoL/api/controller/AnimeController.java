package com.adrianoL.api.controller;

import com.adrianoL.api.docs.AnimeControllerDocs;
import com.adrianoL.api.dto.AnimeDTO;
import com.adrianoL.api.dto.ImageDTO;
import com.adrianoL.api.dto.PageDTO;
import com.adrianoL.api.dto.filter.AnimeFilter;
import com.adrianoL.api.dto.input.AnimeInput;
import com.adrianoL.domain.model.Image;
import com.adrianoL.domain.service.AnimeService;
import com.adrianoL.domain.service.ImageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.adrianoL.api.dto_mapper.ObjectMapper.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/anime")
public class AnimeController implements AnimeControllerDocs {

    private final AnimeService animeService;
    private final ImageService imageService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Override
    public PageDTO<AnimeDTO> getAllAnimes(
            @PageableDefault(size = 10, sort = {"title"}, direction = Sort.Direction.ASC) Pageable pageable,
            AnimeFilter animeFilter
    )
    {
        return animeService.findAll(pageable, animeFilter);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public AnimeDTO getAnimeById(@PathVariable Long id){
        return animeService.findById(id);
    }

    @SecurityRequirement(name = "bearer-key")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public AnimeDTO createAnime(@RequestBody AnimeInput anime){
        return animeService.create(anime);
    }

    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public AnimeDTO updateAnime(@PathVariable Long id, @RequestBody AnimeInput anime){
        return animeService.update(id, anime);
    }

    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteAnime(@PathVariable Long id){
        animeService.delete(id);
    }

    @GetMapping("/{id}/image")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> getImage(@PathVariable Long id,
                                             @RequestHeader(name = "accept", defaultValue = "image/*")
                                             String acceptHeader
    ) {
        var anime = animeService.findById(id);
        return imageService.getImage(anime.getImage());
    }

    @SecurityRequirement(name = "bearer-key")
    @PutMapping(value = "/{animeId}/image")
    @ResponseStatus(HttpStatus.OK)
    public ImageDTO saveImage(@PathVariable Long animeId, @RequestParam("file") MultipartFile file) {
        var anime = animeService.findById(animeId);

        Image image = Image.builder()
                .filename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .fileSize(file.getSize())
                .build();

        animeService.saveImage(anime, image, file);

        return parseObject(image, ImageDTO.class);
    }

    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping(value = "/{animeId}/image")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable Long animeId){
        var anime = animeService.findById(animeId);
        animeService.deleteImage(anime);
    }
}
