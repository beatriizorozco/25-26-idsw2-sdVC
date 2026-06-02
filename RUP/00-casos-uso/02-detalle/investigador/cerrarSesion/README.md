# FUNIBER > Investigador > cerrarSesion > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/investigador/cerrarSesion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/cerrarSesion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/cerrarSesion/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/cerrarSesion/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `cerrarSesion()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para cerrar la sesión activa del investigador y devolver el sistema a un estado no autenticado.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|cerrarSesion()|
|**Actor primario**|Investigador|
|**Objetivo**|Cerrar la sesión activa del Investigador y devolver el sistema a un estado no autenticado.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Investigador y sistema disponible para navegación.|
|**Postcondición exitosa**|La sesión del Investigador queda cerrada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: cerrarSesion()](/images/RUP/00-casos-uso/02-detalle/investigador/cerrarSesion/cerrarSesion.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - CERRAR SESIÓN
<div align=center>

|![Wireframe: cerrarSesion](/images/RUP/00-casos-uso/02-detalle/investigador/cerrarSesion/cerrarSesion-wireframe.svg)|
|-|
|**Estado**: SolicitandoCierre / ConfirmandoCierre|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita cerrar sesión
- **Sistema** presenta solicitud de confirmación<br>¿Desea cerrar la sesión?
- **Investigador** solicita confirmar o solicita cancelar el cierre
- **Investigador** solicita cancelar cierre

### Validaciones del wireframe
- ¿El campo o bloque **Cerrar sesión** resulta claro para el Investigador?
- ¿El campo o bloque **Investigador** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita cerrar sesión|| |
||**Sistema**|presenta solicitud de confirmación<br>¿Desea cerrar la sesión?| |
|**Investigador**|solicita confirmar o solicita cancelar el cierre|| |
|**Investigador**|solicita cancelar cierre|| |
||**Sistema**|presenta sesión cerrada| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoCierre**|Estado interno asociado a solicitando cierre.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**ConfirmandoCierre**|Estado donde el sistema valida o confirma la eliminación de sesión.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### Información tratada
  - Credenciales
  - Estado de autenticación
  - Rol

## Opciones de navegación

### Operaciones relacionadas
- El caso de uso finaliza y devuelve el control al estado indicado por el diagrama de especificación.

### Navegación del sistema
- **Estado de entrada**: PANEL_PRINCIPAL_ABIERTO.
- **Estado de salida**: SESION_CERRADA.

## Conexión con diagrama de contexto

Este caso de uso se integra en los diagramas de contexto del Investigador, manteniendo la trazabilidad entre navegación, estado del sistema y responsabilidad del actor.

## Vocabulario utilizado

### Actor (Investigador)
- **solicita**: expresa la intención de realizar una acción.
- **visualiza**: observa la información presentada por el sistema.
- **selecciona**: elige una entidad, acción o alternativa disponible.

### Sistema
- **presenta**: muestra información organizada al actor.
- **permite**: habilita acciones disponibles sin imponer detalles de implementación.
- **registra**: conserva la información indicada por el actor cuando el caso de uso lo requiere.

## Características metodológicas

### Separación de responsabilidades
- **Actor**: usuario que consulta proyectos asociados, gestiona sus entregables, publicaciones, perfil y carga de trabajo.
- **Sistema**: presenta información, habilita acciones y mantiene la navegación del caso de uso.

### Ausencia de detalles de implementación
- No especifica tecnología de interfaz.
- No incluye estructura de base de datos.
- No impone componentes concretos de desarrollo.

### Conversación atómica
- El caso de uso representa una conversación completa.
- Tiene un objetivo claro para el actor Investigador.
- Termina con una acción, navegación o estado observable.

## Referencias

- [Diagramas de contexto](../../../01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](../../../01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](../../../00-modelo-del-dominio/modelo-dominio.md)
- [Detalle y prototipado](../../README.md)
- [conversation-log.md](../../../../../conversation-log.md)
