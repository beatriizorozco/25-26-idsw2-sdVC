# FUNIBER > Investigador > Abrir Investigador > Desarrollo

> |[Inicio](/README.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigador/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirInvestigador/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirInvestigador/README.md)|**Desarrollo**|
> |-|-|-|-|-|

## Implementación

`GET /api/investigadores/{id}` permite abrir un perfil visible del directorio o de un proyecto compartido. Si el investigador autenticado no comparte visibilidad con el perfil solicitado, el backend responde como recurso no encontrado.

El detalle muestra únicamente la información visible del perfil y los proyectos compartidos o accesibles para el usuario autenticado.

## Código relacionado

- [`InvestigadoresPage.tsx`](/src/frontend/src/pages/InvestigadoresPage.tsx)
- [`InvestigadorDetalleResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/InvestigadorDetalleResponse.java)
- [`InvestigadorService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/InvestigadorService.java)
