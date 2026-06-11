# FUNIBER > Coordinador > eliminarEntregable > Análisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarEntregable/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarEntregable/README.md)|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Analizar la retirada lógica de un entregable tras confirmación expresa.

## Diagrama de colaboración

<div align=center>

|![Análisis: eliminarEntregable()](/images/RUP/01-analisis/casos-uso/coordinador/eliminarEntregable/eliminarEntregable-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis

- **Vista Boundary**: Atiende la conversación con el Coordinador, presenta la información y captura sus decisiones.
- **EntregableController**: Coordina permisos, validaciones, navegación y operaciones del caso de uso.
- **Entidades y repositorios**: EntregableRepository y Entregable.

## Flujo de colaboración

1. **Entrada**: ENTREGABLE_ABIERTO invoca eliminarEntregable().
2. **Coordinación**: La vista delega en EntregableController.
3. **Entidades**: Solicita confirmación y motivo; al confirmar registra fecha, responsable y retirada lógica.
4. **Salida**: El caso finaliza en ENTREGABLES_ABIERTOS si confirma; ENTREGABLE_ABIERTO si cancela.

## Reglas funcionales

- No existe borrado físico: se conservan autoría, proyecto, archivos y versiones.
- Las decisiones de autorización se coordinan en el controlador; los repositorios abstraen la consulta y persistencia.
- El análisis permanece independiente de React, Spring Boot y cualquier base de datos concreta.

## Colaboraciones relacionadas

- abrirEntregables() y abrirEntregable().

## Trazabilidad

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/eliminarEntregable/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarEntregable/README.md)
