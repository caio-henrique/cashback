package com.cashback.usecase.album.search.repository.albums.representation;

import com.cashback.common.enums.Gender;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Album implements Serializable {

    private Long id;
    private String name;
    private String releaseDate;
    private Integer totalTracks;
    private String artists;
    private BigDecimal price;
    private Gender gender;

    private Album(Long id, String name, String releaseDate, Integer totalTracks, String artists,
                  BigDecimal price, Gender gender) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.totalTracks = totalTracks;
        this.artists = artists;
        this.price = price;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getTotalTracks() {
        return totalTracks;
    }

    public String getArtists() {
        return artists;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Gender getGender() {
        return gender;
    }

    @Bean
    public static Album valueOf(Long id, String name, String releaseDate, Integer totalTracks, String artists,
                                BigDecimal price, Gender gender) {
        return new Album(id, name, releaseDate, totalTracks, artists, price, gender);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) &&
                Objects.equals(name, album.name) &&
                Objects.equals(releaseDate, album.releaseDate) &&
                Objects.equals(totalTracks, album.totalTracks) &&
                Objects.equals(artists, album.artists) &&
                Objects.equals(price, album.price) &&
                gender == album.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, totalTracks, artists, price, gender);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", totalTracks=" + totalTracks +
                ", artists='" + artists + '\'' +
                ", price=" + price +
                ", gender=" + gender +
                '}';
    }
}
