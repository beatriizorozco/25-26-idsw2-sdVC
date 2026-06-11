# FUNIBER > Investigador > abrirMisPublicaciones > Analisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirMisPublicaciones/README.md)|**Analisis**|[Diseno](/RUP/02-dise%C3%B1o/casos-uso/investigador/abrirMisPublicaciones/README.md)|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Proposito

Consultar y filtrar las publicaciones creadas por el Investigador autenticado.

## Diagrama de colaboracion

|![Analisis: abrirMisPublicaciones()](/images/RUP/01-analisis/casos-uso/investigador/abrirMisPublicaciones/abrirMisPublicaciones-analisis.svg)|
|-|
|Codigo fuente: [colaboracion.puml](colaboracion.puml)|

## Responsabilidades

- **abrirMisPublicacionesView**: Presenta la conversacion y captura las decisiones del Investigador.
- **PublicacionController**: Coordina el caso de uso sin contener reglas de autorizacion ni persistencia.
- **PoliticaPublicacion**: Decide si el actor puede consultar, editar, retirar o acceder al alcance propio.
- **Repositorios especializados**: Persisten publicaciones, respuestas y adjuntos sin mezclar responsabilidades.

## Flujo de colaboracion

1. **Entrada**: PANEL_PRINCIPAL_ABIERTO invoca abrirMisPublicaciones().
2. **Autorizacion**: PoliticaPublicacion aplica la regla correspondiente al actor y contexto.
3. **Dominio**: El controlador coordina los repositorios especializados necesarios.
4. **Salida**: El caso finaliza en MIS_PUBLICACIONES_ABIERTAS.

## Aplicacion de SOLID

- **SRP**: Vista, coordinacion, autorizacion y persistencia tienen responsabilidades independientes.
- **OCP**: Nuevas reglas de permisos se incorporan mediante PoliticaPublicacion sin duplicar casos de uso.
- **ISP**: Cada repositorio expone solo operaciones de su agregado.
- **DIP**: El controlador depende de abstracciones de politica y repositorio.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirMisPublicaciones/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
