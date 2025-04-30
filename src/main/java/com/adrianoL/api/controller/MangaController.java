package com.adrianoL.api.controller;

import com.adrianoL.api.dto.MangaDTO;
import com.adrianoL.api.dto.input.MangaInput;
import com.adrianoL.domain.service.MangaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/manga")
public class MangaController {

    private final MangaService mangaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MangaDTO> findAll(){
        return mangaService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MangaDTO findMangaById(@PathVariable Long id){
        return mangaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MangaDTO createManga(@RequestBody MangaInput manga){
        return mangaService.create(manga);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MangaDTO updateManga(@PathVariable Long id, @RequestBody MangaInput manga){
        return mangaService.update(id, manga);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMangaById(@PathVariable Long id){
        mangaService.delete(id);
    }
}
