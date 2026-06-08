# FUNIBER > Coordinador > eliminarEntregable > Detalle y prototipado

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

Especificación detallada del caso de uso `eliminarEntregable()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador solicitar o confirmar la retirada de entregable cuando su rol lo permite.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|eliminarEntregable()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador solicitar o confirmar la retirada de entregable cuando su rol lo permite.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El entregable queda retirado de la gestión activa y conserva su trazabilidad.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: eliminarEntregable()](/images/RUP/00-casos-uso/02-detalle/coordinador/eliminarEntregable/eliminarEntregable.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - ELIMINAR ENTREGABLE
<div align=center>

|![Wireframe: eliminarEntregable](/images/RUP/00-casos-uso/02-detalle/coordinador/eliminarEntregable/eliminarEntregable-wireframe.svg)|
|-|
|**Estado**: SolicitandoRetirada / ConfirmandoRetirada|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita retirar el entregable
- **Sistema** presenta una confirmación de retirada<br>**Sistema** muestra información del entregable:<br>- ID<br>- Título<br>- Tipo<br>- Estado<br>**Sistema** permite solicitar:<br>- Confirmar retirada<br>- Cancelar
- **Coordinador** solicita confirmar la retirada
- **Coordinador** solicita cancelar la retirada<br>**Sistema** presenta el listado de entregables sin cambios

### Validaciones del wireframe
- ¿El campo o bloque **Confirmación** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Tipo** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita retirar el entregable|| |
||**Sistema**|presenta una confirmación de retirada<br>**Sistema** muestra información del entregable:<br>- ID<br>- Título<br>- Tipo<br>- Estado<br>**Sistema** permite solicitar:<br>- Confirmar retirada<br>- Cancelar| |
|**Coordinador**|solicita confirmar la retirada|| |
|**Coordinador**|solicita cancelar la retirada<br>|| |
||**Sistema**|presenta el listado de entregables sin cambios| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoRetirada**|Estado interno asociado a solicitando retirada.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**ConfirmandoRetirada**|Estado donde el sistema valida o confirma la retirada de entregable.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Patrón de retirada segura

- **Confirmación**: la conversación separa solicitud y retirada.
- **Sin detalle técnico**: se aplica una retirada lógica que conserva versiones, autoría y relaciones.
- **Retorno al contexto**: el actor vuelve al listado o estado indicado por el diagrama.

### Información tratada
  - Título
  - Descripción
  - Fecha
  - Estado

### Reglas de dominio
- La retirada registra fecha, actor responsable y motivo.
- El entregable retirado deja de aparecer en la gestión activa.
- Se conservan proyecto, autoría, versiones, estado y trazabilidad.
## Opciones de navegación

### Operaciones relacionadas
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: ENTREGABLE_ABIERTO.
- **Estado de salida**: ENTREGABLES_ABIERTOS.

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
