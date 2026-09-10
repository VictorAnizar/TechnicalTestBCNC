package com.techtest.bcnc.prices.infraestructure.in.mapper;

import com.techtest.bcnc.prices.domain.models.Price;
import com.techtest.bcnc.prices.infraestructure.in.dto.PriceResponse;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {
    public PriceResponse toDto(Price price){
        if(price==(null)) return null;

        return new PriceResponse(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice()
        );
    }
}
