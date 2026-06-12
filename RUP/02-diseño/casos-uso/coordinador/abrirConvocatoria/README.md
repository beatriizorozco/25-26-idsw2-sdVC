# FUNIBER > Coordinador > abrirConvocatoria > Diseño

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatoria/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirConvocatoria/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirConvocatoria/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Consultar el detalle completo de la convocatoria seleccionada.

## Diagrama de secuencia

|![Diseño: abrirConvocatoria()](/images/RUP/02-dise%C3%B1o/casos-uso/coordinador/abrirConvocatoria/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Correspondencia con el contexto

- **Entrada válida**: `CONVOCATORIAS_ABIERTAS`.
- **Salida principal**: `CONVOCATORIA_ABIERTA`.
- **Continuidades permitidas**: `importarConvocatoria()` y `abrirConvocatorias()`.
- No se añade una navegación directa al panel porque no existe en el diagrama de contexto.

## Decisiones de diseño

- La identidad de la convocatoria se recibe desde la selección del listado.
- `ConsultaConvocatoriaService` recupera el detalle sin asumir importación ni persistencia.
- `PoliticaConvocatoria` valida el acceso antes de presentar los datos.
- El repositorio devuelve datos generales, plazos, requisitos, documentación, contacto y referencia externa.

## Principios SOLID

- **SRP**: El servicio de consulta no contiene reglas de importación.
- **OCP**: El detalle puede ampliarse con nuevos campos sin modificar autorización o navegación.
- **ISP**: La consulta depende únicamente de operaciones de lectura.
- **DIP**: El servicio no conoce la implementación concreta de la base de datos.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatoria/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirConvocatoria/README.md)
