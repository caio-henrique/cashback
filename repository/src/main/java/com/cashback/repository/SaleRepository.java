package com.cashback.repository;

import com.cashback.repository.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    Page<Sale> findByCreateAtBetween(LocalDateTime initialDate, LocalDateTime finalDate, Pageable pageable);
}
