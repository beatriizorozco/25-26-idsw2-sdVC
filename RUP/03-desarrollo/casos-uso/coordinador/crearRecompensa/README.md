# FUNIBER > Coordinador > crearRecompensa > Desarrollo

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/crearRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/crearRecompensa/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

## Implementación

El formulario obtiene proyectos completados y participantes elegibles desde `/api/recompensas/opciones-creacion`. `POST /api/recompensas` valida participación, tipo permitido y duplicados. Los investigadores-docentes pueden recibir recompensa económica o reducción docente; los demás solo económica.

## Código relacionado

- [`RecompensasPage.tsx`](/src/frontend/src/pages/RecompensasPage.tsx)
- [`RecompensaService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/RecompensaService.java)
- [`V5__proyectos_y_recompensas.sql`](/src/backend/src/main/resources/db/migration/V5__proyectos_y_recompensas.sql)
