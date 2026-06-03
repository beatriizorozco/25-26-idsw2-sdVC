# FUNIBER > Coordinador > abrirSolicitudEliminacionPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta del detalle de una solicitud de eliminación de perfil pendiente.

## Diagrama de secuencia

|![Diseño: abrirSolicitudEliminacionPerfil()](/images/RUP/02-diseño/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **SolicitudEliminacionPerfilDetallePage**: Presenta la solicitud seleccionada.
- **SolicitudEliminacionPerfilController**: Expone `GET /api/solicitudes-eliminacion-perfil/{id}`.
- **SesionService**: Valida que el actor sea Coordinador.
- **SolicitudEliminacionPerfilService**: Recupera el detalle y prepara acciones.
- **SolicitudEliminacionPerfilRepository**: Obtiene la solicitud.

## Decisiones de Diseño

- La solicitud solo se abre desde el listado de solicitudes pendientes.
- La respuesta incluye perfil afectado, solicitante, motivo y estado.
- Desde el detalle se puede volver al listado o ejecutar `eliminarPerfil()`.
- La salida correcta es `SOLICITUD_ELIMINACION_PERFIL_ABIERTA`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudEliminacionPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)
