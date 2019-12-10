package com.cashback.usecase.sale.find.sales;

import com.cashback.usecase.common.Page;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.List;

public class FindSalesResponse extends Page implements Serializable {

    private FindSalesResponse(List<?> content, Integer totalElements, Integer totalPages,
                               Integer pageNumber, Boolean last, Boolean first) {
        super(content, totalElements, totalPages, pageNumber, last, first);
    }

    @Bean
    public static FindSalesResponse valueOf(List<?> content, Integer totalElements, Integer totalPages,
                                             Integer pageNumber, Boolean last, Boolean first) {
        return new FindSalesResponse(content, totalElements, totalPages, pageNumber, last, first);
    }

    @Override
    public String toString() {
        return "FindSalesResponse{" +
                "content=" + super.getContent() +
                ", totalElements=" + super.getTotalElements() +
                ", totalPages=" + super.getTotalPages() +
                ", pageNumber=" + super.getPageNumber() +
                ", last=" + super.getLast() +
                ", first=" + super.getFirst() +
                '}';
    }
}