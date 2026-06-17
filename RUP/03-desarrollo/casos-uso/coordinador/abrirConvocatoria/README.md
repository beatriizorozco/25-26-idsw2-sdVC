# FUNIBER > Coordinador > abrirConvocatoria > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatoria/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirConvocatoria/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirConvocatoria/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Backend:** [ConvocatoriaController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/ConvocatoriaController.java) · [ConsultaConvocatoriaService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ConsultaConvocatoriaService.java) · [SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java) · [ConvocatoriaRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ConvocatoriaRepository.java) · [Convocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/model/Convocatoria.java) · [PoliticaConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/PoliticaConvocatoria.java)
- **Frontend:** [ConvocatoriasPage.tsx](/src/frontend/src/pages/ConvocatoriasPage.tsx)
- **Pruebas:** [ConvocatoriaIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/ConvocatoriaIntegrationTests.java)

## Descripción

Presenta el detalle completo de una convocatoria mediante `GET /api/convocatorias/{id}`.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `GET /api/convocatorias/{id}`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Controlador REST|[ConvocatoriaController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/ConvocatoriaController.java)|
|Servicio de aplicación|[ConsultaConvocatoriaService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ConsultaConvocatoriaService.java)|
|Servicio de aplicación|[SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)|
|Repositorio|[ConvocatoriaRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ConvocatoriaRepository.java)|
|Entidad de dominio|[Convocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/model/Convocatoria.java)|
|Política de dominio|[PoliticaConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/PoliticaConvocatoria.java)|
|Contrato de datos|[ConvocatoriaResponse.java](/src/backend/src/main/java/es/funiber/investigacion/dto/ConvocatoriaResponse.java)|
|Página React|[ConvocatoriasPage.tsx](/src/frontend/src/pages/ConvocatoriasPage.tsx)|
|Prueba de integración|[ConvocatoriaIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/ConvocatoriaIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `abrirConvocatoria()` desde el estado permitido por el diagrama de contexto.
2. La interfaz React captura la solicitud y utiliza el cliente HTTP común.
3. El controlador REST valida sesión, permisos y datos de entrada.
4. El servicio de aplicación coordina las reglas del caso de uso.
5. Los repositorios consultan o persisten únicamente la información necesaria.
6. La interfaz presenta el resultado y conserva la navegación definida.

## Decisiones de implementación

- El detalle conserva entidad, fechas, requisitos, criterios, dotación, contacto y referencia externa.

- **Responsabilidad única:** La presentación, coordinación, reglas y persistencia permanecen separadas.
- **Abierto y cerrado:** Las reglas variables se delegan en servicios, políticas o estrategias extensibles.
- **Seguridad:** El backend vuelve a validar permisos y propiedad aunque la interfaz oculte acciones.
- **Trazabilidad:** La implementación mantiene correspondencia con Detalle, Análisis y Diseño.

## Validación

### Backend

```bash
cd src/backend
mvn test -Dtest=ConvocatoriaIntegrationTests
```

### Frontend

```bash
cd src/frontend
npm run build
```

### Comprobación funcional

1. Iniciar sesión como Coordinador.
2. Acceder al flujo `abrirConvocatoria()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatoria/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/abrirConvocatoria/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/abrirConvocatoria/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
