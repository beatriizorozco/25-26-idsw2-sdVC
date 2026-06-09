# FUNIBER > Investigador > abrirInvestigadores > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigadores/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirInvestigadores/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta de investigadores visibles para el Investigador autenticado dentro del proyecto desde el que navega.

## Diagrama de secuencia

|![Diseño: abrirInvestigadores()](/images/RUP/02-diseño/casos-uso/investigador/abrirInvestigadores/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **InvestigadoresProyectoPage**: Presenta los participantes visibles del proyecto.
- **InvestigadorController**: Expone `GET /api/investigadores/me`.
- **SesionService**: Exige una sesión de Investigador.
- **InvestigadorService**: Limita el alcance al proyecto visible.
- **ProyectoRepository**: Recupera los participantes del proyecto.

## Decisiones de Diseño

- El Investigador nunca accede al directorio global.
- `proyectoId` es obligatorio para mantener el alcance visible.
- Un proyecto ajeno o inexistente responde `404 Not Found`.
- El listado solo permite abrir perfiles visibles dentro del mismo proyecto.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigadores/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirInvestigadores/README.md)
