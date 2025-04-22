package com.adrianoL.domain.service;

import com.adrianoL.api.dto.MangaDTO;
import com.adrianoL.domain.exception.MangaNotFoundException;
import com.adrianoL.domain.model.Manga;
import com.adrianoL.domain.repository.MangaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.adrianoL.api.dto_mapper.ObjectMapper.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MangaService {

    private final MangaRepository mangaRepository;
    private final GenreService genreService;

    public Manga getMangaOrException(Long id){
        return mangaRepository.findById(id).orElseThrow(
                () -> new MangaNotFoundException(
                        String.format("Manga with ID: %s not found", id)
                )
        );
    }

    public MangaDTO findById(Long id){
        return parseObject(getMangaOrException(id), MangaDTO.class);
    }

    public List<MangaDTO> listAll(){
        return parseListObject(mangaRepository.findAll(), MangaDTO.class);
    }

    @Transactional
    public MangaDTO create(MangaDTO manga){

        Manga mangaEntity = parseObject(manga, Manga.class);

        var genres = mangaEntity.getGenres().stream().map(
                genre -> genreService.getGenreOrException(genre.getId())
        ).collect(Collectors.toSet());

        mangaEntity.setGenres(genres);

        mangaRepository.save(mangaEntity);
        return parseObject(mangaEntity, MangaDTO.class);
    }

    @Transactional
    public MangaDTO update(Long id, MangaDTO manga){

        Manga currentManga = getMangaOrException(id);
        BeanUtils.copyProperties(manga, currentManga, "id");

        mangaRepository.save(currentManga);

        return parseObject(currentManga, MangaDTO.class);
    }

    @Transactional
    public void delete(Long id){
        Manga mangaEntity = getMangaOrException(id);
        mangaRepository.deleteById(mangaEntity.getId());
    }
}
