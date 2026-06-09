# FUNIBER > Investigador > abrirInvestigador > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigador/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirInvestigador/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta del perfil visible de un investigador desde el contexto de un proyecto compartido.

## Diagrama de secuencia

|![Diseño: abrirInvestigador()](/images/RUP/02-diseño/casos-uso/investigador/abrirInvestigador/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **InvestigadorDetallePage**: Presenta el perfil visible del investigador seleccionado.
- **InvestigadorController**: Expone `GET /api/investigadores/me/{id}`.
- **SesionService**: Exige una sesión de Investigador.
- **InvestigadorService**: Comprueba la visibilidad y compone el detalle.
- **InvestigadorRepository** y **ProyectoRepository**: Recuperan el perfil y sus proyectos visibles compartidos.

## Decisiones de Diseño

- El Investigador solo puede abrir perfiles compartidos en un proyecto visible.
- `proyectoId` forma parte del contexto para comprobar visibilidad.
- Un perfil ajeno o no visible responde `404 Not Found`.
- El detalle no muestra acciones exclusivas del Coordinador.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigador/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirInvestigador/README.md)
