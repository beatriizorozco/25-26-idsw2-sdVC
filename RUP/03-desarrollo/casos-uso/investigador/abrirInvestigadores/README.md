# FUNIBER > Investigador > Abrir Investigadores > Desarrollo

> |[Inicio](/README.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigadores/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirInvestigadores/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirInvestigadores/README.md)|**Desarrollo**|
> |-|-|-|-|-|

## Implementación

El Investigador reutiliza `GET /api/investigadores` en modo solo lectura. Sin `proyectoId` consulta el directorio global de perfiles activos; con `proyectoId` consulta únicamente los participantes de un proyecto en el que también participa.

El frontend comparte la misma pantalla de directorio que Coordinador, pero sin exponer acciones de alta.

## Código relacionado

- [`InvestigadoresPage.tsx`](/src/frontend/src/pages/InvestigadoresPage.tsx)
- [`InvestigadorController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/InvestigadorController.java)
- [`InvestigadorService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/InvestigadorService.java)
