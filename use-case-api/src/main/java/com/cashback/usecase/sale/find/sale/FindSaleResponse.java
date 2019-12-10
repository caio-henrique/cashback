package com.cashback.usecase.sale.find.sale;

import com.cashback.usecase.sale.find.sale.representation.Product;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class FindSaleResponse implements Serializable {

    private Long id;
    private List<Product> products;
    private BigDecimal total;
    private BigDecimal cashback;
    private LocalDateTime createAt;

    private FindSaleResponse(Long id, List<Product> products, BigDecimal total, BigDecimal cashback, LocalDateTime createAt) {
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
    public static FindSaleResponse valueOf(Long id, List<Product> products, BigDecimal total,
                                           BigDecimal cashback, LocalDateTime createAt) {

        return new FindSaleResponse(id, products, total, cashback, createAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindSaleResponse that = (FindSaleResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(products, that.products) &&
                Objects.equals(total, that.total) &&
                Objects.equals(cashback, that.cashback) &&
                Objects.equals(createAt, that.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, total, cashback, createAt);
    }

    @Override
    public String toString() {
        return "FindSaleResponse{" +
                "id=" + id +
                ", products=" + products +
                ", total=" + total +
                ", cashback=" + cashback +
                ", createAt=" + createAt +
                '}';
    }
}
