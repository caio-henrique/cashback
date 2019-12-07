package com.cashback.usecase;

import com.cashback.common.enums.Gender;
import com.cashback.repository.AlbumRepository;
import com.cashback.usecase.album.CreateAlbumsImpl;
import com.cashback.usecase.album.creation.CreateAlbumsRequest;
import com.cashback.usecase.album.creation.representation.Album;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class CreateAlbumsTest {

    @Mock
    private AlbumRepository albumRepositoryMocked;

    private CreateAlbumsImpl createAlbumsImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.createAlbumsImpl = new CreateAlbumsImpl(albumRepositoryMocked);
    }

    @Test(expected = ConstraintViolationException.class)
    public void invalid_request_test_for_new_album_creation() {

        Album album = Album.valueOf(null, "2019-12-07", 10, "Test");
        CreateAlbumsRequest.valueOf(Collections.singletonList(album), Gender.CLASSIC);
    }

    @Test
    public void new_albums_creation_test() {

        Album album = Album.valueOf("Test", "2019-12-07", 10, "Test");
        CreateAlbumsRequest createAlbumsRequest = CreateAlbumsRequest.valueOf(Collections.singletonList(album), Gender.ROCK);
        ArgumentCaptor<List<com.cashback.repository.entity.Album>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        createAlbumsImpl.execute(createAlbumsRequest);

        verify(albumRepositoryMocked).deleteAllByGender(any(Gender.class));
        verify(albumRepositoryMocked).saveAll(argumentCaptor.capture());
        assertEquals(album.getName(), argumentCaptor.getValue().get(0).getName());
        assertEquals(album.getReleaseDate(), argumentCaptor.getValue().get(0).getReleaseDate());
        assertEquals(album.getTotalTracks(), argumentCaptor.getValue().get(0).getTotalTracks());
        assertEquals(album.getArtists(), argumentCaptor.getValue().get(0).getArtists());
        assertEquals(createAlbumsRequest.getGender(), argumentCaptor.getValue().get(0).getGender());
        assertNotNull(argumentCaptor.getValue().get(0).getPrice());
    }
}
