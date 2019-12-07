package com.cashback.integration.api.search.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item implements Serializable {

    private String id;
    private String albumType;
    private List<Artist> artists;
    private String name;
    private String releaseDate;
    private String releaseDatePrecision;
    private Integer totalTracks;

    @JsonCreator
    public Item(@JsonProperty("id") String id, @JsonProperty("album_type") String albumType,
                @JsonProperty("artists") List<Artist> artists, @JsonProperty("name") String name,
                @JsonProperty("release_date") String releaseDate,
                @JsonProperty("release_date_precision") String releaseDatePrecision,
                @JsonProperty("total_tracks") Integer totalTracks) {
        this.id = id;
        this.albumType = albumType;
        this.artists = artists;
        this.name = name;
        this.releaseDate = releaseDate;
        this.releaseDatePrecision = releaseDatePrecision;
        this.totalTracks = totalTracks;
    }

    public String getId() {
        return id;
    }

    public String getAlbumType() {
        return albumType;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getReleaseDatePrecision() {
        return releaseDatePrecision;
    }

    public Integer getTotalTracks() {
        return totalTracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(albumType, item.albumType) &&
                Objects.equals(artists, item.artists) &&
                Objects.equals(name, item.name) &&
                Objects.equals(releaseDate, item.releaseDate) &&
                Objects.equals(releaseDatePrecision, item.releaseDatePrecision) &&
                Objects.equals(totalTracks, item.totalTracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumType, artists, name, releaseDate, releaseDatePrecision, totalTracks);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", albumType='" + albumType + '\'' +
                ", artists=" + artists +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", releaseDatePrecision='" + releaseDatePrecision + '\'' +
                ", totalTracks=" + totalTracks +
                '}';
    }
}
