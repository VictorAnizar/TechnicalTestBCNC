--Se crea la tabla de precios, ningun valor puede ser nulo
CREATE TABLE PRICES (
    BRAND_ID INT NOT NULL, --foreign key de la cadena del grupo (1 = ZARA).
    START_DATE TIMESTAMP NOT NULL, --rango de fechas en el que aplica el precio tarifa indicado.
    END_DATE TIMESTAMP NOT NULL, --rango de fechas en el que aplica el precio tarifa indicado.
    PRICE_LIST INT NOT NULL, --Identificador de la tarifa de precios aplicable.
    PRODUCT_ID INT NOT NULL, --Identificador código de producto.
    PRIORITY INT NOT NULL, --Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
    PRICE DECIMAL(10, 2) NOT NULL, --precio final de venta.
    CURR VARCHAR(3) NOT NULL, --iso de la moneda.
    PRIMARY KEY (PRICE_LIST)
);