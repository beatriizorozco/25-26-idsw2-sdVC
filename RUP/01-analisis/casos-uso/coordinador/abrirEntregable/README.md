# FUNIBER > Coordinador > abrirEntregable > Análisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirEntregable/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirEntregable/README.md)|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Analizar la consulta del detalle, archivos y acciones disponibles de un entregable activo.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirEntregable()](/images/RUP/01-analisis/casos-uso/coordinador/abrirEntregable/abrirEntregable-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis

- **Vista Boundary**: Atiende la conversación con el Coordinador, presenta la información y captura sus decisiones.
- **EntregableController**: Coordina permisos, validaciones, navegación y operaciones del caso de uso.
- **Entidades y repositorios**: EntregableRepository, ArchivoRepository, Entregable y ArchivoAdjunto.

## Flujo de colaboración

1. **Entrada**: ENTREGABLES_ABIERTOS invoca abrirEntregable().
2. **Coordinación**: La vista delega en EntregableController.
3. **Entidades**: Recupera el entregable activo y sus archivos adjuntos.
4. **Salida**: El caso finaliza en ENTREGABLE_ABIERTO.

## Reglas funcionales

- El Coordinador dispone de las acciones de edición y retirada.
- Las decisiones de autorización se coordinan en el controlador; los repositorios abstraen la consulta y persistencia.
- El análisis permanece independiente de React, Spring Boot y cualquier base de datos concreta.

## Colaboraciones relacionadas

- editarEntregable(), eliminarEntregable() y abrirEntregables().

## Trazabilidad

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/abrirEntregable/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirEntregable/README.md)
