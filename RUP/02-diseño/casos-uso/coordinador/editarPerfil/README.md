# FUNIBER > Coordinador > editarPerfil > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarPerfil/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la actualización de los datos del perfil propio del Coordinador. La operación valida sesión, permisos y datos antes de persistir los cambios.

## Diagrama de secuencia

|![Diseño: editarPerfil()](/images/RUP/02-diseño/casos-uso/coordinador/editarPerfil/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EditarPerfilForm**: Precarga el formulario con los datos actuales, captura y envía los cambios.
- **PerfilController**: Expone `PATCH /api/perfil`.
- **SesionService**: Valida la sesión del Coordinador.
- **PerfilService**: Actualiza el perfil asociado a la sesión actual.
- **PerfilRepository**: Obtiene y persiste el perfil actualizado.

## Decisiones de Diseño

- El Coordinador edita su perfil propio desde `OPCIONES_PERFIL_ABIERTO`.
- El frontend precarga el formulario con el `PerfilResponse` disponible antes de enviar cambios.
- El frontend comprueba la sesión local antes de invocar la API.
- La actualización es parcial mediante `PATCH`.
- La API devuelve el perfil actualizado para refrescar la vista.
- La vista actualiza los datos visibles y notifica éxito antes de volver a `OPCIONES_PERFIL_ABIERTO`.
- Los errores de validación devuelven `400 Bad Request`.
- La salida correcta vuelve a `OPCIONES_PERFIL_ABIERTO`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/editarPerfil/README.md)
