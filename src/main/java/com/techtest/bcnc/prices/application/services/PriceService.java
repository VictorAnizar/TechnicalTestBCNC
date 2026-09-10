package com.techtest.bcnc.prices.application.services;

import com.techtest.bcnc.prices.domain.port.in.GetPriceUseCase;
import com.techtest.bcnc.prices.domain.models.Price;
import com.techtest.bcnc.prices.domain.port.out.PriceRepositoryPort;
import com.techtest.bcnc.prices.domain.exceptons.PriceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PriceService implements GetPriceUseCase {

    @Autowired
    PriceRepositoryPort priceRepositoryPort;

    @Override
    public Price getApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepositoryPort.findPrice(applicationDate, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException("No se encontró el precio con los parametros proporcionados :("));
    }
}
