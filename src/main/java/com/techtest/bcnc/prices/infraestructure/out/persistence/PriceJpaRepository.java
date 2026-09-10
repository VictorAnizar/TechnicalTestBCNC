package com.techtest.bcnc.prices.infraestructure.out.persistence;

import com.techtest.bcnc.prices.infraestructure.out.persistence.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {
    @Query(value = """
        SELECT * FROM PRICES
        WHERE BRAND_ID = :brandId
          AND PRODUCT_ID = :productId
          AND :applicationDate BETWEEN START_DATE AND END_DATE
        ORDER BY PRIORITY DESC
        LIMIT 1
    """, nativeQuery = true)
    Optional<PriceEntity> findApplicablePrice(
            @Param("applicationDate") LocalDateTime applicationDate,
            @Param("productId") Long productId,
            @Param("brandId") Long brandId
    );
}
