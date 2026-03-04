package com.adrianoL.infrastructure.repository;

import com.adrianoL.domain.model.Anime;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class UserListSpec {

    public static <T> Specification<T> titleLike(String q){
        return (root, query, builder) -> {
              var mediaType = Objects.nonNull(root.get("anime")) ? root.get("anime") : root.get("manga");
              return q == null ? null : builder.like(mediaType.get("title"), '%' + q.toLowerCase() + '%');
        };
    }

    public static <T> Specification<T> authorLike(String author){
        return (root, query, builder) -> {
            var mediaType = Objects.nonNull(root.get("anime")) ? root.get("anime") : root.get("manga");
            return author == null ? null : builder.like(mediaType.get("author"), '%' + author.toLowerCase() + '%');
        };
    }

    public static <T> Specification<T> belongsToUsername(String username){
        return (root, query, builder) ->
                builder.equal(root.get("user").get("username"), username);
    }

    public static <T> Specification<T> listStatusEquals(String status){
        return (root, query, builder) ->
                status == null ? null : builder.equal(root.get("status"), status);
    }

    public static <T> Specification<T> ratingEquals(Integer score){
        return (root, query, builder) ->
                score == null ? null : builder.equal(root.get("personalRating"), score);
    }
}
