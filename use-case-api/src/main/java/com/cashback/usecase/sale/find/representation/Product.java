package com.cashback.usecase.sale.find.representation;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Serializable {

    private String description;
    private BigDecimal price;

    private Product(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Bean
    public static Product valueOf(String description, BigDecimal price) {

        return new Product(description, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(description, product.description) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
