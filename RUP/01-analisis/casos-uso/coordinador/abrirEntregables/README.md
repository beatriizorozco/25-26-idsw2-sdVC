# FUNIBER > Coordinador > abrirEntregables > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirEntregables/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirEntregables/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirEntregables/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Analizar la consulta y filtrado de los entregables activos del proyecto abierto.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirEntregables()](/images/RUP/01-analisis/casos-uso/coordinador/abrirEntregables/abrirEntregables-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis

- **Vista Boundary**: Atiende la conversación con el Coordinador, presenta la información y captura sus decisiones.
- **EntregableController**: Coordina permisos, validaciones, navegación y operaciones del caso de uso.
- **Entidades y repositorios**: ProyectoRepository, EntregableRepository, Proyecto y Entregable.

## Flujo de colaboración

1. **Entrada**: PROYECTO_ABIERTO o ENTREGABLE_ABIERTO invoca abrirEntregables().
2. **Coordinación**: La vista delega en EntregableController.
3. **Entidades**: Valida el proyecto y recupera únicamente sus entregables activos.
4. **Salida**: El caso finaliza en ENTREGABLES_ABIERTOS.

## Reglas funcionales

- El listado siempre queda acotado por idProyecto; no existe una consulta global.
- Las decisiones de autorización se coordinan en el controlador; los repositorios abstraen la consulta y persistencia.
- El análisis permanece independiente de React, Spring Boot y cualquier base de datos concreta.

## Colaboraciones relacionadas

- abrirEntregable(), crearEntregable() y abrirProyecto().

## Trazabilidad

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/abrirEntregables/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirEntregables/README.md)
