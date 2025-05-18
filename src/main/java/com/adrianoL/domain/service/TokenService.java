package com.adrianoL.domain.service;

import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.config.properties_metadata.TokenPropertiesConfig;
import com.adrianoL.domain.model.auth.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import static com.adrianoL.api.dto_mapper.ObjectMapper.*;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final TokenPropertiesConfig tokenPropertiesConfig;

    public Jwt generateToken(User user){

        Instant createdAt = Instant.now();
        var parsedUser = parseObject(user, UserDTO.class);
        var scopes = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("AniR8")
                .issuedAt(createdAt)
                .expiresAt(createdAt.plusSeconds(tokenPropertiesConfig.accessToken().expirationTime()))
                .subject(user.getUsername())
                .claim("user", parsedUser)
                .claim("scope", scopes)
                .build();

        var header = JwsHeader.with(SignatureAlgorithm.RS256).type("JWT").build();
        return  jwtEncoder.encode(JwtEncoderParameters.from(header, claims));
    }
}
