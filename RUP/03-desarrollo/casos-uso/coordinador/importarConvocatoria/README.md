# FUNIBER > Coordinador > Importar Convocatoria > Desarrollo

## Implementación

Importa convocatorias externas mediante un flujo de previsualización y confirmación:

- `POST /api/convocatorias/importaciones/previsualizacion`
- `POST /api/convocatorias/importaciones`

## Código relacionado

- [`ImportacionConvocatoriaService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/ImportacionConvocatoriaService.java)
- [`ImportadorConvocatoria.java`](/src/backend/src/main/java/es/funiber/investigacion/service/ImportadorConvocatoria.java)
- [`RegistroImportadoresConvocatoria.java`](/src/backend/src/main/java/es/funiber/investigacion/service/RegistroImportadoresConvocatoria.java)
- [`ImportadorJsonConvocatoria.java`](/src/backend/src/main/java/es/funiber/investigacion/service/ImportadorJsonConvocatoria.java)
- [`ImportadorReferenciaConvocatoria.java`](/src/backend/src/main/java/es/funiber/investigacion/service/ImportadorReferenciaConvocatoria.java)
- [`ValidadorConvocatoria.java`](/src/backend/src/main/java/es/funiber/investigacion/service/ValidadorConvocatoria.java)
- [`ConvocatoriasPage.tsx`](/src/frontend/src/pages/ConvocatoriasPage.tsx)

## Decisiones

- Previsualizar no persiste ningún cambio.
- Cancelar descarta la previsualización.
- Confirmar vuelve a validar antes de guardar.
- Una referencia externa ya incorporada actualiza su registro sin crear duplicados.
- Nuevas fuentes se incorporan implementando `ImportadorConvocatoria`, sin modificar el servicio de importación.
