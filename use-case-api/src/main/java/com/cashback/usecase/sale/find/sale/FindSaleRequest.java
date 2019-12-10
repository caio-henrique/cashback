package com.cashback.usecase.sale.find.sale;

import com.cashback.common.validator.ObjectValidator;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class FindSaleRequest implements Serializable {

    @NotNull
    private Long identifier;

    private FindSaleRequest(@NotNull Long identifier) {
        this.identifier = identifier;
    }

    public Long getIdentifier() {
        return identifier;
    }

    @Bean
    public static FindSaleRequest valueOf(Long identifier) {

        FindSaleRequest findSaleRequest = new FindSaleRequest(identifier);
        ObjectValidator.validate(findSaleRequest);
        return findSaleRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindSaleRequest that = (FindSaleRequest) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return "FindSaleRequest{" +
                "identifier=" + identifier +
                '}';
    }
}
