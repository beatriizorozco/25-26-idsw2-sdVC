# FUNIBER > Coordinador > abrirSolicitudesEliminacionPerfil > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `abrirSolicitudesEliminacionPerfil()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el detalle de solicitud de eliminación de perfil y las acciones disponibles según su rol.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirSolicitudesEliminacionPerfil()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el detalle de solicitud de eliminación de perfil y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Coordinador visualiza el detalle de solicitud de eliminación de perfil y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirSolicitudesEliminacionPerfil()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudesEliminacionPerfil/abrirSolicitudesEliminacionPerfil.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - SOLICITUDES DE ELIMINACIÓN DE PERFIL
<div align=center>

|![Wireframe: abrirSolicitudesEliminacionPerfil](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudesEliminacionPerfil/abrirSolicitudesEliminacionPerfil-wireframe.svg)|
|-|
|**Estado**: MostrandoLista / FiltrandoLista|

</div>

**Correspondencia con especificación:**
- abrirSolicitudesEliminacionPerfil()
- abrirSolicitudesEliminacionPerfil()
- **Coordinador** solicita abrir solicitudes eliminacion perfil
- **Sistema** presenta las solicitudes de eliminacion de perfil<br>- ID, nombre, campo de cada investigador<br>- Permite solicitar abrir una solicitud<br>- Permite solicitar volver a las opciones de perfil<br>- Permite solicitar volver a las solicitudes de eliminacion de perfil

### Validaciones del wireframe
- ¿El campo o bloque **Solicitudes pendientes** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre** resulta claro para el Coordinador?
- ¿El campo o bloque **Campo** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir solicitudes eliminacion perfil|| |
||**Sistema**|presenta las solicitudes de eliminacion de perfil<br>- ID, nombre, campo de cada investigador<br>- Permite solicitar abrir una solicitud<br>- Permite solicitar volver a las opciones de perfil<br>- Permite solicitar volver a las solicitudes de eliminacion de perfil| |
|**Coordinador**|solicita una de las opciones|| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoLista**|Estado interno asociado a mostrando lista.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**FiltrandoLista**|Estado interno asociado a filtrando lista.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### Información tratada
  - Solicitante
  - Motivo
  - Estado
  - Fecha

## Opciones de navegación

### Operaciones relacionadas
- **abrirSolicitudEliminacionPerfil()** -> Navegar a `abrirSolicitudEliminacionPerfil()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: OPCIONES_PERFIL_ABIERTO, SOLICITUD_ELIMINACION_PERFIL_ABIERTA.
- **Estado de salida**: SOLICITUD_ELIMINACION_PERFIL_ABIERTA.

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
