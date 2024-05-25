package com.adrianoL.api.controller;

import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.api.dto.UserMangaDTO;
import com.adrianoL.api.dto.input.UpdateUsersMangaInput;
import com.adrianoL.api.dto.input.UserMangaInput;
import com.adrianoL.domain.service.UserMangaService;
import com.adrianoL.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.adrianoL.api.dto_mapper.ObjectMapper.parseObject;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/mangalist")
public class UserMangaController {

    private final UserMangaService userMangaService;
    private final UserService userService;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserMangaDTO> getUserAllEntries(@PathVariable String username){
        return userMangaService.getAllMangaFromUsersList(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserMangaDTO createEntryInUserList(@RequestBody UserMangaInput userMangaInput, JwtAuthenticationToken authToken){
        var user = userService.getUserByUsernameOrException(authToken.getName());
        return userMangaService.create(parseObject(user, UserDTO.class), userMangaInput);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserMangaDTO updateEntryInUserList(@PathVariable Long id, @RequestBody UpdateUsersMangaInput updateUsersMangaInput, JwtAuthenticationToken authToken){
        var user = userService.getUserByUsernameOrException(authToken.getName());
        return userMangaService.update(parseObject(user, UserDTO.class), id, updateUsersMangaInput);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromUserList(@PathVariable Long id, JwtAuthenticationToken authToken){
        var user = userService.getUserByUsernameOrException(authToken.getName());
        userMangaService.delete(parseObject(user, UserDTO.class), id);
    }

}
