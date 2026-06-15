# FUNIBER > Coordinador > agregarInvestigador > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/agregarInvestigador/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/agregarInvestigador/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/agregarInvestigador/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para asociar un investigador a un proyecto. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `agregarInvestigador()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: agregarInvestigador()](/images/RUP/01-analisis/casos-uso/coordinador/agregarInvestigador/agregarInvestigador-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### AgregarInvestigadorView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `agregarInvestigador()` del Coordinador.
- Presentar la información de investigadores necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `agregarInvestigador(proyectoId)` desde `PROYECTO_ABIERTO`.
- **Control**: Se comunica con `ProyectoController`.
- **Salida**: Conserva `PROYECTO_ABIERTO`, con el investigador asociado o sin cambios si se cancela.

### Clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `agregarInvestigador()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `AgregarInvestigadorView`.
- **Repositorio**: Coordina `ProyectoRepository`, `InvestigadorRepository` y `CargaTrabajoRepository`.

### Clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores.
- Proporcionar operaciones para obtener candidatos no asociados.
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
- Persistir la asociación entre el proyecto y el investigador seleccionado.

#### CargaTrabajoRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Aportar la carga de trabajo de los candidatos.
- Permitir recomendar al investigador compatible con menor carga.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: `PROYECTO_ABIERTO` -> `AgregarInvestigadorView.agregarInvestigador(proyectoId)`.
2. **Preparación**: `AgregarInvestigadorView` -> `ProyectoController.prepararCandidatos(proyectoId)`.
3. **Candidatos**: El controlador obtiene investigadores no asociados y sus cargas de trabajo.
4. **Evaluación**: `ProyectoController` comprueba compatibilidad y disponibilidad y recomienda al candidato compatible con menor carga.
5. **Asignación**: `AgregarInvestigadorView` -> `ProyectoController.asociarInvestigador(proyectoId, investigadorId)`.
6. **Finalización**: Se conserva `PROYECTO_ABIERTO`, con la asociación registrada o sin cambios si se cancela.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `agregarInvestigador()`|`AgregarInvestigadorView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`ProyectoController`|`prepararCandidatos(proyectoId)`, `asociarInvestigador(...)`|
|Evaluar compatibilidad y disponibilidad|`ProyectoController`|`evaluarCompatibilidadYDisponibilidad()`|
|Comparar cargas|`CargaTrabajoRepository`|`obtenerCargas(candidatos)`|
|Persistir la asociación|`ProyectoRepository`|`asociarInvestigador(proyectoId, investigadorId)`|
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
- Seleccionar perfiles existentes, comprobar compatibilidad y disponibilidad, y recomendar al candidato compatible con menor carga.

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

- **Origen**: Caso de uso detallado `agregarInvestigador()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`InvestigadorRepository` abstrae el acceso a datos de investigadores, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`AgregarInvestigadorView`), lógica de aplicación (`ProyectoController`) y datos de proyecto, investigador y carga.

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: agregarInvestigador()](/RUP/00-casos-uso/02-detalle/coordinador/agregarInvestigador/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
