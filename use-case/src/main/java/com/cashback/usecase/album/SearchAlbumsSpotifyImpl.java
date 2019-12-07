package com.cashback.usecase.album;

import com.cashback.integration.search.SearchIntegration;
import com.cashback.integration.search.SearchIntegrationRequest;
import com.cashback.integration.search.SearchIntegrationResponse;
import com.cashback.integration.search.representation.Album;
import com.cashback.usecase.album.search.spotify.SearchAlbumsSpotify;
import com.cashback.usecase.album.search.spotify.SearchAlbumsSpotifyRequest;
import com.cashback.usecase.album.search.spotify.SearchAlbumsSpotifyResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchAlbumsSpotifyImpl implements SearchAlbumsSpotify {

    private SearchIntegration searchIntegration;

    public SearchAlbumsSpotifyImpl(SearchIntegration searchIntegration) {
        this.searchIntegration = searchIntegration;
    }

    @Override
    public SearchAlbumsSpotifyResponse execute(SearchAlbumsSpotifyRequest searchAlbumsSpotifyRequest) {

        SearchIntegrationResponse searchIntegrationResponse = searchIntegration.searchByGenre(
                SearchIntegrationRequest.valueOf(searchAlbumsSpotifyRequest.getGender(), 50));
        return SearchAlbumsSpotifyResponse.valueOf(this.buildAlbums(searchIntegrationResponse.getAlbums()));
    }

    private List<com.cashback.usecase.album.search.spotify.representation.Album> buildAlbums(List<Album> albums) {

        return albums.parallelStream().map(album -> com.cashback.usecase.album.search.spotify.representation.Album.valueOf(
                album.getName(), album.getReleaseDate(), album.getTotalTracks(), album.getArtists()))
                .collect(Collectors.toList());
    }
}
