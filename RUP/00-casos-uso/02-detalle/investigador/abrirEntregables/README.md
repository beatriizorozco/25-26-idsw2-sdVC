# FUNIBER > Investigador > abrirEntregables > Detalle y prototipado

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

Especificación detallada del caso de uso `abrirEntregables()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para presentar al investigador el listado de entregables con opciones de consulta, filtrado y navegación.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirEntregables()|
|**Actor primario**|Investigador|
|**Objetivo**|Presentar al Investigador el listado de entregables con opciones de consulta, filtrado y navegación.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Investigador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Investigador visualiza el listado de entregables y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirEntregables()](/images/RUP/00-casos-uso/02-detalle/investigador/abrirEntregables/abrirEntregables.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - ENTREGABLES
<div align=center>

|![Wireframe: abrirEntregables](/images/RUP/00-casos-uso/02-detalle/investigador/abrirEntregables/abrirEntregables-wireframe.svg)|
|-|
|**Estado**: SolicitandoApertura / MostrandoListado / FiltrandoBuscando|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita abrir entregables
- **Sistema** presenta y visualiza el listado de entregables del proyecto con:<br>- ID<br>- Título<br>- Tipo<br>- Fecha límite<br>- Estado<br>- Información adicional (si aplica)<br>**Sistema** permite introducir filtros y búsqueda<br>**Sistema** permite solicitar:<br>- Abrir entregable<br>- Crear entregable<br>- Volver al proyecto
- **Investigador** introduce filtros o búsqueda
- **Sistema** muestra y visualiza el listado según filtros/búsqueda

### Validaciones del wireframe
- ¿El campo o bloque **Filtrar / buscar entregables** resulta claro para el Investigador?
- ¿El campo o bloque **ID** resulta claro para el Investigador?
- ¿El campo o bloque **Título** resulta claro para el Investigador?
- ¿El campo o bloque **Tipo** resulta claro para el Investigador?
- ¿El campo o bloque **Estado** resulta claro para el Investigador?
- ¿El campo o bloque **Fecha límite (desde)** resulta claro para el Investigador?
- ¿El campo o bloque **Fecha límite (hasta)** resulta claro para el Investigador?
- ¿El campo o bloque **Listado** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita abrir entregables|| |
||**Sistema**|presenta y visualiza el listado de entregables del proyecto con:<br>- ID<br>- Título<br>- Tipo<br>- Fecha límite<br>- Estado<br>- Información adicional (si aplica)<br>**Sistema** permite introducir filtros y búsqueda<br>**Sistema** permite solicitar:<br>- Abrir entregable<br>- Crear entregable<br>- Volver al proyecto| |
|**Investigador**|introduce filtros o búsqueda|| |
||**Sistema**|muestra y visualiza el listado según filtros/búsqueda| |
|**Investigador**|solicita continuar con el listado|| |
|**Investigador**|solicita abrir un entregable del listado<br>|| |
||**Sistema**|presenta y visualiza el entregable solicitado| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoApertura**|Estado interno asociado a solicitando apertura.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**MostrandoListado**|Estado interno asociado a mostrando listado.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**FiltrandoBuscando**|Estado interno asociado a filtrando buscando.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Funcionalidad unificada: listar = filtrar = buscar

- **Listar**: muestra entregables sin criterio aplicado.
- **Filtrar/Buscar**: permite localizar entregables por título, proyecto, fecha, estado.
- **Navegar**: permite abrir detalles u operaciones relacionadas según el rol.

### Información tratada
  - Título
  - Proyecto
  - Fecha
  - Estado

## Opciones de navegación

### Operaciones relacionadas
- **abrirEntregable()** -> Navegar a `abrirEntregable()` cuando el actor solicita esa continuidad.
- **crearEntregable()** -> Navegar a `crearEntregable()` cuando el actor solicita esa continuidad.
- **abrirProyecto()** -> Navegar a `abrirProyecto()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PROYECTO_ABIERTO, ENTREGABLE_ABIERTO.
- **Estado de salida**: ENTREGABLES_ABIERTOS, ENTREGABLE_ABIERTO, PROYECTO_ABIERTO.

## Conexión con diagrama de contexto

Este caso de uso se integra en los diagramas de contexto del Investigador, manteniendo la trazabilidad entre navegación, estado del sistema y responsabilidad del actor.

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
