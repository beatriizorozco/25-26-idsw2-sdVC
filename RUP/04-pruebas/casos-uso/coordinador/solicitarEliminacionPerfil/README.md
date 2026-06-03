# FUNIBER > Coordinador > solicitarEliminacionPerfil > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/solicitarEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/solicitarEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/solicitarEliminacionPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/solicitarEliminacionPerfil/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado indirectamente mediante `PerfilIntegrationTests.noPermiteEliminarElUnicoCoordinadorActivo`.

## Resultado

- Se valida que el Coordinador puede registrar una solicitud de eliminación propia con `201 Created`.
- Se comprueba que la solicitud queda pendiente para revisión.
- La eliminación posterior se rechaza si implicaría desactivar al único Coordinador activo.
- Queda pendiente completar evidencia visual manual del modal.
