package com.adrianoL.domain.service;

import com.adrianoL.domain.exception.GenreNotFoundException;
import com.adrianoL.domain.model.Genre;
import com.adrianoL.domain.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre getGenreOrException(Long id){
        return genreRepository.findById(id).orElseThrow(
                () -> new GenreNotFoundException(
                        String.format("Genre with ID: %s not found", id)
                )
        );
    }

    public List<Genre> listAll(){
        return genreRepository.findAll();
    }

    public Genre findById(Long id){
        return getGenreOrException(id);
    }

    @Transactional
    public Genre create(Genre genre){
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre update(Long id, Genre genre){
        Genre currentGenre = getGenreOrException(id);
        BeanUtils.copyProperties(genre, currentGenre, "id");

        return genreRepository.save(currentGenre);
    }

    @Transactional
    public void delete(Long id){
        genreRepository.deleteById(id);
    }
}
