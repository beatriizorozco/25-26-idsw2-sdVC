# FUNIBER > Coordinador > iniciarSesion > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/iniciarSesion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/iniciarSesion/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/iniciarSesion/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para autenticar al Coordinador. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `iniciarSesion()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: iniciarSesion()](/images/RUP/01-analisis/casos-uso/coordinador/iniciarSesion/iniciarSesion-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### IniciarSesionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `iniciarSesion()` del Coordinador.
- Presentar la información de sesiones necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `iniciarSesion()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `SesionController`.
- **Salida**: Devuelve el control a la navegación definida para el Coordinador.

### Clases de control

#### SesionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `iniciarSesion()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `IniciarSesionView`.
- **Repositorio**: Delega operaciones de datos a `SesionRepository`.

### Clases de entidad (entity)

#### SesionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de sesiones.
- Proporcionar operaciones `obtenerPorCorreo(correo)` y `registrarSesion(usuario)`.
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

1. **Inicio**: Estado de contexto -> `IniciarSesionView.iniciarSesion()`.
2. **Solicitud principal**: `IniciarSesionView` -> `SesionController.iniciarSesion(credenciales)`.
3. **Acceso a usuario**: `IniciarSesionView` -> `SesionController.validarCredenciales(credenciales)`.
4. **Validación de credenciales**: `SesionController` -> `SesionRepository.obtenerPorCorreo(correo)`.
5. **Registro de sesión**: `SesionController` -> `SesionRepository.registrarSesion(usuario)`.
6. **Finalización**: `IniciarSesionView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `iniciarSesion()`|`IniciarSesionView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`SesionController`|`iniciarSesion(credenciales)`|
|Aplicar permisos y validaciones|`SesionController`|`validarCredenciales(credenciales)`|
|Acceder a datos de sesiones|`SesionRepository`|`obtenerPorCorreo(correo)`, `registrarSesion(usuario)`|
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

- **Origen**: Caso de uso detallado `iniciarSesion()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`SesionRepository` abstrae el acceso a datos de sesiones, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`IniciarSesionView`), lógica de aplicación (`SesionController`) y datos (`Sesion`, `SesionRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: iniciarSesion()](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
