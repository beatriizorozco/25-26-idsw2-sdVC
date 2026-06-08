# FUNIBER > Investigador > abrirProyectos > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirProyectos/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirProyectos/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta de proyectos visibles para el Investigador autenticado, sin exponer proyectos ajenos.

## Diagrama de secuencia

|![Diseño: abrirProyectos()](/images/RUP/02-diseño/casos-uso/investigador/abrirProyectos/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **MisProyectosPage**: Presenta y filtra los proyectos visibles.
- **ProyectoController**: Expone `GET /api/proyectos/me`.
- **SesionService**: Resuelve al Investigador autenticado.
- **ProyectoService**: Limita siempre la consulta al alcance autorizado.
- **ProyectoRepository**: Recupera proyectos propios o visibles asociados.

## Decisiones de Diseño

- El identificador del actor se obtiene de la sesión, nunca de un parámetro manipulable.
- Sin contexto adicional se muestran sus proyectos propios.
- El filtro mantiene las restricciones de visibilidad.
- El Investigador no dispone de acciones de creación, edición ni eliminación.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirProyectos/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirProyectos/README.md)
