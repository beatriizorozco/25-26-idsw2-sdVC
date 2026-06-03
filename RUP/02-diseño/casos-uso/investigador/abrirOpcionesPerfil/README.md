# FUNIBER > Investigador > abrirOpcionesPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirOpcionesPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirOpcionesPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la obtención de las opciones de perfil propias del Investigador.

## Diagrama de secuencia

|![Diseño: abrirOpcionesPerfil()](/images/RUP/02-diseño/casos-uso/investigador/abrirOpcionesPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **PerfilPage**: Solicita y presenta el perfil propio.
- **PerfilController**: Expone `GET /api/perfil`.
- **SesionService**: Valida la sesión activa.
- **PerfilService**: Obtiene el perfil asociado al usuario autenticado.
- **PerfilRepository**: Recupera el perfil.

## Decisiones de Diseño

- El Investigador no envía `perfilId`; solo puede abrir su propio perfil.
- La respuesta incluye acciones propias: editar, solicitar eliminación y volver al panel.
- La salida correcta es `OPCIONES_PERFIL_ABIERTO`.
- Si no hay sesión activa, la API responde `401 Unauthorized`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesPerfil/README.md)
