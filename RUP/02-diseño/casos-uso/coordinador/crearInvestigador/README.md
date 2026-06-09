# FUNIBER > Coordinador > crearInvestigador > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearInvestigador/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/crearInvestigador/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar el alta de un nuevo investigador por el Coordinador, validando unicidad y abriendo el perfil registrado.

## Diagrama de secuencia

|![Diseño: crearInvestigador()](/images/RUP/02-diseño/casos-uso/coordinador/crearInvestigador/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **CrearInvestigadorPage**: Presenta el formulario de alta.
- **InvestigadorController**: Expone `POST /api/investigadores`.
- **SesionService**: Exige una sesión de Coordinador.
- **InvestigadorService**: Valida, registra y devuelve el perfil creado.
- **InvestigadorRepository**: Comprueba duplicados y persiste el nuevo investigador.

## Decisiones de Diseño

- El usuario y el correo deben ser únicos antes de persistir el perfil.
- La sede condiciona la futura interpretación docente del perfil, pero no bloquea su alta.
- La cancelación no modifica estado y devuelve al directorio.
- La creación exitosa responde `201 Created` y abre el detalle del investigador nuevo.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearInvestigador/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/crearInvestigador/README.md)
