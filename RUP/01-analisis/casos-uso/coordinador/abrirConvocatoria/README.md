# FUNIBER > Coordinador > abrirConvocatoria > Análisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatoria/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirConvocatoria/README.md)|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Consultar el detalle completo de una convocatoria importada y ofrecer las continuidades permitidas.

## Diagrama de colaboración

|![Análisis: abrirConvocatoria()](/images/RUP/01-analisis/casos-uso/coordinador/abrirConvocatoria/abrirConvocatoria-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

## Responsabilidades

- **DetalleConvocatoriaView**: Presenta la convocatoria y permite volver al listado o solicitar su importación.
- **ConvocatoriaController**: Coordina la recuperación del detalle solicitado.
- **PoliticaConvocatoria**: Autoriza la consulta sin acceder a datos.
- **ConvocatoriaRepository**: Recupera la convocatoria identificada.
- **Convocatoria**: Representa datos generales, plazos, requisitos, documentación y origen externo.

## Flujo de colaboración

1. `CONVOCATORIAS_ABIERTAS` invoca `abrirConvocatoria(id)`.
2. La política autoriza la consulta.
3. El repositorio recupera la convocatoria seleccionada.
4. La vista presenta el detalle y finaliza en `CONVOCATORIA_ABIERTA`.
5. Desde el detalle se puede invocar `importarConvocatoria()` o regresar mediante `abrirConvocatorias()`.

## Aplicación de SOLID

- **SRP**: La vista presenta, el controlador coordina, la política autoriza y el repositorio consulta.
- **OCP**: Nuevas reglas de consulta se añaden en la política sin alterar la recuperación del detalle.
- **DIP**: El controlador no depende de una tecnología concreta de persistencia.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatoria/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
