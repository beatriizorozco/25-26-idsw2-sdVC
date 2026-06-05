# FUNIBER > Coordinador > editarCargaTrabajo > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarCargaTrabajo/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante `CargaTrabajoIntegrationTests.coordinadorNoPuedeRegistrarMasDeDieciseisHorasDocentes`.

## Resultado

- Se valida que el Coordinador puede solicitar la actualización de carga de otra persona.
- Se comprueba que el backend rechaza cargas docentes superiores a 16 horas para investigadores-docentes.
- Se comprueba que la validación se realiza en backend, aunque el frontend normalice la entrada.
- Se mantiene separada la carga de trabajo del módulo de recompensas por proyectos completados.

## Evidencia manual

- Se revisó en navegador la actualización de carga de `docente.santander`.
- Se confirmó que el formulario mantiene la docencia dentro del límite de 16 horas.
- Se confirmó que el sistema recalcula total semanal y margen docente tras guardar.

