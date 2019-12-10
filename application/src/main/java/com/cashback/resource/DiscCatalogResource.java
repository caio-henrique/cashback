package com.cashback.resource;

import com.cashback.common.enums.Gender;
import com.cashback.usecase.album.search.repository.album.FindAlbum;
import com.cashback.usecase.album.search.repository.album.FindAlbumRequest;
import com.cashback.usecase.album.search.repository.album.FindAlbumResponse;
import com.cashback.usecase.album.search.repository.albums.FindAlbums;
import com.cashback.usecase.album.search.repository.albums.FindAlbumsRequest;
import com.cashback.usecase.album.search.repository.albums.FindAlbumsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog/disc")
public class DiscCatalogResource {

    private FindAlbums findAlbums;
    private FindAlbum findAlbum;

    public DiscCatalogResource(FindAlbums findAlbums, FindAlbum findAlbum) {
        this.findAlbums = findAlbums;
        this.findAlbum = findAlbum;
    }

    @GetMapping
    public FindAlbumsResponse findAlbums(Gender gender, @PageableDefault(size = 10) Pageable pageable) {

        return findAlbums.execute(FindAlbumsRequest.valueOf(gender, pageable));
    }

    @GetMapping("/{identifier}")
    public FindAlbumResponse findAlbum(@PathVariable Long identifier) {

        return findAlbum.execute(FindAlbumRequest.valueOf(identifier));
    }
}
