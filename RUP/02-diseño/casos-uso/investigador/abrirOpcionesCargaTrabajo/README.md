# FUNIBER > Investigador > abrirOpcionesCargaTrabajo > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta de la carga de trabajo propia del Investigador. El caso permite visualizar su dedicación y, solo si su sede lo clasifica como investigador-docente, su margen frente al límite docente de 16 horas semanales y las compensaciones pendientes si existe exceso.

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
- **RecompensaRepository**: Recupera compensaciones pendientes del Investigador cuando aplica por sede.

## Decisiones de Diseño

- El Investigador solo consulta su propia carga.
- La API no acepta identificadores externos para este rol.
- El frontend comprueba la sesión local antes de solicitar datos.
- La carga docente se muestra junto al margen o exceso respecto a 16 horas semanales solo en sedes con docencia investigadora.
- Si la sede clasifica al usuario como solo investigador, el resumen omite exceso docente y compensaciones por docencia.
- Las compensaciones aparecen solo como información derivada del exceso docente aplicable.
- La salida correcta presenta `OPCIONES_CARGA_TRABAJO_ABIERTAS`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)
