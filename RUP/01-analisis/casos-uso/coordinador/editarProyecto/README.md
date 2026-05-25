# FUNIBER > Coordinador > editarProyecto > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarProyecto/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarProyecto/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarProyecto/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarProyecto/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para actualizar los datos de proyecto. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `editarProyecto()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: editarProyecto()](/images/RUP/01-analisis/casos-uso/coordinador/editarProyecto/editarProyecto-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### EditarProyectoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `editarProyecto()` del Coordinador.
- Presentar la información de proyectos necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `editarProyecto()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `ProyectoController`.
- **Salida**: Devuelve el control a la navegación definida para el Coordinador.

### Clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `editarProyecto()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarProyectoView`.
- **Repositorio**: Delega operaciones de datos a `ProyectoRepository`.

### Clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos.
- Proporcionar operaciones `obtenerPorId(id)` y `actualizar(entidad)`.
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

1. **Inicio**: Estado de contexto -> `EditarProyectoView.editarProyecto()`.
2. **Solicitud principal**: `EditarProyectoView` -> `ProyectoController.editarProyecto(id, datos)`.
3. **Acceso a datos**: `EditarProyectoView` -> `ProyectoController.validarCambios(datos)`.
4. **Validación de cambios**: `ProyectoController` -> `ProyectoRepository.obtenerPorId(id)`.
5. **Persistencia**: `ProyectoController` -> `ProyectoRepository.actualizar(entidad)`.
6. **Finalización**: `EditarProyectoView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `editarProyecto()`|`EditarProyectoView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`ProyectoController`|`editarProyecto(id, datos)`|
|Aplicar permisos y validaciones|`ProyectoController`|`validarCambios(datos)`|
|Acceder a datos de proyectos|`ProyectoRepository`|`obtenerPorId(id)`, `actualizar(entidad)`|
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

- **eliminarProyecto()**: colaboración relacionada desde la navegación del caso de uso.
- **agregarInvestigador()**: colaboración relacionada desde la navegación del caso de uso.
- **eliminarInvestigador()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirEntregables()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirInvestigadores()**: colaboración relacionada desde la navegación del caso de uso.

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

- **Origen**: Caso de uso detallado `editarProyecto()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`ProyectoRepository` abstrae el acceso a datos de proyectos, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`EditarProyectoView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: editarProyecto()](/RUP/00-casos-uso/02-detalle/coordinador/editarProyecto/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
