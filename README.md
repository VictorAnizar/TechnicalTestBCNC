# Prueba Técnica BCNC

API REST desarrollada con Spring Boot y Java 21 siguiendo los principios de **Arquitectura Hexagonal (Puertos y Adaptadores)** y **Vertical Slicing** para la consulta de tarifas aplicables a productos según la cadena de venta y la fecha de aplicación.

---

## Tabla de Contenidos

1. [Tecnologías Utilizadas](#-tecnologías-utilizadas)
2. [Arquitectura del Proyecto](#-arquitectura-del-proyecto)
3. [Instalación y Configuración](#-instalación-y-configuración)
4. [Ejecución de la Aplicación](#-ejecución-de-la-aplicación)
5. [Pruebas (Testing)](#-pruebas-testing)
6. [Documentación Técnica (OpenAPI / Postman)](#-documentación-técnica-openapi--postman)

---

## Tecnologías Utilizadas

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA)
* **Base de Datos:** H2 Database (en memoria)
* **Pruebas:** JUnit 5, Mockito, MockMvc
* **Herramientas de Construcción:** Maven Wrapper (`./mvnw`)
* **Documentación & Mapeo:** OpenAPI 3.0 / Swagger UI, Lombok

---

## Arquitectura del Proyecto

El proyecto está estructurado bajo **Arquitectura Hexagonal** y organizado mediante **Vertical Slicing**. Esto permite que el núcleo del dominio permanezca 100% aislado de frameworks, bases de datos o clientes HTTP.

### Estructura de Directorios

        src/main/java/com/techtest/bcnc/prices/
        |-> domain/
        |   |--> models/
        |   |--> exceptions/
        |   |--> port/                    # Puertos
        |       |--> in/
        |       |--> out/
        |
        |--> application/
        |   |--> services/
        |
        |--> infraestructure/             # Adaptadores
             |--> in/
             |  ├--> controllers/
             |  ├--> dto/
             |  |--> mapper/
             |--> out/
                |--> persistence/

---

## Instalación y Configuración

### Prerrequisitos
* **Java JDK 21** instalado y configurado en las variables de entorno del sistema (`JAVA_HOME`).
* **Git** para la gestión del repositorio local y remoto.

### Pasos de Configuración Inicial

1. **Clonar el repositorio:**
   git clone https://github.com/VictorAnizar/TechnicalTestBCNC.git
   cd TechnicalTestBCNC

   2. **Configuración de Base de Datos H2 (`application.properties`):**
      La base de datos se ejecuta en memoria y se inicializa automáticamente al arrancar con las tablas y registros requeridos (`table.sql` e `import.sql`):
      ```
      spring.application.name=bcnc
      spring.datasource.url=jdbc:h2:mem:prices;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
      spring.datasource.driverClassName=org.h2.Driver
      spring.datasource.username=sa
      spring.datasource.password=
      spring.h2.console.enabled=true
      spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
      spring.jpa.hibernate.ddl-auto=create-drop
      spring.jpa.defer-datasource-initialization=true
      spring.sql.init.mode=always
      ```

---

## Ejecución de la Aplicación

Para iniciar el servidor local con Maven Wrapper:

En Windows:
mvnw.cmd spring-boot:run

En Linux / macOS:
./mvnw spring-boot:run

La aplicación arrancará en `http://localhost:8080/bcnc/api/v1`.

### Acceso a la Consola H2
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:prices`
* **User:** `sa`
* **Password:** *(dejar en blanco)*

---

## Pruebas (Testing)

Se han implementado dos niveles de testeo para garantizar la calidad del software:

1. **Pruebas de Integración:** Valida de extremo a extremo los 5 escenarios solicitados en los requerimientos usando `MockMvc` contra la base de datos H2.
    * Test 1: Consulta a las 10:00 del día 14 para el producto 35455 y brand 1 (ZARA).
    * Test 2: Consulta a las 16:00 del día 14 para el producto 35455 y brand 1.
    * Test 3: Consulta a las 21:00 del día 14 para el producto 35455 y brand 1.
    * Test 4: Consulta a las 10:00 del día 15 para el producto 35455 y brand 1.
    * Test 5: Consulta a las 21:00 del día 16 para el producto 35455 y brand 1.

2. **Pruebas Unitarias:** Valida el comportamiento aislado del controlador REST usando Mockito para mockear el puerto de entrada y el mapeador.

Para ejecutar toda la suite de pruebas:
./mvnw test

---

## Documentación Técnica (OpenAPI / Postman)

### Contrato OpenAPI 3.0 / Swagger
El archivo de especificación en formato YAML se encuentra disponible en la raíz del proyecto para importar en editor.swagger.io o Swagger UI.

#### Endpoint Principal
`GET /bcnc/api/v1/prices`

#### Parámetros de Consulta (Query Params)
* `date` (String, ISO-8601): Fecha y hora de consulta (ej. `2020-06-16T21:00:00`).
* `productId` (Long): Identificador del producto (ej. `35455`).
* `brandId` (Long): Identificador de la cadena (ej. `1`).

#### Respuesta Exitosa (200 OK)
```{
"productId": 35455,
"brandId": 1,
"priceList": 4,
"startDate": "2020-06-15T16:00:00",
"endDate": "2020-12-31T23:59:59",
"price": 38.95
} 
```

#### Colección de Postman
El proyecto includes el archivo `BCNC Test.postman_collection.json` listo para ser importado en Postman.

---

##  Troubleshooting (Resolución de Problemas)

### Error JNI al ejecutar H2 Console
* **Problema:** Al intentar ejecutar el instalador o consola independiente de H2 aparecía el error `"A JNI error has occurred, please check your installation and try again"`.
* **Causa:** El ejecutable del sistema operativo utilizaba una versión antigua de Java (Java 8 JRE) por defecto.
* **Solución:** Se actualizaron las variables de entorno `JAVA_HOME` y `Path` del sistema operativo para apuntar al JDK 21 de Java recién instalado.