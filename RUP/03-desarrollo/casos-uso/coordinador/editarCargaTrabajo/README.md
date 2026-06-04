# FUNIBER > Coordinador > editarCargaTrabajo > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarCargaTrabajo/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El Coordinador selecciona una persona del listado de cargas y actualiza sus horas mediante `PATCH /api/carga-trabajo/{perfilId}`. El backend valida que la sesión sea de Coordinador y guarda la carga semanal.

Si el perfil pertenece a una sede con docencia, el sistema impide guardar más de 16 horas semanales de docencia. Las recompensas se gestionan posteriormente cuando se completa un proyecto y pueden resolverse como recompensa económica o como reducción de horas docentes en el siguiente cuatrimestre.

## Código relacionado

- [`CargaTrabajoPage.tsx`](/src/frontend/src/pages/CargaTrabajoPage.tsx)
- [`CargaTrabajoController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/CargaTrabajoController.java)
- [`CargaTrabajoService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/CargaTrabajoService.java)
- [`CargaTrabajoUpdateRequest.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/CargaTrabajoUpdateRequest.java)
- [`CargaTrabajo.java`](/src/backend/src/main/java/es/funiber/investigacion/model/CargaTrabajo.java)
