# FUNIBER > Coordinador > eliminarInvestigador > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarInvestigador/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarInvestigador/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/eliminarInvestigador/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para eliminar un elemento de investigador. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `eliminarInvestigador()`.

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
- **Entrada**: Recibe `eliminarInvestigador()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `InvestigadorController`.
- **Salida**: Devuelve el control a la navegación definida para el Coordinador.

### Clases de control

#### InvestigadorController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `eliminarInvestigador()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarInvestigadorView`.
- **Repositorio**: Delega operaciones de datos a `InvestigadorRepository`.

### Clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores.
- Proporcionar operaciones `obtenerPorId(id)` y `eliminar(id)`.
- Mantener la consistencia conceptual de investigadores.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `InvestigadorController`.
- **Entidad**: Gestiona instancias de `Investigador`.

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de investigador.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `EliminarInvestigadorView.eliminarInvestigador()`.
2. **Solicitud principal**: `EliminarInvestigadorView` -> `InvestigadorController.eliminarInvestigador(id)`.
3. **Acceso a datos**: `EliminarInvestigadorView` -> `InvestigadorController.validarEliminacion(id)`.
4. **Validación de eliminación**: `InvestigadorController` -> `InvestigadorRepository.obtenerPorId(id)`.
5. **Persistencia**: `InvestigadorController` -> `InvestigadorRepository.eliminar(id)`.
6. **Finalización**: `EliminarInvestigadorView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `eliminarInvestigador()`|`EliminarInvestigadorView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`InvestigadorController`|`eliminarInvestigador(id)`|
|Aplicar permisos y validaciones|`InvestigadorController`|`validarEliminacion(id)`|
|Acceder a datos de investigadores|`InvestigadorRepository`|`obtenerPorId(id)`, `eliminar(id)`|
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

- **abrirEntregables()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirProyectos()**: colaboración relacionada desde la navegación del caso de uso.

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

- **Origen**: Caso de uso detallado `eliminarInvestigador()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`InvestigadorRepository` abstrae el acceso a datos de investigadores, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`EliminarInvestigadorView`), lógica de aplicación (`InvestigadorController`) y datos (`Investigador`, `InvestigadorRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: eliminarInvestigador()](/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
