# FUNIBER > Coordinador > crearRecompensa > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/crearRecompensa/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/crearRecompensa/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la creación guiada de una recompensa, desde la selección del proyecto completado y el beneficiario elegible hasta la validación y persistencia.

## Diagrama de secuencia

|![Diseño: crearRecompensa()](/images/RUP/02-diseño/casos-uso/coordinador/crearRecompensa/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **CrearRecompensaForm**: Guía las selecciones y captura concepto, tipo y valor.
- **RecompensaController**: Expone las opciones de creación y `POST /api/recompensas`.
- **SesionService**: Exige una sesión de Coordinador.
- **RecompensaService**: Valida proyecto, beneficiario, tipo, valor y duplicados.
- **ProyectoRepository**, **InvestigadorRepository** y **RecompensaRepository**: Recuperan opciones y persisten la recompensa.

## Decisiones de Diseño

- La creación se prepara mediante opciones obtenidas del servidor, no mediante valores libres.
- Solo aparecen proyectos completados pendientes de recompensa.
- Los beneficiarios se limitan a investigadores elegibles del proyecto.
- Los tipos permitidos se obtienen después de seleccionar al beneficiario.
- Investigador-docente admite recompensa económica o reducción docente; investigador sin docencia solo económica.
- La reducción docente se selecciona por asignaturas completas mediante valores cerrados de 4, 8, 12 o 16 horas.
- Si no existen proyectos o beneficiarios elegibles, se presenta un estado vacío sin permitir guardar.
- Cancelar conserva `RECOMPENSAS_ABIERTAS` y no invoca `POST`.
- Un duplicado devuelve `409 Conflict`; datos incompatibles devuelven `400 Bad Request`.
- La creación correcta devuelve `201 Created` y abre la recompensa.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearRecompensa/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/crearRecompensa/README.md)
