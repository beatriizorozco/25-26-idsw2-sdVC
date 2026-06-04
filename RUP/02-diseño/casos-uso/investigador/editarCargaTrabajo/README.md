# FUNIBER > Investigador > editarCargaTrabajo > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/editarCargaTrabajo/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la edición de la carga de trabajo propia del Investigador, manteniendo la restricción de que no puede modificar cargas ajenas y registrando compensación económica solo si su sede aplica docencia investigadora y supera el límite docente de 16 horas semanales.

## Diagrama de secuencia

|![Diseño: editarCargaTrabajo()](/images/RUP/02-diseño/casos-uso/investigador/editarCargaTrabajo/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EditarCargaTrabajoPropiaForm**: Precarga y captura los cambios de carga del Investigador.
- **CargaTrabajoController**: Expone `GET /api/carga-trabajo/me` y `PATCH /api/carga-trabajo/me`.
- **SesionService**: Valida la sesión activa.
- **CargaTrabajoService**: Valida y actualiza la carga propia.
- **CargaTrabajoRepository**: Recupera y guarda la carga del usuario autenticado.
- **RecompensaRepository**: Registra compensación pendiente si hay exceso docente aplicable por sede.

## Decisiones de Diseño

- El Investigador solo puede editar su propia carga.
- La API no acepta identificadores externos para este rol.
- El formulario se precarga con la carga actual antes de guardar cambios.
- La actualización usa `PATCH`.
- La carga docente se compara con el límite de 16 horas semanales solo en sedes con docencia investigadora.
- Si se supera el límite docente en una sede con docencia, se registra o actualiza una compensación pendiente.
- Si la sede clasifica al usuario como solo investigador, no se genera compensación docente.
- La salida correcta vuelve a `OPCIONES_CARGA_TRABAJO_ABIERTAS`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/editarCargaTrabajo/README.md)
