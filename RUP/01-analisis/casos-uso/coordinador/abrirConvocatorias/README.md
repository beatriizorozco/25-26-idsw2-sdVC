# FUNIBER > Coordinador > abrirConvocatorias > Análisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatorias/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirConvocatorias/README.md)|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Consultar y filtrar el catálogo global de convocatorias disponibles, indicando cuáles ya se incorporaron al seguimiento interno.

## Diagrama de colaboración

|![Análisis: abrirConvocatorias()](/images/RUP/01-analisis/casos-uso/coordinador/abrirConvocatorias/abrirConvocatorias-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

## Responsabilidades

- **ListarConvocatoriasView**: Presenta los filtros y el listado, y captura la selección del Coordinador.
- **ConvocatoriaController**: Coordina la consulta sin autorizar ni persistir.
- **PoliticaConvocatoria**: Decide si el actor puede consultar o importar convocatorias.
- **ConvocatoriaRepository**: Recupera convocatorias usando los criterios solicitados.
- **Convocatoria**: Conserva la información disponible, su referencia de origen y su estado de incorporación al seguimiento.

## Flujo de colaboración

1. `PANEL_PRINCIPAL_ABIERTO` o `CONVOCATORIA_ABIERTA` invoca `abrirConvocatorias()`.
2. La vista solicita el listado al controlador.
3. La política autoriza la consulta del Coordinador.
4. El repositorio recupera las convocatorias que cumplen el filtro.
5. La vista presenta el resultado y finaliza en `CONVOCATORIAS_ABIERTAS`.

El estado de entrada no altera el comportamiento interno: desde ambos estados se recupera el mismo listado global.

## Aplicación de SOLID

- **SRP**: Vista, coordinación, autorización, persistencia y dominio tienen responsabilidades propias.
- **OCP**: Las reglas de acceso evolucionan en `PoliticaConvocatoria` sin modificar el controlador.
- **ISP**: El repositorio expone únicamente operaciones de consulta y persistencia de convocatorias.
- **DIP**: El controlador colabora con la política y el repositorio como abstracciones del análisis.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatorias/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
