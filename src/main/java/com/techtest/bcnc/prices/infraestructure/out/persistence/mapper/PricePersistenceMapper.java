package com.techtest.bcnc.prices.infraestructure.out.persistence.mapper;

import com.techtest.bcnc.prices.domain.models.Price;
import com.techtest.bcnc.prices.infraestructure.out.persistence.entities.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public class PricePersistenceMapper {
    public Price toDomain(PriceEntity priceEntity) {
        if (priceEntity == null) return null;
        return new Price(
                priceEntity.getBrandId(),
                priceEntity.getStartDate(),
                priceEntity.getEndDate(),
                priceEntity.getPriceList(),
                priceEntity.getProductId(),
                priceEntity.getPriority(),
                priceEntity.getPrice(),
                priceEntity.getCurrency()
        );
    }
}
