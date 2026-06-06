# FUNIBER > Investigador > abrirRecompensas > Desarrollo

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirRecompensas/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirRecompensas/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirRecompensas/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

## Implementación

El Investigador consulta únicamente sus recompensas mediante `GET /api/recompensas/me`. El módulo también aparece para sedes sin docencia porque estos perfiles pueden recibir recompensas económicas.

## Código relacionado

- [`RecompensasPage.tsx`](/src/frontend/src/pages/RecompensasPage.tsx)
- [`PanelPrincipalService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/PanelPrincipalService.java)
- [`RecompensaService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/RecompensaService.java)
