package com.adrianoL.api.controller;

import com.adrianoL.api.docs.GenreControllerDocs;
import com.adrianoL.api.dto.GenreDTO;
import com.adrianoL.domain.service.GenreService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/genre")
public class GenreController implements GenreControllerDocs {

    private final GenreService genreService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<GenreDTO> findAll(){
        return genreService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public GenreDTO getGenreById(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @SecurityRequirement(name = "bearer-key")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public GenreDTO createGenre(@RequestBody GenreDTO genre){
        return genreService.create(genre);
    }

    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public GenreDTO updateGenre(@PathVariable Long id, @RequestBody GenreDTO genre){
        return genreService.update(id, genre);
    }

    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteGenre(@PathVariable Long id){
        genreService.delete(id);
    }
}
