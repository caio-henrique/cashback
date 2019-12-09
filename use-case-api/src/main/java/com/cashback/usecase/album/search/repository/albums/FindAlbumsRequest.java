package com.cashback.usecase.album.search.repository.albums;

import com.cashback.common.enums.Gender;
import com.cashback.common.validator.ObjectValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class FindAlbumsRequest implements Serializable {

    @NotNull
    private Gender gender;

    @NotNull
    private Pageable pageable;

    private FindAlbumsRequest(Gender gender, Pageable pageable) {
        this.gender = gender;
        this.pageable = pageable;
    }

    public Gender getGender() {
        return gender;
    }

    public Pageable getPageable() {
        return pageable;
    }

    @Bean
    public static FindAlbumsRequest valueOf(Gender gender, Pageable pageable) {

        FindAlbumsRequest findAlbumsRequest = new FindAlbumsRequest(gender, pageable);
        ObjectValidator.validate(findAlbumsRequest);
        return findAlbumsRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindAlbumsRequest that = (FindAlbumsRequest) o;
        return gender == that.gender &&
                Objects.equals(pageable, that.pageable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, pageable);
    }

    @Override
    public String toString() {
        return "FindAlbumsRequest{" +
                "gender=" + gender +
                ", pageable=" + pageable +
                '}';
    }
}
