package com.techtest.bcnc.prices.domain.port.in;

import com.techtest.bcnc.prices.domain.models.Price;

import java.time.LocalDateTime;

public interface GetPriceUseCase {
    Price getApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
