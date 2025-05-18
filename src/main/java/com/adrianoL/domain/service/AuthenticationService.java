package com.adrianoL.domain.service;

import com.adrianoL.api.dto.AuthenticationDTO;
import com.adrianoL.api.dto.input.SigninInput;
import com.adrianoL.config.properties_metadata.TokenPropertiesConfig;
import com.adrianoL.domain.model.auth.User;
import com.adrianoL.domain.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationService {

    private final TokenPropertiesConfig tokenPropertiesConfig;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public AuthenticationDTO authenticate(SigninInput signinInput){

        var authenticationToken = new UsernamePasswordAuthenticationToken(
                signinInput.getEmail(), signinInput.getPassword()
        );

        var authenticationResult = authenticationManager.authenticate(authenticationToken);
        var user = ((SecurityUser) authenticationResult.getPrincipal()).getUser();
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);

        return generateTokens(user);
    }

    @Transactional
    public AuthenticationDTO generateTokens(User user){
        var jwt = tokenService.generateToken(user);
        return new AuthenticationDTO(
                jwt.getTokenValue(),
                tokenPropertiesConfig.accessToken().expirationTime()
        );
    }
}
