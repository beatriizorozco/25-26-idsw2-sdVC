# FUNIBER > Coordinador > abrirInvestigador > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirInvestigador/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirInvestigador/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirInvestigador/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta del perfil de un investigador por el Coordinador, incluyendo sus proyectos asociados dentro del alcance permitido.

## Diagrama de secuencia

|![Diseño: abrirInvestigador()](/images/RUP/02-diseño/casos-uso/coordinador/abrirInvestigador/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **InvestigadorDetallePage**: Presenta el perfil consultado.
- **InvestigadorController**: Expone `GET /api/investigadores/{id}`.
- **SesionService**: Recupera la sesión autenticada y su rol.
- **InvestigadorService**: Recupera el perfil activo y compone sus relaciones visibles.
- **UsuarioRepository** y **ProyectoRepository**: Recuperan el investigador y sus proyectos asociados.

## Decisiones de Diseño

- Solo se pueden consultar investigadores activos.
- El mismo detalle puede abrirse desde directorio global o desde un proyecto, manteniendo `proyectoId` como contexto opcional.
- Un investigador inexistente, inactivo o fuera del alcance visible responde `404 Not Found`.
- El detalle incluye proyectos asociados para mantener la continuidad de navegación.
- No se introducen acciones de edición porque ese caso no forma parte del bloque 6.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirInvestigador/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirInvestigador/README.md)
