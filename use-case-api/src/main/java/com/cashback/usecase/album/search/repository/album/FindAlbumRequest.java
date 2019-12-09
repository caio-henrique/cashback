package com.cashback.usecase.album.search.repository.album;

import com.cashback.common.validator.ObjectValidator;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class FindAlbumRequest implements Serializable {

    @NotNull
    private Long identifier;

    private FindAlbumRequest(Long identifier) {
        this.identifier = identifier;
    }

    public Long getIdentifier() {
        return identifier;
    }

    @Bean
    public static FindAlbumRequest valueOf(Long identifier) {

        FindAlbumRequest findAlbumRequest = new FindAlbumRequest(identifier);
        ObjectValidator.validate(findAlbumRequest);
        return findAlbumRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindAlbumRequest that = (FindAlbumRequest) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return "FindAlbumRequest{" +
                "identifier=" + identifier +
                '}';
    }
}
