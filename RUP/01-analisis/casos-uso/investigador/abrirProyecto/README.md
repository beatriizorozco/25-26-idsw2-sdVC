# FUNIBER > Investigador > abrirProyecto > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirProyecto/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirProyecto/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirProyecto/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirProyecto/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para presentar a Investigador el detalle de proyecto y sus acciones disponibles. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `abrirProyecto()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirProyecto()](/images/RUP/01-analisis/casos-uso/investigador/abrirProyecto/abrirProyecto-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### DetalleProyectoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirProyecto()` del Investigador.
- Presentar la información de proyectos necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Investigador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `abrirProyecto()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `ProyectoController`.
- **Salida**: Devuelve el control a la navegación definida para el Investigador.

### Clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `abrirProyecto()`.
- Aplicar reglas de permisos del Investigador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `DetalleProyectoView`.
- **Repositorio**: Delega operaciones de datos a `ProyectoRepository`.

### Clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos.
- Proporcionar operaciones `obtenerPorId(id)` y `verificarPermisos(actor)`.
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

1. **Inicio**: Estado de contexto -> `DetalleProyectoView.abrirProyecto()`.
2. **Solicitud principal**: `DetalleProyectoView` -> `ProyectoController.obtenerProyecto(id)`.
3. **Acceso a datos**: `DetalleProyectoView` -> `ProyectoController.prepararAccionesDisponibles(investigador)`.
4. **Preparación de acciones**: `ProyectoController` -> `ProyectoRepository.obtenerPorId(id)`.
5. **Verificación de permisos**: `ProyectoController` -> `ProyectoRepository.verificarPermisos(actor)`.
6. **Finalización**: `DetalleProyectoView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada contextual**: Puede iniciarse desde `PROYECTOS_ABIERTOS`, `ENTREGABLES_ABIERTOS`; la vista conserva el origen para que el controlador ajuste el alcance cuando exista identificador de contexto.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `abrirProyecto()`|`DetalleProyectoView`|Recibe la acción del Investigador|
|Coordinar reglas del caso de uso|`ProyectoController`|`obtenerProyecto(id)`|
|Aplicar permisos y validaciones|`ProyectoController`|`prepararAccionesDisponibles(investigador)`|
|Acceder a datos de proyectos|`ProyectoRepository`|`obtenerPorId(id)`, `verificarPermisos(actor)`|
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

- **abrirEntregables()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirInvestigadores()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirProyectos()**: colaboración relacionada desde la navegación del caso de uso.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Investigador.
- Restringir operaciones de creación, edición y eliminación a publicaciones y entregables propios del Investigador.

- Cuando el caso de uso se inicia desde un estado de detalle, el an?lisis modela un par?metro de contexto para ajustar el alcance sin duplicar el caso de uso. Si no se recibe identificador, el controlador conserva el comportamiento base del caso de uso; si se recibe, recupera o prepara la entidad acotada al contexto.

## Características del análisis

### Separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador.
- **Control**: Solo coordinación, permisos y lógica de aplicación.
- **Entidad**: Solo datos, repositorios y reglas conceptuales del dominio.

### Agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario.
- No asume implementación concreta de base de datos.
- Mantiene independencia de frameworks.

### Trazabilidad completa

- **Origen**: Caso de uso detallado `abrirProyecto()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`ProyectoRepository` abstrae el acceso a datos de proyectos, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`DetalleProyectoView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Investigador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: abrirProyecto()](/RUP/00-casos-uso/02-detalle/investigador/abrirProyecto/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
