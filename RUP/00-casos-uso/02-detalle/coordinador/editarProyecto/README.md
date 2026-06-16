# FUNIBER > Coordinador > editarProyecto > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarProyecto/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarProyecto/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarProyecto/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `editarProyecto()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador actualizar la información de proyecto manteniendo la trazabilidad del sistema.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|editarProyecto()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador actualizar la información de proyecto manteniendo la trazabilidad del sistema.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Coordinador autenticado con un proyecto en `PROYECTO_ABIERTO`.|
|**Postcondición exitosa**|La información de proyecto queda actualizada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: editarProyecto()](/images/RUP/00-casos-uso/02-detalle/coordinador/editarProyecto/editarProyecto.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - EDITAR PROYECTO
<div align=center>

|![Wireframe: editarProyecto](/images/RUP/00-casos-uso/02-detalle/coordinador/editarProyecto/editarProyecto-wireframe.svg)|
|-|
|**Estado**: PresentandoEdicion / ValidandoCambios|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita editar proyecto
- **Sistema** presenta los datos editables y únicamente las transiciones de estado permitidas.
- **Coordinador** modifica datos y solicita guardar; **Sistema** valida fechas, campos obligatorios y transición de estado.

### Validaciones del wireframe
- ¿El campo o bloque **Datos del proyecto** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿El campo o bloque **Coordinador** resulta claro para el Coordinador?
- ¿El campo o bloque **Fecha inicio** resulta claro para el Coordinador?
- ¿El campo o bloque **Fecha fin** resulta claro para el Coordinador?
- ¿El campo o bloque **Descripción** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita editar proyecto|| |
||**Sistema**|presenta los datos editables y las transiciones de estado permitidas| |
|**Coordinador**|modifica datos y solicita guardar|| |
||**Sistema**|valida fechas, datos obligatorios y transición de estado; registra los cambios si son válidos| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**PresentandoEdicion**|Presenta los datos editables y estados permitidos.|Sistema debe impedir modificaciones incompatibles con el estado del proyecto.|
|**ValidandoCambios**|Valida los cambios antes de registrarlos.|Sistema debe conservar los datos anteriores si hay errores o cancelación.|

## Funcionalidad específica

### Patrón de edición completa

- **Editar completo**: concentra el mantenimiento de proyecto.
- **Persistencia conceptual**: la especificación describe la actualización sin entrar en tecnología.
- **Retorno controlado**: el actor conserva la navegación hacia la entidad o listado relacionado.

### Información tratada
  - ID
  - Título
  - Estado
  - Investigadores asociados

## Opciones de navegación

### Navegación del sistema
- **Estado de entrada**: PROYECTO_ABIERTO.
- **Estado de salida**: PROYECTO_ABIERTO.

## Conexión con diagrama de contexto

Este caso de uso corresponde a `PROYECTO_ABIERTO` → `editarProyecto()` → `PROYECTO_ABIERTO`. La edición respeta las transiciones definidas en el diagrama de estados del proyecto.

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
