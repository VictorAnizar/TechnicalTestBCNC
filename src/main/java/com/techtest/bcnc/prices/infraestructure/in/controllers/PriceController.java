package com.techtest.bcnc.prices.infraestructure.in.controllers;

import com.techtest.bcnc.prices.domain.port.in.GetPriceUseCase;
import com.techtest.bcnc.prices.domain.models.Price;
import com.techtest.bcnc.prices.infraestructure.in.dto.PriceResponse;
import com.techtest.bcnc.prices.infraestructure.in.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bcnc/api/v1/prices")
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;

    private final PriceMapper mapper;

    public PriceController(GetPriceUseCase getPriceUseCase, PriceMapper mapper){
        this.getPriceUseCase=getPriceUseCase;
        this.mapper=mapper;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaAplicacion,
            @RequestParam("productId") Long idProd,
            @RequestParam("brandId")Long idBrand){
        Price price = getPriceUseCase.getApplicablePrice(fechaAplicacion, idProd, idBrand);
        return ResponseEntity.ok(mapper.toDto(price));
    }
}
