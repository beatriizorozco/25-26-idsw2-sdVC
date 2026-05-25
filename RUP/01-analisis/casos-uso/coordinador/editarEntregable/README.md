# FUNIBER > Coordinador > editarEntregable > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarEntregable/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarEntregable/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarEntregable/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarEntregable/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para actualizar los datos de entregable. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `editarEntregable()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: editarEntregable()](/images/RUP/01-analisis/casos-uso/coordinador/editarEntregable/editarEntregable-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### EditarEntregableView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `editarEntregable()` del Coordinador.
- Presentar la información de entregables necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `editarEntregable()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `EntregableController`.
- **Salida**: Devuelve el control a la navegación definida para el Coordinador.

### Clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `editarEntregable()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarEntregableView`.
- **Repositorio**: Delega operaciones de datos a `EntregableRepository`.

### Clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de entregables.
- Proporcionar operaciones `obtenerPorId(id)` y `actualizar(entidad)`.
- Mantener la consistencia conceptual de entregables.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `EntregableController`.
- **Entidad**: Gestiona instancias de `Entregable`.

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de entregable.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `EditarEntregableView.editarEntregable()`.
2. **Solicitud principal**: `EditarEntregableView` -> `EntregableController.editarEntregable(id, datos)`.
3. **Acceso a datos**: `EditarEntregableView` -> `EntregableController.validarCambios(datos)`.
4. **Validación de cambios**: `EntregableController` -> `EntregableRepository.obtenerPorId(id)`.
5. **Persistencia**: `EntregableController` -> `EntregableRepository.actualizar(entidad)`.
6. **Finalización**: `EditarEntregableView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `editarEntregable()`|`EditarEntregableView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`EntregableController`|`editarEntregable(id, datos)`|
|Aplicar permisos y validaciones|`EntregableController`|`validarCambios(datos)`|
|Acceder a datos de entregables|`EntregableRepository`|`obtenerPorId(id)`, `actualizar(entidad)`|
|Representar atributos de dominio|`Entregable`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|título|`Entregable`|Atributo conceptual tratado por la entidad de dominio.|
|proyecto|`Entregable`|Atributo conceptual tratado por la entidad de dominio.|
|responsable|`Entregable`|Atributo conceptual tratado por la entidad de dominio.|
|fecha|`Entregable`|Atributo conceptual tratado por la entidad de dominio.|
|estado|`Entregable`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **eliminarEntregable()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirEntregables()**: colaboración relacionada desde la navegación del caso de uso.

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

- **Origen**: Caso de uso detallado `editarEntregable()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`EntregableRepository` abstrae el acceso a datos de entregables, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`EditarEntregableView`), lógica de aplicación (`EntregableController`) y datos (`Entregable`, `EntregableRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: editarEntregable()](/RUP/00-casos-uso/02-detalle/coordinador/editarEntregable/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
