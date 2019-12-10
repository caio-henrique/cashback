package com.cashback.usecase.sale.create;

import com.cashback.common.validator.ObjectValidator;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CreateSaleRequest implements Serializable {

    @NotEmpty
    private List<Long> productIds;

    public CreateSaleRequest(List<Long> productIds) {
        this.productIds = productIds;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    @Bean
    @JsonCreator
    public static CreateSaleRequest valueOf(@JsonProperty("productIds") List<Long> productIds) {

        CreateSaleRequest createSaleRequest = new CreateSaleRequest(productIds);
        ObjectValidator.validate(createSaleRequest);
        return createSaleRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateSaleRequest that = (CreateSaleRequest) o;
        return Objects.equals(productIds, that.productIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productIds);
    }

    @Override
    public String toString() {
        return "CreateSaleRequest{" +
                "productIds=" + productIds +
                '}';
    }
}
