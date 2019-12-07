package com.cashback.usecase.album.search.spotify;

import com.cashback.common.enums.Gender;
import com.cashback.common.validator.ObjectValidator;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class SearchAlbumsSpotifyRequest implements Serializable {

    @NotNull
    private Gender gender;

    private SearchAlbumsSpotifyRequest(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    @Bean
    public static SearchAlbumsSpotifyRequest valueOf(Gender gender) {

        SearchAlbumsSpotifyRequest searchAlbumsSpotifyRequest = new SearchAlbumsSpotifyRequest(gender);
        ObjectValidator.validate(searchAlbumsSpotifyRequest);
        return searchAlbumsSpotifyRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchAlbumsSpotifyRequest that = (SearchAlbumsSpotifyRequest) o;
        return gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender);
    }

    @Override
    public String toString() {
        return "SearchAlbumsSpotifyRequest{" +
                "gender=" + gender +
                '}';
    }
}
