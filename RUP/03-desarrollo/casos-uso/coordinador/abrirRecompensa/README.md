# FUNIBER > Coordinador > abrirRecompensa > Desarrollo

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirRecompensa/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

## Implementación

`GET /api/recompensas/{id}` devuelve proyecto completado, beneficiario, tipo, concepto y valor. Exige rol Coordinador y devuelve `404` cuando no existe.

## Código relacionado

- [`RecompensaResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/RecompensaResponse.java)
- [`RecompensaService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/RecompensaService.java)
