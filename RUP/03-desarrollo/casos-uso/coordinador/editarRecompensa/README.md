# FUNIBER > Coordinador > editarRecompensa > Desarrollo

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarRecompensa/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

## Implementación

`GET /api/recompensas/{id}/edicion` prepara participantes elegibles y `PATCH /api/recompensas/{id}` conserva el proyecto de origen y vuelve a validar beneficiario, tipo, valor y duplicados. Una reducción docente editada debe seguir representando asignaturas completas de 4 horas, hasta un máximo de 16.

## Código relacionado

- [`RecompensasPage.tsx`](/src/frontend/src/pages/RecompensasPage.tsx)
- [`RecompensaEdicionResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/RecompensaEdicionResponse.java)
- [`RecompensaService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/RecompensaService.java)
