# FUNIBER > Coordinador > importarConvocatoria > Análisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/importarConvocatoria/README.md)|**Análisis**|Diseño|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Incorporar al seguimiento interno una convocatoria obtenida desde una fuente externa.

## Diagrama de colaboración

|![Análisis: importarConvocatoria()](/images/RUP/01-analisis/casos-uso/coordinador/importarConvocatoria/importarConvocatoria-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

## Responsabilidades

- **ImportarConvocatoriaView**: Captura la fuente, presenta la información extraída y solicita confirmación.
- **ConvocatoriaController**: Coordina la importación sin conocer cómo se interpreta cada fuente.
- **PoliticaConvocatoria**: Autoriza la importación.
- **ImportadorConvocatoria**: Abstracción que transforma una fuente externa en datos normalizados.
- **ValidadorConvocatoria**: Comprueba integridad y duplicados antes de persistir.
- **ConvocatoriaRepository**: Consulta referencias existentes y guarda la convocatoria confirmada.
- **FuenteConvocatoria**: Representa el archivo o enlace de origen.
- **Convocatoria**: Conserva la información importada y la trazabilidad de su fuente.

## Flujo de colaboración

1. `CONVOCATORIA_ABIERTA` invoca `importarConvocatoria()`.
2. La vista entrega una `FuenteConvocatoria` al controlador.
3. La política autoriza al Coordinador.
4. `ImportadorConvocatoria` extrae datos normalizados desde la fuente.
5. `ValidadorConvocatoria` comprueba integridad y duplicados mediante el repositorio.
6. Tras la confirmación, el repositorio guarda la convocatoria y el caso permanece en `CONVOCATORIA_ABIERTA`.
7. Si se cancela o falla la validación, no se persisten cambios.

## Aplicación de SOLID

- **SRP**: Importar, validar, autorizar, persistir y presentar son responsabilidades independientes.
- **OCP**: Nuevos formatos o proveedores se incorporan implementando `ImportadorConvocatoria`, sin modificar el controlador.
- **ISP**: El importador solo expone la operación necesaria para extraer datos normalizados.
- **DIP**: El controlador depende de la abstracción `ImportadorConvocatoria`, no de PDF, DOC, XLS o enlaces concretos.

## Decisión de análisis

La importación no es creación manual ni edición. La convocatoria procede de una fuente externa y debe conservar su referencia de origen para evitar duplicados y mantener trazabilidad.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/importarConvocatoria/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
