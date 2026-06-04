# FUNIBER > Investigador > abrirOpcionesCargaTrabajo > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta de la carga de trabajo propia del Investigador. El caso permite visualizar su dedicación y, solo si su sede lo clasifica como investigador-docente, su margen frente al límite docente de 16 horas semanales.

## Diagrama de secuencia

|![Diseño: abrirOpcionesCargaTrabajo()](/images/RUP/02-diseño/casos-uso/investigador/abrirOpcionesCargaTrabajo/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **CargaTrabajoPropiaPage**: Presenta el resumen y detalle de carga del Investigador autenticado.
- **CargaTrabajoController**: Expone `GET /api/carga-trabajo/me`.
- **SesionService**: Valida que exista una sesión activa.
- **CargaTrabajoService**: Obtiene la carga propia y calcula el resumen personal.
- **CargaTrabajoRepository**: Recupera la carga asociada al usuario autenticado.

## Decisiones de Diseño

- El Investigador solo consulta su propia carga.
- La API no acepta identificadores externos para este rol.
- La carga docente se muestra junto al margen respecto a 16 horas semanales solo en sedes con docencia investigadora.
- Si la sede clasifica al usuario como solo investigador, el resumen omite margen docente.
- Las recompensas quedan fuera de este caso de uso y se consultan desde el módulo de recompensas cuando existan proyectos completados.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)
