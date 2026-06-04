# FUNIBER > Coordinador > abrirOpcionesCargaTrabajo > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El Coordinador abre el módulo `Carga de trabajo` desde el panel principal. El frontend consulta `GET /api/carga-trabajo`, presenta el listado de personas activas, identifica sedes con investigador-docente y muestra sugerencias para proyectos libres priorizando a quienes tienen menor carga semanal.

La regla funcional de sede queda aplicada en backend: solo las sedes configuradas como docentes, por ahora Santander, generan candidatos investigador-docente. Estos perfiles no pueden superar 16 horas semanales de docencia; las recompensas no nacen por exceso de horas, sino por proyectos completados en el módulo específico de recompensas.

## Código relacionado

- [`CargaTrabajoPage.tsx`](/src/frontend/src/pages/CargaTrabajoPage.tsx)
- [`api.ts`](/src/frontend/src/services/api.ts)
- [`CargaTrabajoController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/CargaTrabajoController.java)
- [`CargaTrabajoService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/CargaTrabajoService.java)
- [`PanelCargaTrabajoResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/PanelCargaTrabajoResponse.java)
- [`SedeFuniber.java`](/src/backend/src/main/java/es/funiber/investigacion/model/SedeFuniber.java)
