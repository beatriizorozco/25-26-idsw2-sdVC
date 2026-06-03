# FUNIBER > Coordinador > eliminarPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/eliminarPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la eliminación de un perfil solicitada previamente. El Coordinador confirma la operación, el sistema elimina el perfil y marca la solicitud como resuelta.

## Diagrama de secuencia

|![Diseño: eliminarPerfil()](/images/RUP/02-diseño/casos-uso/coordinador/eliminarPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EliminarPerfilModal**: Muestra la confirmación de eliminación y captura confirmación o cancelación.
- **SolicitudEliminacionPerfilController**: Expone `DELETE /api/solicitudes-eliminacion-perfil/{idSolicitud}/perfil`.
- **SesionService**: Valida el rol Coordinador.
- **PerfilService**: Coordina la eliminación y la resolución de la solicitud.
- **PerfilRepository**: Elimina el perfil.
- **SolicitudEliminacionPerfilRepository**: Marca la solicitud como resuelta.

## Decisiones de Diseño

- Solo el Coordinador puede eliminar perfiles desde una solicitud abierta.
- El frontend muestra confirmación antes de invocar la API.
- Cancelar no invoca la API y mantiene `SOLICITUD_ELIMINACION_PERFIL_ABIERTA`.
- Confirmar invoca la API, elimina el perfil y vuelve a `SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS`.
- No se diseña flujo de rechazo.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarPerfil/README.md)
