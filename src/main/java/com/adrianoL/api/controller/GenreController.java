package com.adrianoL.api.controller;

import com.adrianoL.domain.model.Genre;
import com.adrianoL.domain.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Genre> findAll(){
        return genreService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Genre getGenreById(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre createGenre(@RequestBody Genre genre){
        return genreService.create(genre);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Genre updateGenre(@PathVariable Long id, @RequestBody Genre genre){
        return genreService.update(id, genre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable Long id){
        genreService.delete(id);
    }
}
