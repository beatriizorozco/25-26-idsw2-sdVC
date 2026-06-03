# FUNIBER > Coordinador > solicitarEliminacionPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/solicitarEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/solicitarEliminacionPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/solicitarEliminacionPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/solicitarEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar el registro de una solicitud de eliminación de perfil desde las opciones de perfil. Al confirmar, el sistema crea la solicitud y cierra la sesión.

## Diagrama de secuencia

|![Diseño: solicitarEliminacionPerfil()](/images/RUP/02-diseño/casos-uso/coordinador/solicitarEliminacionPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **SolicitudEliminacionPerfilModal**: Captura confirmación y motivo.
- **PerfilController**: Expone `POST /api/perfil/solicitud-eliminacion`.
- **SesionService**: Valida y cierra la sesión cuando la solicitud se registra.
- **PerfilService**: Comprueba duplicados y coordina la solicitud.
- **SolicitudEliminacionPerfilRepository**: Persiste la solicitud.

## Decisiones de Diseño

- El frontend muestra confirmación antes de registrar la solicitud.
- La cancelación no llama a la API y mantiene `OPCIONES_PERFIL_ABIERTO`.
- La confirmación comprueba la sesión local y crea una solicitud pendiente.
- Tras confirmar, la sesión queda cerrada y la salida es `SESION_CERRADA`.
- No existe flujo de rechazo en este caso.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/solicitarEliminacionPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/solicitarEliminacionPerfil/README.md)
