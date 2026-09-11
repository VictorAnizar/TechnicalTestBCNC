package com.techtest.bcnc.prices.infraestructure.in.controllers;

import com.techtest.bcnc.prices.domain.port.in.GetPriceUseCase;
import com.techtest.bcnc.prices.domain.models.Price;
import com.techtest.bcnc.prices.infraestructure.in.dto.PriceResponse;
import com.techtest.bcnc.prices.infraestructure.in.mapper.PriceMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bcnc/api/v1/prices")
@Tag(name = "Prices", description = "API para consulta de precios aplicables")
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;

    private final PriceMapper mapper;

    public PriceController(GetPriceUseCase getPriceUseCase, PriceMapper mapper){
        this.getPriceUseCase=getPriceUseCase;
        this.mapper=mapper;
    }

    @GetMapping
    @Operation(summary = "Obtener precio aplicable a un producto", description = "Devuelve el precio de mayor prioridad para un producto, cadena y fecha dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Precio encontrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
            @ApiResponse(responseCode = "404", description = "No se encontró ningún precio aplicable para los criterios dados")
    })
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaAplicacion,
            @RequestParam("productId") Long idProd,
            @RequestParam("brandId")Long idBrand){
        Price price = getPriceUseCase.getApplicablePrice(fechaAplicacion, idProd, idBrand);
        return ResponseEntity.ok(mapper.toDto(price));
    }
}
