# FUNIBER > Investigador > responderPublicacion > Analisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/responderPublicacion/README.md)|**Analisis**|[Diseno](/RUP/02-dise%C3%B1o/casos-uso/investigador/responderPublicacion/README.md)|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Proposito

Registrar una respuesta en una publicacion activa.

## Diagrama de colaboracion

|![Analisis: responderPublicacion()](/images/RUP/01-analisis/casos-uso/investigador/responderPublicacion/responderPublicacion-analisis.svg)|
|-|
|Codigo fuente: [colaboracion.puml](colaboracion.puml)|

## Responsabilidades

- **responderPublicacionView**: Presenta la conversacion y captura las decisiones del Investigador.
- **PublicacionController**: Coordina el caso de uso sin contener reglas de autorizacion ni persistencia.
- **PoliticaPublicacion**: Decide si el actor puede consultar, editar, retirar o acceder al alcance propio.
- **Repositorios especializados**: Persisten publicaciones, respuestas y adjuntos sin mezclar responsabilidades.

## Flujo de colaboracion

1. **Entrada**: PUBLICACION_ABIERTA invoca responderPublicacion().
2. **Autorizacion**: PoliticaPublicacion aplica la regla correspondiente al actor y contexto.
3. **Dominio**: El controlador coordina los repositorios especializados necesarios.
4. **Salida**: El caso finaliza en PUBLICACION_ABIERTA.

## Aplicacion de SOLID

- **SRP**: Vista, coordinacion, autorizacion y persistencia tienen responsabilidades independientes.
- **OCP**: Nuevas reglas de permisos se incorporan mediante PoliticaPublicacion sin duplicar casos de uso.
- **ISP**: Cada repositorio expone solo operaciones de su agregado.
- **DIP**: El controlador depende de abstracciones de politica y repositorio.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/responderPublicacion/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
