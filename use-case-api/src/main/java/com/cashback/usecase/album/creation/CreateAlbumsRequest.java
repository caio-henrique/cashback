package com.cashback.usecase.album.creation;

import com.cashback.common.enums.Gender;
import com.cashback.usecase.album.creation.representation.Album;
import com.cashback.common.validator.ObjectValidator;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CreateAlbumsRequest implements Serializable {

    @NotNull
    private List<Album> albums;

    @NotNull
    private Gender gender;

    private CreateAlbumsRequest(List<Album> albums, Gender gender) {
        this.albums = albums;
        this.gender = gender;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public Gender getGender() {
        return gender;
    }

    @Bean
    public static CreateAlbumsRequest valueOf(List<Album> albums, Gender gender) {

        CreateAlbumsRequest createAlbumsRequest = new CreateAlbumsRequest(albums, gender);
        ObjectValidator.validate(createAlbumsRequest);
        return createAlbumsRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAlbumsRequest that = (CreateAlbumsRequest) o;
        return Objects.equals(albums, that.albums) &&
                gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(albums, gender);
    }

    @Override
    public String toString() {
        return "CreateAlbumsRequest{" +
                "albums=" + albums +
                ", gender=" + gender +
                '}';
    }
}
