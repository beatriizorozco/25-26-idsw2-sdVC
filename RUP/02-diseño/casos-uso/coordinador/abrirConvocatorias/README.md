# FUNIBER > Coordinador > abrirConvocatorias > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatorias/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirConvocatorias/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirConvocatorias/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Consultar y filtrar el catálogo global de convocatorias disponibles para el Coordinador.

## Diagrama de secuencia

|![Diseño: abrirConvocatorias()](/images/RUP/02-dise%C3%B1o/casos-uso/coordinador/abrirConvocatorias/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Correspondencia con el contexto

- **Entradas válidas**: `PANEL_PRINCIPAL_ABIERTO` y `CONVOCATORIA_ABIERTA`.
- **Salida principal**: `CONVOCATORIAS_ABIERTAS`.
- **Continuidades permitidas**: `abrirConvocatoria()` y `abrirPanelPrincipal()`.
- El estado de entrada no modifica el alcance: ambos recorridos consultan el mismo catálogo global.

## Decisiones de diseño

- `ConvocatoriaController` adapta HTTP y delega la consulta.
- `ConsultaConvocatoriaService` coordina únicamente operaciones de lectura.
- `PoliticaConvocatoria` concentra la autorización del Coordinador.
- `ConvocatoriaRepository` abstrae la consulta filtrada y la persistencia concreta.
- La consulta devuelve también el estado de incorporación al seguimiento interno.

## Principios SOLID

- **SRP**: Controller, servicio de consulta, política y repositorio tienen razones de cambio diferentes.
- **OCP**: Nuevos criterios se incorporan mediante objetos de filtro y consultas del repositorio sin alterar el flujo.
- **ISP**: El servicio de consulta no depende de operaciones de importación.
- **DIP**: El servicio depende de contratos de política y repositorio.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatorias/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirConvocatorias/README.md)
