package com.adrianoL.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        return Objects.equals(getId(), userDTO.getId()) && Objects.equals(getUsername(), userDTO.getUsername()) && Objects.equals(getEmail(), userDTO.getEmail()) && Objects.equals(getRoles(), userDTO.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getRoles());
    }
}
