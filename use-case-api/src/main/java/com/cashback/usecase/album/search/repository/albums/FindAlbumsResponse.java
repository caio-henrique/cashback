package com.cashback.usecase.album.search.repository.albums;

import com.cashback.usecase.common.Page;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.List;

public class FindAlbumsResponse extends Page implements Serializable {

    private FindAlbumsResponse(List<?> content, Integer totalElements, Integer totalPages,
                              Integer pageNumber, Boolean last, Boolean first) {
        super(content, totalElements, totalPages, pageNumber, last, first);
    }

    @Bean
    public static FindAlbumsResponse valueOf(List<?> content, Integer totalElements, Integer totalPages,
                               Integer pageNumber, Boolean last, Boolean first) {
        return new FindAlbumsResponse(content, totalElements, totalPages, pageNumber, last, first);
    }

    @Override
    public String toString() {
        return "FindAlbumsResponse{" +
                "content=" + super.getContent() +
                ", totalElements=" + super.getTotalElements() +
                ", totalPages=" + super.getTotalPages() +
                ", pageNumber=" + super.getPageNumber() +
                ", last=" + super.getLast() +
                ", first=" + super.getFirst() +
                '}';
    }
}
