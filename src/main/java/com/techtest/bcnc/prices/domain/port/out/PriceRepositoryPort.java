package com.techtest.bcnc.prices.domain.port.out;

import com.techtest.bcnc.prices.domain.models.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<Price> findPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
