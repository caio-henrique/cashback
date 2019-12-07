package com.cashback.integration.search;

import com.cashback.common.validator.ObjectValidator;
import com.cashback.integration.search.representation.Album;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SearchIntegrationResponse implements Serializable {

    @NotNull
    private List<Album> albums;

    private SearchIntegrationResponse(List<Album> albums) {
        this.albums = albums;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    @Bean
    public static SearchIntegrationResponse valueOf(List<Album> albums) {

        SearchIntegrationResponse searchIntegrationResponse = new SearchIntegrationResponse(albums);
        ObjectValidator.validate(searchIntegrationResponse);
        return searchIntegrationResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchIntegrationResponse that = (SearchIntegrationResponse) o;
        return Objects.equals(albums, that.albums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albums);
    }

    @Override
    public String toString() {
        return "SearchIntegrationResponse{" +
                "albums=" + albums +
                '}';
    }
}
