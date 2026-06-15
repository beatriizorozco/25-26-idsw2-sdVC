# FUNIBER > Investigador > abrirEntregable > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirEntregable/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirEntregable/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirEntregable/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Analizar la consulta del detalle y archivos de un entregable del proyecto, preparando acciones según autoría.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirEntregable()](/images/RUP/01-analisis/casos-uso/investigador/abrirEntregable/abrirEntregable-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis

- **Vista Boundary**: Atiende la conversación con el Investigador, presenta la información y captura sus decisiones.
- **EntregableController**: Coordina permisos, validaciones, navegación y operaciones del caso de uso.
- **Entidades y repositorios**: ProyectoRepository, EntregableRepository, ArchivoRepository, Proyecto, Entregable y ArchivoAdjunto.

## Flujo de colaboración

1. **Entrada**: ENTREGABLES_ABIERTOS invoca abrirEntregable().
2. **Coordinación**: La vista delega en EntregableController.
3. **Entidades**: Recupera el entregable activo, verifica participación y obtiene sus archivos.
4. **Salida**: El caso finaliza en ENTREGABLE_ABIERTO.

## Reglas funcionales

- Puede consultar entregables del proyecto, pero editar o retirar únicamente los propios.
- Las decisiones de autorización se coordinan en el controlador; los repositorios abstraen la consulta y persistencia.
- El análisis permanece independiente de React, Spring Boot y cualquier base de datos concreta.

## Colaboraciones relacionadas

- editarEntregable(), eliminarEntregable() y abrirEntregables().

## Trazabilidad

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/investigador/abrirEntregable/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Diseño](/RUP/02-diseño/casos-uso/investigador/abrirEntregable/README.md)
