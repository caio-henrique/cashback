package com.cashback.repository;

import com.cashback.common.enums.Gender;
import com.cashback.repository.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    void deleteAllByGender(Gender gender);

    Page<Album> findByGender(Gender gender, Pageable pageable);
}
