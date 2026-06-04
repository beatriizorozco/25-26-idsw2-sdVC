# FUNIBER > Investigador > solicitarEliminacionPerfil > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante `PerfilIntegrationTests.investigadorSolicitaEliminacionConCreatedYCierraSesion`.

## Resultado

- Se valida que la solicitud se registra con `201 Created`.
- Se comprueba que queda en estado `PENDIENTE`.
- Se confirma que la sesión queda cerrada después de solicitar la eliminación.
- Se completa evidencia visual manual del modal de solicitud y del intento posterior de acceso con la cuenta eliminada.

## Evidencia visual

|Modal de solicitud vacío|Motivo de eliminación informado|
|-|-|
|![Modal vacío](/images/RUP/04-pruebas/investigador-solicitar-eliminacion-modal-vacio.png)|![Motivo informado](/images/RUP/04-pruebas/investigador-solicitar-eliminacion-motivo.png)|

|Acceso bloqueado tras eliminación|
|-|
|![Login bloqueado](/images/RUP/04-pruebas/investigador-login-bloqueado-tras-eliminacion.png)|
