package com.cashback.repository;

import com.cashback.common.enums.Gender;
import com.cashback.repository.entity.Cashback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashbackRepository extends JpaRepository<Cashback, Long> {

    Optional<Cashback> findByGender(Gender gender);
}
