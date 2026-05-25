# FUNIBER > Coordinador > abrirProyecto > Detalle y prototipado

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

Especificación detallada del caso de uso `abrirProyecto()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el detalle de proyecto y las acciones disponibles según su rol.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirProyecto()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el detalle de proyecto y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Coordinador visualiza el detalle de proyecto y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## diagrama de especificación

<div align=center>

|![Caso de uso: abrirProyecto()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/abrirProyecto.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - PROYECTO
<div align=center>

|![Wireframe: abrirProyecto](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/proyectoAbierto-wireframe.svg)|
|-|
|**Estado**: MostrandoListaProyectos / FiltrandoBuscando / SeleccionandoProyecto|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita abrir un proyecto
- **Sistema** presenta la lista de proyectos con:<br>- ID<br>- Título<br>- Estado<br>- Coordinador responsable<br>- Fechas de inicio y fin<br>- Descripción<br>y permite solicitar las siguientes acciones:<br>- Filtrar proyectos<br>- Buscar proyectos<br>- Seleccionar un proyecto
- **Coordinador** aplica filtros/búsqueda<br>**Sistema** muestra la lista filtrada
- **Coordinador** selecciona un proyecto del listado

### validaciones del wireframe
- ¿El campo o bloque **Datos del proyecto** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿El campo o bloque **Entidad financiadora** resulta claro para el Coordinador?
- ¿El campo o bloque **Coordinador** resulta claro para el Coordinador?
- ¿El campo o bloque **Inicio** resulta claro para el Coordinador?
- ¿El campo o bloque **Fin** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir un proyecto|| |
||**Sistema**|presenta la lista de proyectos con:<br>- ID<br>- Título<br>- Estado<br>- Coordinador responsable<br>- Fechas de inicio y fin<br>- Descripción<br>y permite solicitar las siguientes acciones:<br>- Filtrar proyectos<br>- Buscar proyectos<br>- Seleccionar un proyecto| |
|**Coordinador**|aplica filtros/búsqueda<br>|| |
||**Sistema**|muestra la lista filtrada| |
|**Coordinador**|selecciona un proyecto del listado|| |
||**Sistema**|visualiza el proyecto seleccionado con:<br>- Datos del proyecto (ID, título, estado, entidad financiadora, coordinador, inicio, fin)<br>- Descripción<br>- Equipo<br>y permite solicitar las siguientes acciones:<br>- Editar proyecto<br>- Eliminar proyecto<br>- Abrir entregables<br>- Agregar investigador<br>- Abrir investigadores<br>- Volver a proyectos| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoListaProyectos**|Estado interno asociado a mostrando lista proyectos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**FiltrandoBuscando**|Estado interno asociado a filtrando buscando.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**SeleccionandoProyecto**|Estado interno asociado a seleccionando proyecto.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

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
- **editarProyecto()** -> Navegar a `editarProyecto()` cuando el actor solicita esa continuidad.
- **eliminarProyecto()** -> Navegar a `eliminarProyecto()` cuando el actor solicita esa continuidad.
- **agregarInvestigador()** -> Navegar a `agregarInvestigador()` cuando el actor solicita esa continuidad.
- **eliminarInvestigador()** -> Navegar a `eliminarInvestigador()` cuando el actor solicita esa continuidad.
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.
- **abrirInvestigadores()** -> Navegar a `abrirInvestigadores()` cuando el actor solicita esa continuidad.
- **abrirProyectos()** -> Navegar a `abrirProyectos()` cuando el actor solicita esa continuidad.

### navegación del sistema
- **Estado de entrada**: PROYECTOS_ABIERTOS, ENTREGABLES_ABIERTOS.
- **Estado de salida**: PROYECTO_ABIERTO, PROYECTOS_ABIERTOS, ENTREGABLES_ABIERTOS, INVESTIGADORES_ABIERTOS.

## conexión con diagrama de contexto

Este caso de uso se integra en los diagramas de contexto del Coordinador, manteniendo la trazabilidad entre navegación, estado del sistema y responsabilidad del actor.

## vocabulario utilizado

### actor (Coordinador)
- **solicita**: expresa la intención de realizar una acción.
- **visualiza**: observa la información presentada por el sistema.
- **selecciona**: elige una entidad, acción o alternativa disponible.

### sistema
- **presenta**: muestra información organizada al actor.
- **permite**: habilita acciones disponibles sin imponer detalles de implementación.
- **registra**: conserva la información indicada por el actor cuando el caso de uso lo requiere.

## características metodológicas

### separación de responsabilidades
- **Actor**: usuario con visión global sobre proyectos, investigadores, convocatorias, publicaciones, entregables, recompensas y solicitudes de perfil.
- **Sistema**: presenta información, habilita acciones y mantiene la navegación del caso de uso.

### ausencia de detalles de implementación
- No especifica tecnología de interfaz.
- No incluye estructura de base de datos.
- No impone componentes concretos de desarrollo.

### conversación atómica
- El caso de uso representa una conversación completa.
- Tiene un objetivo claro para el actor Coordinador.
- Termina con una acción, navegación o estado observable.

## referencias

- [Diagramas de contexto](../../../01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](../../../01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](../../../00-modelo-del-dominio/modelo-dominio.md)
- [Detalle y prototipado](../../README.md)
- [conversation-log.md](../../../../../conversation-log.md)
