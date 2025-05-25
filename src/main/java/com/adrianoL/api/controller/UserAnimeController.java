package com.adrianoL.api.controller;

import com.adrianoL.api.dto.UserAnimeDTO;
import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.api.dto.input.UpdateUsersAnimeInput;
import com.adrianoL.api.dto.input.UserAnimeInput;
import com.adrianoL.domain.service.UserAnimeService;
import com.adrianoL.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import static com.adrianoL.api.dto_mapper.ObjectMapper.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/animelist")
public class UserAnimeController {

    private final UserAnimeService userAnimeService;
    private final UserService userService;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserAnimeDTO> getUserAllEntries(@PathVariable String username){
        return userAnimeService.getAllUserEntries(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserAnimeDTO createEntryInUserList(@RequestBody UserAnimeInput userAnimeInput, JwtAuthenticationToken authToken){
        var user = userService.getUserByUsernameOrException(authToken.getName());
        return userAnimeService.create(parseObject(user, UserDTO.class), userAnimeInput);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromUserList(@PathVariable Long id, JwtAuthenticationToken authToken){
        var user = userService.getUserByUsernameOrException(authToken.getName());
        userAnimeService.delete(parseObject(user, UserDTO.class), id);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserAnimeDTO updateEntryInUserList(@PathVariable Long id, @RequestBody UpdateUsersAnimeInput updateUsersAnimeInput, JwtAuthenticationToken authToken){
        var user = userService.getUserByUsernameOrException(authToken.getName());
        return userAnimeService.update(parseObject(user, UserDTO.class), id, updateUsersAnimeInput);
    }
}
