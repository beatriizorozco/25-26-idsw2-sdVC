# FUNIBER > Coordinador > editarEntregable > Análisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarEntregable/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarEntregable/README.md)|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Analizar la modificación de un entregable activo y la incorporación opcional de una nueva versión de archivo.

## Diagrama de colaboración

<div align=center>

|![Análisis: editarEntregable()](/images/RUP/01-analisis/casos-uso/coordinador/editarEntregable/editarEntregable-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis

- **Vista Boundary**: Atiende la conversación con el Coordinador, presenta la información y captura sus decisiones.
- **EntregableController**: Coordina permisos, validaciones, navegación y operaciones del caso de uso.
- **Entidades y repositorios**: EntregableRepository, ArchivoRepository, Entregable y ArchivoAdjunto.

## Flujo de colaboración

1. **Entrada**: ENTREGABLE_ABIERTO invoca editarEntregable().
2. **Coordinación**: La vista delega en EntregableController.
3. **Entidades**: Recupera el entregable activo, actualiza sus datos y añade una versión de archivo cuando se proporciona.
4. **Salida**: El caso finaliza en ENTREGABLE_ABIERTO.

## Reglas funcionales

- Los archivos nuevos generan versiones y no sustituyen el histórico existente.
- Las decisiones de autorización se coordinan en el controlador; los repositorios abstraen la consulta y persistencia.
- El análisis permanece independiente de React, Spring Boot y cualquier base de datos concreta.

## Colaboraciones relacionadas

- eliminarEntregable() y abrirEntregables().

## Trazabilidad

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/editarEntregable/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Diseño](/RUP/02-diseño/casos-uso/coordinador/editarEntregable/README.md)
