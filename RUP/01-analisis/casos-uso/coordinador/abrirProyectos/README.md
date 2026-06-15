# FUNIBER > Coordinador > abrirProyectos > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyectos/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirProyectos/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirProyectos/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para presentar a Coordinador el listado de proyectos, con filtrado y navegación. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `abrirProyectos()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirProyectos()](/images/RUP/01-analisis/casos-uso/coordinador/abrirProyectos/abrirProyectos-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### ListarProyectosView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirProyectos()` del Coordinador.
- Presentar la información de proyectos necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `abrirProyectos(investigadorId?)` desde `PANEL_PRINCIPAL_ABIERTO`, `PROYECTO_ABIERTO` o `INVESTIGADOR_ABIERTO`.
- **Control**: Se comunica con `ProyectoController`.
- **Salida**: Presenta `PROYECTOS_ABIERTOS` o deriva a `abrirProyecto()`, `crearProyecto()` o `abrirPanelPrincipal()`.

### Clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `abrirProyectos()`.
- Aplicar reglas de permisos del Coordinador.
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
- Proporcionar operaciones `obtenerActivos()` y `buscarPorCriterio(criterio)`, excluyendo proyectos archivados.
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
2. **Solicitud principal**: `ListarProyectosView` -> `ProyectoController.listarProyectos(investigadorId?)`.
3. **Alcance global**: Sin identificador, `ProyectoRepository.obtenerActivos()`.
4. **Alcance contextual**: Con identificador, `ProyectoRepository.obtenerActivosPorInvestigador(investigadorId)`.
5. **Búsqueda**: El repositorio filtra dentro del mismo alcance.
6. **Finalización**: `ListarProyectosView` presenta `PROYECTOS_ABIERTOS` o deriva a la colaboración seleccionada.

### Patrón de colaboración establecido

- **Entrada contextual**: Sin identificador presenta todos los proyectos activos; con `investigadorId` presenta únicamente los activos asociados a ese investigador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `abrirProyectos()`|`ListarProyectosView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`ProyectoController`|`listarProyectos(investigadorId?)`|
|Aplicar filtrado contextual|`ProyectoController`|`filtrarProyectos(criterio, investigadorId?)`|
|Acceder a datos de proyectos|`ProyectoRepository`|`obtenerActivos()`, `obtenerActivosPorInvestigador(...)`, `buscarPorCriterio(...)`|
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
- **crearProyecto()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirPanelPrincipal()**: colaboración relacionada desde la navegación del caso de uso.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Coordinador.
- Mantener un único caso de uso cuyo alcance cambia de listado global a listado asociado cuando recibe `investigadorId`.

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

- **Origen**: Caso de uso detallado `abrirProyectos()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`ProyectoRepository` abstrae el acceso a datos de proyectos, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`ListarProyectosView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: abrirProyectos()](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyectos/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
