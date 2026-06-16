# FUNIBER > Coordinador > eliminarRecompensa > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarRecompensa/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarRecompensa/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `eliminarRecompensa()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador solicitar o confirmar la anulación de recompensa cuando su rol lo permite.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|eliminarRecompensa()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador solicitar o confirmar la anulación de recompensa cuando su rol lo permite.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|La recompensa queda anulada y conserva íntegramente los datos de su concesión.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: eliminarRecompensa()](/images/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/eliminarRecompensa.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - ELIMINAR RECOMPENSA
<div align=center>

|![Wireframe: eliminarRecompensa](/images/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/eliminarRecompensa-wireframe.svg)|
|-|
|**Estado**: ConfirmandoAnulacion / RecompensaAnulada|

</div>

**Correspondencia con especificación:**
- eliminarRecompensa()
- **Coordinador** solicita anular recompensa
- **Sistema** presenta confirmación de anulación<br>**Coordinador** solicita confirmar anulación<br>**Sistema** presenta la anulacion de la recompensa
- **Coordinador** solicita cancelar la operación

### Validaciones del wireframe
- ¿El campo o bloque **Confirmación de anulación** resulta claro para el Coordinador?
- ¿El campo o bloque **Resultado** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita anular recompensa|| |
|**Coordinador**||| |
||**Sistema**|presenta confirmación de anulación<br>**Coordinador** solicita confirmar anulación<br>| |
|**Coordinador**|solicita cancelar la operación|| |
||**Sistema**|presenta confirmación de anulación| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**ConfirmandoAnulacion**|Estado donde el sistema valida o confirma la anulación de recompensa.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**RecompensaAnulada**|Estado interno asociado a recompensa anulada.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Patrón de anulación segura

- **Confirmación**: la conversación separa solicitud y anulación.
- **Sin detalle técnico**: se conserva la concesión original y se registra su anulación.
- **Retorno al contexto**: el actor vuelve al listado o estado indicado por el diagrama.

### Información tratada
  - Concepto
  - Importe o reconocimiento
  - Estado
  - Proyecto

### Reglas de dominio
- La anulación de una recompensa debe solicitar confirmación para evitar pérdidas accidentales.
- El Coordinador solo anula recompensas registradas en el sistema y asociadas a proyectos completados.
- La anulación registra fecha, Coordinador responsable y motivo.
- La recompensa anulada deja de estar vigente, pero continúa disponible para auditoría.
- La anulación no modifica la carga de trabajo ni el estado del proyecto completado.

## Opciones de navegación

### Operaciones relacionadas
- **abrirRecompensas()** -> Navegar a `abrirRecompensas()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: RECOMPENSA_ABIERTA.
- **Estado de salida**: RECOMPENSAS_ABIERTAS.

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
- [Política de bajas, archivado y conservación histórica](../../../politica-bajas-logicas.md)
- [conversation-log.md](../../../../../conversation-log.md)
