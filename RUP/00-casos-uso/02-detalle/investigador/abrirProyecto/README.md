# FUNIBER > Investigador > abrirProyecto > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/README.md)|[Diseño](/RUP/02-diseño/README.md)|[Desarrollo](/RUP/03-desarrollo/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## propósito

Especificación detallada del caso de uso `abrirProyecto()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para presentar al investigador el detalle de proyecto y las acciones disponibles según su rol.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirProyecto()|
|**Actor primario**|Investigador|
|**Objetivo**|Presentar al Investigador el detalle de proyecto y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Investigador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Investigador visualiza el detalle de proyecto y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## diagrama de especificación

<div align=center>

|![Caso de uso: abrirProyecto()](/images/RUP/00-casos-uso/02-detalle/investigador/abrirProyecto/abrirProyecto.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - PROYECTO
<div align=center>

|![Wireframe: abrirProyecto](/images/RUP/00-casos-uso/02-detalle/investigador/abrirProyecto/proyectoAbierto-wireframe.svg)|
|-|
|**Estado**: MostrandoListaProyectos / FiltrandoBuscando / SolicitandoProyecto|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita abrir un proyecto
- **Sistema** presenta la lista de proyectos con sus datos principales y permite solicitar<br>la introducción de criterios de filtrado y búsqueda, así como solicitar la apertura de un proyecto del listado.
- **Investigador** solicita introducir filtros y/o solicita introducir búsqueda<br>**Sistema** muestra el listado actualizado según los criterios introducidos
- **Investigador** solicita abrir un proyecto del listado

### validaciones del wireframe
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

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita abrir un proyecto|| |
||**Sistema**|presenta la lista de proyectos con sus datos principales y permite solicitar<br>la introducción de criterios de filtrado y búsqueda, así como solicitar la apertura de un proyecto del listado.| |
|**Investigador**|solicita introducir filtros y/o solicita introducir búsqueda<br>|| |
||**Sistema**|muestra el listado actualizado según los criterios introducidos| |
|**Investigador**|solicita abrir un proyecto del listado|| |
||**Sistema**|visualiza el proyecto solicitado, presentando sus datos, la descripción y el equipo, y permite solicitar acciones disponibles sobre el proyecto abierto.| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoListaProyectos**|Estado interno asociado a mostrando lista proyectos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**FiltrandoBuscando**|Estado interno asociado a filtrando buscando.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**SolicitandoProyecto**|Estado interno asociado a solicitando proyecto.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### información tratada
  - ID
  - Título
  - Estado
  - Investigadores asociados

## opciones de navegación

### operaciones relacionadas
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.
- **abrirInvestigadores()** -> Navegar a `abrirInvestigadores()` cuando el actor solicita esa continuidad.
- **abrirProyectos()** -> Navegar a `abrirProyectos()` cuando el actor solicita esa continuidad.

### navegación del sistema
- **Estado de entrada**: PROYECTOS_ABIERTOS, ENTREGABLES_ABIERTOS.
- **Estado de salida**: PROYECTO_ABIERTO, ENTREGABLES_ABIERTOS, INVESTIGADORES_ABIERTOS, PROYECTOS_ABIERTOS.

## conexión con diagrama de contexto

Este caso de uso se integra en los diagramas de contexto del Investigador, manteniendo la trazabilidad entre navegación, estado del sistema y responsabilidad del actor.

## vocabulario utilizado

### actor (Investigador)
- **solicita**: expresa la intención de realizar una acción.
- **visualiza**: observa la información presentada por el sistema.
- **selecciona**: elige una entidad, acción o alternativa disponible.

### sistema
- **presenta**: muestra información organizada al actor.
- **permite**: habilita acciones disponibles sin imponer detalles de implementación.
- **registra**: conserva la información indicada por el actor cuando el caso de uso lo requiere.

## características metodológicas

### separación de responsabilidades
- **Actor**: usuario que consulta proyectos asociados, gestiona sus entregables, publicaciones, perfil y carga de trabajo.
- **Sistema**: presenta información, habilita acciones y mantiene la navegación del caso de uso.

### ausencia de detalles de implementación
- No especifica tecnología de interfaz.
- No incluye estructura de base de datos.
- No impone componentes concretos de desarrollo.

### conversación atómica
- El caso de uso representa una conversación completa.
- Tiene un objetivo claro para el actor Investigador.
- Termina con una acción, navegación o estado observable.

## referencias

- [Diagramas de contexto](../../../01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](../../../01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](../../../00-modelo-del-dominio/modelo-dominio.md)
- [Detalle y prototipado](../../README.md)
- [conversation-log.md](../../../../../conversation-log.md)
