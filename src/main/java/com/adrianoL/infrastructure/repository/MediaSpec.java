package com.adrianoL.infrastructure.repository;

import com.adrianoL.domain.model.Genre;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

public class MediaSpec {

    public static <T> Specification<T> titleContains(String q){
        return (root, query, builder) ->
                q == null ? null : builder.like(root.get("title"), '%' + q.toLowerCase() + '%');
    }

    public static <T> Specification<T> statusEquals(String status){
        return (root, query, builder) ->
                status == null ? null : builder.equal(root.get("status"), status);
    }

    public static <T> Specification<T> authorLike(String author){
        return (root, query, builder) ->
                author == null ? null : builder.like(root.get("author"), '%' + author.toLowerCase() + '%');
    }

    public static <T> Specification<T> hasGenre(List<String> genres) {
        return (root, query, builder) -> {
            if (!ObjectUtils.isEmpty(genres) && Objects.nonNull(query)) {
                Join<T, Genre> genresJoin = root.join("genres");
                Predicate genrePredicate = genresJoin.get("name").in(genres);
                query.groupBy(root.get("id"));
                query.having(builder.equal(builder.countDistinct(genresJoin.get("id")), genres.size()));
                return builder.and(genrePredicate);
            };
            return null;
        };
    }
}
