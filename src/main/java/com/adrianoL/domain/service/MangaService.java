package com.adrianoL.domain.service;

import com.adrianoL.api.dto.MangaDTO;
import com.adrianoL.api.dto.PageDTO;
import com.adrianoL.api.dto.filter.MangaFilter;
import com.adrianoL.api.dto.input.MangaInput;
import com.adrianoL.domain.exception.MangaNotFoundException;
import com.adrianoL.domain.model.Manga;
import com.adrianoL.domain.repository.MangaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
                () -> new MangaNotFoundException(id)
        );
    }

    public MangaDTO findById(Long id){
        return parseObject(getMangaOrException(id), MangaDTO.class);
    }

    public PageDTO<MangaDTO> findAll(Pageable pageable, MangaFilter filter){
        Page<Manga> mangaPage = mangaRepository.findAll(filter.toSpecification(), pageable);
        List<MangaDTO> mangaDTOS = parseListObject(mangaPage.getContent(), MangaDTO.class);
        var pageImpl = new PageImpl<>(mangaDTOS, pageable, mangaPage.getTotalElements());

        return new PageDTO<>(pageImpl);
    }

    @Transactional
    public MangaDTO create(MangaInput manga){

        Manga mangaEntity = parseObject(manga, Manga.class);

        var genres = mangaEntity.getGenres().stream().map(
                genre -> genreService.getGenreOrException(genre.getId())
        ).collect(Collectors.toSet());

        mangaEntity.setGenres(genres);

        mangaRepository.save(mangaEntity);
        return parseObject(mangaEntity, MangaDTO.class);
    }

    @Transactional
    public MangaDTO update(Long id, MangaInput manga){

        Manga currentManga = getMangaOrException(id);
        BeanUtils.copyProperties(manga, currentManga, "id");

        var genres = manga.getGenres().stream().map(
                genre -> genreService.getGenreOrException(genre.getId())
        ).collect(Collectors.toSet());

        currentManga.setGenres(genres);

        mangaRepository.save(currentManga);
        return parseObject(currentManga, MangaDTO.class);
    }

    @Transactional
    public void delete(Long id){
        Manga mangaEntity = getMangaOrException(id);
        mangaRepository.deleteById(mangaEntity.getId());
    }
}
