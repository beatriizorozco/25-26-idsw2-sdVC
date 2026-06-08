# FUNIBER > Coordinador > abrirProyectos > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/README.md)|[Diseño](/RUP/02-diseño/README.md)|[Desarrollo](/RUP/03-desarrollo/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `abrirProyectos()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el listado de proyectos con opciones de consulta, filtrado y navegación.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirProyectos(investigadorId?)|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar todos los proyectos o los asociados al investigador de contexto, con opciones de filtrado y navegación.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Coordinador autenticado en `PANEL_PRINCIPAL_ABIERTO`, `PROYECTO_ABIERTO` o `INVESTIGADOR_ABIERTO`.|
|**Postcondición exitosa**|El estado queda en `PROYECTOS_ABIERTOS` con el alcance correspondiente al contexto de entrada.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirProyectos()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirProyectos/abrirProyectos.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - PROYECTOS
<div align=center>

|![Wireframe: abrirProyectos](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirProyectos/abrirProyectos-wireframe.svg)|
|-|
|**Estado**: MostrandoLista / FiltrandoLista|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita abrir proyectos
- **Sistema** presenta todos los proyectos o, desde `INVESTIGADOR_ABIERTO`, los asociados al investigador de contexto. Permite filtrar, abrir, crear o volver al panel.
- **Coordinador** filtra o busca proyectos<br>**Sistema** actualiza el listado

### Validaciones del wireframe
- ¿El campo o bloque **Listado de proyectos de investigación** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿El campo o bloque **Coordinador** resulta claro para el Coordinador?
- ¿El campo o bloque **Inicio** resulta claro para el Coordinador?
- ¿El campo o bloque **Fin** resulta claro para el Coordinador?
- ¿El campo o bloque **Acción** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir proyectos|| |
||**Sistema**|presenta todos los proyectos o los asociados al investigador de contexto, incluyendo código, título, estado, coordinador y fechas; permite filtrar, abrir, crear o volver al panel| |
|**Coordinador**|filtra o busca proyectos<br>|| |
||**Sistema**|actualiza el listado| |
|**Coordinador**|selecciona una opción de navegación|| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**PresentandoProyectos**|Presenta los proyectos correspondientes al contexto de entrada.|Sistema debe respetar el alcance global o por investigador.|
|**FiltrandoProyectos**|Aplica o limpia criterios sobre el conjunto presentado.|Sistema debe conservar el alcance de contexto al filtrar.|

## Funcionalidad específica

### Funcionalidad unificada: listar = filtrar = buscar

- **Listar**: muestra proyectos sin criterio aplicado.
- **Filtrar/Buscar**: permite localizar proyectos por ID, título, estado, inicio y fin.
- **Navegar**: permite abrir detalles u operaciones relacionadas según el rol.

### Información tratada
  - ID
  - Título
  - Estado
  - Inicio y fin

## Opciones de navegación

### Operaciones relacionadas
- **abrirProyecto()** -> Navegar a `abrirProyecto()` cuando el actor solicita esa continuidad.
- **crearProyecto()** -> Navegar a `crearProyecto()` cuando el actor solicita esa continuidad.
- **abrirPanelPrincipal()** -> Navegar a `abrirPanelPrincipal()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PANEL_PRINCIPAL_ABIERTO, PROYECTO_ABIERTO, INVESTIGADOR_ABIERTO.
- **Estado de salida**: PROYECTOS_ABIERTOS, PROYECTO_ABIERTO, PANEL_PRINCIPAL_ABIERTO.

## Conexión con diagrama de contexto

Este caso de uso corresponde a `PANEL_PRINCIPAL_ABIERTO`, `PROYECTO_ABIERTO` o `INVESTIGADOR_ABIERTO` → `abrirProyectos(investigadorId?)` → `PROYECTOS_ABIERTOS`. El parámetro es opcional: solo restringe el listado cuando existe un investigador de contexto.

## Vocabulario utilizado

### Actor (Coordinador)
- **solicita**: expresa la intención de realizar una acción.
- **visualiza**: observa la información presentada por el sistema.
- **selecciona**: elige una entidad, acción o alternativa disponible.

### Sistema
- **presenta**: muestra información organizada al actor.
- **permite**: habilita acciones disponibles sin imponer detalles de implementación.
- **registra**: conserva la información indicada por el actor cuando el caso de uso lo requiere.

## Características metodológicas

### Separación de responsabilidades
- **Actor**: usuario con visión global sobre proyectos, investigadores, convocatorias, publicaciones, entregables, recompensas y solicitudes de perfil.
- **Sistema**: presenta información, habilita acciones y mantiene la navegación del caso de uso.

### Ausencia de detalles de implementación
- No especifica tecnología de interfaz.
- No incluye estructura de base de datos.
- No impone componentes concretos de desarrollo.

### Conversación atómica
- El caso de uso representa una conversación completa.
- Tiene un objetivo claro para el actor Coordinador.
- Termina con una acción, navegación o estado observable.

## Referencias

- [Diagramas de contexto](../../../01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](../../../01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](../../../00-modelo-del-dominio/modelo-dominio.md)
- [Detalle y prototipado](../../README.md)
- [conversation-log.md](../../../../../conversation-log.md)
