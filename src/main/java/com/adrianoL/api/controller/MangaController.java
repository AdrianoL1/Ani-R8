package com.adrianoL.api.controller;

import com.adrianoL.api.docs.MangaControllerDocs;
import com.adrianoL.api.dto.MangaDTO;
import com.adrianoL.api.dto.PageDTO;
import com.adrianoL.api.dto.filter.MangaFilter;
import com.adrianoL.api.dto.input.MangaInput;
import com.adrianoL.domain.service.MangaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/manga")
public class MangaController implements MangaControllerDocs {

    private final MangaService mangaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Override
    public PageDTO<MangaDTO> findAll(
            @PageableDefault(size = 10, sort = {"title"}, direction = Sort.Direction.ASC) Pageable pageable,
            MangaFilter mangaFilter
    )
    {
        return mangaService.findAll(pageable, mangaFilter);
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
