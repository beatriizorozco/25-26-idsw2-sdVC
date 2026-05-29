# FUNIBER > Investigador > abrirEntregables > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirEntregables/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirEntregables/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirEntregables/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirEntregables/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para presentar a Investigador el listado de entregables, con filtrado y navegación. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `abrirEntregables()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirEntregables()](/images/RUP/01-analisis/casos-uso/investigador/abrirEntregables/abrirEntregables-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### ListarEntregablesView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirEntregables()` del Investigador.
- Presentar la información de entregables necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Investigador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `abrirEntregables()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `EntregableController`.
- **Salida**: Devuelve el control a la navegación definida para el Investigador.

### Clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `abrirEntregables()`.
- Aplicar reglas de permisos del Investigador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ListarEntregablesView`.
- **Repositorio**: Delega operaciones de datos a `EntregableRepository`.

### Clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de entregables.
- Proporcionar operaciones `obtenerPropios(investigador)` y `buscarPorCriterio(criterio)`.
- Mantener la consistencia conceptual de entregables.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `EntregableController`.
- **Entidad**: Gestiona instancias de `Entregable`.

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de entregable.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `ListarEntregablesView.abrirEntregables()`.
2. **Solicitud principal**: `ListarEntregablesView` -> `EntregableController.listarEntregables()`.
3. **Acceso a datos**: `ListarEntregablesView` -> `EntregableController.filtrarEntregables(criterio)`.
4. **Filtrado o refinamiento**: `EntregableController` -> `EntregableRepository.obtenerPropios(investigador)`.
5. **Búsqueda**: `EntregableController` -> `EntregableRepository.buscarPorCriterio(criterio)`.
6. **Finalización**: `ListarEntregablesView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada contextual**: Puede iniciarse desde `PROYECTO_ABIERTO`, `ENTREGABLE_ABIERTO`; la vista conserva el origen para que el controlador ajuste el alcance cuando exista identificador de contexto.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `abrirEntregables()`|`ListarEntregablesView`|Recibe la acción del Investigador|
|Coordinar reglas del caso de uso|`EntregableController`|`listarEntregables()`|
|Aplicar permisos y validaciones|`EntregableController`|`filtrarEntregables(criterio)`|
|Acceder a datos de entregables|`EntregableRepository`|`obtenerPropios(investigador)`, `buscarPorCriterio(criterio)`|
|Representar atributos de dominio|`Entregable`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|título|`Entregable`|Atributo conceptual tratado por la entidad de dominio.|
|proyecto|`Entregable`|Atributo conceptual tratado por la entidad de dominio.|
|responsable|`Entregable`|Atributo conceptual tratado por la entidad de dominio.|
|fecha|`Entregable`|Atributo conceptual tratado por la entidad de dominio.|
|estado|`Entregable`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **abrirEntregable()**: colaboración relacionada desde la navegación del caso de uso.
- **crearEntregable()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirProyecto()**: colaboración relacionada desde la navegación del caso de uso.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Investigador.
- Restringir operaciones de creación, edición y eliminación a publicaciones y entregables propios del Investigador.

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

- **Origen**: Caso de uso detallado `abrirEntregables()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`EntregableRepository` abstrae el acceso a datos de entregables, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`ListarEntregablesView`), lógica de aplicación (`EntregableController`) y datos (`Entregable`, `EntregableRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Investigador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: abrirEntregables()](/RUP/00-casos-uso/02-detalle/investigador/abrirEntregables/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
