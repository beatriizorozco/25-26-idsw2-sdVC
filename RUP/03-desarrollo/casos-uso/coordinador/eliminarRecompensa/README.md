# FUNIBER > Coordinador > eliminarRecompensa > Desarrollo

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarRecompensa/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

## Implementación

El Diseño actualizado exige confirmación y motivo antes de `PATCH /api/recompensas/{id}/anulacion`. La operación debe anular la recompensa y conservar proyecto, participantes, carga de trabajo y datos originales de concesión.

La implementación actual todavía utiliza borrado físico mediante `DELETE /api/recompensas/{id}`. Debe migrarse a anulación lógica antes de considerar alineado este caso de uso.

## Código relacionado

- [`RecompensasPage.tsx`](/src/frontend/src/pages/RecompensasPage.tsx)
- [`RecompensaIntegrationTests.java`](/src/backend/src/test/java/es/funiber/investigacion/controller/RecompensaIntegrationTests.java)
