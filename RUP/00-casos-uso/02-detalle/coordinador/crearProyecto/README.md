# FUNIBER > Coordinador > crearProyecto > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/crearProyecto/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/crearProyecto/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/crearProyecto/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `crearProyecto()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador registrar un nuevo proyecto dentro de la plataforma.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|crearProyecto()|
|**Actor primario**|Coordinador|
|**Objetivo**|Registrar los datos mínimos de un proyecto y abrirlo para continuar su definición.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Coordinador autenticado en `PROYECTOS_ABIERTOS`.|
|**Postcondición exitosa**|El proyecto queda registrado en estado `Creado`, con código y Coordinador asignados, y pasa a `PROYECTO_ABIERTO`.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: crearProyecto()](/images/RUP/00-casos-uso/02-detalle/coordinador/crearProyecto/crearProyecto.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - CREAR PROYECTO
<div align=center>

|![Wireframe: crearProyecto](/images/RUP/00-casos-uso/02-detalle/coordinador/crearProyecto/crearProyecto-wireframe.svg)|
|-|
|**Estado**: SolicitandoDatosMinimos / ValidandoDatos|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita crear un nuevo proyecto
- **Sistema** permite introducir título, convocatoria de origen, fechas previstas y descripción inicial. El estado, código y Coordinador se asignan automáticamente.
- **Coordinador** solicita crear; **Sistema** valida datos y fechas antes de registrar el proyecto.
- Proyecto creado y abierto

### Validaciones del wireframe
- ¿El campo o bloque **Datos del proyecto** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado inicial** resulta claro para el Coordinador?
- ¿El campo o bloque **Coordinador responsable** resulta claro para el Coordinador?
- ¿El campo o bloque **Fecha inicio** resulta claro para el Coordinador?
- ¿El campo o bloque **Fecha fin** resulta claro para el Coordinador?
- ¿El campo o bloque **Descripción** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita crear un nuevo proyecto|| |
||**Sistema**|permite introducir título, convocatoria de origen, fechas previstas y descripción inicial; informa que asignará código, Coordinador y estado `Creado`| |
|**Coordinador**|introduce los datos y solicita crear|| |
||**Sistema**|valida datos y fechas; registra el proyecto y lo abre para continuar su definición| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoDatosMinimos**|Solicita únicamente la información necesaria para crear el proyecto.|Sistema debe asignar automáticamente código, Coordinador y estado inicial.|
|**ValidandoDatos**|Comprueba datos obligatorios y coherencia de fechas.|Sistema debe informar errores antes de registrar.|

## Funcionalidad específica

### Patrón crear -> usar

- **Crear mínimo**: solicita solo los datos necesarios para registrar proyecto.
- **Continuidad**: tras la creación, el actor puede abrir o editar la entidad creada.
- **Validación temprana**: el prototipo permite detectar si faltan datos antes del desarrollo.

### Información tratada
  - ID
  - Título
  - Estado
  - Investigadores asociados

## Opciones de navegación

### Navegación del sistema
- **Estado de entrada**: PROYECTOS_ABIERTOS.
- **Estado de salida**: PROYECTO_ABIERTO si se crea; PROYECTOS_ABIERTOS si se cancela.

## Conexión con diagrama de contexto

Este caso de uso corresponde a `PROYECTOS_ABIERTOS` → `crearProyecto()` → `PROYECTO_ABIERTO`. Sigue el patrón crear → usar: registra lo mínimo y abre el proyecto para continuar su definición.

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
