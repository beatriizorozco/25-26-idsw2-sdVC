# FUNIBER > Coordinador > abrirConvocatorias > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatorias/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirConvocatorias/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirConvocatorias/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirConvocatorias/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para presentar a Coordinador el listado de convocatorias, con filtrado y navegación. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `abrirConvocatorias()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirConvocatorias()](/images/RUP/01-analisis/casos-uso/coordinador/abrirConvocatorias/abrirConvocatorias-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### ListarConvocatoriasView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirConvocatorias()` del Coordinador.
- Presentar la información de convocatorias necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `abrirConvocatorias()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `ConvocatoriaController`.
- **Salida**: Devuelve el control a la navegación definida para el Coordinador.

### Clases de control

#### ConvocatoriaController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `abrirConvocatorias()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ListarConvocatoriasView`.
- **Repositorio**: Delega operaciones de datos a `ConvocatoriaRepository`.

### Clases de entidad (entity)

#### ConvocatoriaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de convocatorias.
- Proporcionar operaciones `obtenerTodos()` y `buscarPorCriterio(criterio)`.
- Mantener la consistencia conceptual de convocatorias.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `ConvocatoriaController`.
- **Entidad**: Gestiona instancias de `Convocatoria`.

#### Convocatoria
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de convocatoria.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ConvocatoriaRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `ListarConvocatoriasView.abrirConvocatorias()`.
2. **Solicitud principal**: `ListarConvocatoriasView` -> `ConvocatoriaController.listarConvocatorias()`.
3. **Acceso a datos**: `ListarConvocatoriasView` -> `ConvocatoriaController.filtrarConvocatorias(criterio)`.
4. **Filtrado o refinamiento**: `ConvocatoriaController` -> `ConvocatoriaRepository.obtenerTodos()`.
5. **Búsqueda**: `ConvocatoriaController` -> `ConvocatoriaRepository.buscarPorCriterio(criterio)`.
6. **Finalización**: `ListarConvocatoriasView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `abrirConvocatorias()`|`ListarConvocatoriasView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`ConvocatoriaController`|`listarConvocatorias()`|
|Aplicar permisos y validaciones|`ConvocatoriaController`|`filtrarConvocatorias(criterio)`|
|Acceder a datos de convocatorias|`ConvocatoriaRepository`|`obtenerTodos()`, `buscarPorCriterio(criterio)`|
|Representar atributos de dominio|`Convocatoria`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|título|`Convocatoria`|Atributo conceptual tratado por la entidad de dominio.|
|entidad convocante|`Convocatoria`|Atributo conceptual tratado por la entidad de dominio.|
|plazos|`Convocatoria`|Atributo conceptual tratado por la entidad de dominio.|
|estado|`Convocatoria`|Atributo conceptual tratado por la entidad de dominio.|
|requisitos|`Convocatoria`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **abrirConvocatoria()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirPanelPrincipal()**: colaboración relacionada desde la navegación del caso de uso.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Coordinador.
- Permitir al Coordinador acceso global sobre publicaciones, entregables, proyectos, investigadores, recompensas y perfiles según el caso de uso.
- Tratar las convocatorias como entidades importadas; no se modela CRUD manual de convocatorias.

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

- **Origen**: Caso de uso detallado `abrirConvocatorias()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`ConvocatoriaRepository` abstrae el acceso a datos de convocatorias, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`ListarConvocatoriasView`), lógica de aplicación (`ConvocatoriaController`) y datos (`Convocatoria`, `ConvocatoriaRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: abrirConvocatorias()](/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatorias/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
