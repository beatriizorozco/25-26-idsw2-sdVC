# FUNIBER > Coordinador > eliminarPublicacion > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarPublicacion/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarPublicacion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarPublicacion/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/eliminarPublicacion/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para retirar una publicación conservando su autoría y trazabilidad. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `eliminarPublicacion()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: eliminarPublicacion()](/images/RUP/01-analisis/casos-uso/coordinador/eliminarPublicacion/eliminarPublicacion-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### EliminarPublicacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `eliminarPublicacion()` del Coordinador.
- Presentar la información de publicaciones necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `eliminarPublicacion()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `PublicacionController`.
- **Salida**: Devuelve el control a la navegación definida para el Coordinador.

### Clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `eliminarPublicacion()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarPublicacionView`.
- **Repositorio**: Delega operaciones de datos a `PublicacionRepository`.

### Clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de publicaciones.
- Proporcionar operaciones `obtenerPorId(id)` y `retirar(id)`.
- Mantener la consistencia conceptual de publicaciones.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`.
- **Entidad**: Gestiona instancias de `Publicacion`.

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de publicación.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `EliminarPublicacionView.eliminarPublicacion()`.
2. **Confirmación previa**: `EliminarPublicacionView` -> `PublicacionController.solicitarConfirmacion(id)`.
3. **Presentación de confirmación**: `PublicacionController` -> `EliminarPublicacionView.presentarConfirmacion()`.
4. **Decisión del actor**: si confirma, `EliminarPublicacionView` -> `PublicacionController.confirmarRetirada(id)`; si cancela, `EliminarPublicacionView` -> `PublicacionController.cancelarRetirada()`.
5. **Validación y persistencia**: `PublicacionController` -> `PublicacionRepository.obtenerPorId(id)` y `PublicacionRepository.retirar(id)`.
6. **Finalización**: si confirma, se presenta el listado de publicaciones; si cancela, se mantiene la publicación abierta.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `eliminarPublicacion()`|`EliminarPublicacionView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`PublicacionController`|`solicitarConfirmacion(id)`, `confirmarRetirada(id)`|
|Aplicar permisos, validaciones y cancelación|`PublicacionController`|`validarRetirada(id)`, `cancelarRetirada()`|
|Acceder a datos de publicaciones|`PublicacionRepository`|`obtenerPorId(id)`, `retirar(id)`|
|Representar atributos de dominio|`Publicacion`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|título|`Publicacion`|Atributo conceptual tratado por la entidad de dominio.|
|contenido|`Publicacion`|Atributo conceptual tratado por la entidad de dominio.|
|autor|`Publicacion`|Atributo conceptual tratado por la entidad de dominio.|
|fecha|`Publicacion`|Atributo conceptual tratado por la entidad de dominio.|
|estado|`Publicacion`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **abrirPublicaciones()**: colaboración relacionada desde la navegación del caso de uso.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Coordinador.
- Permitir al Coordinador acceso global sobre publicaciones, entregables, proyectos, investigadores, recompensas y perfiles según el caso de uso.

## Características del análisis

### Separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador.
- **Control**: Solo coordinación, permisos y lógica de aplicación.
- **Entidad**: Solo datos, repositorios y reglas conceptuales del dominio.

### Agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario.
- No asume implementación concreta de base de datos.
- Mantiene independencia de frameworks.

### Trazabilidad completa

- **Origen**: Caso de uso detallado `eliminarPublicacion()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`PublicacionRepository` abstrae el acceso a datos de publicaciones, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`EliminarPublicacionView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: eliminarPublicacion()](/RUP/00-casos-uso/02-detalle/coordinador/eliminarPublicacion/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
