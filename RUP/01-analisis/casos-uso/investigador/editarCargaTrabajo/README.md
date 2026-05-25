# FUNIBER > Investigador > editarCargaTrabajo > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/editarCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/editarCargaTrabajo/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/editarCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para actualizar sus propios datos de carga de trabajo. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `editarCargaTrabajo()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: editarCargaTrabajo()](/images/RUP/01-analisis/casos-uso/investigador/editarCargaTrabajo/editarCargaTrabajo-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### EditarCargaTrabajoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `editarCargaTrabajo()` del Investigador.
- Presentar la información de cargas de trabajo necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Investigador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `editarCargaTrabajo()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `CargaTrabajoController`.
- **Salida**: Devuelve el control a la navegación definida para el Investigador.

### Clases de control

#### CargaTrabajoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `editarCargaTrabajo()`.
- Aplicar reglas de permisos del Investigador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarCargaTrabajoView`.
- **Repositorio**: Delega operaciones de datos a `CargaTrabajoRepository`.

### Clases de entidad (entity)

#### CargaTrabajoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de cargas de trabajo.
- Proporcionar operaciones `obtenerPorId(id)` y `actualizar(entidad)`.
- Mantener la consistencia conceptual de cargas de trabajo.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `CargaTrabajoController`.
- **Entidad**: Gestiona instancias de `CargaTrabajo`.

#### CargaTrabajo
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de carga de trabajo.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `CargaTrabajoRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `EditarCargaTrabajoView.editarCargaTrabajo()`.
2. **Solicitud principal**: `EditarCargaTrabajoView` -> `CargaTrabajoController.editarCargaTrabajo(id, datos)`.
3. **Acceso a datos**: `EditarCargaTrabajoView` -> `CargaTrabajoController.validarCambios(datos)`.
4. **Validación de cambios**: `CargaTrabajoController` -> `CargaTrabajoRepository.obtenerPorId(id)`.
5. **Persistencia**: `CargaTrabajoController` -> `CargaTrabajoRepository.actualizar(entidad)`.
6. **Finalización**: `EditarCargaTrabajoView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Investigador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `editarCargaTrabajo()`|`EditarCargaTrabajoView`|Recibe la acción del Investigador|
|Coordinar reglas del caso de uso|`CargaTrabajoController`|`editarCargaTrabajo(id, datos)`|
|Aplicar permisos y validaciones|`CargaTrabajoController`|`validarCambios(datos)`|
|Acceder a datos de cargas de trabajo|`CargaTrabajoRepository`|`obtenerPorId(id)`, `actualizar(entidad)`|
|Representar atributos de dominio|`CargaTrabajo`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|dedicación|`CargaTrabajo`|Atributo conceptual tratado por la entidad de dominio.|
|disponibilidad|`CargaTrabajo`|Atributo conceptual tratado por la entidad de dominio.|
|proyectos asociados|`CargaTrabajo`|Atributo conceptual tratado por la entidad de dominio.|
|observaciones|`CargaTrabajo`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **abrirPanelPrincipal()**: colaboración relacionada desde la navegación del caso de uso.

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

- **Origen**: Caso de uso detallado `editarCargaTrabajo()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`CargaTrabajoRepository` abstrae el acceso a datos de cargas de trabajo, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`EditarCargaTrabajoView`), lógica de aplicación (`CargaTrabajoController`) y datos (`CargaTrabajo`, `CargaTrabajoRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Investigador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: editarCargaTrabajo()](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
