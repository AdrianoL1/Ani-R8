package com.adrianoL.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class AuthenticationDTO {

    private String accessToken;
    private Long accessTokenExpiresAt;

    public AuthenticationDTO(String accessToken, Long accessTokenExpiresAt) {
        this.accessToken = accessToken;
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticationDTO that)) return false;
        return Objects.equals(getAccessToken(), that.getAccessToken()) && Objects.equals(getAccessTokenExpiresAt(), that.getAccessTokenExpiresAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessToken(), getAccessTokenExpiresAt());
    }
}
