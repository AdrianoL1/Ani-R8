package com.adrianoL.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageDTO<T> {

    private List<T> results;
    private long totalItems;
    private Integer next;
    private Integer previous;
    private int totalPages;
    private int currentPage;

    public PageDTO(Page<T> page){
        this.setResults(page.getContent());
        this.setTotalItems(page.getTotalElements());
        this.setNext(page.hasNext() ? page.getNumber() + 1 : null);
        this.setPrevious(page.hasPrevious() ? page.getNumber() - 1 : null);
        this.setTotalPages(page.getTotalPages());
        this.setCurrentPage(page.getNumber());
    }
}
