# FUNIBER > Investigador > abrirPublicacion > Diseno

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirPublicacion/README.md)|[Analisis](/RUP/01-analisis/casos-uso/investigador/abrirPublicacion/README.md)|**Diseno**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirPublicacion/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Proposito

Consultar el detalle, respuestas y adjuntos de una publicacion activa.

## Diagrama de secuencia

|![Diseno: abrirPublicacion()](/images/RUP/02-dise%C3%B1o/casos-uso/investigador/abrirPublicacion/secuencia.svg)|
|-|
|Codigo fuente: [secuencia.puml](secuencia.puml)|

## Decisiones de diseno

- PublicacionController adapta HTTP y delega la operacion.
- PublicacionService coordina el flujo sin asumir persistencia ni autorizacion.
- PoliticaPublicacion concentra permisos globales y comprobaciones de autoria.
- Los repositorios de publicaciones, respuestas y adjuntos permanecen separados.
- Las consultas llegan explicitamente a Base de Datos.
- Las retiradas son logicas y conservan fecha, motivo y responsable.

## Principios SOLID

- **SRP**: Cada componente tiene una unica razon de cambio.
- **OCP**: Las reglas de autorizacion pueden ampliarse mediante la politica sin duplicar servicios.
- **ISP**: Cada repositorio ofrece exclusivamente operaciones de su agregado.
- **DIP**: Servicio y politica dependen de contratos, no de detalles de persistencia.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirPublicacion/README.md)
- [Analisis](/RUP/01-analisis/casos-uso/investigador/abrirPublicacion/README.md)
