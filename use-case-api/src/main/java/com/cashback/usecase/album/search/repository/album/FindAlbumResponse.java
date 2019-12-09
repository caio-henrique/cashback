package com.cashback.usecase.album.search.repository.album;

import com.cashback.usecase.album.search.repository.album.representation.Album;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.Objects;

public class FindAlbumResponse implements Serializable {

    private Album album;

    private FindAlbumResponse(Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    @Bean
    public static FindAlbumResponse valueOf(Album album) {

        return new FindAlbumResponse(album);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindAlbumResponse that = (FindAlbumResponse) o;
        return Objects.equals(album, that.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(album);
    }

    @Override
    public String toString() {
        return "FindAlbumResponse{" +
                "album=" + album +
                '}';
    }
}
