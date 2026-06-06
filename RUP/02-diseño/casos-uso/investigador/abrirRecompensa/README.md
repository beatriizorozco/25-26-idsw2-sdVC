# FUNIBER > Investigador > abrirRecompensa > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirRecompensa/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirRecompensa/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta de una recompensa propia sin exponer acciones de gestión ni recompensas de otros investigadores.

## Diagrama de secuencia

|![Diseño: abrirRecompensa()](/images/RUP/02-diseño/casos-uso/investigador/abrirRecompensa/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **MiRecompensaDetallePage**: Presenta el detalle propio en modo consulta.
- **RecompensaController**: Expone `GET /api/recompensas/me/{id}`.
- **SesionService**: Obtiene el Investigador autenticado.
- **RecompensaService**: Verifica que la recompensa pertenezca al Investigador.
- **RecompensaRepository** y **ProyectoRepository**: Recuperan recompensa propia y proyecto completado.

## Decisiones de Diseño

- El propietario se obtiene de la sesión, nunca de un parámetro modificable.
- Una recompensa inexistente o ajena devuelve `404 Not Found` para no revelar su existencia.
- La respuesta no incluye acciones CRUD.
- El detalle conserva la trazabilidad al proyecto completado.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirRecompensa/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirRecompensa/README.md)
