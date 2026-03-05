package com.adrianoL.api.dto.filter;

import com.adrianoL.domain.model.UserAnime;
import com.adrianoL.infrastructure.repository.UserListSpec;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import static com.adrianoL.infrastructure.repository.UserListSpec.*;

@Getter
@Setter
public class UserAnimeFilter {

    private String q;
    private Integer personalRating;
    private String status;
    private String author;

    public Specification<UserAnime> toSpecification(){
        return UserListSpec.<UserAnime>listStatusEquals(status)
                .and(ratingEquals(personalRating))
                .and(authorLike(author))
                .and(titleLike(q));
    }
}
