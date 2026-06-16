# FUNIBER > Investigador > abrirPublicacion > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirPublicacion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirPublicacion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirPublicacion/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `abrirPublicacion()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para presentar al investigador el detalle de publicación y las acciones disponibles según su rol.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirPublicacion()|
|**Actor primario**|Investigador|
|**Objetivo**|Presentar al Investigador el detalle de publicación y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Investigador autenticado en `PUBLICACIONES_ABIERTAS` con una publicación activa seleccionada.|
|**Postcondición exitosa**|El Investigador visualiza el detalle de publicación y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirPublicacion()](/images/RUP/00-casos-uso/02-detalle/investigador/abrirPublicacion/abrirPublicacion.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - PUBLICACIÓN
<div align=center>

|![Wireframe: abrirPublicacion](/images/RUP/00-casos-uso/02-detalle/investigador/abrirPublicacion/abrirPublicacion-wireframe.svg)|
|-|
|**Estado**: MostrandoPublicacion|

</div>

**Correspondencia con especificación:**
- abrirPublicacion()
- **Investigador** solicita abrir una publicación
- **Sistema** presenta contenido de la publicación<br>- Título<br>- Texto<br>- Autor<br>- Respuestas asociadas<br>**Sistema** permite solicitar:<br>- responder
- Publicación abierta

### Validaciones del wireframe
- ¿El campo o bloque **Contenido de la publicación** resulta claro para el Investigador?
- ¿El campo o bloque **Título** resulta claro para el Investigador?
- ¿El campo o bloque **Texto** resulta claro para el Investigador?
- ¿El campo o bloque **Autor** resulta claro para el Investigador?
- ¿El campo o bloque **Respuestas asociadas** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita abrir una publicación|| |
||**Sistema**|presenta contenido de la publicación<br>- Título<br>- Texto<br>- Autor<br>- Respuestas asociadas<br>**Sistema** permite solicitar:<br>- responder| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoPublicacion**|Estado interno asociado a mostrando publicacion.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

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
- **responderPublicacion()** -> Navegar a `responderPublicacion()` cuando el actor solicita esa continuidad.
- **abrirPublicaciones()** -> Navegar a `abrirPublicaciones()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PUBLICACIONES_ABIERTAS.
- **Estado de salida**: PUBLICACION_ABIERTA, PUBLICACIONES_ABIERTAS.

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
