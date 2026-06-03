# FUNIBER > Coordinador > abrirOpcionesPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirOpcionesPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirOpcionesPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la obtención de las opciones de perfil disponibles para el Coordinador en el MVP actual. El caso abre el perfil propio del Coordinador y presenta, además, el acceso a las solicitudes de eliminación pendientes que solo este rol puede revisar.

## Diagrama de secuencia

|![Diseño: abrirOpcionesPerfil()](/images/RUP/02-diseño/casos-uso/coordinador/abrirOpcionesPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **PerfilPage**: Solicita y presenta la información de perfil propio.
- **PerfilController**: Expone `GET /api/perfil`.
- **SesionService**: Valida la sesión activa y el rol `COORDINADOR`.
- **PerfilService**: Obtiene el perfil asociado a la sesión actual.
- **PerfilRepository**: Recupera los datos del perfil.

## Decisiones de Diseño

- El frontend comprueba la sesión local antes de solicitar los datos.
- La vista renderiza el perfil propio antes de presentar `OPCIONES_PERFIL_ABIERTO`.
- El Coordinador puede editar su perfil propio, solicitar eliminación y revisar solicitudes pendientes.
- La respuesta incluye los datos del perfil de la sesión actual.
- Si no hay sesión activa, la API responde `401 Unauthorized`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesPerfil/README.md)
