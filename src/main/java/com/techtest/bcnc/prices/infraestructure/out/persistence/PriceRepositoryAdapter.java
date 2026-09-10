package com.techtest.bcnc.prices.infraestructure.out.persistence;

import com.techtest.bcnc.prices.domain.models.Price;
import com.techtest.bcnc.prices.domain.port.out.PriceRepositoryPort;
import com.techtest.bcnc.prices.infraestructure.out.persistence.mapper.PricePersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    @Autowired
    PriceJpaRepository priceJpaRepository;

    @Autowired
    PricePersistenceMapper pricePersistenceMapper;

    @Override
    public Optional<Price> findPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceJpaRepository.findApplicablePrice(applicationDate, productId, brandId)
                .map(pricePersistenceMapper::toDomain);
    }

}
