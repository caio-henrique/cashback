package com.cashback.usecase.album;

import com.cashback.common.enums.Gender;
import com.cashback.repository.AlbumRepository;
import com.cashback.repository.entity.Album;
import com.cashback.usecase.album.find.FindAlbumsImpl;
import com.cashback.usecase.album.search.repository.albums.FindAlbumsRequest;
import com.cashback.usecase.album.search.repository.albums.FindAlbumsResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FindAlbumsTest {

    @Mock
    private AlbumRepository albumRepositoryMocked;

    private FindAlbumsImpl findAlbums;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.findAlbums = new FindAlbumsImpl(albumRepositoryMocked);
    }

    @Test
    public void albums_search_test() {

        Album albumMocked =  new Album();
        albumMocked.setId(1l);
        albumMocked.setGender(Gender.MPB);
        albumMocked.setArtists("Test");
        albumMocked.setPrice(BigDecimal.ONE);
        albumMocked.setTotalTracks(1);
        albumMocked.setName("Test");
        albumMocked.setReleaseDate("1992-10-21");
        PageImpl<Album> pageAlbumsMocked = new PageImpl<>(Arrays.asList(albumMocked),
                PageRequest.of(1, 1), 1l);
        Mockito.when(albumRepositoryMocked.findByGender(Mockito.any(Gender.class), Mockito.any(Pageable.class)))
                .thenReturn(pageAlbumsMocked);

        FindAlbumsResponse findAlbumsResponse = findAlbums.execute(FindAlbumsRequest.valueOf(Gender.CLASSIC,
                PageRequest.of(1, 1)));

        assertEquals(1, findAlbumsResponse.getContent().size());
        assertEquals(Integer.valueOf(1), findAlbumsResponse.getPageNumber());
        assertEquals(Integer.valueOf(1), findAlbumsResponse.getTotalElements());
        assertEquals(albumMocked.getId(), ((com.cashback.usecase.album.search.repository.albums.representation.Album)findAlbumsResponse.getContent().get(0)).getId());
        assertEquals(albumMocked.getName(), ((com.cashback.usecase.album.search.repository.albums.representation.Album)findAlbumsResponse.getContent().get(0)).getName());
        assertEquals(albumMocked.getReleaseDate(), ((com.cashback.usecase.album.search.repository.albums.representation.Album)findAlbumsResponse.getContent().get(0)).getReleaseDate());
        assertEquals(albumMocked.getTotalTracks(), ((com.cashback.usecase.album.search.repository.albums.representation.Album)findAlbumsResponse.getContent().get(0)).getTotalTracks());
        assertEquals(albumMocked.getArtists(), ((com.cashback.usecase.album.search.repository.albums.representation.Album)findAlbumsResponse.getContent().get(0)).getArtists());
        assertEquals(albumMocked.getPrice(), ((com.cashback.usecase.album.search.repository.albums.representation.Album)findAlbumsResponse.getContent().get(0)).getPrice());
        assertEquals(albumMocked.getGender(), ((com.cashback.usecase.album.search.repository.albums.representation.Album)findAlbumsResponse.getContent().get(0)).getGender());
    }

}
