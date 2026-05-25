# FUNIBER > Coordinador > editarEntregable > Detalle y prototipado

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

Especificación detallada del caso de uso `editarEntregable()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador actualizar la información de entregable manteniendo la trazabilidad del sistema.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|editarEntregable()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador actualizar la información de entregable manteniendo la trazabilidad del sistema.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|La información de entregable queda actualizada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## diagrama de especificación

<div align=center>

|![Caso de uso: editarEntregable()](/images/RUP/00-casos-uso/02-detalle/coordinador/editarEntregable/editarEntregable.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - EDITAR ENTREGABLE
<div align=center>

|![Wireframe: editarEntregable](/images/RUP/00-casos-uso/02-detalle/coordinador/editarEntregable/editarEntregable-wireframe.svg)|
|-|
|**Estado**: SolicitandoEdicion / IntroduciendoCambios / ValidandoDatos|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita editar el entregable
- **Sistema** presenta la pantalla de edición<br>**Sistema** muestra los datos actuales y permite introducir cambios:<br>- Título<br>- Tipo<br>- Fecha límite<br>- Estado<br>- Descripción (si aplica)<br>- Archivo adjunto (si aplica)<br>**Sistema** permite solicitar:<br>- Guardar cambios<br>- Cancelar edición
- **Coordinador** solicita guardar los cambios
- **Sistema** permite solicitar la actualización del entregable<br>**Sistema** presenta y visualiza el entregable con los cambios

### validaciones del wireframe
- ¿El campo o bloque **Datos del entregable** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Tipo** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿El campo o bloque **Fecha límite** resulta claro para el Coordinador?
- ¿El campo o bloque **Descripción (si aplica)** resulta claro para el Coordinador?
- ¿El campo o bloque **Adjunto (si aplica)** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita editar el entregable|| |
||**Sistema**|presenta la pantalla de edición<br>**Sistema** muestra los datos actuales y permite introducir cambios:<br>- Título<br>- Tipo<br>- Fecha límite<br>- Estado<br>- Descripción (si aplica)<br>- Archivo adjunto (si aplica)<br>**Sistema** permite solicitar:<br>- Guardar cambios<br>- Cancelar edición| |
|**Coordinador**|solicita guardar los cambios|| |
||**Sistema**|permite solicitar la actualización del entregable<br>**Sistema** presenta y visualiza el entregable con los cambios| |
||**Sistema**|muestra errores de introducción<br>**Sistema** permite introducir correcciones y mantiene la pantalla de edición| |
|**Coordinador**|solicita cancelar la edición<br>|| |
||**Sistema**|presenta y visualiza el entregable sin cambios| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoEdicion**|Estado interno asociado a solicitando edicion.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**IntroduciendoCambios**|Estado interno asociado a introduciendo cambios.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**ValidandoDatos**|Estado interno asociado a validando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### patrón de edición completa

- **Editar completo**: concentra el mantenimiento de entregable.
- **Persistencia conceptual**: la especificación describe la actualización sin entrar en tecnología.
- **Retorno controlado**: el actor conserva la navegación hacia la entidad o listado relacionado.

### información tratada
  - Título
  - Descripción
  - Fecha
  - Estado

## opciones de navegación

### operaciones relacionadas
- **eliminarEntregable()** -> Navegar a `eliminarEntregable()` cuando el actor solicita esa continuidad.
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.

### navegación del sistema
- **Estado de entrada**: ENTREGABLE_ABIERTO.
- **Estado de salida**: ENTREGABLE_ABIERTO, ENTREGABLES_ABIERTOS.

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
