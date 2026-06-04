# FUNIBER > Investigador > abrirOpcionesCargaTrabajo > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El Investigador abre `Carga de trabajo` desde el panel principal y consulta únicamente su información mediante `GET /api/carga-trabajo/me`. El frontend presenta su sede, tipo de perfil, total semanal, horas de docencia y margen docente cuando aplica.

El backend no permite que el Investigador abra el panel global de cargas. La información docente se calcula según sede: Barcelona se presenta como sede sin docencia investigadora, mientras que Santander activa el límite de 16 horas semanales de docencia.

## Código relacionado

- [`CargaTrabajoPage.tsx`](/src/frontend/src/pages/CargaTrabajoPage.tsx)
- [`api.ts`](/src/frontend/src/services/api.ts)
- [`CargaTrabajoController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/CargaTrabajoController.java)
- [`CargaTrabajoService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/CargaTrabajoService.java)
- [`CargaTrabajoPersonaResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/CargaTrabajoPersonaResponse.java)
