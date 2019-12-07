package com.cashback.integration.search;

import com.cashback.common.enums.Gender;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.Objects;

public class SearchIntegrationRequest implements Serializable {

    private Gender gender;
    private Integer limit;

    private SearchIntegrationRequest(Gender gender, Integer limit) {
        this.gender = gender;
        this.limit = limit;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getLimit() {
        return limit;
    }

    @Bean
    public static SearchIntegrationRequest valueOf(Gender gender, Integer limit) {

        SearchIntegrationRequest searchIntegrationRequest = new SearchIntegrationRequest(gender, limit);
        return searchIntegrationRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchIntegrationRequest that = (SearchIntegrationRequest) o;
        return gender == that.gender &&
                Objects.equals(limit, that.limit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, limit);
    }

    @Override
    public String toString() {
        return "SearchIntegrationRequest{" +
                "gender=" + gender +
                ", limit=" + limit +
                '}';
    }
}
