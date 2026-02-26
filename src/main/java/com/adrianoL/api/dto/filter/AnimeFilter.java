package com.adrianoL.api.dto.filter;

import com.adrianoL.domain.model.Anime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static com.adrianoL.infrastructure.repository.AnimeSpec.*;

import java.util.List;

@Getter
@Setter
public class AnimeFilter {

    private String q;
    private String status;
    private List<String> genre;
    private String author;
    private String airedFrom;
    private String airedTo;

    public Specification<Anime> toSpecification(){
        return titleContains(q)
                .and(statusEquals(status))
                .and(authorLike(author))
                .and(airedFrom(airedFrom))
                .and(airedTo(airedTo));
    }
}


