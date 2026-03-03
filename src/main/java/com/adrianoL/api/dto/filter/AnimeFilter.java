package com.adrianoL.api.dto.filter;

import com.adrianoL.domain.model.Anime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static com.adrianoL.infrastructure.repository.AnimeSpec.*;
import static com.adrianoL.infrastructure.repository.MediaSpec.*;

import java.util.List;

@Getter
@Setter
public class AnimeFilter {

    private String q;
    private String status;
    private List<String> genres;
    private String author;
    private String airedFrom;
    private String airedTo;

    public Specification<Anime> toSpecification(){
        return airedFrom(airedFrom)
                .and(titleContains(q))
                .and(statusEquals(status))
                .and(authorLike(author))
                .and(airedTo(airedTo))
                .and(hasGenre(genres));
    }
}


