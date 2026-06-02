# FUNIBER > Investigador > abrirPanelPrincipal > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirPanelPrincipal/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirPanelPrincipal/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirPanelPrincipal/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirPanelPrincipal/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El panel obtiene las acciones disponibles desde la sesión autenticada. El Investigador recibe el conjunto permitido para su rol, sin convocatorias.

## Código relacionado

- [`PanelPrincipalPage.tsx`](/src/frontend/src/pages/PanelPrincipalPage.tsx)
- [`PanelPrincipalController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/PanelPrincipalController.java)
- [`PanelPrincipalService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/PanelPrincipalService.java)

