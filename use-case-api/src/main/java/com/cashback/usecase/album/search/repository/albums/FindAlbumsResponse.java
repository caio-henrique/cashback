package com.cashback.usecase.album.search.repository.albums;

import com.cashback.usecase.album.search.repository.albums.representation.Album;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Objects;

public class FindAlbumsResponse implements Serializable {

    private Page<Album> albums;

    private FindAlbumsResponse(Page<Album> albums) {
        this.albums = albums;
    }

    public Page<Album> getAlbums() {
        return albums;
    }

    @Bean
    public static FindAlbumsResponse valueOf(Page<Album> albums) {

        return new FindAlbumsResponse(albums);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindAlbumsResponse that = (FindAlbumsResponse) o;
        return Objects.equals(albums, that.albums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albums);
    }

    @Override
    public String toString() {
        return "FindAlbumsResponse{" +
                "albums=" + albums +
                '}';
    }
}
