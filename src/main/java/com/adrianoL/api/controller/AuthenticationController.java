package com.adrianoL.api.controller;

import com.adrianoL.api.dto.AuthenticationDTO;
import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.api.dto.input.CreateUserInput;
import com.adrianoL.api.dto.input.SigninInput;
import com.adrianoL.domain.model.auth.User;
import com.adrianoL.domain.service.AuthenticationService;
import com.adrianoL.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.adrianoL.api.dto_mapper.ObjectMapper.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO signUp(@RequestBody CreateUserInput createUserInput) {
        User user = parseObject(createUserInput, User.class);
        user = userService.create(user);
        return parseObject(user, UserDTO.class);
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationDTO signIn(@RequestBody SigninInput signinInput) {
        return authenticationService.authenticate(signinInput);
    }
}
