# FUNIBER > Coordinador > abrirProyectos > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyectos/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirProyectos/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta global de proyectos por el Coordinador, incluyendo filtrado y el contexto opcional de un investigador.

## Diagrama de secuencia

|![Diseño: abrirProyectos()](/images/RUP/02-diseño/casos-uso/coordinador/abrirProyectos/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **ProyectosPage**: Presenta y filtra proyectos.
- **ProyectoController**: Expone `GET /api/proyectos`.
- **SesionService**: Exige una sesión de Coordinador.
- **ProyectoService**: Resuelve el alcance global o por investigador.
- **ProyectoRepository**: Recupera los proyectos solicitados.

## Decisiones de Diseño

- El Coordinador puede consultar todos los proyectos.
- `investigadorId` limita opcionalmente el listado a los proyectos asociados.
- El filtro se procesa en servidor y conserva el alcance seleccionado.
- Cada elemento permite abrir directamente su detalle.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyectos/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirProyectos/README.md)
