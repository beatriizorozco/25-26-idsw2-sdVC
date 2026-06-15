# FUNIBER > Coordinador > agregarInvestigador > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/agregarInvestigador/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/agregarInvestigador/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/agregarInvestigador/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la asociación de un investigador existente a un proyecto mediante una selección informada.

## Diagrama de secuencia

|![Diseño: agregarInvestigador()](/images/RUP/02-diseño/casos-uso/coordinador/agregarInvestigador/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **AgregarInvestigadorDialog**: Presenta candidatos y solicita confirmación.
- **ProyectoController**: Expone candidatos y la asociación.
- **SesionService**: Exige una sesión de Coordinador.
- **ProyectoService**: Evalúa compatibilidad, disponibilidad y menor carga.
- **ProyectoRepository**, **InvestigadorRepository** y **CargaTrabajoRepository**: Recuperan la información necesaria, revalidan la candidatura y registran la participación activa.

## Decisiones de Diseño

- Solo se muestran perfiles existentes todavía no asociados.
- La recomendación prioriza al candidato compatible y disponible con menor carga.
- La recomendación informa, pero el Coordinador confirma la selección.
- La disponibilidad y carga se revalidan al confirmar para evitar asignaciones basadas en información desactualizada.
- La asignación registra fecha y Coordinador responsable, conservando el histórico de participaciones.
- La operación asocia el perfil; nunca crea ni modifica sus datos personales.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/agregarInvestigador/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/agregarInvestigador/README.md)
