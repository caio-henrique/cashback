package com.cashback.integration.search.representation;

import com.cashback.common.validator.ObjectValidator;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class Album implements Serializable {

    @NotBlank
    private String name;
    private String releaseDate;
    private Integer totalTracks;

    @NotBlank
    private String artists;

    private Album(String name, String releaseDate, Integer totalTracks, String artists) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.totalTracks = totalTracks;
        this.artists = artists;
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

    @Bean
    public static Album valueOf(String name, String releaseDate, Integer totalTracks, String artists) {

        Album album = new Album(name, releaseDate, totalTracks, artists);
//        IntegrationObjectValidator.validate(ValidationCodes.ERROR_RESPONSE_INTEGRATION_ADDRESS_MIGRATION,
//                addressMigrationIntegrationResponse);
        ObjectValidator.validate(album);
        return album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(name, album.name) &&
                Objects.equals(releaseDate, album.releaseDate) &&
                Objects.equals(totalTracks, album.totalTracks) &&
                Objects.equals(artists, album.artists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseDate, totalTracks, artists);
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", totalTracks=" + totalTracks +
                ", artists='" + artists + '\'' +
                '}';
    }
}
