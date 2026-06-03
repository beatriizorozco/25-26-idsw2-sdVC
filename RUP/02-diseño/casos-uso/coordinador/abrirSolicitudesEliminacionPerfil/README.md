# FUNIBER > Coordinador > abrirSolicitudesEliminacionPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar el listado de solicitudes pendientes de eliminación de perfil accesible para el Coordinador.

## Diagrama de secuencia

|![Diseño: abrirSolicitudesEliminacionPerfil()](/images/RUP/02-diseño/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **SolicitudesEliminacionPerfilPage**: Solicita y presenta el listado.
- **SolicitudEliminacionPerfilController**: Expone `GET /api/solicitudes-eliminacion-perfil`.
- **SesionService**: Valida que la sesión sea de Coordinador.
- **SolicitudEliminacionPerfilService**: Coordina listado y filtrado.
- **SolicitudEliminacionPerfilRepository**: Recupera solicitudes pendientes.

## Decisiones de Diseño

- Solo el Coordinador puede abrir este listado.
- El filtrado viaja como parámetro de consulta opcional.
- La salida correcta es `SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS`.
- Desde el listado se puede abrir una solicitud concreta.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudesEliminacionPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)
