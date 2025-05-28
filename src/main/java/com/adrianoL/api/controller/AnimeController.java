package com.adrianoL.api.controller;

import com.adrianoL.api.docs.AnimeControllerDocs;
import com.adrianoL.api.dto.AnimeDTO;
import com.adrianoL.api.dto.input.AnimeInput;
import com.adrianoL.domain.service.AnimeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/anime")
public class AnimeController implements AnimeControllerDocs {

    private final AnimeService animeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<AnimeDTO> getAllAnimes(){
        return animeService.listAll();
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
}
