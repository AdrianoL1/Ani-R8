package com.adrianoL.api.controller;

import com.adrianoL.api.dto.AnimeDTO;
import com.adrianoL.api.dto.input.AnimeInput;
import com.adrianoL.domain.service.AnimeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/anime")
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnimeDTO> getAllAnimes(){
        return animeService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimeDTO getAnimeById(@PathVariable Long id){
        return animeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimeDTO createAnime(@RequestBody AnimeInput anime){
        return animeService.create(anime);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimeDTO updateAnime(@PathVariable Long id, @RequestBody AnimeInput anime){
        return animeService.update(id, anime);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnime(@PathVariable Long id){
        animeService.delete(id);
    }
}
