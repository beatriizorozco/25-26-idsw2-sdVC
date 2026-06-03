# FUNIBER > Investigador > solicitarEliminacionPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la solicitud de eliminación del perfil propio del Investigador. La solicitud queda pendiente para revisión del Coordinador.

## Diagrama de secuencia

|![Diseño: solicitarEliminacionPerfil()](/images/RUP/02-diseño/casos-uso/investigador/solicitarEliminacionPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **SolicitudEliminacionPerfilModal**: Captura confirmación y motivo.
- **PerfilController**: Expone `POST /api/perfil/solicitud-eliminacion`.
- **SesionService**: Valida y cierra la sesión cuando la solicitud se registra.
- **PerfilService**: Comprueba duplicados y coordina la solicitud.
- **SolicitudEliminacionPerfilRepository**: Persiste la solicitud.

## Decisiones de Diseño

- El Investigador solo solicita eliminación de su propio perfil.
- El frontend muestra confirmación antes de registrar la solicitud.
- Cancelar no invoca la API y mantiene `OPCIONES_PERFIL_ABIERTO`.
- Confirmar comprueba la sesión local, registra la solicitud y cierra sesión.
- La salida confirmada es `SESION_CERRADA`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/solicitarEliminacionPerfil/README.md)
