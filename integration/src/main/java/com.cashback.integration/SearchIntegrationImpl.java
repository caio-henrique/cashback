package com.cashback.integration;

import com.cashback.integration.api.search.SearchSpotifyClient;
import com.cashback.integration.api.search.SearchSpotifyResponse;
import com.cashback.integration.api.search.representation.Artist;
import com.cashback.integration.api.search.representation.Item;
import com.cashback.integration.search.SearchIntegration;
import com.cashback.integration.search.SearchIntegrationRequest;
import com.cashback.integration.search.SearchIntegrationResponse;
import com.cashback.integration.search.representation.Album;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchIntegrationImpl implements SearchIntegration {

    private SearchSpotifyClient searchSpotifyClient;

    public SearchIntegrationImpl(SearchSpotifyClient searchSpotifyClient) {
        this.searchSpotifyClient = searchSpotifyClient;
    }

    @Override
    public SearchIntegrationResponse searchByGenre(SearchIntegrationRequest searchIntegrationRequest) {

        SearchSpotifyResponse searchSpotifyResponse = searchSpotifyClient.search(searchIntegrationRequest.getGender().name(),
                searchIntegrationRequest.getGender().name(), "album", searchIntegrationRequest.getLimit());
        return SearchIntegrationResponse.valueOf(this.buildAlbums(searchSpotifyResponse.getAlbums().getItems()));
    }

    private List<Album> buildAlbums(List<Item> items) {

        return items.parallelStream().map(item -> Album.valueOf(item.getName(), item.getReleaseDate(), item.getTotalTracks(),
                this.getArtistsNames(item.getArtists()))).collect(Collectors.toList());
    }

    private String getArtistsNames(List<Artist> artists) {

        return artists.parallelStream()
                .map(artist -> artist.getName())
                .collect(Collectors.joining(","));
    }
}
