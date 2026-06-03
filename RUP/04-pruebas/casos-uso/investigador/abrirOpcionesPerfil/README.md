# FUNIBER > Investigador > abrirOpcionesPerfil > Pruebas

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesPerfil/README.md)|[Analisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesPerfil/README.md)|[Diseno](/RUP/02-diseño/casos-uso/investigador/abrirOpcionesPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirOpcionesPerfil/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante la carga del perfil propio dentro del flujo funcional de perfil.

## Resultado

- Se valida que el Investigador autenticado puede consultar `GET /api/perfil`.
- Se comprueba que la respuesta contiene los datos propios del Investigador.
- Se confirma que el Investigador puede acceder a editar perfil y solicitar eliminacion.
- Se valida que el Investigador no puede listar solicitudes de eliminacion de otros perfiles.
- Queda pendiente completar evidencia visual manual de la pantalla de opciones.
