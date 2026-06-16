# FUNIBER > Investigador > abrirProyecto > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirProyecto/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirProyecto/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirProyecto/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `abrirProyecto()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para presentar al investigador el detalle de proyecto y las acciones disponibles según su rol.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirProyecto(proyectoId)|
|**Actor primario**|Investigador|
|**Objetivo**|Presentar al Investigador el detalle de proyecto y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Investigador autenticado y proyecto visible seleccionado desde `PROYECTOS_ABIERTOS`, o proyecto conservado como contexto desde `ENTREGABLES_ABIERTOS`.|
|**Postcondición exitosa**|El proyecto visible queda presentado en `PROYECTO_ABIERTO`.|
|**Postcondición de fallo**|El proyecto no se abre y el actor vuelve a `PROYECTOS_ABIERTOS`.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirProyecto()](/images/RUP/00-casos-uso/02-detalle/investigador/abrirProyecto/abrirProyecto.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - PROYECTO
<div align=center>

|![Wireframe: abrirProyecto](/images/RUP/00-casos-uso/02-detalle/investigador/abrirProyecto/proyectoAbierto-wireframe.svg)|
|-|
|**Estado**: ValidandoParticipacion / PresentandoProyecto|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita abrir el proyecto seleccionado
- **Sistema** valida que el proyecto sea visible para el Investigador y presenta directamente su detalle.
- **Investigador** consulta el detalle o solicita abrir entregables, investigadores o volver a proyectos.

### Validaciones del wireframe
- ¿El campo o bloque **Datos del proyecto** resulta claro para el Investigador?
- ¿El campo o bloque **ID** resulta claro para el Investigador?
- ¿El campo o bloque **Título** resulta claro para el Investigador?
- ¿El campo o bloque **Estado** resulta claro para el Investigador?
- ¿El campo o bloque **Entidad financiadora** resulta claro para el Investigador?
- ¿El campo o bloque **Coordinador** resulta claro para el Investigador?
- ¿El campo o bloque **Inicio** resulta claro para el Investigador?
- ¿El campo o bloque **Fin** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita abrir el proyecto seleccionado|| |
||**Sistema**|valida su visibilidad y presenta código, título, estado, convocatoria, coordinador, fechas, descripción, equipo y entregables asociados| |
|**Investigador**|consulta el detalle o solicita una acción disponible|| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**ValidandoParticipacion**|Comprueba que el proyecto sea visible para el Investigador.|Sistema debe impedir el acceso a proyectos no visibles.|
|**PresentandoProyecto**|Presenta el detalle en modo consulta.|Sistema debe limitar las acciones a consulta y navegación autorizada.|

## Funcionalidad específica

### Conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### Información tratada
  - ID
  - Título
  - Estado
  - Investigadores asociados

## Opciones de navegación

### Operaciones relacionadas
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.
- **abrirInvestigadores()** -> Navegar a `abrirInvestigadores()` cuando el actor solicita esa continuidad.
- **abrirProyectos()** -> Navegar a `abrirProyectos()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PROYECTOS_ABIERTOS, ENTREGABLES_ABIERTOS.
- **Estado de salida**: PROYECTO_ABIERTO, ENTREGABLES_ABIERTOS, INVESTIGADORES_ABIERTOS, PROYECTOS_ABIERTOS.

## Conexión con diagrama de contexto

Este caso de uso corresponde a `PROYECTOS_ABIERTOS` o `ENTREGABLES_ABIERTOS` → `abrirProyecto(proyectoId)` → `PROYECTO_ABIERTO`. No vuelve a listar proyectos y no concede operaciones de mantenimiento al Investigador.

## Vocabulario utilizado

### Actor (Investigador)
- **solicita**: expresa la intención de realizar una acción.
- **visualiza**: observa la información presentada por el sistema.
- **selecciona**: elige una entidad, acción o alternativa disponible.

### Sistema
- **presenta**: muestra información organizada al actor.
- **permite**: habilita acciones disponibles sin imponer detalles de implementación.
- **registra**: conserva la información indicada por el actor cuando el caso de uso lo requiere.

## Características metodológicas

### Separación de responsabilidades
- **Actor**: usuario que consulta proyectos asociados, gestiona sus entregables, publicaciones, perfil y carga de trabajo.
- **Sistema**: presenta información, habilita acciones y mantiene la navegación del caso de uso.

### Ausencia de detalles de implementación
- No especifica tecnología de interfaz.
- No incluye estructura de base de datos.
- No impone componentes concretos de desarrollo.

### Conversación atómica
- El caso de uso representa una conversación completa.
- Tiene un objetivo claro para el actor Investigador.
- Termina con una acción, navegación o estado observable.

## Referencias

- [Diagramas de contexto](../../../01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](../../../01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](../../../00-modelo-del-dominio/modelo-dominio.md)
- [Detalle y prototipado](../../README.md)
- [conversation-log.md](../../../../../conversation-log.md)
