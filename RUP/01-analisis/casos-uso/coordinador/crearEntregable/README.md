# FUNIBER > Coordinador > crearEntregable > Análisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearEntregable/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/crearEntregable/README.md)|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Analizar la creación de un entregable dentro del proyecto activo, con archivo inicial opcional.

## Diagrama de colaboración

<div align=center>

|![Análisis: crearEntregable()](/images/RUP/01-analisis/casos-uso/coordinador/crearEntregable/crearEntregable-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis

- **Vista Boundary**: Atiende la conversación con el Coordinador, presenta la información y captura sus decisiones.
- **EntregableController**: Coordina permisos, validaciones, navegación y operaciones del caso de uso.
- **Entidades y repositorios**: ProyectoRepository, EntregableRepository, ArchivoRepository, Proyecto, Entregable y ArchivoAdjunto.

## Flujo de colaboración

1. **Entrada**: ENTREGABLES_ABIERTOS invoca crearEntregable().
2. **Coordinación**: La vista delega en EntregableController.
3. **Entidades**: Valida que el proyecto esté activo, guarda el entregable y registra el archivo opcional.
4. **Salida**: El caso finaliza en ENTREGABLE_ABIERTO.

## Reglas funcionales

- La creación pertenece al proyecto abierto y el archivo conserva quién realizó la subida.
- Las decisiones de autorización se coordinan en el controlador; los repositorios abstraen la consulta y persistencia.
- El análisis permanece independiente de React, Spring Boot y cualquier base de datos concreta.

## Colaboraciones relacionadas

- abrirEntregable(), editarEntregable(), eliminarEntregable() y abrirEntregables().

## Trazabilidad

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/crearEntregable/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Diseño](/RUP/02-diseño/casos-uso/coordinador/crearEntregable/README.md)
