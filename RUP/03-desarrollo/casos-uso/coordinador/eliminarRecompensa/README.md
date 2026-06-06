# FUNIBER > Coordinador > eliminarRecompensa > Desarrollo

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarRecompensa/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

## Implementación

La interfaz exige confirmación antes de `DELETE /api/recompensas/{id}`. La operación elimina únicamente la recompensa y conserva proyecto, participantes y carga de trabajo.

## Código relacionado

- [`RecompensasPage.tsx`](/src/frontend/src/pages/RecompensasPage.tsx)
- [`RecompensaIntegrationTests.java`](/src/backend/src/test/java/es/funiber/investigacion/controller/RecompensaIntegrationTests.java)
