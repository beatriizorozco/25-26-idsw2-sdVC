# FUNIBER > Coordinador > eliminarInvestigador > Detalle y prototipado

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

Especificación detallada del caso de uso `eliminarInvestigador()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador solicitar o confirmar la eliminación de investigador cuando su rol lo permite.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|eliminarInvestigador()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador solicitar o confirmar la eliminación de investigador cuando su rol lo permite.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|Investigador queda eliminado o marcado para eliminación según corresponda.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## diagrama de especificación

<div align=center>

|![Caso de uso: eliminarInvestigador()](/images/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/eliminarInvestigador.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - ELIMINAR INVESTIGADOR DEL PROYECTO
<div align=center>

|![Wireframe: eliminarInvestigador](/images/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/eliminarInvestigador-wireframe.svg)|
|-|
|**Estado**: ConfirmandoEliminacion / InvestigadorEliminado|

</div>

**Correspondencia con especificación:**
- eliminarInvestigador()
- **Coordinador** solicita eliminar investigador de proyecto
- **Sistema** presenta confirmación de eliminación<br>**Coordinador** solicita confirmar eliminación<br>**Sistema** elimina al investigador del proyecto
- **Coordinador** solicita cancelar la operación

### validaciones del wireframe
- ¿El campo o bloque **Investigador seleccionado** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre** resulta claro para el Coordinador?
- ¿El campo o bloque **Campo** resulta claro para el Coordinador?
- ¿El campo o bloque **Confirmación** resulta claro para el Coordinador?
- ¿El campo o bloque **Acciones** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita eliminar investigador de proyecto|| |
|**Coordinador**||| |
||**Sistema**|presenta confirmación de eliminación<br>**Coordinador** solicita confirmar eliminación<br>| |
|**Coordinador**|solicita cancelar la operación|| |
||**Sistema**|presenta confirmación de eliminación| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**ConfirmandoEliminacion**|Estado donde el sistema valida o confirma la eliminación de investigador.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**InvestigadorEliminado**|Estado interno asociado a investigador eliminado.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### patrón de eliminación segura

- **Confirmación**: la conversación separa solicitud y eliminación.
- **Sin detalle técnico**: no se define borrado físico ni lógico.
- **Retorno al contexto**: el actor vuelve al listado o estado indicado por el diagrama.

### información tratada
  - Nombre
  - Perfil
  - Especialización
  - Proyectos asociados

## opciones de navegación

### operaciones relacionadas
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.
- **abrirProyectos()** -> Navegar a `abrirProyectos()` cuando el actor solicita esa continuidad.

### navegación del sistema
- **Estado de entrada**: PROYECTO_ABIERTO.
- **Estado de salida**: PROYECTO_ABIERTO, ENTREGABLES_ABIERTOS, PROYECTOS_ABIERTOS.

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
