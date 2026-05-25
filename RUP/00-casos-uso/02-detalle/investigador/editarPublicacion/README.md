# FUNIBER > Investigador > editarPublicacion > Detalle y prototipado

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

Especificación detallada del caso de uso `editarPublicacion()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para permitir al investigador actualizar la información de publicación manteniendo la trazabilidad del sistema.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|editarPublicacion()|
|**Actor primario**|Investigador|
|**Objetivo**|Permitir al Investigador actualizar la información de publicación manteniendo la trazabilidad del sistema.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Investigador y sistema disponible para navegación.|
|**Postcondición exitosa**|La información de publicación queda actualizada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: editarPublicacion()](/images/RUP/00-casos-uso/02-detalle/investigador/editarPublicacion/editarPublicacion.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - EDITAR PUBLICACIÓN
<div align=center>

|![Wireframe: editarPublicacion](/images/RUP/00-casos-uso/02-detalle/investigador/editarPublicacion/editarPublicacion-wireframe.svg)|
|-|
|**Estado**: MostrandoPublicacion / EditandoDatos / ValidandoDatos|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita editar una publicación propia
- **Sistema** presenta el formulario de edición con los datos actuales:<br>- Título<br>- Estado<br>- Visibilidad<br>- Descripción<br>- Adjuntos (opcional)<br>y permite solicitar las siguientes acciones:<br>- Guardar cambios<br>- Cancelar
- **Investigador** solicita guardar cambios
- **Sistema** visualiza la publicación actualizada

### Validaciones del wireframe
- ¿El campo o bloque **ID** resulta claro para el Investigador?
- ¿El campo o bloque **Título** resulta claro para el Investigador?
- ¿El campo o bloque **Estado** resulta claro para el Investigador?
- ¿El campo o bloque **Visibilidad** resulta claro para el Investigador?
- ¿El campo o bloque **Descripción** resulta claro para el Investigador?
- ¿El campo o bloque **Adjuntos (opcional)** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita editar una publicación propia|| |
||**Sistema**|presenta el formulario de edición con los datos actuales:<br>- Título<br>- Estado<br>- Visibilidad<br>- Descripción<br>- Adjuntos (opcional)<br>y permite solicitar las siguientes acciones:<br>- Guardar cambios<br>- Cancelar| |
|**Investigador**|solicita guardar cambios|| |
||**Sistema**|visualiza la publicación actualizada| |
||**Sistema**|muestra errores de introducción<br>**Sistema** permite introducir correcciones| |
|**Investigador**|solicita cancelar<br>|| |
||**Sistema**|visualiza la publicación sin cambios| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoPublicacion**|Estado interno asociado a mostrando publicacion.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**EditandoDatos**|Estado donde el sistema permite modificar la información de publicación.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**ValidandoDatos**|Estado interno asociado a validando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Patrón de edición completa

- **Editar completo**: concentra el mantenimiento de publicación.
- **Persistencia conceptual**: la especificación describe la actualización sin entrar en tecnología.
- **Retorno controlado**: el actor conserva la navegación hacia la entidad o listado relacionado.

### Información tratada
  - Título
  - Contenido
  - Autor
  - Respuestas

## Opciones de navegación

### Operaciones relacionadas
- **abrirMisPublicaciones()** -> Navegar a `abrirMisPublicaciones()` cuando el actor solicita esa continuidad.
- **eliminarPublicacion()** -> Navegar a `eliminarPublicacion()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: MI_PUBLICACION_ABIERTA.
- **Estado de salida**: MI_PUBLICACION_ABIERTA, MIS_PUBLICACIONES_ABIERTAS.

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
