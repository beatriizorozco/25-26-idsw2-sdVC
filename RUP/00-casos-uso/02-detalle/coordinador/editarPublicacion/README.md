# FUNIBER > Coordinador > editarPublicacion > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarPublicacion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarPublicacion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarPublicacion/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `editarPublicacion()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador actualizar la información de publicación manteniendo la trazabilidad del sistema.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|editarPublicacion()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador actualizar la información de publicación manteniendo la trazabilidad del sistema.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Coordinador autenticado con una publicación abierta en `PUBLICACION_ABIERTA` o `MI_PUBLICACION_ABIERTA`.|
|**Postcondición exitosa**|La información de publicación queda actualizada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: editarPublicacion()](/images/RUP/00-casos-uso/02-detalle/coordinador/editarPublicacion/editarPublicacion.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - EDITAR PUBLICACIÓN
<div align=center>

|![Wireframe: editarPublicacion](/images/RUP/00-casos-uso/02-detalle/coordinador/editarPublicacion/editarPublicacion-wireframe.svg)|
|-|
|**Estado**: EditandoContenido / CambiosGuardados|

</div>

**Correspondencia con especificación:**
- editarPublicacion()
- **Coordinador** solicita editar publicación
- **Sistema** presenta contenido editable<br>- Título<br>- Texto<br>**Sistema** permite introducir modificaciones<br>**Coordinador** introduce cambios y solicita guardar
- **Coordinador** solicita cancelar edición

### Validaciones del wireframe
- ¿El campo o bloque **Contenido editable de la publicación** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Texto** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita editar publicación|| |
|**Coordinador**||| |
||**Sistema**|presenta contenido editable<br>- Título<br>- Texto<br>| |
|**Coordinador**|solicita cancelar edición|| |
||**Sistema**|presenta confirmación de cambios guardados| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**EditandoContenido**|Estado donde el sistema permite modificar la información de publicación.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**CambiosGuardados**|Estado interno asociado a cambios guardados.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Patrón de edición completa

- **Editar completo**: concentra el mantenimiento de publicación.
- **Persistencia conceptual**: la especificación describe la actualización sin entrar en tecnología.
- **Retorno controlado**: el actor conserva la navegación hacia la entidad o listado relacionado.

### Información tratada
  - Título
  - Contenido
  - Estado
  - Visibilidad
  - Adjuntos

### Principios de diseño aplicables
- **Responsabilidad única**: El caso de uso solo coordina la edición; la autorización y la persistencia se delegarán a colaboradores específicos en Análisis y Diseño.
- **OCP**: La misma capacidad admite contexto global o propio sin crear variantes como `editarMiPublicacion()`.
- **Inversión de dependencias**: El caso expresa reglas del dominio sin depender de tecnología de interfaz o almacenamiento.

## Opciones de navegación

### Operaciones relacionadas
- **eliminarPublicacion()** -> Navegar a `eliminarPublicacion()` cuando el actor solicita esa continuidad.
- **abrirPublicaciones()** -> Navegar a `abrirPublicaciones()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PUBLICACION_ABIERTA, MI_PUBLICACION_ABIERTA.
- **Estado de salida**: Se conserva el estado de detalle desde el que comenzó la edición.

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
