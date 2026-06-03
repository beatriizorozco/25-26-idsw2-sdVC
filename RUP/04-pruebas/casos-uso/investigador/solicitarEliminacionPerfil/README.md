# FUNIBER > Investigador > solicitarEliminacionPerfil > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante `PerfilIntegrationTests.investigadorSolicitaEliminacionConCreatedYCierraSesion`.

## Resultado

- Se valida que la solicitud se registra con `201 Created`.
- Se comprueba que queda en estado `PENDIENTE`.
- Se confirma que la sesión queda cerrada después de solicitar la eliminación.
- Queda pendiente completar evidencia visual manual del modal.
