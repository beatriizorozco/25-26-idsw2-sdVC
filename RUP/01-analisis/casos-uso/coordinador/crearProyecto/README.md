# FUNIBER > Coordinador > crearProyecto > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearProyecto/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/crearProyecto/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/crearProyecto/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para registrar un nuevo proyecto. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `crearProyecto()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: crearProyecto()](/images/RUP/01-analisis/casos-uso/coordinador/crearProyecto/crearProyecto-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### CrearProyectoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `crearProyecto()` del Coordinador.
- Presentar la información de proyectos necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `crearProyecto()` desde `PROYECTOS_ABIERTOS`.
- **Control**: Se comunica con `ProyectoController`.
- **Salida**: Abre el proyecto creado en `PROYECTO_ABIERTO` o conserva `PROYECTOS_ABIERTOS` si se cancela.

### Clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `crearProyecto()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CrearProyectoView`.
- **Repositorio**: Delega operaciones de datos a `ProyectoRepository`.

### Clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos.
- Proporcionar la operación `guardar(proyecto)`.
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

#### ConvocatoriaRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Proporcionar convocatorias disponibles para asociar el proyecto desde su creación.
- Evitar que el caso de uso cree proyectos vinculados a convocatorias inexistentes.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: `PROYECTOS_ABIERTOS` -> `CrearProyectoView.crearProyecto()`.
2. **Preparación**: `ProyectoController` obtiene las convocatorias disponibles.
3. **Validación previa**: `ProyectoController.validarDatosYFechas(datosMinimos)`.
4. **Asignación automática**: `ProyectoController` asigna código, Coordinador y estado `Creado`.
5. **Persistencia**: `ProyectoController` -> `ProyectoRepository.guardar(proyecto)`.
6. **Finalización**: Abre el proyecto creado o conserva `PROYECTOS_ABIERTOS` si se cancela.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `crearProyecto()`|`CrearProyectoView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`ProyectoController`|`crearProyecto(datosMinimos, coordinador)`|
|Aplicar validaciones|`ProyectoController`|`validarDatosYFechas(datosMinimos)`|
|Acceder a datos de proyectos|`ProyectoRepository`|`guardar(proyecto)`|
|Obtener convocatorias disponibles|`ConvocatoriaRepository`|`obtenerDisponibles()`|
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
- **abrirInvestigadores()**: colaboración disponible desde `PROYECTO_ABIERTO`.
- **abrirProyectos()**: colaboración disponible desde `PROYECTO_ABIERTO`.
- **abrirProyecto()**: colaboración disponible desde `PROYECTOS_ABIERTOS` si se cancela.
- **crearProyecto()**: colaboración disponible desde `PROYECTOS_ABIERTOS` si se cancela.
- **abrirPanelPrincipal()**: colaboración disponible desde `PROYECTOS_ABIERTOS` si se cancela.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Coordinador.
- Registrar únicamente los datos mínimos, validar fechas y asignar automáticamente código, coordinador y estado `Creado`.

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

- **Origen**: Caso de uso detallado `crearProyecto()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`ProyectoRepository` abstrae el acceso a datos de proyectos, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`CrearProyectoView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: crearProyecto()](/RUP/00-casos-uso/02-detalle/coordinador/crearProyecto/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
