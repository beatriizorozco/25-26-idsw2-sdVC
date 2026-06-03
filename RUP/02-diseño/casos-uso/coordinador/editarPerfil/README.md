# FUNIBER > Coordinador > editarPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la actualización de datos de perfil realizada por el Coordinador. La operación valida sesión, permisos y datos antes de persistir los cambios.

## Diagrama de secuencia

|![Diseño: editarPerfil()](/images/RUP/02-diseño/casos-uso/coordinador/editarPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EditarPerfilForm**: Captura y envía los cambios.
- **PerfilController**: Expone `PUT /api/perfiles/{perfilId}`.
- **SesionService**: Valida la sesión del Coordinador.
- **PerfilService**: Valida permisos y reglas de actualización.
- **PerfilRepository**: Obtiene y persiste el perfil actualizado.

## Decisiones de Diseño

- El Coordinador puede editar perfiles autorizados desde `OPCIONES_PERFIL_ABIERTO`.
- La API devuelve el perfil actualizado para refrescar la vista.
- Los errores de validación devuelven `400 Bad Request`.
- La salida correcta vuelve a `OPCIONES_PERFIL_ABIERTO`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/editarPerfil/README.md)
