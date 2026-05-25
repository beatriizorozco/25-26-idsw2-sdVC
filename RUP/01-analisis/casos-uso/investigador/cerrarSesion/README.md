# FUNIBER > Investigador > cerrarSesion > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/cerrarSesion/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/cerrarSesion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/cerrarSesion/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/cerrarSesion/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para cerrar la sesión del Investigador. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `cerrarSesion()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: cerrarSesion()](/images/RUP/01-analisis/casos-uso/investigador/cerrarSesion/cerrarSesion-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### CerrarSesionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `cerrarSesion()` del Investigador.
- Presentar la información de sesiones necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Investigador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `cerrarSesion()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `SesionController`.
- **Salida**: Devuelve el control a la navegación definida para el Investigador.

### Clases de control

#### SesionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `cerrarSesion()`.
- Aplicar reglas de permisos del Investigador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CerrarSesionView`.
- **Repositorio**: Delega operaciones de datos a `SesionRepository`.

### Clases de entidad (entity)

#### SesionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de sesiones.
- Proporcionar operaciones `obtenerSesionActiva(actor)` y `cerrar(sesion)`.
- Mantener la consistencia conceptual de sesiones.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `SesionController`.
- **Entidad**: Gestiona instancias de `Sesion`.

#### Sesion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de sesión.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `SesionRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `CerrarSesionView.cerrarSesion()`.
2. **Solicitud principal**: `CerrarSesionView` -> `SesionController.cerrarSesion(sesion)`.
3. **Acceso a sesión**: `CerrarSesionView` -> `SesionController.invalidarSesion(sesion)`.
4. **Invalidación**: `SesionController` -> `SesionRepository.obtenerSesionActiva(actor)`.
5. **Persistencia**: `SesionController` -> `SesionRepository.cerrar(sesion)`.
6. **Finalización**: `CerrarSesionView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Investigador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `cerrarSesion()`|`CerrarSesionView`|Recibe la acción del Investigador|
|Coordinar reglas del caso de uso|`SesionController`|`cerrarSesion(sesion)`|
|Aplicar permisos y validaciones|`SesionController`|`invalidarSesion(sesion)`|
|Acceder a datos de sesiones|`SesionRepository`|`obtenerSesionActiva(actor)`, `cerrar(sesion)`|
|Representar atributos de dominio|`Sesion`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|credenciales|`Sesion`|Atributo conceptual tratado por la entidad de dominio.|
|rol|`Sesion`|Atributo conceptual tratado por la entidad de dominio.|
|estado de sesión|`Sesion`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- No requiere colaboraciones adicionales; finaliza devolviendo el control al estado de navegación definido.

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

- **Origen**: Caso de uso detallado `cerrarSesion()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`SesionRepository` abstrae el acceso a datos de sesiones, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`CerrarSesionView`), lógica de aplicación (`SesionController`) y datos (`Sesion`, `SesionRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Investigador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: cerrarSesion()](/RUP/00-casos-uso/02-detalle/investigador/cerrarSesion/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
