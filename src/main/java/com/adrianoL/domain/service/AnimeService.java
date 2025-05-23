package com.adrianoL.domain.service;

import com.adrianoL.api.dto.AnimeDTO;
import com.adrianoL.api.dto.input.AnimeInput;
import com.adrianoL.domain.exception.AnimeNotFoundException;
import com.adrianoL.domain.model.Anime;
import com.adrianoL.domain.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.adrianoL.api.dto_mapper.ObjectMapper.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final GenreService genreService;

    public Anime getAnimeOrException(Long id){
        return animeRepository.findById(id).orElseThrow(
                () -> new AnimeNotFoundException(id)
        );
    }

    public AnimeDTO findById(Long id){
        return parseObject(getAnimeOrException(id), AnimeDTO.class);
    }

    public List<AnimeDTO> listAll(){
        return parseListObject(animeRepository.findAll(), AnimeDTO.class);
    }

    @Transactional
    public AnimeDTO create(AnimeInput anime) {

        Anime animeEntity = parseObject(anime, Anime.class);

        var genres = animeEntity.getGenres().stream().map(
                genre -> genreService.getGenreOrException(genre.getId())
        ).collect(Collectors.toSet());

        animeEntity.setGenres(genres);

        animeRepository.save(animeEntity);
        return parseObject(animeEntity, AnimeDTO.class);
    }

    @Transactional
    public AnimeDTO update(Long id, AnimeInput anime){
        Anime currentAnime = getAnimeOrException(id);

        BeanUtils.copyProperties(anime, currentAnime, "id");

        var genres = anime.getGenres().stream().map(
                genre -> genreService.getGenreOrException(genre.getId())
        ).collect(Collectors.toSet());

        currentAnime.setGenres(genres);

        animeRepository.save(currentAnime);
        return parseObject(currentAnime, AnimeDTO.class);
    }

    @Transactional
    public void delete(Long id){
        Anime animeEntity = getAnimeOrException(id);
        animeRepository.deleteById(animeEntity.getId());
    }
}
