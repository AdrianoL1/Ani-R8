package com.adrianoL.domain.service;

import com.adrianoL.api.dto.GenreDTO;
import com.adrianoL.domain.exception.GenreNotFoundException;
import com.adrianoL.domain.model.Genre;
import com.adrianoL.domain.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.adrianoL.api.dto_mapper.ObjectMapper.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre getGenreOrException(Long id){
        return genreRepository.findById(id).orElseThrow(
                () -> new GenreNotFoundException(id)
        );
    }

    public List<GenreDTO> listAll(){
        return parseListObject(genreRepository.findAll(), GenreDTO.class);
    }

    public GenreDTO findById(Long id){
        return parseObject(getGenreOrException(id), GenreDTO.class);
    }

    @Transactional
    public GenreDTO create(GenreDTO genre){
        Genre genreEntity = parseObject(genre, Genre.class);
        genreRepository.save(genreEntity);

        return parseObject(genreEntity, GenreDTO.class);
    }

    @Transactional
    public GenreDTO update(Long id, GenreDTO genre){
        Genre currentGenre = getGenreOrException(id);
        BeanUtils.copyProperties(genre, currentGenre, "id");

        genreRepository.save(currentGenre);

        return parseObject(currentGenre, GenreDTO.class);
    }

    @Transactional
    public void delete(Long id){
        Genre genreEntity = getGenreOrException(id);
        genreRepository.deleteById(genreEntity.getId());
    }
}
