# FUNIBER > Investigador > solicitarEliminacionPerfil > Detalle y prototipado

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

Especificación detallada del caso de uso `solicitarEliminacionPerfil()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para permitir al investigador solicitar la eliminación de su perfil dentro de la plataforma.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|solicitarEliminacionPerfil()|
|**Actor primario**|Investigador|
|**Objetivo**|Permitir al Investigador solicitar la eliminación de su perfil dentro de la plataforma.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Investigador y sistema disponible para navegación.|
|**Postcondición exitosa**|La solicitud de eliminación de perfil queda registrada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## diagrama de especificación

<div align=center>

|![Caso de uso: solicitarEliminacionPerfil()](/images/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/solicitarEliminacionPerfil.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - SOLICITAR ELIMINACIÓN DE PERFIL
<div align=center>

|![Wireframe: solicitarEliminacionPerfil](/images/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/solicitarEliminacionPerfil-wireframe.svg)|
|-|
|**Estado**: SeleccionandoPerfil / ConfirmandoSolicitud / SolicitudEnviada|

</div>

**Correspondencia con especificación:**
- solicitarEliminacionPerfil()
- **Investigador** solicita la eliminacion de su perfil
- **Sistema** presenta confirmación de solicitud de eliminación<br>- Confirmar o cancelar
- **Investigador** solicita confirmar eliminación<br>**Sistema** permite confirmar la solicitud de eliminación

### validaciones del wireframe
- ¿El campo o bloque **Perfil a eliminar** resulta claro para el Investigador?
- ¿El campo o bloque **ID** resulta claro para el Investigador?
- ¿El campo o bloque **Nombre** resulta claro para el Investigador?
- ¿El campo o bloque **Confirmación de solicitud** resulta claro para el Investigador?
- ¿El campo o bloque **Acción** resulta claro para el Investigador?
- ¿El campo o bloque **Solicitud enviada** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita la eliminacion de su perfil|| |
||**Sistema**|presenta confirmación de solicitud de eliminación<br>- Confirmar o cancelar| |
|**Investigador**|solicita confirmar eliminación<br>|| |
||**Sistema**|permite confirmar la solicitud de eliminación| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SeleccionandoPerfil**|Estado interno asociado a seleccionando perfil.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**ConfirmandoSolicitud**|Estado donde el sistema valida o confirma la eliminación de perfil.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**SolicitudEnviada**|Estado interno asociado a solicitud enviada.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### información tratada
  - Datos personales
  - Especialización
  - Contacto
  - Preferencias

## opciones de navegación

### operaciones relacionadas
- El caso de uso finaliza y devuelve el control al estado indicado por el diagrama de especificación.

### navegación del sistema
- **Estado de entrada**: OPCIONES_PERFIL_ABIERTO.
- **Estado de salida**: SESION_CERRADA.

## conexión con diagrama de contexto

Este caso de uso se integra en los diagramas de contexto del Investigador, manteniendo la trazabilidad entre navegación, estado del sistema y responsabilidad del actor.

## vocabulario utilizado

### actor (Investigador)
- **solicita**: expresa la intención de realizar una acción.
- **visualiza**: observa la información presentada por el sistema.
- **selecciona**: elige una entidad, acción o alternativa disponible.

### sistema
- **presenta**: muestra información organizada al actor.
- **permite**: habilita acciones disponibles sin imponer detalles de implementación.
- **registra**: conserva la información indicada por el actor cuando el caso de uso lo requiere.

## características metodológicas

### separación de responsabilidades
- **Actor**: usuario que consulta proyectos asociados, gestiona sus entregables, publicaciones, perfil y carga de trabajo.
- **Sistema**: presenta información, habilita acciones y mantiene la navegación del caso de uso.

### ausencia de detalles de implementación
- No especifica tecnología de interfaz.
- No incluye estructura de base de datos.
- No impone componentes concretos de desarrollo.

### conversación atómica
- El caso de uso representa una conversación completa.
- Tiene un objetivo claro para el actor Investigador.
- Termina con una acción, navegación o estado observable.

## referencias

- [Diagramas de contexto](../../../01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](../../../01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](../../../00-modelo-del-dominio/modelo-dominio.md)
- [Detalle y prototipado](../../README.md)
- [conversation-log.md](../../../../../conversation-log.md)
