package com.cashback.usecase.sale.find;

import com.cashback.common.validator.ObjectValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FindSalesRequest implements Serializable {

    @NotNull
    private LocalDateTime initialDate;

    @NotNull
    private LocalDateTime finalDate;

    @NotNull
    private Pageable pageable;

    private FindSalesRequest(LocalDateTime initialDate, LocalDateTime finalDate, Pageable pageable) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.pageable = pageable;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public Pageable getPageable() {
        return pageable;
    }

    @Bean
    public static FindSalesRequest valueOf(LocalDateTime initialDate, LocalDateTime finalDate, Pageable pageable) {

        FindSalesRequest findSalesRequest = new FindSalesRequest(initialDate, finalDate, pageable);
        ObjectValidator.validate(findSalesRequest);
        return findSalesRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindSalesRequest that = (FindSalesRequest) o;
        return Objects.equals(initialDate, that.initialDate) &&
                Objects.equals(finalDate, that.finalDate) &&
                Objects.equals(pageable, that.pageable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initialDate, finalDate, pageable);
    }

    @Override
    public String toString() {
        return "FindSalesRequest{" +
                "initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                ", pageable=" + pageable +
                '}';
    }
}
