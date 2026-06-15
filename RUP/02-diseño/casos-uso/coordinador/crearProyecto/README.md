# FUNIBER > Coordinador > crearProyecto > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearProyecto/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/crearProyecto/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/crearProyecto/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la creación mínima de un proyecto y su apertura inmediata para continuar la definición.

## Diagrama de secuencia

|![Diseño: crearProyecto()](/images/RUP/02-diseño/casos-uso/coordinador/crearProyecto/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **CrearProyectoForm**: Captura los datos mínimos y permite cancelar.
- **ProyectoController**: Expone opciones y `POST /api/proyectos`.
- **SesionService**: Exige una sesión de Coordinador.
- **ProyectoService**: Valida fechas y asigna código, Coordinador y estado inicial.
- **ConvocatoriaRepository** y **ProyectoRepository**: Recuperan convocatorias y persisten el proyecto.

## Decisiones de Diseño

- La convocatoria se selecciona entre opciones importadas disponibles.
- El código, el Coordinador responsable y el estado `Creado` se asignan en servidor.
- Cancelar conserva `PROYECTOS_ABIERTOS` sin invocar `POST`.
- La creación correcta devuelve `201 Created` y abre el proyecto.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearProyecto/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/crearProyecto/README.md)
