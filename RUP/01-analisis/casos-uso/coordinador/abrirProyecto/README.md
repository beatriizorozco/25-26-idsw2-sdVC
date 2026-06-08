# FUNIBER > Coordinador > abrirProyecto > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirProyecto/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirProyecto/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirProyecto/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para presentar a Coordinador el detalle de proyecto y sus acciones disponibles. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `abrirProyecto()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirProyecto()](/images/RUP/01-analisis/casos-uso/coordinador/abrirProyecto/abrirProyecto-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### DetalleProyectoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirProyecto()` del Coordinador.
- Presentar la información de proyectos necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `abrirProyecto(proyectoId)` desde `PROYECTOS_ABIERTOS` o `ENTREGABLES_ABIERTOS`.
- **Control**: Se comunica con `ProyectoController`.
- **Salida**: Presenta `PROYECTO_ABIERTO` y permite navegar a las colaboraciones de gestión disponibles.

### Clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `abrirProyecto()`.
- Aplicar reglas de permisos del Coordinador.
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
- Proporcionar la operación `obtenerPorId(proyectoId)`.
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

#### InvestigadorRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Recuperar el equipo investigador asociado al proyecto.
- Aportar la información necesaria para presentar composición, sede, perfil y carga disponible.

#### EntregableRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Recuperar los entregables asociados al proyecto abierto.
- Permitir que el detalle del proyecto muestre su trabajo pendiente o registrado.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `DetalleProyectoView.abrirProyecto()`.
2. **Solicitud principal**: `DetalleProyectoView` -> `ProyectoController.obtenerProyecto(id)`.
3. **Acceso a datos**: `DetalleProyectoView` -> `ProyectoController.prepararAccionesDisponibles(coordinador)`.
4. **Preparación de acciones**: `ProyectoController` -> `ProyectoRepository.obtenerPorId(id)`.
5. **Composición del detalle**: El controlador reúne proyecto, equipo investigador y entregables asociados.
6. **Finalización**: `DetalleProyectoView` presenta `PROYECTO_ABIERTO` y sus acciones permitidas.

### Patrón de colaboración establecido

- **Entrada contextual**: Puede iniciarse desde `PROYECTOS_ABIERTOS`, `ENTREGABLES_ABIERTOS`; la vista conserva el origen para que el controlador ajuste el alcance cuando exista identificador de contexto.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `abrirProyecto()`|`DetalleProyectoView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`ProyectoController`|`obtenerProyecto(id)`|
|Aplicar permisos y validaciones|`ProyectoController`|`prepararAccionesDisponibles(coordinador)`|
|Acceder a datos de proyectos|`ProyectoRepository`|`obtenerPorId(proyectoId)`|
|Recuperar equipo investigador|`InvestigadorRepository`|`obtenerEquipoPorProyecto(proyectoId)`|
|Recuperar entregables|`EntregableRepository`|`obtenerPorProyecto(proyectoId)`|
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

- **editarProyecto()**: colaboración relacionada desde la navegación del caso de uso.
- **eliminarProyecto()**: colaboración relacionada desde la navegación del caso de uso.
- **agregarInvestigador()**: colaboración relacionada desde la navegación del caso de uso.
- **eliminarInvestigador()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirEntregables()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirInvestigadores()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirProyectos()**: colaboración relacionada desde la navegación del caso de uso.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Coordinador.
- Recuperar directamente el proyecto seleccionado junto con su equipo investigador y sus entregables, sin repetir el listado.

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

- **Origen**: Caso de uso detallado `abrirProyecto()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`ProyectoRepository` abstrae el acceso a datos de proyectos, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`DetalleProyectoView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: abrirProyecto()](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
