# FUNIBER > Investigador > editarCargaTrabajo > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/editarCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/editarCargaTrabajo/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/editarCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El Investigador actualiza su carga semanal mediante `PATCH /api/carga-trabajo/me`. El backend solo modifica la carga asociada a la sesión actual, por lo que no puede editar cargas de otros perfiles.

Tras guardar, el sistema recalcula el total semanal. Si el usuario pertenece a una sede con docencia investigadora, no se permite superar 16 horas semanales de docencia. Si pertenece a una sede sin docencia investigadora, la docencia debe quedar en 0 horas y cualquier valor superior se rechaza. Las recompensas por proyectos completados quedan fuera de este caso de uso y se gestionan en el módulo de recompensas.

## Código relacionado

- [`CargaTrabajoPage.tsx`](/src/frontend/src/pages/CargaTrabajoPage.tsx)
- [`CargaTrabajoController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/CargaTrabajoController.java)
- [`CargaTrabajoService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/CargaTrabajoService.java)
- [`CargaTrabajoUpdateRequest.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/CargaTrabajoUpdateRequest.java)
- [`CargaTrabajoIntegrationTests.java`](/src/backend/src/test/java/es/funiber/investigacion/controller/CargaTrabajoIntegrationTests.java)
