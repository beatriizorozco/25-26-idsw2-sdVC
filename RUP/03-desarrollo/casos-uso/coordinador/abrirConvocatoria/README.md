# FUNIBER > Coordinador > Abrir Convocatoria > Desarrollo

## Implementación

Presenta el detalle completo de una convocatoria mediante `GET /api/convocatorias/{id}`.

## Código relacionado

- [`ConvocatoriaController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/ConvocatoriaController.java)
- [`ConsultaConvocatoriaService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/ConsultaConvocatoriaService.java)
- [`ConvocatoriaResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/ConvocatoriaResponse.java)
- [`ConvocatoriasPage.tsx`](/src/frontend/src/pages/ConvocatoriasPage.tsx)

## Decisiones

- El detalle conserva entidad, fechas, requisitos, criterios, dotación, contacto y referencia externa.
- Una convocatoria inexistente produce una respuesta `404`.
- La vista vuelve al listado sin navegar directamente al panel desde este caso de uso.
