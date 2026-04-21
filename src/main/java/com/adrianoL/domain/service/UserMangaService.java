package com.adrianoL.domain.service;

import com.adrianoL.api.dto.PageDTO;
import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.api.dto.UserMangaDTO;
import com.adrianoL.api.dto.filter.UserMangaFilter;
import com.adrianoL.api.dto.input.UpdateUsersMangaInput;
import com.adrianoL.api.dto.input.UserMangaInput;
import com.adrianoL.domain.exception.ResourceNotFoundException;
import com.adrianoL.domain.model.Manga;
import com.adrianoL.domain.model.UserManga;
import com.adrianoL.domain.model.auth.User;
import com.adrianoL.domain.model.enums.UserAnimeStatus;
import com.adrianoL.domain.model.enums.UserMangaStatus;
import com.adrianoL.domain.repository.UserMangaRepository;
import com.adrianoL.infrastructure.repository.UserListSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public PageDTO<UserMangaDTO> getUsersMangaList(String username, Pageable pageable, UserMangaFilter filter){
        var user = userService.findByUsername(username);
        Page<UserManga> userMangaPage = userMangaRepository.findAll(
                UserListSpec.<UserManga>belongsToUsername(user.getUsername())
                        .and(filter.toSpecification()), pageable
        );
        List<UserMangaDTO> userMangaDTOS = parseListObject(userMangaPage.getContent(), UserMangaDTO.class);
        var pageImpl = new PageImpl<>(userMangaDTOS, pageable, userMangaPage.getTotalElements());

        return new PageDTO<>(pageImpl);
    }

    @Transactional
    public UserMangaDTO create(UserDTO user, UserMangaInput userMangaInput){
        var manga = mangaService.findById(userMangaInput.getMangaId());

        int totalVolumesRead;
        int totalChaptersRead;

        try{
            int totalChapters = Integer.parseInt(manga.getChapters());
            int totalVolumes = Integer.parseInt(manga.getVolumes());

            totalChaptersRead = userMangaInput.getChaptersRead() > totalChapters ? totalChapters : userMangaInput.getChaptersRead();
            totalVolumesRead =  userMangaInput.getVolumesRead() > totalVolumes ? totalVolumes : userMangaInput.getVolumesRead();
        }catch (Exception e){
            totalVolumesRead = userMangaInput.getVolumesRead();
            totalChaptersRead = userMangaInput.getChaptersRead();
        }

        UserManga userList = UserManga.builder()
                .user(parseObject(user, User.class))
                .manga(parseObject(manga, Manga.class))
                .status(UserMangaStatus.valueOf(userMangaInput.getStatus()))
                .personalRating(userMangaInput.getPersonalRating())
                .chaptersRead(totalChaptersRead)
                .volumesRead(totalVolumesRead)
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
