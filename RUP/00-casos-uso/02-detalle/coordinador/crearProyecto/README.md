# FUNIBER > Coordinador > crearProyecto > Detalle y prototipado

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

Especificación detallada del caso de uso `crearProyecto()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador registrar un nuevo proyecto dentro de la plataforma.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|crearProyecto()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador registrar un nuevo proyecto dentro de la plataforma.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El nuevo proyecto queda registrado y disponible para consulta o edición.|
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
|**Estado**: SolicitandoDatos / RegistrandoProyecto|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita crear un nuevo proyecto
- **Sistema** presenta el formulario de creación del proyecto y permite introducir:<br>- Título<br>- Estado inicial<br>- Fecha de inicio<br>- Fecha de fin<br>- Descripción<br>*El ID del proyecto se genera automáticamente<br>y permite solicitar las siguientes acciones:<br>- Crear proyecto<br>- Volver
- **Coordinador** introduce los datos solicitados<br>**Sistema** registra el nuevo proyecto
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
||**Sistema**|presenta el formulario de creación del proyecto y permite introducir:<br>- Título<br>- Estado inicial<br>- Fecha de inicio<br>- Fecha de fin<br>- Descripción<br>*El ID del proyecto se genera automáticamente<br>y permite solicitar las siguientes acciones:<br>- Crear proyecto<br>- Volver| |
|**Coordinador**|introduce los datos solicitados<br>|| |
||**Sistema**|registra el nuevo proyecto| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoDatos**|Estado interno asociado a solicitando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**RegistrandoProyecto**|Estado donde el sistema registra la información del proyecto.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

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

### Operaciones relacionadas
- **editarProyecto()** -> Navegar a `editarProyecto()` cuando el actor solicita esa continuidad.
- **eliminarProyecto()** -> Navegar a `eliminarProyecto()` cuando el actor solicita esa continuidad.
- **agregarInvestigador()** -> Navegar a `agregarInvestigador()` cuando el actor solicita esa continuidad.
- **eliminarInvestigador()** -> Navegar a `eliminarInvestigador()` cuando el actor solicita esa continuidad.
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.
- **abrirInvestigadores()** -> Navegar a `abrirInvestigadores()` cuando el actor solicita esa continuidad.
- **abrirProyectos()** -> Navegar a `abrirProyectos()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PROYECTOS_ABIERTOS.
- **Estado de salida**: PROYECTO_ABIERTO, PROYECTOS_ABIERTOS, ENTREGABLES_ABIERTOS, INVESTIGADORES_ABIERTOS.

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
