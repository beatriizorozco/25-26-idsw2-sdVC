# FUNIBER > Investigador > abrirPanelPrincipal > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirPanelPrincipal/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirPanelPrincipal/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirPanelPrincipal/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirPanelPrincipal/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para presentar a Investigador el detalle de panel principal y sus acciones disponibles. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `abrirPanelPrincipal()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirPanelPrincipal()](/images/RUP/01-analisis/casos-uso/investigador/abrirPanelPrincipal/abrirPanelPrincipal-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### DetallePanelPrincipalView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirPanelPrincipal()` del Investigador.
- Presentar la información de panel principal necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Investigador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `abrirPanelPrincipal()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `PanelPrincipalController`.
- **Salida**: Devuelve el control a la navegación definida para el Investigador.

### Clases de control

#### PanelPrincipalController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `abrirPanelPrincipal()`.
- Aplicar reglas de permisos del Investigador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `DetallePanelPrincipalView`.
- **Repositorio**: Delega operaciones de datos a `PanelPrincipalRepository`.

### Clases de entidad (entity)

#### PanelPrincipalRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de panel principal.
- Proporcionar operaciones `obtenerPorId(id)` y `verificarPermisos(actor)`.
- Mantener la consistencia conceptual de panel principal.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `PanelPrincipalController`.
- **Entidad**: Gestiona instancias de `PanelPrincipal`.

#### PanelPrincipal
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de panel principal.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PanelPrincipalRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `DetallePanelPrincipalView.abrirPanelPrincipal()`.
2. **Solicitud principal**: `DetallePanelPrincipalView` -> `PanelPrincipalController.obtenerPanelPrincipal(id)`.
3. **Acceso a datos**: `DetallePanelPrincipalView` -> `PanelPrincipalController.prepararAccionesDisponibles(investigador)`.
4. **Preparación de acciones**: `PanelPrincipalController` -> `PanelPrincipalRepository.obtenerPorId(id)`.
5. **Verificación de permisos**: `PanelPrincipalController` -> `PanelPrincipalRepository.verificarPermisos(actor)`.
6. **Finalización**: `DetallePanelPrincipalView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Investigador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `abrirPanelPrincipal()`|`DetallePanelPrincipalView`|Recibe la acción del Investigador|
|Coordinar reglas del caso de uso|`PanelPrincipalController`|`obtenerPanelPrincipal(id)`|
|Aplicar permisos y validaciones|`PanelPrincipalController`|`prepararAccionesDisponibles(investigador)`|
|Acceder a datos de panel principal|`PanelPrincipalRepository`|`obtenerPorId(id)`, `verificarPermisos(actor)`|
|Representar atributos de dominio|`PanelPrincipal`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|accesos disponibles|`PanelPrincipal`|Atributo conceptual tratado por la entidad de dominio.|
|resumen de actividad|`PanelPrincipal`|Atributo conceptual tratado por la entidad de dominio.|
|rol activo|`PanelPrincipal`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **abrirOpcionesPerfil()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirOpcionesCargaTrabajo()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirProyectos()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirInvestigadores()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirMisPublicaciones()**: colaboración relacionada desde la navegación del caso de uso.

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

- **Origen**: Caso de uso detallado `abrirPanelPrincipal()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`PanelPrincipalRepository` abstrae el acceso a datos de panel principal, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`DetallePanelPrincipalView`), lógica de aplicación (`PanelPrincipalController`) y datos (`PanelPrincipal`, `PanelPrincipalRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Investigador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: abrirPanelPrincipal()](/RUP/00-casos-uso/02-detalle/investigador/abrirPanelPrincipal/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
