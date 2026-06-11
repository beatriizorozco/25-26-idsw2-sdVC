# FUNIBER > Coordinador > responderPublicacion > Detalle y prototipado

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

Especificación detallada del caso de uso `responderPublicacion()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador registrar una respuesta dentro de una publicación.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|responderPublicacion()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador registrar una respuesta dentro de una publicación.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Coordinador autenticado con una publicación activa en `PUBLICACION_ABIERTA`.|
|**Postcondición exitosa**|La respuesta queda asociada a la publicación.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: responderPublicacion()](/images/RUP/00-casos-uso/02-detalle/coordinador/responderPublicacion/responderPublicacion.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - RESPONDER PUBLICACIÓN
<div align=center>

|![Wireframe: responderPublicacion](/images/RUP/00-casos-uso/02-detalle/coordinador/responderPublicacion/responderPublicacion-wireframe.svg)|
|-|
|**Estado**: IntroduciendoRespuesta / RespuestaRegistrada|

</div>

**Correspondencia con especificación:**
- responderPublicacion()
- **Coordinador** solicita responder publicación
- **Sistema** permite introducir respuesta<br>**Coordinador** introduce contenido y solicita enviar
- **Coordinador** solicita cancelar la operación

### Validaciones del wireframe
- ¿El campo o bloque **Introducir respuesta** resulta claro para el Coordinador?
- ¿El campo o bloque **Respuesta** resulta claro para el Coordinador?
- ¿El campo o bloque **Resultado** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita responder publicación|| |
|**Coordinador**||| |
||**Sistema**|permite introducir respuesta<br>**Coordinador** introduce contenido y solicita enviar| |
|**Coordinador**|solicita cancelar la operación|| |
||**Sistema**|presenta confirmación de respuesta publicada| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**IntroduciendoRespuesta**|Estado interno asociado a introduciendo respuesta.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**RespuestaRegistrada**|Estado interno asociado a respuesta registrada.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### Información tratada
  - Título
  - Contenido
  - Autor
  - Respuestas

## Opciones de navegación

### Operaciones relacionadas
- **abrirPublicaciones()** -> Navegar a `abrirPublicaciones()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PUBLICACION_ABIERTA.
- **Estado de salida**: PUBLICACION_ABIERTA.

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
