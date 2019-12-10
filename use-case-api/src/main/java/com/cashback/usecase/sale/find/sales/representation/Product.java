package com.cashback.usecase.sale.find.sales.representation;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Serializable {

    private String description;
    private BigDecimal price;
    private BigDecimal cashback;

    private Product(String description, BigDecimal price, BigDecimal cashback) {
        this.description = description;
        this.price = price;
        this.cashback = cashback;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getCashback() {
        return cashback;
    }

    @Bean
    public static Product valueOf(String description, BigDecimal price, BigDecimal cashback) {

        return new Product(description, price, cashback);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(description, product.description) &&
                Objects.equals(price, product.price) &&
                Objects.equals(cashback, product.cashback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, price, cashback);
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", cashback=" + cashback +
                '}';
    }
}
