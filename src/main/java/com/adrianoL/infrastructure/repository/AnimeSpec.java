package com.adrianoL.infrastructure.repository;

import com.adrianoL.domain.model.Anime;
import org.springframework.data.jpa.domain.Specification;

public class AnimeSpec {

    public static Specification<Anime> airedFrom(String startYear){
        return (root, query, builder) ->
                startYear == null ? null : builder.equal(root.get("airedFrom"), startYear);
    }

    public static Specification<Anime> airedTo(String endYear){
        return (root, query, builder) ->
                endYear == null ? null : builder.equal(root.get("airedTo"), endYear);
    }
}
