# FUNIBER > Investigador > crearEntregable > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/crearEntregable/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/crearEntregable/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/crearEntregable/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Analizar la creación de un entregable propio dentro de un proyecto en el que participa.

## Diagrama de colaboración

<div align=center>

|![Análisis: crearEntregable()](/images/RUP/01-analisis/casos-uso/investigador/crearEntregable/crearEntregable-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis

- **Vista Boundary**: Atiende la conversación con el Investigador, presenta la información y captura sus decisiones.
- **EntregableController**: Coordina permisos, validaciones, navegación y operaciones del caso de uso.
- **Entidades y repositorios**: ProyectoRepository, EntregableRepository, ArchivoRepository, Proyecto, Entregable y ArchivoAdjunto.

## Flujo de colaboración

1. **Entrada**: ENTREGABLES_ABIERTOS invoca crearEntregable().
2. **Coordinación**: La vista delega en EntregableController.
3. **Entidades**: Verifica participación, asigna al Investigador autenticado como autor y guarda el archivo opcional.
4. **Salida**: El caso finaliza en ENTREGABLE_ABIERTO.

## Reglas funcionales

- El autor no se elige manualmente: siempre es el Investigador autenticado.
- Las decisiones de autorización se coordinan en el controlador; los repositorios abstraen la consulta y persistencia.
- El análisis permanece independiente de React, Spring Boot y cualquier base de datos concreta.

## Colaboraciones relacionadas

- editarEntregable(), eliminarEntregable() y abrirEntregables().

## Trazabilidad

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/investigador/crearEntregable/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Diseño](/RUP/02-diseño/casos-uso/investigador/crearEntregable/README.md)
