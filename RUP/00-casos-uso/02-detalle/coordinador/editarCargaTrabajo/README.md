# FUNIBER > Coordinador > editarCargaTrabajo > Detalle y prototipado

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

Especificación detallada del caso de uso `editarCargaTrabajo()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador actualizar la información de carga de trabajo manteniendo la trazabilidad del sistema.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|editarCargaTrabajo()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador actualizar la información de carga de trabajo manteniendo la trazabilidad del sistema.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|La información de carga de trabajo queda actualizada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: editarCargaTrabajo()](/images/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/editarCargaTrabajo.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - EDITAR CARGA DE TRABAJO
<div align=center>

|![Wireframe: editarCargaTrabajo](/images/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/editarCargaTrabajo-wireframe.svg)|
|-|
|**Estado**: MostrandoCarga / EditandoCarga / ValidandoDatos|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita editar la carga de trabajo de una persona
- **Sistema** presenta la carga de trabajo actual<br>**Sistema** permite introducir modificaciones
- **Coordinador** solicita guardar los cambios
- **Sistema** permite solicitar la actualización de la carga de trabajo<br>**Sistema** presenta la carga de trabajo actualizada

### Validaciones del wireframe
- ¿El campo o bloque **Persona** resulta claro para el Coordinador?
- ¿El campo o bloque **Código** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre** resulta claro para el Coordinador?
- ¿El campo o bloque **Sede** resulta claro para el Coordinador?
- ¿El campo o bloque **Perfil** resulta claro para el Coordinador?
- ¿El campo o bloque **Editar horas semanales** resulta claro para el Coordinador?
- ¿El campo o bloque **Docencia (0–16)** resulta claro para el Coordinador?
- ¿El campo o bloque **Investigación** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita editar la carga de trabajo de una persona|| |
||**Sistema**|presenta la carga de trabajo actual<br>**Sistema** permite introducir modificaciones| |
|**Coordinador**|solicita guardar los cambios|| |
||**Sistema**|permite solicitar la actualización de la carga de trabajo<br>**Sistema** presenta la carga de trabajo actualizada| |
||**Sistema**|muestra errores de introducción<br>**Sistema** permite introducir correcciones| |
|**Coordinador**|solicita cancelar la edición<br>|| |
||**Sistema**|presenta la carga de trabajo sin cambios| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoCarga**|Estado interno asociado a mostrando carga.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**EditandoCarga**|Estado donde el sistema permite modificar la información de carga de trabajo.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**ValidandoDatos**|Estado interno asociado a validando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Patrón de edición completa

- **Editar completo**: concentra el mantenimiento de carga de trabajo.
- **Persistencia conceptual**: la especificación describe la actualización sin entrar en tecnología.
- **Retorno controlado**: el actor conserva la navegación hacia la entidad o listado relacionado.

### Información tratada
  - Dedicación
  - Disponibilidad
  - Proyecto
  - Observaciones

## Opciones de navegación

### Operaciones relacionadas
- **abrirPanelPrincipal()** -> Navegar a `abrirPanelPrincipal()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: OPCIONES_CARGA_TRABAJO_ABIERTAS.
- **Estado de salida**: OPCIONES_CARGA_TRABAJO_ABIERTAS, PANEL_PRINCIPAL_ABIERTO.

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
