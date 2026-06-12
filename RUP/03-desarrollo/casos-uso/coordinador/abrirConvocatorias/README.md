# FUNIBER > Coordinador > Abrir Convocatorias > Desarrollo

## Implementación

Consulta y filtra las convocatorias incorporadas mediante `GET /api/convocatorias`, aplicando filtros por texto, área y estado.

## Código relacionado

- [`ConvocatoriaController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/ConvocatoriaController.java)
- [`ConsultaConvocatoriaService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/ConsultaConvocatoriaService.java)
- [`ConvocatoriaRepository.java`](/src/backend/src/main/java/es/funiber/investigacion/repository/ConvocatoriaRepository.java)
- [`ConvocatoriasPage.tsx`](/src/frontend/src/pages/ConvocatoriasPage.tsx)
- [`ConvocatoriaIntegrationTests.java`](/src/backend/src/test/java/es/funiber/investigacion/controller/ConvocatoriaIntegrationTests.java)

## Decisiones

- Solo el Coordinador puede consultar convocatorias.
- El listado no permite crear, editar ni eliminar manualmente.
- Los filtros se combinan sin alterar la información importada.
