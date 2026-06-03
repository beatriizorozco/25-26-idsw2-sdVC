# FUNIBER > Investigador > editarPerfil > Pruebas

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarPerfil/README.md)|[Analisis](/RUP/01-analisis/casos-uso/investigador/editarPerfil/README.md)|[Diseno](/RUP/02-diseño/casos-uso/investigador/editarPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/editarPerfil/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante la misma ruta funcional de actualizacion de perfil propio.

## Resultado

- Se valida que `PATCH /api/perfil` actualiza el perfil del usuario autenticado.
- Se comprueba que el flujo no permite editar perfiles de otros usuarios desde la interfaz del Investigador.
- Se confirma que los datos modificados se devuelven en la respuesta y pueden mostrarse de nuevo en pantalla.
- Queda pendiente completar evidencia visual manual del formulario del Investigador.
