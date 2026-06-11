# FUNIBER > Coordinador > Eliminar Entregable > Desarrollo

## Implementación

La retirada usa `PATCH /api/entregables/{id}/retirada`. No borra físicamente el registro: conserva proyecto, autoría, motivo y versiones.

## Código Relacionado

- [`V9__entregables.sql`](/src/backend/src/main/resources/db/migration/V9__entregables.sql)
- [`EntregableIntegrationTests.java`](/src/backend/src/test/java/es/funiber/investigacion/controller/EntregableIntegrationTests.java)
