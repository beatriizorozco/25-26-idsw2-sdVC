# FUNIBER > Coordinador > Crear Investigador > Desarrollo

> |[Inicio](/README.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearInvestigador/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/crearInvestigador/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/crearInvestigador/README.md)|**Desarrollo**|
> |-|-|-|-|-|

## Implementación

`POST /api/investigadores` permite al Coordinador registrar un nuevo perfil investigador. El backend valida unicidad de usuario y correo, asigna rol `INVESTIGADOR`, genera contraseña temporal con el patrón `usuario123` y crea también su carga de trabajo inicial vacía.

En frontend, el alta se realiza dentro del propio módulo de investigadores, sin romper la navegación del directorio.

## Código relacionado

- [`InvestigadoresPage.tsx`](/src/frontend/src/pages/InvestigadoresPage.tsx)
- [`InvestigadorCreateRequest.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/InvestigadorCreateRequest.java)
- [`InvestigadorService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/InvestigadorService.java)
