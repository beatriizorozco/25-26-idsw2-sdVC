# FUNIBER > Coordinador > eliminarInvestigador > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarInvestigador/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la desasignación segura de un investigador del proyecto sin eliminar su perfil ni su participación histórica.

## Diagrama de secuencia

|![Diseño: eliminarInvestigador()](/images/RUP/02-diseño/casos-uso/coordinador/eliminarInvestigador/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **ProyectoDetallePage**: Presenta el miembro seleccionado y solicita confirmación.
- **ProyectoController**: Expone `PATCH /api/proyectos/{id}/investigadores/{investigadorId}/desasignacion`.
- **SesionService**: Exige una sesión de Coordinador.
- **ProyectoService**: Valida y registra la desasignación lógica.
- **ProyectoRepository** e **InvestigadorRepository**: Comprueban la pertenencia, actualizan el equipo activo y conservan la participación histórica.

## Decisiones de Diseño

- La API solo se invoca después de confirmación explícita.
- Eliminar al investigador del proyecto significa desasignarlo.
- El perfil, su carga, sus datos personales y la participación histórica permanecen intactos.
- La desasignación registra fecha y Coordinador responsable.
- Cancelar conserva `PROYECTO_ABIERTO`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarInvestigador/README.md)
