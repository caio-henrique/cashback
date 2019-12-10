package com.cashback.usecase.sale.create;

import com.cashback.common.enums.Gender;
import com.cashback.common.exception.GenericBusinessException;
import com.cashback.repository.AlbumRepository;
import com.cashback.repository.entity.Album;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindAlbumsTest {

    @Mock
    private AlbumRepository albumRepository;

    private FindAlbumsImpl findAlbums;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.findAlbums = new FindAlbumsImpl(albumRepository);
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

        Mockito.when(albumRepository.findAllById(Mockito.any(List.class)))
                .thenReturn(Collections.singletonList(albumMocked));

        List<Album> albumList = findAlbums.execute(Collections.singletonList(1L));

        assertEquals(1, albumList.size());
        assertEquals(albumMocked.getId(), albumList.get(0).getId());
        assertEquals(albumMocked.getGender(), albumList.get(0).getGender());
        assertEquals(albumMocked.getArtists(), albumList.get(0).getArtists());
        assertEquals(albumMocked.getPrice(), albumList.get(0).getPrice());
        assertEquals(albumMocked.getTotalTracks(), albumList.get(0).getTotalTracks());
        assertEquals(albumMocked.getName(), albumList.get(0).getName());
        assertEquals(albumMocked.getReleaseDate(), albumList.get(0).getReleaseDate());
    }

    @Test(expected = GenericBusinessException.class)
    public void invalid_product_codes_test() {

        Mockito.when(albumRepository.findAllById(Mockito.any(List.class))).thenReturn(Collections.emptyList());

        findAlbums.execute(Arrays.asList(1L));
    }

    @Test(expected = GenericBusinessException.class)
    public void empity_product_codes_test() {

        findAlbums.execute(Collections.emptyList());
    }
}
