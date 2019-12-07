package com.cashback.usecase;

import com.cashback.common.enums.Gender;
import com.cashback.integration.search.SearchIntegration;
import com.cashback.integration.search.SearchIntegrationRequest;
import com.cashback.integration.search.SearchIntegrationResponse;
import com.cashback.integration.search.representation.Album;
import com.cashback.usecase.album.SearchAlbumsSpotifyImpl;
import com.cashback.usecase.album.search.spotify.SearchAlbumsSpotifyRequest;
import com.cashback.usecase.album.search.spotify.SearchAlbumsSpotifyResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SearchAlbumsSpotifyTest {

    @Mock
    private SearchIntegration searchIntegrationMocked;

    private SearchAlbumsSpotifyImpl searchAlbumsSpotify;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.searchAlbumsSpotify = new SearchAlbumsSpotifyImpl(searchIntegrationMocked);
    }

    @Test
    public void album_search_test() {

        Album albumMocked = Album.valueOf("Test", "2019-12-07", 10, "Test");
        SearchIntegrationResponse searchIntegrationResponseMocked = SearchIntegrationResponse.valueOf(
                Collections.singletonList(albumMocked));
        SearchIntegrationRequest searchIntegrationRequestMocked = SearchIntegrationRequest.valueOf(
                Gender.ROCK, 50);
        Mockito.when(searchIntegrationMocked.searchByGenre(searchIntegrationRequestMocked))
                .thenReturn(searchIntegrationResponseMocked);

        SearchAlbumsSpotifyResponse searchAlbumsSpotifyResponse = searchAlbumsSpotify.execute(
                SearchAlbumsSpotifyRequest.valueOf(Gender.ROCK));

        assertEquals(1, searchAlbumsSpotifyResponse.getAlbums().size());
        assertEquals(albumMocked.getName(), searchAlbumsSpotifyResponse.getAlbums().get(0).getName());
        assertEquals(albumMocked.getReleaseDate(), searchAlbumsSpotifyResponse.getAlbums().get(0).getReleaseDate());
        assertEquals(albumMocked.getTotalTracks(), searchAlbumsSpotifyResponse.getAlbums().get(0).getTotalTracks());
        assertEquals(albumMocked.getArtists(), searchAlbumsSpotifyResponse.getAlbums().get(0).getArtists());
    }
}
