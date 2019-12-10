package com.cashback.usecase.sale.find.representation;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Sale implements Serializable {

    private Long id;
    private List<Product> products;
    private BigDecimal total;
    private BigDecimal cashback;
    private LocalDateTime createAt;

    private Sale(Long id, List<Product> products, BigDecimal total, BigDecimal cashback, LocalDateTime createAt) {
        this.id = id;
        this.products = products;
        this.total = total;
        this.cashback = cashback;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getCashback() {
        return cashback;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Bean
    public static Sale valueOf(Long id, List<Product> products, BigDecimal total,
                               BigDecimal cashback, LocalDateTime createAt) {

        return new Sale(id, products, total, cashback, createAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) &&
                Objects.equals(products, sale.products) &&
                Objects.equals(total, sale.total) &&
                Objects.equals(cashback, sale.cashback) &&
                Objects.equals(createAt, sale.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, total, cashback, createAt);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", products=" + products +
                ", total=" + total +
                ", cashback=" + cashback +
                ", createAt=" + createAt +
                '}';
    }
}
