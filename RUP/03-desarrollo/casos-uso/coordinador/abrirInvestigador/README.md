# FUNIBER > Coordinador > Abrir Investigador > Desarrollo

> |[Inicio](/README.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirInvestigador/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirInvestigador/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirInvestigador/README.md)|**Desarrollo**|
> |-|-|-|-|-|

## Implementación

`GET /api/investigadores/{id}` recupera el detalle de un investigador activo y compone sus proyectos asociados visibles. El Coordinador puede abrir el detalle tanto desde el directorio general como desde un proyecto concreto.

La respuesta incluye datos de perfil, sede, unidad, línea de investigación, biografía y la relación de proyectos visibles asociados para mantener continuidad con el resto del flujo de navegación.

## Código relacionado

- [`InvestigadoresPage.tsx`](/src/frontend/src/pages/InvestigadoresPage.tsx)
- [`InvestigadorDetalleResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/InvestigadorDetalleResponse.java)
- [`InvestigadorService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/InvestigadorService.java)
