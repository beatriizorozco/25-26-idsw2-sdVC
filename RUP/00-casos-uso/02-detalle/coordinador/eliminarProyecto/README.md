# FUNIBER > Coordinador > eliminarProyecto > Detalle y prototipado

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

Especificación detallada del caso de uso `eliminarProyecto()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador solicitar o confirmar la eliminación de proyecto cuando su rol lo permite.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|eliminarProyecto()|
|**Actor primario**|Coordinador|
|**Objetivo**|Eliminar un proyecto únicamente cuando puede hacerse sin perder trazabilidad ni dejar dependencias huérfanas.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Coordinador autenticado con un proyecto en `PROYECTO_ABIERTO`.|
|**Postcondición exitosa**|El proyecto queda eliminado y el estado pasa a `PROYECTOS_ABIERTOS`.|
|**Postcondición de fallo**|No se aplican cambios si existen dependencias que deben conservarse o el actor cancela.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: eliminarProyecto()](/images/RUP/00-casos-uso/02-detalle/coordinador/eliminarProyecto/eliminarProyecto.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - ELIMINAR PROYECTO
<div align=center>

|![Wireframe: eliminarProyecto](/images/RUP/00-casos-uso/02-detalle/coordinador/eliminarProyecto/eliminarProyecto-wireframe.svg)|
|-|
|**Estado**: ComprobandoDependencias / ConfirmandoEliminacion|

</div>

**Correspondencia con especificación:**
- **Coordinador** tiene un proyecto abierto<br>**Coordinador** solicita eliminar proyecto
- **Sistema** comprueba entregables, equipo, recompensas y demás información vinculada. Solo presenta confirmación si puede eliminarse sin perder trazabilidad.
- **Coordinador** solicita confirmar eliminación<br>**Sistema** visualiza el listado de proyectos actualizado
- **Coordinador** cancela eliminación<br>**Sistema** visualiza el proyecto sin cambios

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
|**Coordinador**|tiene un proyecto abierto<br>**Coordinador** solicita eliminar proyecto|| |
||**Sistema**|comprueba entregables, equipo, recompensas y demás información vinculada; impide eliminar si debe conservarse trazabilidad y, en caso contrario, presenta confirmación| |
|**Coordinador**|solicita confirmar eliminación<br>|| |
||**Sistema**|visualiza el listado de proyectos actualizado| |
|**Coordinador**|cancela eliminación<br>|| |
||**Sistema**|visualiza el proyecto sin cambios| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**ComprobandoDependencias**|Comprueba toda la información vinculada al proyecto.|Sistema debe impedir pérdidas de trazabilidad o referencias huérfanas.|
|**ConfirmandoEliminacion**|Solicita confirmación cuando el proyecto puede eliminarse.|Sistema debe eliminar únicamente tras confirmación explícita.|

## Funcionalidad específica

### Patrón de eliminación segura

- **Confirmación**: la conversación separa solicitud y eliminación.
- **Protección de trazabilidad**: la eliminación se impide mientras existan dependencias que deban conservarse.
- **Retorno al contexto**: el actor vuelve al listado o estado indicado por el diagrama.

### Información tratada
  - ID
  - Título
  - Estado
  - Investigadores asociados

## Opciones de navegación

### Operaciones relacionadas
- **abrirProyectos()** -> Navegar a `abrirProyectos()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PROYECTO_ABIERTO.
- **Estado de salida**: PROYECTOS_ABIERTOS.

## Conexión con diagrama de contexto

Este caso de uso corresponde a `PROYECTO_ABIERTO` → `eliminarProyecto()` → `PROYECTOS_ABIERTOS` cuando se elimina, o vuelve a `PROYECTO_ABIERTO` si se cancela o existen dependencias.

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
