# FUNIBER > Coordinador > abrirSolicitudesEliminacionPerfil > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para presentar a Coordinador el detalle de solicitud de eliminación de perfil y sus acciones disponibles. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `abrirSolicitudesEliminacionPerfil()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirSolicitudesEliminacionPerfil()](/images/RUP/01-analisis/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/abrirSolicitudesEliminacionPerfil-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### DetalleSolicitudEliminacionPerfilView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirSolicitudesEliminacionPerfil()` del Coordinador.
- Presentar la información de solicitudes de eliminación de perfil necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `abrirSolicitudesEliminacionPerfil()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `SolicitudEliminacionPerfilController`.
- **Salida**: Devuelve el control a la navegación definida para el Coordinador.

### Clases de control

#### SolicitudEliminacionPerfilController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `abrirSolicitudesEliminacionPerfil()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `DetalleSolicitudEliminacionPerfilView`.
- **Repositorio**: Delega operaciones de datos a `SolicitudEliminacionPerfilRepository`.

### Clases de entidad (entity)

#### SolicitudEliminacionPerfilRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de solicitudes de eliminación de perfil.
- Proporcionar operaciones `obtenerPorId(id)` y `verificarPermisos(actor)`.
- Mantener la consistencia conceptual de solicitudes de eliminación de perfil.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `SolicitudEliminacionPerfilController`.
- **Entidad**: Gestiona instancias de `SolicitudEliminacionPerfil`.

#### SolicitudEliminacionPerfil
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de solicitud de eliminación de perfil.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `SolicitudEliminacionPerfilRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `DetalleSolicitudEliminacionPerfilView.abrirSolicitudesEliminacionPerfil()`.
2. **Solicitud principal**: `DetalleSolicitudEliminacionPerfilView` -> `SolicitudEliminacionPerfilController.obtenerSolicitudEliminacionPerfil(id)`.
3. **Acceso a datos**: `DetalleSolicitudEliminacionPerfilView` -> `SolicitudEliminacionPerfilController.prepararAccionesDisponibles(coordinador)`.
4. **Preparación de acciones**: `SolicitudEliminacionPerfilController` -> `SolicitudEliminacionPerfilRepository.obtenerPorId(id)`.
5. **Verificación de permisos**: `SolicitudEliminacionPerfilController` -> `SolicitudEliminacionPerfilRepository.verificarPermisos(actor)`.
6. **Finalización**: `DetalleSolicitudEliminacionPerfilView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `abrirSolicitudesEliminacionPerfil()`|`DetalleSolicitudEliminacionPerfilView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`SolicitudEliminacionPerfilController`|`obtenerSolicitudEliminacionPerfil(id)`|
|Aplicar permisos y validaciones|`SolicitudEliminacionPerfilController`|`prepararAccionesDisponibles(coordinador)`|
|Acceder a datos de solicitudes de eliminación de perfil|`SolicitudEliminacionPerfilRepository`|`obtenerPorId(id)`, `verificarPermisos(actor)`|
|Representar atributos de dominio|`SolicitudEliminacionPerfil`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|ID de solicitud|`SolicitudEliminacionPerfil`|Atributo conceptual tratado por la entidad de dominio.|
|investigador solicitante|`SolicitudEliminacionPerfil`|Atributo conceptual tratado por la entidad de dominio.|
|fecha de solicitud|`SolicitudEliminacionPerfil`|Atributo conceptual tratado por la entidad de dominio.|
|motivo|`SolicitudEliminacionPerfil`|Atributo conceptual tratado por la entidad de dominio.|
|estado|`SolicitudEliminacionPerfil`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **abrirSolicitudEliminacionPerfil()**: colaboración relacionada desde la navegación del caso de uso.

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

- **Origen**: Caso de uso detallado `abrirSolicitudesEliminacionPerfil()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`SolicitudEliminacionPerfilRepository` abstrae el acceso a datos de solicitudes de eliminación de perfil, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`DetalleSolicitudEliminacionPerfilView`), lógica de aplicación (`SolicitudEliminacionPerfilController`) y datos (`SolicitudEliminacionPerfil`, `SolicitudEliminacionPerfilRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: abrirSolicitudesEliminacionPerfil()](/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudesEliminacionPerfil/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
