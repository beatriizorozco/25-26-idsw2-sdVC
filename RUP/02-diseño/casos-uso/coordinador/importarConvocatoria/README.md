# FUNIBER > Coordinador > importarConvocatoria > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/importarConvocatoria/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/importarConvocatoria/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/importarConvocatoria/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Previsualizar y confirmar la incorporación de una convocatoria procedente de una fuente externa.

## Diagrama de secuencia

|![Diseño: importarConvocatoria()](/images/RUP/02-dise%C3%B1o/casos-uso/coordinador/importarConvocatoria/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Correspondencia con el contexto

- **Entrada y salida**: `CONVOCATORIA_ABIERTA`.
- Confirmar actualiza la convocatoria abierta con la información importada.
- Cancelar mantiene la convocatoria abierta sin persistir cambios.

## Decisiones de diseño

- La importación se divide en **previsualización** y **confirmación**.
- `ImportacionConvocatoriaService` coordina exclusivamente este flujo.
- `RegistroImportadoresConvocatoria` selecciona una implementación compatible de `ImportadorConvocatoria`.
- Cada formato o proveedor implementa el contrato sin modificar Controller, servicio o validadores.
- `ValidadorConvocatoria` comprueba estructura, reglas y duplicados antes de mostrar y antes de guardar.
- La confirmación realiza un `UPSERT` por referencia externa para incorporar o actualizar sin duplicar.
- Cancelar es una decisión visual y no genera escritura.

## Principios SOLID

- **SRP**: Autorización, selección, extracción, validación, coordinación y persistencia están separadas.
- **OCP**: PDF, DOC, XLS, enlaces y futuros proveedores se añaden como nuevos importadores registrados.
- **LSP**: Cualquier importador compatible sustituye al contrato sin alterar el flujo.
- **ISP**: `ImportadorConvocatoria` solo expone compatibilidad y extracción.
- **DIP**: El servicio depende del contrato y del registro, no de formatos concretos.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/importarConvocatoria/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/importarConvocatoria/README.md)
