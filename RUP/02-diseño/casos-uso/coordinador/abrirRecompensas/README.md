# FUNIBER > Coordinador > abrirRecompensas > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirRecompensas/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirRecompensas/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirRecompensas/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta global de recompensas por el Coordinador, incluyendo filtrado, proyecto completado de origen y beneficiario.

## Diagrama de secuencia

|![Diseño: abrirRecompensas()](/images/RUP/02-diseño/casos-uso/coordinador/abrirRecompensas/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **RecompensasPage**: Presenta y filtra el listado global.
- **RecompensaController**: Expone `GET /api/recompensas`.
- **SesionService**: Exige una sesión de Coordinador.
- **RecompensaService**: Coordina listado y composición de respuestas.
- **RecompensaRepository**, **ProyectoRepository** e **InvestigadorRepository**: Recuperan recompensas, proyectos y beneficiarios.

## Decisiones de Diseño

- Solo el Coordinador accede al listado global.
- El criterio de búsqueda se envía como parámetro opcional.
- Solo se presentan recompensas originadas por proyectos completados.
- Cada elemento incluye información suficiente del proyecto y del beneficiario.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirRecompensas/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirRecompensas/README.md)
