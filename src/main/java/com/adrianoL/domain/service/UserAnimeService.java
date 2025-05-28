package com.adrianoL.domain.service;

import com.adrianoL.api.dto.UserAnimeDTO;
import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.api.dto.input.UpdateUsersAnimeInput;
import com.adrianoL.api.dto.input.UserAnimeInput;
import com.adrianoL.domain.exception.ResourceNotFoundException;
import com.adrianoL.domain.model.Anime;
import com.adrianoL.domain.model.UserAnime;
import com.adrianoL.domain.model.auth.User;
import com.adrianoL.domain.repository.UserAnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.adrianoL.api.dto_mapper.ObjectMapper.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAnimeService {

    private final UserAnimeRepository userAnimeRepository;
    private final UserService userService;
    private final AnimeService animeService;

    public UserAnime getAnimeFromUsersListOrException(Long animeId, UserDTO user){
        return userAnimeRepository.getAnimeFromUsersList(animeId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Anime not found in your list."));
    }

    public List<UserAnimeDTO> getAllUserEntries(String username){
        var user = userService.findByUsername(username);
        return parseListObject(userAnimeRepository.findAllUserEntriesByUsername(user.getUsername()), UserAnimeDTO.class);
    }

    @Transactional
    public UserAnimeDTO create (UserDTO user, UserAnimeInput userAnimeInput){
        var anime = animeService.findById(userAnimeInput.getAnimeId());

        UserAnime userList = UserAnime.builder()
                .user(parseObject(user, User.class))
                .anime(parseObject(anime, Anime.class))
                .status(userAnimeInput.getStatus())
                .personalRating(userAnimeInput.getPersonalRating())
                .episodesWatched(userAnimeInput.getEpisodesWatched())
                .build();

        userAnimeRepository.save(userList);
        return parseObject(userList, UserAnimeDTO.class);
    }

    @Transactional
    public UserAnimeDTO update(UserDTO user, Long animeId, UpdateUsersAnimeInput updateUsersAnimeInput){
        var animeEntity = animeService.findById(animeId);
        var currentUserAnime = getAnimeFromUsersListOrException(animeEntity.getId(), user);

        BeanUtils.copyProperties(updateUsersAnimeInput, currentUserAnime, "id");

        userAnimeRepository.save(currentUserAnime);
        return parseObject(currentUserAnime, UserAnimeDTO.class);
    }

    @Transactional
    public void delete(UserDTO user, Long id){
        var animeEntity = animeService.findById(id);
        var userAnimeEntity = getAnimeFromUsersListOrException(animeEntity.getId(), user);

        userAnimeRepository.delete(userAnimeEntity);
    }
}
