# FUNIBER > Coordinador > eliminarRecompensa > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarRecompensa/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarRecompensa/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/eliminarRecompensa/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para eliminar un elemento de recompensa. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `eliminarRecompensa()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: eliminarRecompensa()](/images/RUP/01-analisis/casos-uso/coordinador/eliminarRecompensa/eliminarRecompensa-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### EliminarRecompensaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `eliminarRecompensa()` del Coordinador.
- Presentar la información de recompensas necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `eliminarRecompensa()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `RecompensaController`.
- **Salida**: Devuelve el control a la navegación definida para el Coordinador.

### Clases de control

#### RecompensaController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `eliminarRecompensa()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarRecompensaView`.
- **Repositorio**: Delega operaciones de datos a `RecompensaRepository`.

### Clases de entidad (entity)

#### RecompensaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de recompensas.
- Proporcionar operaciones `obtenerPorId(id)` y `eliminar(id)`.
- Mantener la consistencia conceptual de recompensas.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `RecompensaController`.
- **Entidad**: Gestiona instancias de `Recompensa`.

#### Recompensa
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de recompensa.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `RecompensaRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `EliminarRecompensaView.eliminarRecompensa()`.
2. **Confirmación previa**: `EliminarRecompensaView` -> `RecompensaController.solicitarConfirmacion(id)`.
3. **Presentación de confirmación**: `RecompensaController` -> `EliminarRecompensaView.presentarConfirmacion()`.
4. **Decisión del actor**: si confirma, `EliminarRecompensaView` -> `RecompensaController.confirmarEliminacion(id)`; si cancela, `EliminarRecompensaView` -> `RecompensaController.cancelarEliminacion()`.
5. **Validación y persistencia**: `RecompensaController` -> `RecompensaRepository.obtenerPorId(id)` y `RecompensaRepository.eliminar(id)`.
6. **Finalización**: si confirma, se presenta el listado de recompensas; si cancela, se mantiene la recompensa abierta.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `eliminarRecompensa()`|`EliminarRecompensaView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`RecompensaController`|`solicitarConfirmacion(id)`, `confirmarEliminacion(id)`|
|Aplicar permisos, validaciones y cancelación|`RecompensaController`|`validarEliminacion(id)`, `cancelarEliminacion()`|
|Acceder a datos de recompensas|`RecompensaRepository`|`obtenerPorId(id)`, `eliminar(id)`|
|Representar atributos de dominio|`Recompensa`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|concepto|`Recompensa`|Atributo conceptual tratado por la entidad de dominio.|
|proyecto|`Recompensa`|Atributo conceptual tratado por la entidad de dominio.|
|investigador|`Recompensa`|Atributo conceptual tratado por la entidad de dominio.|
|estado|`Recompensa`|Atributo conceptual tratado por la entidad de dominio.|
|valor|`Recompensa`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **abrirRecompensas()**: colaboración relacionada desde la navegación del caso de uso.

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

- **Origen**: Caso de uso detallado `eliminarRecompensa()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`RecompensaRepository` abstrae el acceso a datos de recompensas, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`EliminarRecompensaView`), lógica de aplicación (`RecompensaController`) y datos (`Recompensa`, `RecompensaRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: eliminarRecompensa()](/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
