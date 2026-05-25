# FUNIBER > Investigador > editarCargaTrabajo > Detalle y prototipado

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

Especificación detallada del caso de uso `editarCargaTrabajo()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para permitir al investigador actualizar la información de carga de trabajo manteniendo la trazabilidad del sistema.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|editarCargaTrabajo()|
|**Actor primario**|Investigador|
|**Objetivo**|Permitir al Investigador actualizar la información de carga de trabajo manteniendo la trazabilidad del sistema.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Investigador y sistema disponible para navegación.|
|**Postcondición exitosa**|La información de carga de trabajo queda actualizada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## diagrama de especificación

<div align=center>

|![Caso de uso: editarCargaTrabajo()](/images/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/editarCargaTrabajo.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - EDITAR CARGA DE TRABAJO
<div align=center>

|![Wireframe: editarCargaTrabajo](/images/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/editarCargaTrabajo-wireframe.svg)|
|-|
|**Estado**: MostrandoCarga / EditandoCarga / ValidandoDatos|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita editar su carga de trabajo
- **Sistema** presenta la carga de trabajo actual<br>**Sistema** permite introducir modificaciones
- **Investigador** solicita guardar los cambios
- **Sistema** permite solicitar la actualización de la carga de trabajo<br>**Sistema** presenta la carga de trabajo actualizada

### validaciones del wireframe
- ¿El campo o bloque **Datos personales** resulta claro para el Investigador?
- ¿El campo o bloque **Código** resulta claro para el Investigador?
- ¿El campo o bloque **Nombre** resulta claro para el Investigador?
- ¿El campo o bloque **Sede** resulta claro para el Investigador?
- ¿El campo o bloque **Perfil** resulta claro para el Investigador?
- ¿El campo o bloque **Editar horas semanales** resulta claro para el Investigador?
- ¿El campo o bloque **Docencia (0–16)** resulta claro para el Investigador?
- ¿El campo o bloque **Investigación** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita editar su carga de trabajo|| |
||**Sistema**|presenta la carga de trabajo actual<br>**Sistema** permite introducir modificaciones| |
|**Investigador**|solicita guardar los cambios|| |
||**Sistema**|permite solicitar la actualización de la carga de trabajo<br>**Sistema** presenta la carga de trabajo actualizada| |
||**Sistema**|muestra errores de introducción<br>**Sistema** permite introducir correcciones| |
|**Investigador**|solicita cancelar la edición<br>|| |
||**Sistema**|presenta la carga de trabajo sin cambios| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoCarga**|Estado interno asociado a mostrando carga.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**EditandoCarga**|Estado donde el sistema permite modificar la información de carga de trabajo.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**ValidandoDatos**|Estado interno asociado a validando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### patrón de edición completa

- **Editar completo**: concentra el mantenimiento de carga de trabajo.
- **Persistencia conceptual**: la especificación describe la actualización sin entrar en tecnología.
- **Retorno controlado**: el actor conserva la navegación hacia la entidad o listado relacionado.

### información tratada
  - Dedicación
  - Disponibilidad
  - Proyecto
  - Observaciones

## opciones de navegación

### operaciones relacionadas
- **abrirPanelPrincipal()** -> Navegar a `abrirPanelPrincipal()` cuando el actor solicita esa continuidad.

### navegación del sistema
- **Estado de entrada**: OPCIONES_CARGA_TRABAJO_ABIERTAS.
- **Estado de salida**: OPCIONES_CARGA_TRABAJO_ABIERTAS, PANEL_PRINCIPAL_ABIERTO.

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
