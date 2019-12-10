package com.cashback.usecase.album;

import com.cashback.common.enums.Gender;
import com.cashback.common.exception.ResourceNotFoundException;
import com.cashback.repository.AlbumRepository;
import com.cashback.repository.entity.Album;
import com.cashback.usecase.album.find.FindAlbumImpl;
import com.cashback.usecase.album.search.repository.album.FindAlbumRequest;
import com.cashback.usecase.album.search.repository.album.FindAlbumResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class FindAlbumTest {

    @Mock
    private AlbumRepository albumRepositoryMocked;

    private FindAlbumImpl findAlbum;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.findAlbum = new FindAlbumImpl(albumRepositoryMocked);
    }

    @Test
    public void album_search_by_id_test() {

        Album albumMocked =  new Album();
        albumMocked.setId(1l);
        albumMocked.setGender(Gender.MPB);
        albumMocked.setArtists("Test");
        albumMocked.setPrice(BigDecimal.ONE);
        albumMocked.setTotalTracks(1);
        albumMocked.setName("Test");
        albumMocked.setReleaseDate("1992-10-21");

        Mockito.when(albumRepositoryMocked.findById(1l)).thenReturn(Optional.of(albumMocked));

        FindAlbumResponse albumResponse = findAlbum.execute(FindAlbumRequest.valueOf(1l));

        assertEquals(albumMocked.getId(), albumResponse.getAlbum().getId());
        assertEquals(albumMocked.getGender(), albumResponse.getAlbum().getGender());
        assertEquals(albumMocked.getName(), albumResponse.getAlbum().getName());
        assertEquals(albumMocked.getReleaseDate(), albumResponse.getAlbum().getReleaseDate());
        assertEquals(albumMocked.getTotalTracks(), albumResponse.getAlbum().getTotalTracks());
        assertEquals(albumMocked.getArtists(), albumResponse.getAlbum().getArtists());
        assertEquals(albumMocked.getPrice(), albumResponse.getAlbum().getPrice());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_exception_album_lookup_by_id() {

        Mockito.when(albumRepositoryMocked.findById(1l)).thenReturn(Optional.empty());

        findAlbum.execute(FindAlbumRequest.valueOf(1l));
    }
}
