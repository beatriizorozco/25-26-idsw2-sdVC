# FUNIBER > Investigador > abrirInvestigador > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigador/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirInvestigador/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta del perfil visible de un investigador desde el directorio general o desde el contexto de un proyecto compartido.

## Diagrama de secuencia

|![Diseño: abrirInvestigador()](/images/RUP/02-diseño/casos-uso/investigador/abrirInvestigador/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **InvestigadorDetallePage**: Presenta el perfil visible del investigador seleccionado.
- **InvestigadorController**: Expone `GET /api/investigadores/{id}`.
- **SesionService**: Recupera la sesión autenticada y su rol.
- **InvestigadorService**: Comprueba la visibilidad y compone el detalle.
- **UsuarioRepository** y **ProyectoRepository**: Recuperan el perfil y sus proyectos visibles compartidos.

## Decisiones de Diseño

- El Investigador puede abrir perfiles activos desde el directorio global o desde un proyecto compartido.
- `proyectoId` forma parte del contexto cuando la consulta nace desde un proyecto.
- El mismo recurso de detalle se reutiliza para Coordinador e Investigador con validación de alcance en servicio.
- Un perfil ajeno o no visible responde `404 Not Found`.
- El detalle solo muestra proyectos visibles y no incluye acciones exclusivas del Coordinador.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigador/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirInvestigador/README.md)
