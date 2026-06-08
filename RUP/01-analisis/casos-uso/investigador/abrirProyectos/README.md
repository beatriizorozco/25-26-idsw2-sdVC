# FUNIBER > Investigador > abrirProyectos > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirProyectos/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirProyectos/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirProyectos/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirProyectos/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para presentar a Investigador el listado de proyectos, con filtrado y navegación. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `abrirProyectos()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirProyectos()](/images/RUP/01-analisis/casos-uso/investigador/abrirProyectos/abrirProyectos-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### ListarProyectosView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirProyectos()` del Investigador.
- Presentar la información de proyectos necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Investigador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `abrirProyectos(investigadorId?)` desde `PANEL_PRINCIPAL_ABIERTO`, `PROYECTO_ABIERTO` o `INVESTIGADOR_ABIERTO`.
- **Control**: Se comunica con `ProyectoController`.
- **Salida**: Presenta `PROYECTOS_ABIERTOS` o deriva a `abrirProyecto()` o `abrirPanelPrincipal()`.

### Clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `abrirProyectos()`.
- Aplicar reglas de permisos del Investigador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ListarProyectosView`.
- **Repositorio**: Delega operaciones de datos a `ProyectoRepository`.

### Clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos.
- Proporcionar operaciones `obtenerActivosPropios(investigador)` y `buscarPorCriterio(criterio)`, excluyendo proyectos archivados.
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

1. **Inicio**: `PANEL_PRINCIPAL_ABIERTO`, `PROYECTO_ABIERTO` o `INVESTIGADOR_ABIERTO` -> `ListarProyectosView.abrirProyectos(investigadorId?)`.
2. **Solicitud principal**: `ListarProyectosView` -> `ProyectoController.listarProyectos(actorId, investigadorId?)`.
3. **Alcance propio**: Sin identificador, `ProyectoRepository.obtenerActivosPropios(actorId)`.
4. **Alcance contextual**: Con identificador, `ProyectoRepository.obtenerActivosVisiblesPorInvestigador(investigadorId)`.
5. **Búsqueda**: El repositorio filtra únicamente dentro del alcance visible.
6. **Finalización**: `ListarProyectosView` presenta `PROYECTOS_ABIERTOS` o deriva a la colaboración seleccionada.

### Patrón de colaboración establecido

- **Entrada contextual**: Sin identificador presenta los proyectos activos propios; con `investigadorId` presenta los proyectos activos visibles asociados al investigador consultado.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `abrirProyectos()`|`ListarProyectosView`|Recibe la acción del Investigador|
|Coordinar reglas del caso de uso|`ProyectoController`|`listarProyectos(actorId, investigadorId?)`|
|Aplicar filtrado visible|`ProyectoController`|`filtrarProyectos(criterio, actorId, investigadorId?)`|
|Acceder a datos de proyectos|`ProyectoRepository`|`obtenerActivosPropios(...)`, `obtenerActivosVisiblesPorInvestigador(...)`, `buscarVisiblesPorCriterio(...)`|
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

- **abrirProyecto()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirPanelPrincipal()**: colaboración relacionada desde la navegación del caso de uso.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Investigador.
- Restringir el listado a proyectos activos propios o visibles según el investigador de contexto, sin ofrecer operaciones de gestión.

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

- **Origen**: Caso de uso detallado `abrirProyectos()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`ProyectoRepository` abstrae el acceso a datos de proyectos, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`ListarProyectosView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Investigador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: abrirProyectos()](/RUP/00-casos-uso/02-detalle/investigador/abrirProyectos/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
