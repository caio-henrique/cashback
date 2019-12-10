package com.cashback.usecase.album.find;

import com.cashback.common.exception.ResourceNotFoundException;
import com.cashback.repository.AlbumRepository;
import com.cashback.repository.entity.Album;
import com.cashback.usecase.album.search.repository.album.FindAlbum;
import com.cashback.usecase.album.search.repository.album.FindAlbumRequest;
import com.cashback.usecase.album.search.repository.album.FindAlbumResponse;
import org.springframework.stereotype.Service;

@Service
public class FindAlbumImpl implements FindAlbum {

    private AlbumRepository albumRepository;

    public FindAlbumImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public FindAlbumResponse execute(FindAlbumRequest findAlbumRequest) {

        Album album = albumRepository.findById(findAlbumRequest.getIdentifier())
                .orElseThrow(() -> new ResourceNotFoundException());

        return FindAlbumResponse.valueOf(com.cashback.usecase.album.search.repository.album.representation.Album
                .valueOf(album.getId(), album.getName(), album.getReleaseDate(), album.getTotalTracks(),
                        album.getArtists(), album.getPrice(), album.getGender()));
    }
}
