# FUNIBER > Investigador > abrirRecompensas > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirRecompensas/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirRecompensas/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirRecompensas/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta y filtrado de las recompensas propias del Investigador autenticado.

## Diagrama de secuencia

|![Diseño: abrirRecompensas()](/images/RUP/02-diseño/casos-uso/investigador/abrirRecompensas/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **MisRecompensasPage**: Presenta y filtra recompensas propias.
- **RecompensaController**: Expone `GET /api/recompensas/me`.
- **SesionService**: Obtiene el Investigador autenticado.
- **RecompensaService**: Restringe la consulta al propietario.
- **RecompensaRepository** y **ProyectoRepository**: Recuperan recompensas propias y proyectos completados.

## Decisiones de Diseño

- La ruta `/me` impide consultar listados de otros investigadores.
- El identificador del propietario procede exclusivamente de la sesión.
- La respuesta no incluye acciones de creación, edición o eliminación.
- Solo se muestran recompensas vinculadas a proyectos completados.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirRecompensas/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirRecompensas/README.md)
