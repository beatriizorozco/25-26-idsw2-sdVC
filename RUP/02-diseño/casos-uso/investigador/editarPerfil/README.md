# FUNIBER > Investigador > editarPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/editarPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/editarPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/editarPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la actualización del perfil propio del Investigador.

## Diagrama de secuencia

|![Diseño: editarPerfil()](/images/RUP/02-diseño/casos-uso/investigador/editarPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EditarPerfilForm**: Captura los cambios del Investigador.
- **PerfilController**: Expone `PUT /api/perfil`.
- **SesionService**: Valida la sesión.
- **PerfilService**: Valida que el perfil pertenezca al usuario autenticado.
- **PerfilRepository**: Persiste los cambios.

## Decisiones de Diseño

- El Investigador solo puede editar su propio perfil.
- La salida correcta vuelve a `OPCIONES_PERFIL_ABIERTO`.
- Los errores de validación devuelven `400 Bad Request`.
- La API no acepta identificadores externos para este rol.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/editarPerfil/README.md)
