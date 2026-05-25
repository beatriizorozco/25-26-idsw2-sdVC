# FUNIBER > Investigador > crearEntregable > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/crearEntregable/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/crearEntregable/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/crearEntregable/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/crearEntregable/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para registrar un nuevo entregable. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `crearEntregable()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: crearEntregable()](/images/RUP/01-analisis/casos-uso/investigador/crearEntregable/crearEntregable-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### CrearEntregableView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `crearEntregable()` del Investigador.
- Presentar la información de entregables necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Investigador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `crearEntregable()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `EntregableController`.
- **Salida**: Devuelve el control a la navegación definida para el Investigador.

### Clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `crearEntregable()`.
- Aplicar reglas de permisos del Investigador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CrearEntregableView`.
- **Repositorio**: Delega operaciones de datos a `EntregableRepository`.

### Clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de entregables.
- Proporcionar operaciones `existeDuplicado(datos)` y `guardar(entidad)`.
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

1. **Inicio**: Estado de contexto -> `CrearEntregableView.crearEntregable()`.
2. **Solicitud principal**: `CrearEntregableView` -> `EntregableController.crearEntregable(datos)`.
3. **Validación previa**: `CrearEntregableView` -> `EntregableController.validarEntregable(datos)`.
4. **Consulta de consistencia**: `EntregableController` -> `EntregableRepository.existeDuplicado(datos)`.
5. **Persistencia**: `EntregableController` -> `EntregableRepository.guardar(entidad)`.
6. **Finalización**: `CrearEntregableView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Investigador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `crearEntregable()`|`CrearEntregableView`|Recibe la acción del Investigador|
|Coordinar reglas del caso de uso|`EntregableController`|`crearEntregable(datos)`|
|Aplicar permisos y validaciones|`EntregableController`|`validarEntregable(datos)`|
|Acceder a datos de entregables|`EntregableRepository`|`existeDuplicado(datos)`, `guardar(entidad)`|
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

- **editarEntregable()**: colaboración relacionada desde la navegación del caso de uso.
- **eliminarEntregable()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirEntregables()**: colaboración relacionada desde la navegación del caso de uso.

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

- **Origen**: Caso de uso detallado `crearEntregable()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`EntregableRepository` abstrae el acceso a datos de entregables, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`CrearEntregableView`), lógica de aplicación (`EntregableController`) y datos (`Entregable`, `EntregableRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Investigador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: crearEntregable()](/RUP/00-casos-uso/02-detalle/investigador/crearEntregable/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
