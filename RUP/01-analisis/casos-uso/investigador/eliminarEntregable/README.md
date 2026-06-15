# FUNIBER > Investigador > eliminarEntregable > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/eliminarEntregable/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/eliminarEntregable/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/eliminarEntregable/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Analizar la retirada lógica de un entregable propio tras confirmación expresa.

## Diagrama de colaboración

<div align=center>

|![Análisis: eliminarEntregable()](/images/RUP/01-analisis/casos-uso/investigador/eliminarEntregable/eliminarEntregable-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis

- **Vista Boundary**: Atiende la conversación con el Investigador, presenta la información y captura sus decisiones.
- **EntregableController**: Coordina permisos, validaciones, navegación y operaciones del caso de uso.
- **Entidades y repositorios**: EntregableRepository y Entregable.

## Flujo de colaboración

1. **Entrada**: ENTREGABLE_ABIERTO invoca eliminarEntregable().
2. **Coordinación**: La vista delega en EntregableController.
3. **Entidades**: Comprueba la autoría, solicita motivo y registra la retirada lógica con fecha y responsable.
4. **Salida**: El caso finaliza en ENTREGABLES_ABIERTOS si confirma; ENTREGABLE_ABIERTO si cancela.

## Reglas funcionales

- Solo el autor puede retirar y nunca se eliminan físicamente archivos, versiones ni relaciones.
- Las decisiones de autorización se coordinan en el controlador; los repositorios abstraen la consulta y persistencia.
- El análisis permanece independiente de React, Spring Boot y cualquier base de datos concreta.

## Colaboraciones relacionadas

- abrirEntregables() y abrirEntregable().

## Trazabilidad

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/investigador/eliminarEntregable/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Diseño](/RUP/02-diseño/casos-uso/investigador/eliminarEntregable/README.md)
