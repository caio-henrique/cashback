package com.cashback.usecase.sale.create;

import com.cashback.common.exception.GenericBusinessException;
import com.cashback.repository.AlbumRepository;
import com.cashback.repository.entity.Album;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("findAlbumsSaleImpl")
class FindAlbumsImpl {

    private AlbumRepository albumRepository;

    public FindAlbumsImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> execute(List<Long> productIds) {

        this.productIdIsValid(productIds);
        List<Album> albums = albumRepository.findAllById(productIds);
        this.albunsIsValid(productIds, albums);

        return albums;
    }

    private Boolean productIdIsValid(List<Long> productIds) {
        if(productIds.isEmpty())
            throw new GenericBusinessException("error-invalid-product-codes");

        return Boolean.TRUE;
    }

    private Boolean albunsIsValid(List<Long> productIds, List<Album> albums) {
        if(productIds.size() != albums.size())
            throw new GenericBusinessException("error-invalid-product-codes");

        return Boolean.TRUE;
    }
}
