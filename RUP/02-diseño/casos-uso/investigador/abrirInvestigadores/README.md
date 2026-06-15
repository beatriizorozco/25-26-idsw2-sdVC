# FUNIBER > Investigador > abrirInvestigadores > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigadores/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirInvestigadores/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirInvestigadores/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta de investigadores visibles para el Investigador autenticado, tanto desde el directorio general como desde el contexto de un proyecto compartido.

## Diagrama de secuencia

|![Diseño: abrirInvestigadores()](/images/RUP/02-diseño/casos-uso/investigador/abrirInvestigadores/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **InvestigadoresPage**: Presenta el directorio global en modo consulta o los participantes visibles de un proyecto compartido.
- **InvestigadorController**: Expone `GET /api/investigadores`.
- **SesionService**: Recupera la sesión autenticada y su rol.
- **InvestigadorService**: Resuelve si la consulta es global o contextual.
- **UsuarioRepository** y **ProyectoRepository**: Recuperan el directorio activo o los participantes del proyecto visible.

## Decisiones de Diseño

- Sin `proyectoId` el Investigador consulta el directorio global en modo solo lectura.
- Con `proyectoId` la consulta se limita a los participantes del proyecto compartido.
- El endpoint se reutiliza con distinta política de autorización según el rol autenticado.
- Un proyecto ajeno o inexistente responde `404 Not Found`.
- El listado solo permite abrir perfiles activos visibles para el usuario autenticado.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigadores/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirInvestigadores/README.md)
