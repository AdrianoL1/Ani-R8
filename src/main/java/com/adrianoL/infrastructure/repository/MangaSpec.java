package com.adrianoL.infrastructure.repository;

import com.adrianoL.domain.model.Manga;
import org.springframework.data.jpa.domain.Specification;

public class MangaSpec {

    public static Specification<Manga> publishedFrom(String startYear){
        return (root, query, builder) ->
                startYear == null ? null : builder.equal(root.get("publishedFrom"), startYear);
    }

    public static Specification<Manga> publishedTo(String endYear){
        return (root, query, builder) ->
                endYear == null ? null : builder.equal(root.get("publishedTo"), endYear);
    }
}
