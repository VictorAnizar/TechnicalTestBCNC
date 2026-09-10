package com.techtest.bcnc.prices.infraestructure.in.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class PriceResponse {
    private Long productId;
    private Long brandId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
}
