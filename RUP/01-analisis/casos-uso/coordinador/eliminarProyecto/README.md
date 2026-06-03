# FUNIBER > Coordinador > eliminarProyecto > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarProyecto/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarProyecto/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarProyecto/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/eliminarProyecto/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para eliminar un elemento de proyecto. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `eliminarProyecto()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: eliminarProyecto()](/images/RUP/01-analisis/casos-uso/coordinador/eliminarProyecto/eliminarProyecto-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### EliminarProyectoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `eliminarProyecto()` del Coordinador.
- Presentar la información de proyectos necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `eliminarProyecto()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `ProyectoController`.
- **Salida**: Devuelve el control a la navegación definida para el Coordinador.

### Clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `eliminarProyecto()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarProyectoView`.
- **Repositorio**: Delega operaciones de datos a `ProyectoRepository`.

### Clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos.
- Proporcionar operaciones `obtenerPorId(id)` y `eliminar(id)`.
- Mantener la consistencia conceptual de proyectos.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `ProyectoController`.
- **Entidad**: Gestiona instancias de `Proyecto`.

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de proyecto.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ProyectoRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `EliminarProyectoView.eliminarProyecto()`.
2. **Confirmación previa**: `EliminarProyectoView` -> `ProyectoController.solicitarConfirmacion(id)`.
3. **Presentación de confirmación**: `ProyectoController` -> `EliminarProyectoView.presentarConfirmacion()`.
4. **Decisión del actor**: si confirma, `EliminarProyectoView` -> `ProyectoController.confirmarEliminacion(id)`; si cancela, `EliminarProyectoView` -> `ProyectoController.cancelarEliminacion()`.
5. **Validación y persistencia**: `ProyectoController` -> `ProyectoRepository.obtenerPorId(id)` y `ProyectoRepository.eliminar(id)`.
6. **Finalización**: si confirma, se actualiza la navegación del proyecto; si cancela, se mantiene el proyecto abierto sin cambios.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `eliminarProyecto()`|`EliminarProyectoView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`ProyectoController`|`solicitarConfirmacion(id)`, `confirmarEliminacion(id)`|
|Aplicar permisos, validaciones y cancelación|`ProyectoController`|`validarEliminacion(id)`, `cancelarEliminacion()`|
|Acceder a datos de proyectos|`ProyectoRepository`|`obtenerPorId(id)`, `eliminar(id)`|
|Representar atributos de dominio|`Proyecto`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|ID|`Proyecto`|Atributo conceptual tratado por la entidad de dominio.|
|título|`Proyecto`|Atributo conceptual tratado por la entidad de dominio.|
|estado|`Proyecto`|Atributo conceptual tratado por la entidad de dominio.|
|fechas|`Proyecto`|Atributo conceptual tratado por la entidad de dominio.|
|investigadores asociados|`Proyecto`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **abrirProyectos()**: colaboración relacionada desde la navegación del caso de uso.

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

- **Origen**: Caso de uso detallado `eliminarProyecto()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`ProyectoRepository` abstrae el acceso a datos de proyectos, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`EliminarProyectoView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: eliminarProyecto()](/RUP/00-casos-uso/02-detalle/coordinador/eliminarProyecto/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
