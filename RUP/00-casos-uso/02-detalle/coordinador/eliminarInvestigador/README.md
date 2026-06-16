# FUNIBER > Coordinador > eliminarInvestigador > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarInvestigador/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarInvestigador/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarInvestigador/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `eliminarInvestigador()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para retirar de un proyecto a un investigador sin eliminar su perfil.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|eliminarInvestigador(investigadorId)|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador retirar del proyecto a un investigador seleccionado, conservando su perfil y la trazabilidad histórica.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Coordinador autenticado con un proyecto en `PROYECTO_ABIERTO` y un miembro del equipo seleccionado.|
|**Postcondición exitosa**|La participación queda marcada como desasignada; se conservan el perfil y el histórico de participación en el proyecto.|
|**Postcondición de fallo**|No se aplican cambios si existen responsabilidades pendientes o el actor cancela.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: eliminarInvestigador()](/images/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/eliminarInvestigador.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - ELIMINAR INVESTIGADOR DEL PROYECTO
<div align=center>

|![Wireframe: eliminarInvestigador](/images/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/eliminarInvestigador-wireframe.svg)|
|-|
|**Estado**: ComprobandoResponsabilidades / ConfirmandoDesasignacion|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita retirar del proyecto al investigador seleccionado.
- **Sistema** comprueba responsabilidades pendientes y, si puede retirarse, solicita confirmación.
- **Coordinador** confirma o cancela; **Sistema** registra la desasignación y conserva la participación histórica cuando procede.

### Validaciones del wireframe
- ¿El campo o bloque **Investigador seleccionado** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre** resulta claro para el Coordinador?
- ¿El campo o bloque **Campo** resulta claro para el Coordinador?
- ¿El campo o bloque **Confirmación** resulta claro para el Coordinador?
- ¿El campo o bloque **Acciones** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita retirar del proyecto al investigador seleccionado|| |
||**Sistema**|comprueba entregables y responsabilidades pendientes; si puede retirarse, presenta confirmación| |
|**Coordinador**|confirma la retirada|| |
||**Sistema**|registra la desasignación y conserva el perfil y la participación histórica del investigador| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**ComprobandoResponsabilidades**|Comprueba si el investigador mantiene responsabilidades pendientes en el proyecto.|Sistema debe impedir una retirada que deje trabajo sin responsable.|
|**ConfirmandoDesasignacion**|Solicita confirmación de la retirada.|Sistema debe marcar la participación como desasignada y conservar su histórico.|

## Funcionalidad específica

### Patrón de eliminación segura

- **Confirmación**: la conversación separa solicitud y eliminación.
- **Sin eliminación física**: el caso marca la participación como desasignada y conserva al Investigador y su histórico en el proyecto.
- **Retorno al contexto**: el actor vuelve al listado o estado indicado por el diagrama.

### Información tratada
  - Nombre
  - Perfil
  - Especialización
  - Proyectos asociados

## Opciones de navegación

### Navegación del sistema
- **Estado de entrada**: PROYECTO_ABIERTO.
- **Estado de salida**: PROYECTO_ABIERTO.

## Conexión con diagrama de contexto

Este caso de uso corresponde a `PROYECTO_ABIERTO` → `eliminarInvestigador(investigadorId)` → `PROYECTO_ABIERTO`. El nombre histórico del caso se conserva, pero funcionalmente registra una desasignación lógica sin eliminar el perfil ni la participación histórica.

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
- [Política de bajas, archivado y conservación histórica](../../../politica-bajas-logicas.md)
- [conversation-log.md](../../../../../conversation-log.md)
