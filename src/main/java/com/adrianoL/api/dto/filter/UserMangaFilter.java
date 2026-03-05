package com.adrianoL.api.dto.filter;

import com.adrianoL.domain.model.UserManga;
import com.adrianoL.infrastructure.repository.UserListSpec;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import static com.adrianoL.infrastructure.repository.UserListSpec.*;

@Getter
@Setter
public class UserMangaFilter {

    private String q;
    private String author;
    private Integer personalRating;
    private String status;

    public Specification<UserManga> toSpecification(){
        return UserListSpec.<UserManga>listStatusEquals(status)
                .and(ratingEquals(personalRating))
                .and(titleLike(q))
                .and(authorLike(author));
    }
}
