# FUNIBER > Coordinador > abrirOpcionesCargaTrabajo > Detalle y prototipado

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

Especificación detallada del caso de uso `abrirOpcionesCargaTrabajo()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el detalle de opciones de carga de trabajo y las acciones disponibles según su rol.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirOpcionesCargaTrabajo()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el detalle de opciones de carga de trabajo y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Coordinador visualiza el detalle de opciones de carga de trabajo y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## diagrama de especificación

<div align=center>

|![Caso de uso: abrirOpcionesCargaTrabajo()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesCargaTrabajo/abrirOpcionesCargaTrabajo.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - CARGA DE TRABAJO
<div align=center>

|![Wireframe: abrirOpcionesCargaTrabajo](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesCargaTrabajo/abrirOpcionesCargaTrabajo-wireframe.svg)|
|-|
|**Estado**: CargandoCargaTrabajo|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita abrir las opciones de carga de trabajo
- **Sistema** muestra la información de carga de trabajo:<br>- Filtros disponibles<br>- Resumen global<br>- Detalle por persona<br>- Detalle de la persona seleccionada<br>y permite solicitar las siguientes acciones:<br>- Aplicar filtros<br>- Limpiar filtros<br>- Editar carga de trabajo<br>- Abrir panel principal
- Pantalla de carga de trabajo mostrada
- editarCargaTrabajo()

### validaciones del wireframe
- ¿El campo o bloque **Filtros** resulta claro para el Coordinador?
- ¿El campo o bloque **Sede** resulta claro para el Coordinador?
- ¿El campo o bloque **Perfil** resulta claro para el Coordinador?
- ¿El campo o bloque **Investigador/docente** resulta claro para el Coordinador?
- ¿El campo o bloque **Área de investigación** resulta claro para el Coordinador?
- ¿El campo o bloque **Proyecto** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado del proyecto** resulta claro para el Coordinador?
- ¿El campo o bloque **Resumen global** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir las opciones de carga de trabajo|| |
||**Sistema**|muestra la información de carga de trabajo:<br>- Filtros disponibles<br>- Resumen global<br>- Detalle por persona<br>- Detalle de la persona seleccionada<br>y permite solicitar las siguientes acciones:<br>- Aplicar filtros<br>- Limpiar filtros<br>- Editar carga de trabajo<br>- Abrir panel principal| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**CargandoCargaTrabajo**|Estado interno asociado a cargando carga trabajo.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### información tratada
  - Dedicación
  - Disponibilidad
  - Proyectos asociados
  - Observaciones

## opciones de navegación

### operaciones relacionadas
- **editarCargaTrabajo()** -> Navegar a `editarCargaTrabajo()` cuando el actor solicita esa continuidad.
- **abrirPanelPrincipal()** -> Navegar a `abrirPanelPrincipal()` cuando el actor solicita esa continuidad.

### navegación del sistema
- **Estado de entrada**: PANEL_PRINCIPAL_ABIERTO.
- **Estado de salida**: OPCIONES_CARGA_TRABAJO_ABIERTAS, PANEL_PRINCIPAL_ABIERTO.

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
