# FUNIBER > Coordinador > eliminarInvestigador > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarInvestigador/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarInvestigador/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para retirar a un investigador de un proyecto sin eliminar su perfil y sin dejar responsabilidades pendientes.

## Diagrama de colaboración

<div align=center>

|![Análisis: eliminarInvestigador()](/images/RUP/01-analisis/casos-uso/coordinador/eliminarInvestigador/eliminarInvestigador-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### EliminarInvestigadorView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `eliminarInvestigador()` del Coordinador.
- Presentar la información de investigadores necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `eliminarInvestigador(proyectoId, investigadorId)` desde `PROYECTO_ABIERTO`.
- **Control**: Se comunica con `ProyectoController`.
- **Salida**: Conserva `PROYECTO_ABIERTO`, con el investigador desasignado o sin cambios.

### Clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `eliminarInvestigador()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarInvestigadorView`.
- **Repositorio**: Coordina `ProyectoRepository`, `InvestigadorRepository` y `EntregableRepository`.

### Clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores.
- Proporcionar operaciones para recuperar el investigador seleccionado; el perfil no se elimina.
- Mantener la consistencia conceptual de investigadores.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `ProyectoController`.
- **Entidad**: Gestiona instancias de `Investigador`.

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de investigador.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`.

#### ProyectoRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Recuperar el proyecto abierto.
- Registrar la desasignación sin borrar la participación histórica entre proyecto e investigador.

#### EntregableRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Consultar entregables pendientes asignados al investigador.
- Aportar la información que puede impedir la desasignación.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: `PROYECTO_ABIERTO` -> `EliminarInvestigadorView.eliminarInvestigador(proyectoId, investigadorId)`.
2. **Comprobación**: `ProyectoController` recupera proyecto, investigador y responsabilidades pendientes.
3. **Decisión**: Si existen entregables pendientes, informa que no puede retirarse; si no existen, solicita confirmación.
4. **Desasignación**: Tras confirmar, `ProyectoRepository.registrarDesasignacion(proyectoId, investigadorId, fecha, coordinador)`.
5. **Conservación histórica**: El investigador desaparece de la composición activa, pero se conservan su perfil y su participación histórica.
6. **Finalización**: Se conserva `PROYECTO_ABIERTO`.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `eliminarInvestigador()`|`EliminarInvestigadorView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`ProyectoController`|`comprobarDesasignacion(...)`, `confirmarDesasignacion(...)`|
|Comprobar responsabilidades pendientes|`EntregableRepository`|`obtenerPendientesAsignados(...)`|
|Registrar la desasignación sin borrar el histórico|`ProyectoRepository`|`registrarDesasignacion(proyectoId, investigadorId, fecha, coordinador)`|
|Conservar el perfil|`InvestigadorRepository`|`obtenerPorId(investigadorId)` sin eliminación|
|Representar atributos de dominio|`Investigador`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|nombre|`Investigador`|Atributo conceptual tratado por la entidad de dominio.|
|correo|`Investigador`|Atributo conceptual tratado por la entidad de dominio.|
|área|`Investigador`|Atributo conceptual tratado por la entidad de dominio.|
|perfil|`Investigador`|Atributo conceptual tratado por la entidad de dominio.|
|carga de trabajo|`Investigador`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **editarProyecto()**: colaboración disponible desde `PROYECTO_ABIERTO`.
- **eliminarProyecto()**: colaboración disponible desde `PROYECTO_ABIERTO`.
- **agregarInvestigador()**: colaboración disponible desde `PROYECTO_ABIERTO`.
- **eliminarInvestigador()**: colaboración disponible desde `PROYECTO_ABIERTO`.
- **abrirEntregables()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirInvestigadores()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirProyectos()**: colaboración relacionada desde la navegación del caso de uso.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Coordinador.
- Comprobar responsabilidades pendientes y registrar la desasignación sin borrar la participación histórica.

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

- **Origen**: Caso de uso detallado `eliminarInvestigador()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`InvestigadorRepository` abstrae el acceso a datos de investigadores, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`EliminarInvestigadorView`), lógica de aplicación (`ProyectoController`) y datos de proyecto, investigador y entregables.

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: eliminarInvestigador()](/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
