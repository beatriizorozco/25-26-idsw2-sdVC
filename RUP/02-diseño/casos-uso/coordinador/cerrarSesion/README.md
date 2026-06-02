# FUNIBER > Coordinador > cerrarSesion > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/cerrarSesion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/cerrarSesion/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/cerrarSesion/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/cerrarSesion/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar el cierre de sesión del Coordinador desde `PANEL_PRINCIPAL_ABIERTO`. El frontend solicita confirmación y solo invalida la sesión del servidor cuando el actor confirma.

## Diagrama de secuencia

|![Diseño: cerrarSesion()](/images/RUP/02-diseño/casos-uso/coordinador/cerrarSesion/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **PanelPrincipalPage**: Recibe la solicitud de cierre.
- **ConfirmarCierreSesionModal**: Presenta las opciones de confirmar y cancelar.
- **AuthController**: Expone `POST /api/auth/logout`.
- **SesionService**: Invalida la sesión activa.

## Decisiones de Diseño

- Cancelar no genera ninguna petición a la API y conserva `PANEL_PRINCIPAL_ABIERTO`.
- Confirmar envía la cookie de sesión y el token CSRF, invalida la sesión, expira la cookie y presenta `SESION_CERRADA`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/cerrarSesion/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/cerrarSesion/README.md)
