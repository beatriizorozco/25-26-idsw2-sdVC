# FUNIBER > Coordinador > Abrir Investigadores > Desarrollo

> |[Inicio](/README.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirInvestigadores/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirInvestigadores/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirInvestigadores/README.md)|**Desarrollo**|
> |-|-|-|-|-|

## Implementación

`GET /api/investigadores` devuelve el directorio de investigadores activos. El Coordinador puede consultarlo en alcance global o limitarlo al contexto de un proyecto mediante `proyectoId`.

El filtrado por texto se aplica sobre usuario, nombre completo, unidad, línea de investigación y sede. El listado devuelve también la carga semanal para identificar rápidamente a los perfiles docentes.

## Código relacionado

- [`InvestigadoresPage.tsx`](/src/frontend/src/pages/InvestigadoresPage.tsx)
- [`InvestigadorController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/InvestigadorController.java)
- [`InvestigadorService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/InvestigadorService.java)
