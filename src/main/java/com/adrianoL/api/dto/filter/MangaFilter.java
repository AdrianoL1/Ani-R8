package com.adrianoL.api.dto.filter;

import com.adrianoL.domain.model.Manga;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import static com.adrianoL.infrastructure.repository.MangaSpec.*;
import static com.adrianoL.infrastructure.repository.MediaSpec.*;

import java.util.List;

@Getter
@Setter
public class MangaFilter {

    private String q;
    private String status;
    private List<String> genres;
    private String author;
    private String publishedFrom;
    private String publishedTo;

    public Specification<Manga> toSpecification(){
        return publishedFrom(publishedFrom)
                .and(titleContains(q))
                .and(statusEquals(status))
                .and(hasGenre(genres))
                .and(authorLike(author))
                .and(publishedTo(publishedTo));
    }
}
