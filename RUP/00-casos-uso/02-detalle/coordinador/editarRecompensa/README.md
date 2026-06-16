# FUNIBER > Coordinador > editarRecompensa > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarRecompensa/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarRecompensa/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `editarRecompensa()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador actualizar la información de recompensa manteniendo la trazabilidad del sistema.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|editarRecompensa()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador actualizar la información de recompensa manteniendo la trazabilidad del sistema.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|La información de recompensa queda actualizada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: editarRecompensa()](/images/RUP/00-casos-uso/02-detalle/coordinador/editarRecompensa/editarRecompensa.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - EDITAR RECOMPENSA
<div align=center>

|![Wireframe: editarRecompensa](/images/RUP/00-casos-uso/02-detalle/coordinador/editarRecompensa/editarRecompensa-wireframe.svg)|
|-|
|**Estado**: EditandoDatos / CambiosGuardados|

</div>

**Correspondencia con especificación:**
- editarRecompensa()
- **Coordinador** solicita editar recompensa
- **Sistema** presenta datos editables de la recompensa<br>**Sistema** permite introducir modificaciones<br>**Coordinador** introduce cambios y solicita guardar<br>**Sistema** registra los cambios
- **Coordinador** solicita cancelar edición

### Validaciones del wireframe
- ¿El campo o bloque **Datos editables de la recompensa** resulta claro para el Coordinador?
- ¿El campo o bloque **Tipo** resulta claro para el Coordinador?
- ¿El campo o bloque **Cantidad** resulta claro para el Coordinador?
- ¿El campo o bloque **Resultado** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita editar recompensa|| |
|**Coordinador**||| |
||**Sistema**|presenta datos editables de la recompensa<br>| |
|**Coordinador**|solicita cancelar edición|| |
||**Sistema**|presenta confirmación de cambios guardados| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**EditandoDatos**|Estado donde el sistema permite modificar la información de recompensa.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**CambiosGuardados**|Estado interno asociado a cambios guardados.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Patrón de edición completa

- **Editar completo**: concentra el mantenimiento de recompensa.
- **Persistencia conceptual**: la especificación describe la actualización sin entrar en tecnología.
- **Retorno controlado**: el actor conserva la navegación hacia la entidad o listado relacionado.

### Información tratada
  - Concepto
  - Importe o reconocimiento
  - Estado
  - Proyecto

### Reglas de dominio
- La edición no puede desvincular la recompensa de un proyecto completado.
- La edición debe mantener un investigador beneficiario válido.
- La reducción docente solo es válida para investigadores-docentes.
- La reducción docente debe corresponder a asignaturas completas de 4 horas, con un máximo de 16 horas.
- Los investigadores sin docencia por sede solo pueden conservar recompensas económicas.

## Opciones de navegación

### Operaciones relacionadas
- **eliminarRecompensa()** -> Navegar a `eliminarRecompensa()` cuando el actor solicita esa continuidad.
- **abrirRecompensas()** -> Navegar a `abrirRecompensas()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: RECOMPENSA_ABIERTA.
- **Estado de salida**: RECOMPENSA_ABIERTA, RECOMPENSAS_ABIERTAS.

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
