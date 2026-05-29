# FUNIBER > Investigador > abrirMisPublicaciones > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirMisPublicaciones/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirMisPublicaciones/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirMisPublicaciones/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirMisPublicaciones/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para presentar a Investigador el listado de publicaciones propias, con filtrado y navegación. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `abrirMisPublicaciones()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirMisPublicaciones()](/images/RUP/01-analisis/casos-uso/investigador/abrirMisPublicaciones/abrirMisPublicaciones-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### ListarPublicacionesView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirMisPublicaciones()` del Investigador.
- Presentar la información de publicaciones propias necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Investigador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `abrirMisPublicaciones()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `PublicacionController`.
- **Salida**: Devuelve el control a la navegación definida para el Investigador.

### Clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `abrirMisPublicaciones()`.
- Aplicar reglas de permisos del Investigador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ListarPublicacionesView`.
- **Repositorio**: Delega operaciones de datos a `PublicacionRepository`.

### Clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de publicaciones propias.
- Proporcionar operaciones `obtenerPropios(investigador)` y `buscarPorCriterio(criterio)`.
- Mantener la consistencia conceptual de publicaciones propias.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`.
- **Entidad**: Gestiona instancias de `Publicacion`.

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de publicación propia.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `ListarPublicacionesView.abrirMisPublicaciones()`.
2. **Solicitud principal**: `ListarPublicacionesView` -> `PublicacionController.listarPublicaciones()`.
3. **Acceso a datos**: `ListarPublicacionesView` -> `PublicacionController.filtrarPublicaciones(criterio)`.
4. **Filtrado o refinamiento**: `PublicacionController` -> `PublicacionRepository.obtenerPropios(investigador)`.
5. **Búsqueda**: `PublicacionController` -> `PublicacionRepository.buscarPorCriterio(criterio)`.
6. **Finalización**: `ListarPublicacionesView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Investigador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `abrirMisPublicaciones()`|`ListarPublicacionesView`|Recibe la acción del Investigador|
|Coordinar reglas del caso de uso|`PublicacionController`|`listarPublicaciones()`|
|Aplicar permisos y validaciones|`PublicacionController`|`filtrarPublicaciones(criterio)`|
|Acceder a datos de publicaciones propias|`PublicacionRepository`|`obtenerPropios(investigador)`, `buscarPorCriterio(criterio)`|
|Representar atributos de dominio|`Publicacion`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|título|`Publicacion`|Atributo conceptual tratado por la entidad de dominio.|
|contenido|`Publicacion`|Atributo conceptual tratado por la entidad de dominio.|
|estado|`Publicacion`|Atributo conceptual tratado por la entidad de dominio.|
|visibilidad|`Publicacion`|Atributo conceptual tratado por la entidad de dominio.|
|autor|`Publicacion`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **abrirMiPublicacion()**: colaboración relacionada desde la navegación del caso de uso.
- **crearPublicacion()**: colaboración relacionada desde la navegación del caso de uso.
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

- **Origen**: Caso de uso detallado `abrirMisPublicaciones()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`PublicacionRepository` abstrae el acceso a datos de publicaciones propias, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`ListarPublicacionesView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Investigador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: abrirMisPublicaciones()](/RUP/00-casos-uso/02-detalle/investigador/abrirMisPublicaciones/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
