package com.adrianoL.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class GenreDTO implements Serializable {

    private Long id;
    private String name;

    public GenreDTO(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreDTO genreDTO)) return false;
        return Objects.equals(getId(), genreDTO.getId()) && Objects.equals(getName(), genreDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

}
