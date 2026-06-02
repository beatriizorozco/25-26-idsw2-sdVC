# FUNIBER > Investigador > iniciarSesion > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/iniciarSesion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/iniciarSesion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/iniciarSesion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/iniciarSesion/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Objetivo

Comprobar que el Investigador puede iniciar sesión desde `SESION_CERRADA` y alcanzar `PANEL_PRINCIPAL_ABIERTO` con su rol real.

## Casos verificados

|Caso|Resultado esperado|Evidencia|
|-|-|-|
|Iniciar sesión como Investigador|Sesión creada con rol `INVESTIGADOR`|Recorrido HTTP local y helper `iniciarSesion(...)`|
|Solicitar token CSRF|Se entrega nombre de cabecera y token no vacíos|`exponeTokenCsrfParaLaSpa()`|

## Evidencia visual

|Formulario inicial|
|-|
|![Formulario de inicio de sesión](/images/RUP/04-pruebas/iniciarSesion-formulario.png)|

|Credenciales incorrectas|
|-|
|![Mensaje tras introducir credenciales incorrectas](/images/RUP/04-pruebas/iniciarSesion-credenciales-incorrectas.png)|

