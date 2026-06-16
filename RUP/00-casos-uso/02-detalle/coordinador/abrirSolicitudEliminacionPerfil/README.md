# FUNIBER > Coordinador > abrirSolicitudEliminacionPerfil > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `abrirSolicitudEliminacionPerfil()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el detalle de solicitud de eliminación de perfil y las acciones disponibles según su rol.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirSolicitudEliminacionPerfil()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el detalle de solicitud de eliminación de perfil y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Coordinador visualiza el detalle de solicitud de eliminación de perfil y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirSolicitudEliminacionPerfil()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudEliminacionPerfil/abrirSolicitudEliminacionPerfil.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - RESOLVER SOLICITUD DE ELIMINACIÓN
<div align=center>

|![Wireframe: abrirSolicitudEliminacionPerfil](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudEliminacionPerfil/resolverSolicitudEliminacionPerfil-wireframe.svg)|
|-|
|**Estado**: CargandoDetalle|

</div>

**Correspondencia con especificación:**
- abrirSolicitudEliminacionPerfil()
- **Coordinador** solicita abrir una solicitud de eliminación de perfil
- **Sistema** presenta y visualiza la solicitud seleccionada con:<br>- ID del investigador<br>- Nombre<br>- Fecha de solicitud<br>- Motivo<br>**Sistema** permite solicitar:<br>- Eliminar perfil<br>- Volver a solicitudes de eliminación de perfil
- Solicitud mostrada

### Validaciones del wireframe
- ¿El campo o bloque **Datos de la solicitud** resulta claro para el Coordinador?
- ¿El campo o bloque **ID Investigador** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre** resulta claro para el Coordinador?
- ¿El campo o bloque **Campo** resulta claro para el Coordinador?
- ¿El campo o bloque **Decisión** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir una solicitud de eliminación de perfil|| |
||**Sistema**|presenta y visualiza la solicitud seleccionada con:<br>- ID del investigador<br>- Nombre<br>- Fecha de solicitud<br>- Motivo<br>**Sistema** permite solicitar:<br>- Eliminar perfil<br>- Volver a solicitudes de eliminación de perfil| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**CargandoDetalle**|Estado interno asociado a cargando detalle.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

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
- **abrirSolicitudesEliminacionPerfil()** -> Navegar a `abrirSolicitudesEliminacionPerfil()` cuando el actor solicita esa continuidad.
- **eliminarPerfil()** -> Navegar a `eliminarPerfil()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS.
- **Estado de salida**: SOLICITUD_ELIMINACION_PERFIL_ABIERTA, SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS.

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
