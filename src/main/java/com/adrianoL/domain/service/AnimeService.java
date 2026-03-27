package com.adrianoL.domain.service;

import com.adrianoL.api.dto.AnimeDTO;
import com.adrianoL.api.dto.PageDTO;
import com.adrianoL.api.dto.filter.AnimeFilter;
import com.adrianoL.api.dto.input.AnimeInput;
import com.adrianoL.domain.exception.AnimeNotFoundException;
import com.adrianoL.domain.model.Anime;
import com.adrianoL.domain.model.Image;
import com.adrianoL.domain.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.adrianoL.api.dto_mapper.ObjectMapper.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final GenreService genreService;
    private final ImageService imageService;

    public Anime getAnimeOrException(Long id){
        return animeRepository.findById(id).orElseThrow(
                () -> new AnimeNotFoundException(id)
        );
    }

    public AnimeDTO findById(Long id){
        return parseObject(getAnimeOrException(id), AnimeDTO.class);
    }

    public PageDTO<AnimeDTO> findAll(Pageable pageable, AnimeFilter filter){
        Page<Anime> animePage = animeRepository.findAll(filter.toSpecification(), pageable);
        List<AnimeDTO> animeDTOS = parseListObject(animePage.getContent(), AnimeDTO.class);
        var pageImpl = new PageImpl<>(animeDTOS, pageable, animePage.getTotalElements());

        return new PageDTO<>(pageImpl);
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

    @Transactional
    public Image saveImage(AnimeDTO anime, Image image, MultipartFile multipartFile) {
        var animeEntity = parseObject(anime, Anime.class);

        if(animeEntity.hasImage()){
            deleteImage(anime);
        }

        image = imageService.save(image, multipartFile);

        animeEntity.setImage(image);
        animeRepository.save(animeEntity);

        return imageService.getImageDataOrException(image.getId());
    }

    @Transactional
    public void deleteImage(AnimeDTO anime){
        var animeEntity = parseObject(anime, Anime.class);

        imageService.delete(animeEntity.getImage());
        animeEntity.setImage(null);
        animeRepository.save(animeEntity);
    }

}
