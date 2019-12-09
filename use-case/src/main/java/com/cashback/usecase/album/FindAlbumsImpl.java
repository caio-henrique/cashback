package com.cashback.usecase.album;

import com.cashback.repository.AlbumRepository;
import com.cashback.repository.entity.Album;
import com.cashback.usecase.album.search.repository.albums.FindAlbums;
import com.cashback.usecase.album.search.repository.albums.FindAlbumsRequest;
import com.cashback.usecase.album.search.repository.albums.FindAlbumsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAlbumsImpl implements FindAlbums {

    private AlbumRepository albumRepository;

    public FindAlbumsImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public FindAlbumsResponse execute(FindAlbumsRequest findAlbumsRequest) {
        Page<Album> albums = albumRepository.findByGender(findAlbumsRequest.getGender(), findAlbumsRequest.getPageable());
        return FindAlbumsResponse.valueOf(this.buildPageAlbums(albums));
    }

    private Page<com.cashback.usecase.album.search.repository.albums.representation.Album> buildPageAlbums(Page<Album> albums) {

        List<com.cashback.usecase.album.search.repository.albums.representation.Album> collect = albums.get().parallel().map(
                album -> com.cashback.usecase.album.search.repository.albums.representation.Album.valueOf(
                album.getId(), album.getName(), album.getReleaseDate(), album.getTotalTracks(), album.getArtists(),
                album.getPrice(), album.getGender()))
                .collect(Collectors.toList());

        return new PageImpl(collect, albums.getPageable(), albums.getTotalElements());
    }
}
