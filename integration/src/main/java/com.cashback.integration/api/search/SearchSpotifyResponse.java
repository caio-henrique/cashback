package com.cashback.integration.api.search;

import com.cashback.integration.api.search.representation.Album;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchSpotifyResponse implements Serializable {

    private Album albums;

    @JsonCreator
    public SearchSpotifyResponse(@JsonProperty("albums") Album albums) {
        this.albums = albums;
    }

    public Album getAlbums() {
        return albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchSpotifyResponse that = (SearchSpotifyResponse) o;
        return Objects.equals(albums, that.albums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albums);
    }

    @Override
    public String toString() {
        return "SearchSpotifyResponse{" +
                "albums=" + albums +
                '}';
    }
}
