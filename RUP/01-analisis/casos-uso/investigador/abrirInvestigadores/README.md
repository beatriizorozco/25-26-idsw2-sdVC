# FUNIBER > Investigador > abrirInvestigadores > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigadores/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirInvestigadores/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirInvestigadores/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirInvestigadores/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para presentar a Investigador el listado de investigadores, con filtrado y navegación. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `abrirInvestigadores()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: abrirInvestigadores()](/images/RUP/01-analisis/casos-uso/investigador/abrirInvestigadores/abrirInvestigadores-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### ListarInvestigadoresView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirInvestigadores()` del Investigador.
- Presentar la información de investigadores necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Investigador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `abrirInvestigadores()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `InvestigadorController`.
- **Salida**: Devuelve el control a la navegación definida para el Investigador.

### Clases de control

#### InvestigadorController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `abrirInvestigadores()`.
- Aplicar reglas de permisos del Investigador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ListarInvestigadoresView`.
- **Repositorio**: Delega operaciones de datos a `InvestigadorRepository`.

### Clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores.
- Proporcionar operaciones `obtenerTodos()` y `buscarPorCriterio(criterio)`.
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

1. **Inicio**: Estado de contexto -> `ListarInvestigadoresView.abrirInvestigadores()`.
2. **Solicitud principal**: `ListarInvestigadoresView` -> `InvestigadorController.listarInvestigadores()`.
3. **Acceso a datos**: `ListarInvestigadoresView` -> `InvestigadorController.filtrarInvestigadores(criterio)`.
4. **Filtrado o refinamiento**: `InvestigadorController` -> `InvestigadorRepository.obtenerTodos()`.
5. **Búsqueda**: `InvestigadorController` -> `InvestigadorRepository.buscarPorCriterio(criterio)`.
6. **Finalización**: `ListarInvestigadoresView` devuelve el control al estado de navegación definido.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Investigador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `abrirInvestigadores()`|`ListarInvestigadoresView`|Recibe la acción del Investigador|
|Coordinar reglas del caso de uso|`InvestigadorController`|`listarInvestigadores()`|
|Aplicar permisos y validaciones|`InvestigadorController`|`filtrarInvestigadores(criterio)`|
|Acceder a datos de investigadores|`InvestigadorRepository`|`obtenerTodos()`, `buscarPorCriterio(criterio)`|
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

- **abrirInvestigador()**: colaboración relacionada desde la navegación del caso de uso.
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

- **Origen**: Caso de uso detallado `abrirInvestigadores()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`InvestigadorRepository` abstrae el acceso a datos de investigadores, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`ListarInvestigadoresView`), lógica de aplicación (`InvestigadorController`) y datos (`Investigador`, `InvestigadorRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Investigador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: abrirInvestigadores()](/RUP/00-casos-uso/02-detalle/investigador/abrirInvestigadores/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
