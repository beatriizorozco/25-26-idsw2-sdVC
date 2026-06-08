# FUNIBER > Coordinador > abrirProyecto > Detalle y prototipado

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

Especificación detallada del caso de uso `abrirProyecto()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el detalle de proyecto y las acciones disponibles según su rol.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirProyecto(proyectoId)|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el detalle de proyecto y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Coordinador autenticado y proyecto seleccionado desde `PROYECTOS_ABIERTOS`, o proyecto conservado como contexto desde `ENTREGABLES_ABIERTOS`.|
|**Postcondición exitosa**|El proyecto seleccionado queda presentado en `PROYECTO_ABIERTO`.|
|**Postcondición de fallo**|El proyecto no se abre y el actor permanece en el estado de entrada.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirProyecto()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/abrirProyecto.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - PROYECTO
<div align=center>

|![Wireframe: abrirProyecto](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/proyectoAbierto-wireframe.svg)|
|-|
|**Estado**: RecuperandoProyecto / PresentandoProyecto|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita abrir el proyecto seleccionado
- **Sistema** recupera y presenta directamente sus datos, equipo, carga disponible y entregables asociados.
- **Coordinador** consulta el detalle o solicita una acción disponible.

### Validaciones del wireframe
- ¿El campo o bloque **Datos del proyecto** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿El campo o bloque **Entidad financiadora** resulta claro para el Coordinador?
- ¿El campo o bloque **Coordinador** resulta claro para el Coordinador?
- ¿El campo o bloque **Inicio** resulta claro para el Coordinador?
- ¿El campo o bloque **Fin** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir el proyecto seleccionado|| |
||**Sistema**|recupera y presenta código, título, estado, convocatoria, coordinador, fechas, descripción, equipo investigador, carga disponible y entregables asociados; permite gestionar el proyecto o volver al listado| |
|**Coordinador**|consulta el detalle o solicita una acción disponible|| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**RecuperandoProyecto**|Localiza el proyecto seleccionado o conservado como contexto.|Sistema debe recuperar exactamente el proyecto solicitado.|
|**PresentandoProyecto**|Presenta el detalle y las acciones autorizadas.|Sistema debe mostrar información y navegación coherentes con el Coordinador.|

## Funcionalidad específica

### Conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### Información tratada
  - ID
  - Título
  - Estado
  - Investigadores asociados

## Opciones de navegación

### Operaciones relacionadas
- **editarProyecto()** -> Navegar a `editarProyecto()` cuando el actor solicita esa continuidad.
- **eliminarProyecto()** -> Navegar a `eliminarProyecto()` cuando el actor solicita esa continuidad.
- **agregarInvestigador()** -> Navegar a `agregarInvestigador()` cuando el actor solicita esa continuidad.
- **eliminarInvestigador()** -> Navegar a `eliminarInvestigador()` cuando el actor solicita esa continuidad.
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.
- **abrirInvestigadores()** -> Navegar a `abrirInvestigadores()` cuando el actor solicita esa continuidad.
- **abrirProyectos()** -> Navegar a `abrirProyectos()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PROYECTOS_ABIERTOS, ENTREGABLES_ABIERTOS.
- **Estado de salida**: PROYECTO_ABIERTO, PROYECTOS_ABIERTOS, ENTREGABLES_ABIERTOS, INVESTIGADORES_ABIERTOS.

## Conexión con diagrama de contexto

Este caso de uso corresponde a `PROYECTOS_ABIERTOS` o `ENTREGABLES_ABIERTOS` → `abrirProyecto(proyectoId)` → `PROYECTO_ABIERTO`. No vuelve a listar proyectos: recibe o conserva el proyecto de contexto y presenta directamente su detalle.

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
