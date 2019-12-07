package com.cashback.usecase.album.search.spotify;

import com.cashback.usecase.album.search.spotify.representation.Album;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SearchAlbumsSpotifyResponse implements Serializable {

    private List<Album> albums;

    private SearchAlbumsSpotifyResponse(List<Album> albums) {
        this.albums = albums;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    @Bean
    public static SearchAlbumsSpotifyResponse valueOf(List<Album> albums) {

        return new SearchAlbumsSpotifyResponse(albums);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchAlbumsSpotifyResponse that = (SearchAlbumsSpotifyResponse) o;
        return Objects.equals(albums, that.albums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albums);
    }

    @Override
    public String toString() {
        return "SearchAlbumsSpotifyResponse{" +
                "albums=" + albums +
                '}';
    }
}
