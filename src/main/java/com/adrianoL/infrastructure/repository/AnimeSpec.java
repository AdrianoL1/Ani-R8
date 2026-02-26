package com.adrianoL.infrastructure.repository;

import com.adrianoL.domain.model.Anime;
import org.springframework.data.jpa.domain.Specification;

public class AnimeSpec {

    public static Specification<Anime> titleContains(String q){
        return (root, query, builder) ->
                q == null ? null : builder.like(root.get("title"), '%' + q.toLowerCase() + '%');
    }

    public static Specification<Anime> statusEquals(String status){
        return (root, query, builder) ->
                status == null ? null : builder.equal(root.get("status"), status);
    }

    public static Specification<Anime> authorLike(String author){
        return (root, query, builder) ->
                author == null ? null : builder.like(root.get("author"), '%' + author.toLowerCase() + '%');
    }

    public static Specification<Anime> airedFrom(String startYear){
        return (root, query, builder) ->
                startYear == null ? null : builder.equal(root.get("airedFrom"), startYear);
    }

    public static Specification<Anime> airedTo(String endYear){
        return (root, query, builder) ->
                endYear == null ? null : builder.equal(root.get("airedTo"), endYear);
    }
}
