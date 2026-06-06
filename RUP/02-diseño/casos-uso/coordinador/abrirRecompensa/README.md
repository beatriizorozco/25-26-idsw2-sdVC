# FUNIBER > Coordinador > abrirRecompensa > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirRecompensa/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirRecompensa/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta completa de una recompensa y la preparación de las acciones de edición y eliminación disponibles para el Coordinador.

## Diagrama de secuencia

|![Diseño: abrirRecompensa()](/images/RUP/02-diseño/casos-uso/coordinador/abrirRecompensa/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **RecompensaDetallePage**: Presenta el detalle y las acciones de gestión.
- **RecompensaController**: Expone `GET /api/recompensas/{id}`.
- **SesionService**: Exige una sesión de Coordinador.
- **RecompensaService**: Compone y valida el detalle.
- **RecompensaRepository**, **ProyectoRepository** e **InvestigadorRepository**: Recuperan las entidades relacionadas.

## Decisiones de Diseño

- La respuesta incluye proyecto completado, beneficiario, condición docente, tipo y valor.
- El servicio valida que el tipo siga siendo compatible con el beneficiario.
- El detalle global habilita editar y eliminar.
- Una recompensa inexistente devuelve `404 Not Found`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirRecompensa/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirRecompensa/README.md)
