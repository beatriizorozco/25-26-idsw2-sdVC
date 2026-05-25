# FUNIBER > Coordinador > abrirEntregable > Detalle y prototipado

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

Especificación detallada del caso de uso `abrirEntregable()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el detalle de entregable y las acciones disponibles según su rol.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirEntregable()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el detalle de entregable y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Coordinador visualiza el detalle de entregable y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirEntregable()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirEntregable/abrirEntregable.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - ENTREGABLE
<div align=center>

|![Wireframe: abrirEntregable](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirEntregable/entregableAbierto-wireframe.svg)|
|-|
|**Estado**: SolicitandoApertura / MostrandoListado / SeleccionandoEntregable|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita abrir un entregable
- **Sistema** presenta y visualiza el listado de entregables<br>**Sistema** muestra información:<br>- ID<br>- Título<br>- Tipo<br>- Fecha límite<br>- Estado<br>- Descripción (si aplica)<br>- Archivo adjunto (si aplica)<br>**Sistema** permite solicitar:<br>- Editar entregable<br>- Eliminar entregable<br>- Volver a entregables
- **Coordinador** solicita abrir un entregable del listado
- **Sistema** presenta y visualiza el entregable solicitado

### Validaciones del wireframe
- ¿El campo o bloque **Datos del entregable** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Tipo** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿El campo o bloque **Fecha límite** resulta claro para el Coordinador?
- ¿El campo o bloque **Descripción** resulta claro para el Coordinador?
- ¿El campo o bloque **Adjunto / enlace** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir un entregable|| |
||**Sistema**|presenta y visualiza el listado de entregables<br>**Sistema** muestra información:<br>- ID<br>- Título<br>- Tipo<br>- Fecha límite<br>- Estado<br>- Descripción (si aplica)<br>- Archivo adjunto (si aplica)<br>**Sistema** permite solicitar:<br>- Editar entregable<br>- Eliminar entregable<br>- Volver a entregables| |
|**Coordinador**|solicita abrir un entregable del listado|| |
||**Sistema**|presenta y visualiza el entregable solicitado| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoApertura**|Estado interno asociado a solicitando apertura.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**MostrandoListado**|Estado interno asociado a mostrando listado.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**SeleccionandoEntregable**|Estado interno asociado a seleccionando entregable.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### Información tratada
  - Título
  - Descripción
  - Fecha
  - Estado

## Opciones de navegación

### Operaciones relacionadas
- **editarEntregable()** -> Navegar a `editarEntregable()` cuando el actor solicita esa continuidad.
- **eliminarEntregable()** -> Navegar a `eliminarEntregable()` cuando el actor solicita esa continuidad.
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: ENTREGABLES_ABIERTOS.
- **Estado de salida**: ENTREGABLE_ABIERTO, ENTREGABLES_ABIERTOS.

## Conexión con diagrama de contexto

Este caso de uso se integra en los diagramas de contexto del Coordinador, manteniendo la trazabilidad entre navegación, estado del sistema y responsabilidad del actor.

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
