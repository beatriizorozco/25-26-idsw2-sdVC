# FUNIBER > Coordinador > eliminarPerfil > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarPerfil/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante `PerfilIntegrationTests.coordinadorPuedeEliminarPerfilDeInvestigadorDesdeSolicitud`, `PerfilIntegrationTests.solicitudesResueltasNoAparecenEnListadoPendiente` y `PerfilIntegrationTests.noPermiteEliminarElUnicoCoordinadorActivo`.

## Resultado

- Se valida que el Coordinador puede confirmar una solicitud pendiente y desactivar el perfil asociado.
- Se comprueba que la solicitud queda marcada como `RESUELTA`.
- Se valida que una solicitud resuelta deja de mostrarse en el listado pendiente.
- Se protege el sistema frente a la eliminación del único Coordinador activo.
- Queda pendiente completar evidencia visual manual del modal de confirmación.
