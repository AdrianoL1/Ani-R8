package com.adrianoL.api.controller;

import com.adrianoL.api.docs.MangaControllerDocs;
import com.adrianoL.api.dto.MangaDTO;
import com.adrianoL.api.dto.input.MangaInput;
import com.adrianoL.domain.service.MangaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/manga")
public class MangaController implements MangaControllerDocs {

    private final MangaService mangaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<MangaDTO> findAll(){
        return mangaService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public MangaDTO findMangaById(@PathVariable Long id){
        return mangaService.findById(id);
    }

    @SecurityRequirement(name = "bearer-key")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public MangaDTO createManga(@RequestBody MangaInput manga){
        return mangaService.create(manga);
    }

    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public MangaDTO updateManga(@PathVariable Long id, @RequestBody MangaInput manga){
        return mangaService.update(id, manga);
    }

    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteMangaById(@PathVariable Long id){
        mangaService.delete(id);
    }
}
