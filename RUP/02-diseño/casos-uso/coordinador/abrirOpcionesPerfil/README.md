# FUNIBER > Coordinador > abrirOpcionesPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirOpcionesPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirOpcionesPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la obtención de las opciones de perfil disponibles para el Coordinador. El diseño contempla que el caso puede abrir el perfil propio o el perfil de un investigador según el contexto de entrada.

## Diagrama de secuencia

|![Diseño: abrirOpcionesPerfil()](/images/RUP/02-diseño/casos-uso/coordinador/abrirOpcionesPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **PerfilPage**: Solicita y presenta la información de perfil.
- **PerfilController**: Expone `GET /api/perfil` y `GET /api/perfiles/{perfilId}`.
- **SesionService**: Valida la sesión activa y el rol `COORDINADOR`.
- **PerfilService**: Decide si se consulta el perfil propio o el perfil indicado.
- **PerfilRepository**: Recupera los datos del perfil.

## Decisiones de Diseño

- El contexto de entrada determina si se usa perfil propio o `perfilId`.
- El Coordinador puede acceder a perfil propio y perfiles de investigadores.
- La respuesta incluye datos del perfil y acciones disponibles.
- Si no hay sesión activa, la API responde `401 Unauthorized`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesPerfil/README.md)
