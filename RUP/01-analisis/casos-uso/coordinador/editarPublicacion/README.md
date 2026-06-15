# FUNIBER > Coordinador > editarPublicacion > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarPublicacion/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarPublicacion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarPublicacion/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Actualizar una publicacion propia o ajena mediante el permiso global del Coordinador.

## Diagrama de colaboracion

|![Analisis: editarPublicacion()](/images/RUP/01-analisis/casos-uso/coordinador/editarPublicacion/editarPublicacion-analisis.svg)|
|-|
|Codigo fuente: [colaboracion.puml](colaboracion.puml)|

## Responsabilidades

- **editarPublicacionView**: Presenta la conversacion y captura las decisiones del Coordinador.
- **PublicacionController**: Coordina el caso de uso sin contener reglas de autorizacion ni persistencia.
- **PoliticaPublicacion**: Decide si el actor puede consultar, editar, retirar o acceder al alcance propio.
- **Repositorios especializados**: Persisten publicaciones, respuestas y adjuntos sin mezclar responsabilidades.

## Flujo de colaboracion

1. **Entrada**: PUBLICACION_ABIERTA o MI_PUBLICACION_ABIERTA invoca editarPublicacion().
2. **Autorizacion**: PoliticaPublicacion aplica la regla correspondiente al actor y contexto.
3. **Dominio**: El controlador coordina los repositorios especializados necesarios.
4. **Salida**: El caso finaliza en Estado de detalle de origen.

## Aplicacion de SOLID

- **SRP**: Vista, coordinacion, autorizacion y persistencia tienen responsabilidades independientes.
- **OCP**: Nuevas reglas de permisos se incorporan mediante PoliticaPublicacion sin duplicar casos de uso.
- **ISP**: Cada repositorio expone solo operaciones de su agregado.
- **DIP**: El controlador depende de abstracciones de politica y repositorio.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarPublicacion/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
