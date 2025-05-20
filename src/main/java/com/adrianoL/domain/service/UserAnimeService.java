package com.adrianoL.domain.service;

import com.adrianoL.api.dto.UserAnimeDTO;
import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.api.dto.input.UserAnimeInput;
import com.adrianoL.domain.model.Anime;
import com.adrianoL.domain.model.UserAnime;
import com.adrianoL.domain.model.auth.User;
import com.adrianoL.domain.repository.UserAnimeRepository;
import lombok.RequiredArgsConstructor;
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
    public void delete(UserDTO user, Long id){
        var animeEntity = animeService.findById(id);
        userAnimeRepository.deleteAnimeFromUserList(animeEntity.getId(), user.getId());
    }
}
