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

- **EditarPerfilForm**: Precarga el formulario con los datos actuales y captura los cambios del Investigador.
- **PerfilController**: Expone `PATCH /api/perfil`.
- **SesionService**: Valida la sesión.
- **PerfilService**: Valida que el perfil pertenezca al usuario autenticado.
- **PerfilRepository**: Persiste los cambios.

## Decisiones de Diseño

- El Investigador solo puede editar su propio perfil.
- El frontend precarga el formulario con el `PerfilResponse` disponible antes de enviar cambios.
- El frontend comprueba la sesión local antes de invocar la API.
- La actualización es parcial mediante `PATCH`; solo se sustituyen los campos modificados.
- La vista actualiza los datos visibles y notifica éxito antes de volver a `OPCIONES_PERFIL_ABIERTO`.
- La salida correcta vuelve a `OPCIONES_PERFIL_ABIERTO`.
- Los errores de validación devuelven `400 Bad Request`.
- La API no acepta identificadores externos para este rol.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarPerfil/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/editarPerfil/README.md)
