package com.cashback.processsor;

import com.cashback.common.enums.Gender;
import com.cashback.usecase.album.creation.CreateAlbums;
import com.cashback.usecase.album.creation.CreateAlbumsRequest;
import com.cashback.usecase.album.creation.representation.Album;
import com.cashback.usecase.album.search.spotify.SearchAlbumsSpotify;
import com.cashback.usecase.album.search.spotify.SearchAlbumsSpotifyRequest;
import com.cashback.usecase.album.search.spotify.SearchAlbumsSpotifyResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenerateAlbumCatalog {

    private SearchAlbumsSpotify searchAlbumsSpotify;
    private CreateAlbums createAlbums;

    public GenerateAlbumCatalog(SearchAlbumsSpotify searchAlbumsSpotify, CreateAlbums createAlbums) {
        this.searchAlbumsSpotify = searchAlbumsSpotify;
        this.createAlbums = createAlbums;
    }

    @Scheduled(fixedDelay = 60000*60)
    public void execute() {

        EnumSet<Gender> genderEnumSet = EnumSet.allOf(Gender.class);
        genderEnumSet.forEach(gender ->  {

            final SearchAlbumsSpotifyResponse searchAlbumsSpotifyResponse = searchAlbumsSpotify.execute(
                    SearchAlbumsSpotifyRequest.valueOf(gender));
            createAlbums.execute(CreateAlbumsRequest.valueOf(this.buildAlbumsForRequestCreation(
                    searchAlbumsSpotifyResponse.getAlbums()), gender));
        });
    }

    private List<Album> buildAlbumsForRequestCreation(
            List<com.cashback.usecase.album.search.spotify.representation.Album> albums) {

        return albums.parallelStream()
                .map(album -> Album.valueOf(album.getName(), album.getReleaseDate(),
                        album.getTotalTracks(), album.getArtists()))
                .collect(Collectors.toList());
    }
}
