# FUNIBER > Coordinador > iniciarSesion > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/iniciarSesion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/iniciarSesion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/iniciarSesion/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Objetivo

Comprobar que el Coordinador puede iniciar sesión desde `SESION_CERRADA`, que las credenciales incorrectas no crean una sesión y que el reintento posterior permite alcanzar `PANEL_PRINCIPAL_ABIERTO`.

## Casos verificados

|Caso|Resultado esperado|Evidencia|
|-|-|-|
|Solicitar token CSRF|Se entrega nombre de cabecera y token no vacíos|`exponeTokenCsrfParaLaSpa()`|
|Introducir contraseña incorrecta|Respuesta `401` con mensaje de error|`rechazaCredencialesIncorrectas()`|
|Reintentar con credenciales válidas|Sesión creada para `coordinador`|`permiteReintentarTrasCredencialesIncorrectas()`|

## Evidencia visual pendiente

- Captura del mensaje mostrado tras introducir credenciales incorrectas.
- Captura del panel tras completar correctamente el reintento.

