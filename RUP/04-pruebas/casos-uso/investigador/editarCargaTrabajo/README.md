# FUNIBER > Investigador > editarCargaTrabajo > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/editarCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/editarCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/editarCargaTrabajo/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante `CargaTrabajoIntegrationTests.investigadorDeSedeSinDocenciaNoPuedeRegistrarHorasDocentes`.

## Resultado

- Se valida que un Investigador puede editar su propia carga de trabajo.
- Se comprueba que un investigador de sede sin docencia no puede registrar horas docentes.
- Se comprueba que el backend devuelve error si `investigador.barcelona` intenta guardar docencia mayor que 0.
- Se confirma que la restricción no depende solo del formulario: queda protegida también en API.

## Evidencia manual

- Se revisó en navegador la edición de carga de `docente.santander` con docencia dentro del límite semanal.
- Se revisó en navegador la edición de carga de `investigador.barcelona`, manteniendo docencia 0 y actualizando horas no docentes.
- Se confirmó que el mensaje de éxito aparece tras guardar una carga válida.

