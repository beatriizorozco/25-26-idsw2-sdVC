# FUNIBER > Coordinador > abrirInvestigadores > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirInvestigadores/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirInvestigadores/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta del directorio de investigadores por el Coordinador, tanto en alcance global como en el contexto de un proyecto seleccionado.

## Diagrama de secuencia

|![Diseño: abrirInvestigadores()](/images/RUP/02-diseño/casos-uso/coordinador/abrirInvestigadores/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **InvestigadoresPage**: Presenta el directorio activo o los participantes del proyecto.
- **InvestigadorController**: Expone `GET /api/investigadores`.
- **SesionService**: Exige una sesión de Coordinador.
- **InvestigadorService**: Resuelve el alcance global o contextual.
- **InvestigadorRepository** y **ProyectoRepository**: Recuperan investigadores activos o participantes del proyecto.

## Decisiones de Diseño

- El mismo endpoint acepta `proyectoId` opcional para no duplicar el caso de uso.
- Sin `proyectoId` se consulta el directorio global de investigadores activos.
- Con `proyectoId` se limita el listado a los participantes del proyecto seleccionado.
- El filtrado se aplica en servidor dentro del alcance recibido.
- El listado permite abrir el detalle del investigador y, en alcance global, iniciar su alta.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirInvestigadores/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirInvestigadores/README.md)
