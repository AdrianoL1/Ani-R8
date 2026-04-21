package com.adrianoL.api.controller;

import com.adrianoL.api.dto.PageDTO;
import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.api.dto.UserMangaDTO;
import com.adrianoL.api.dto.filter.UserMangaFilter;
import com.adrianoL.api.dto.input.UpdateUsersMangaInput;
import com.adrianoL.api.dto.input.UserMangaInput;
import com.adrianoL.domain.service.UserMangaService;
import com.adrianoL.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public PageDTO<UserMangaDTO> getUsersList(
            @PathVariable String username,
            @PageableDefault(size = 10, direction = Sort.Direction.ASC) Pageable pageable,
            UserMangaFilter filter
    ) {
        return userMangaService.getUsersMangaList(username, pageable, filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserMangaDTO createEntryInUserList(@RequestBody @Valid UserMangaInput userMangaInput, JwtAuthenticationToken authToken){
        var user = userService.getUserByUsernameOrException(authToken.getName());
        return userMangaService.create(parseObject(user, UserDTO.class), userMangaInput);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserMangaDTO updateEntryInUserList(@PathVariable @Valid Long id, @RequestBody UpdateUsersMangaInput updateUsersMangaInput, JwtAuthenticationToken authToken){
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
