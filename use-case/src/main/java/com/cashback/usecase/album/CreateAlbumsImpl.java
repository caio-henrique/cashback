package com.cashback.usecase.album;

import com.cashback.common.enums.Gender;
import com.cashback.repository.AlbumRepository;
import com.cashback.repository.entity.Album;
import com.cashback.usecase.album.creation.CreateAlbums;
import com.cashback.usecase.album.creation.CreateAlbumsRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateAlbumsImpl implements CreateAlbums {

    private AlbumRepository albumRepository;

    public CreateAlbumsImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    @Transactional
    public void execute(CreateAlbumsRequest createAlbumsRequest) {

        albumRepository.deleteAllByGender(createAlbumsRequest.getGender());
        albumRepository.saveAll(this.buildEntityAlbums(createAlbumsRequest.getAlbums(),
                createAlbumsRequest.getGender()));
    }

    private List<Album>  buildEntityAlbums(List<com.cashback.usecase.album.creation.representation.Album> albums,
                                           Gender gender) {

        return albums.parallelStream().map(album -> {

            Album entityAlbum = new Album();
            entityAlbum.setName(album.getName());
            entityAlbum.setReleaseDate(album.getReleaseDate());
            entityAlbum.setTotalTracks(album.getTotalTracks());
            entityAlbum.setArtists(album.getArtists());
            entityAlbum.setGender(gender);
            entityAlbum.setPrice(new BigDecimal(10.0 * Math.random()));
            return entityAlbum;
        }).collect(Collectors.toList());
    }
}
