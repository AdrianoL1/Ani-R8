package com.adrianoL.domain.service;

import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.api.dto.UserMangaDTO;
import com.adrianoL.api.dto.input.UpdateUsersMangaInput;
import com.adrianoL.api.dto.input.UserMangaInput;
import com.adrianoL.domain.exception.ResourceNotFoundException;
import com.adrianoL.domain.model.Manga;
import com.adrianoL.domain.model.UserManga;
import com.adrianoL.domain.model.auth.User;
import com.adrianoL.domain.repository.UserMangaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.adrianoL.api.dto_mapper.ObjectMapper.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMangaService {

    private final UserMangaRepository userMangaRepository;
    private final MangaService mangaService;
    private final UserService userService;

    public UserManga getMangaFromUsersListOrException(Long mangaId, UserDTO user){
        return userMangaRepository.getMangaFromUsersList(mangaId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Manga not found in your list!"));
    }

    public List<UserMangaDTO> getAllMangaFromUsersList(String username){
        var user = userService.findByUsername(username);
        return parseListObject(userMangaRepository.findAllUserEntriesByUsername(user.getUsername()), UserMangaDTO.class);
    }

    @Transactional
    public UserMangaDTO create(UserDTO user, UserMangaInput userMangaInput){
        var manga = mangaService.findById(userMangaInput.getMangaId());

        UserManga userList = UserManga.builder()
                .user(parseObject(user, User.class))
                .manga(parseObject(manga, Manga.class))
                .status(userMangaInput.getStatus())
                .personalRating(userMangaInput.getPersonalRating())
                .chaptersRead(userMangaInput.getChaptersRead())
                .volumesRead(userMangaInput.getVolumesRead())
                .build();

        userMangaRepository.save(userList);
        return parseObject(userList, UserMangaDTO.class);
    }

    @Transactional
    public UserMangaDTO update(UserDTO user, Long mangaId, UpdateUsersMangaInput updateUsersMangaInput){
        var mangaEntity = mangaService.findById(mangaId);
        var currentUserManga = getMangaFromUsersListOrException(mangaEntity.getId(), user);

        BeanUtils.copyProperties(updateUsersMangaInput, currentUserManga, "id");

        userMangaRepository.save(currentUserManga);
        return parseObject(currentUserManga, UserMangaDTO.class);
    }

    @Transactional
    public void delete(UserDTO user, Long mangaId){
        var mangaEntity = mangaService.findById(mangaId);
        var userMangaEntity = getMangaFromUsersListOrException(mangaEntity.getId(), user);

        userMangaRepository.delete(userMangaEntity);
    }

}
