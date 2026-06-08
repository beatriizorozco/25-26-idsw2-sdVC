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

Analizar la colaboración necesaria para archivar un proyecto mediante baja lógica, retirándolo de la gestión activa sin perder su estado ni ninguna relación histórica.

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
- **Entrada**: Recibe `eliminarProyecto(proyectoId)` desde `PROYECTO_ABIERTO`.
- **Control**: Se comunica con `ProyectoController`.
- **Salida**: Navega a `PROYECTOS_ABIERTOS` si archiva o conserva `PROYECTO_ABIERTO` si cancela.

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
- Proporcionar operaciones `obtenerPorId(proyectoId)`, `obtenerEquipo(proyectoId)` y `archivar(proyectoId, fecha, coordinador)`.
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

1. **Inicio**: `PROYECTO_ABIERTO` -> `EliminarProyectoView.eliminarProyecto(proyectoId)`.
2. **Preparación**: `ProyectoController.prepararArchivado(proyectoId)` recupera el proyecto y resume qué información se conservará.
3. **Confirmación**: La vista informa de las consecuencias y solicita confirmación.
4. **Archivado**: El repositorio marca el proyecto como archivado y registra fecha y Coordinador responsable.
5. **Trazabilidad**: Estado, equipo, entregables y recompensas permanecen vinculados al proyecto.
6. **Finalización**: Navega a `PROYECTOS_ABIERTOS` si archiva o conserva `PROYECTO_ABIERTO` si cancela.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `eliminarProyecto()`|`EliminarProyectoView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`ProyectoController`|`prepararArchivado(proyectoId)`, `confirmarArchivado(proyectoId, coordinador)`|
|Preservar trazabilidad|`ProyectoRepository`|Conserva la entidad y todas sus relaciones|
|Acceder a datos de proyectos|`ProyectoRepository`|`obtenerPorId(proyectoId)`, `obtenerEquipo(proyectoId)`, `archivar(proyectoId, fecha, coordinador)`|
|Representar atributos de dominio|`Proyecto`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|ID|`Proyecto`|Atributo conceptual tratado por la entidad de dominio.|
|título|`Proyecto`|Atributo conceptual tratado por la entidad de dominio.|
|estado|`Proyecto`|Atributo conceptual tratado por la entidad de dominio.|
|fechas|`Proyecto`|Atributo conceptual tratado por la entidad de dominio.|
|investigadores asociados|`Proyecto`|Atributo conceptual tratado por la entidad de dominio.|
|archivado, fechaArchivado y archivadoPor|`Proyecto`|Metadatos de la baja lógica que conservan la trazabilidad.|

## Colaboraciones relacionadas

- **abrirProyecto()**, **crearProyecto()** y **abrirPanelPrincipal()**: disponibles desde `PROYECTOS_ABIERTOS` tras archivar.
- **editarProyecto()**, **eliminarProyecto()**, **agregarInvestigador()**, **eliminarInvestigador()**, **abrirEntregables()**, **abrirInvestigadores()** y **abrirProyectos()**: disponibles desde `PROYECTO_ABIERTO` si se cancela.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Coordinador.
- Aplicar una baja lógica y excluir proyectos archivados de la gestión activa.
- Conservar el estado de negocio original, el equipo, los entregables y las recompensas.

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
