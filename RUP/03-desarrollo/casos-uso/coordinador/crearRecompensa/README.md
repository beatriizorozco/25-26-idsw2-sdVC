# FUNIBER > Coordinador > crearRecompensa > Desarrollo

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/crearRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/crearRecompensa/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

## Implementación

El formulario obtiene únicamente proyectos completados con recompensas pendientes desde `/api/recompensas/opciones-creacion`. Al seleccionar un proyecto, se excluyen los participantes que ya han recibido todos sus tipos permitidos; al seleccionar un beneficiario, se consultan los tipos todavía disponibles. `POST /api/recompensas` valida participación, tipo permitido y duplicados. Los investigadores-docentes pueden recibir recompensa económica o reducción docente; los demás solo económica. La reducción se selecciona por asignaturas completas de 4 horas y solo admite 4, 8, 12 o 16 horas.

## Código relacionado

- [`RecompensasPage.tsx`](/src/frontend/src/pages/RecompensasPage.tsx)
- [`RecompensaService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/RecompensaService.java)
- [`V5__proyectos_y_recompensas.sql`](/src/backend/src/main/resources/db/migration/V5__proyectos_y_recompensas.sql)
