# FUNIBER > Investigador > crearEntregable > Detalle y prototipado

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

Especificación detallada del caso de uso `crearEntregable()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para permitir al investigador registrar un nuevo entregable dentro de la plataforma.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|crearEntregable()|
|**Actor primario**|Investigador|
|**Objetivo**|Permitir al Investigador registrar un nuevo entregable dentro de la plataforma.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Investigador y sistema disponible para navegación.|
|**Postcondición exitosa**|El nuevo entregable queda registrado y disponible para consulta o edición.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: crearEntregable()](/images/RUP/00-casos-uso/02-detalle/investigador/crearEntregable/crearEntregable.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - CREAR ENTREGABLE
<div align=center>

|![Wireframe: crearEntregable](/images/RUP/00-casos-uso/02-detalle/investigador/crearEntregable/crearEntregable-wireframe.svg)|
|-|
|**Estado**: SolicitandoDatos / RegistrandoEntregable / ValidandoDatos|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita crear un entregable
- **Sistema** muestra y permite introducir los datos del nuevo entregable:<br>- Título<br>- Tipo (informe / dataset / presentación / otro)<br>- Fecha límite<br>- Estado inicial (pendiente / en curso / entregado)<br>- Descripción (opcional)<br>- Archivo adjunto (opcional)<br>**Sistema** permite solicitar:<br>- Crear entregable<br>- Volver a entregables
- **Investigador** solicita crear el entregable
- **Sistema** presenta y visualiza el entregable creado

### Validaciones del wireframe
- ¿El campo o bloque **Datos del entregable** resulta claro para el Investigador?
- ¿El campo o bloque **Título** resulta claro para el Investigador?
- ¿El campo o bloque **Tipo** resulta claro para el Investigador?
- ¿El campo o bloque **Estado inicial** resulta claro para el Investigador?
- ¿El campo o bloque **Fecha límite** resulta claro para el Investigador?
- ¿El campo o bloque **Descripción (opcional)** resulta claro para el Investigador?
- ¿El campo o bloque **Adjunto (opcional)** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita crear un entregable|| |
||**Sistema**|muestra y permite introducir los datos del nuevo entregable:<br>- Título<br>- Tipo (informe / dataset / presentación / otro)<br>- Fecha límite<br>- Estado inicial (pendiente / en curso / entregado)<br>- Descripción (opcional)<br>- Archivo adjunto (opcional)<br>**Sistema** permite solicitar:<br>- Crear entregable<br>- Volver a entregables| |
|**Investigador**|solicita crear el entregable|| |
||**Sistema**|presenta y visualiza el entregable creado| |
||**Sistema**|muestra errores de introducción<br>**Sistema** permite introducir correcciones| |
|**Investigador**|solicita cancelar la creación<br>|| |
||**Sistema**|presenta el listado de entregables sin cambios| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoDatos**|Estado interno asociado a solicitando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**RegistrandoEntregable**|Estado donde el sistema registra la información del entregable.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**ValidandoDatos**|Estado interno asociado a validando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Patrón crear -> usar

- **Crear mínimo**: solicita solo los datos necesarios para registrar entregable.
- **Continuidad**: tras la creación, el actor puede abrir o editar la entidad creada.
- **Validación temprana**: el prototipo permite detectar si faltan datos antes del desarrollo.

### Información tratada
  - Título
  - Descripción
  - Fecha
  - Estado

## Opciones de navegación

### Operaciones relacionadas
- **editarEntregable()** -> Navegar a `editarEntregable()` cuando el actor solicita esa continuidad.
- **eliminarEntregable()** -> Navegar a `eliminarEntregable()` cuando el actor solicita esa continuidad.
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: ENTREGABLES_ABIERTOS.
- **Estado de salida**: ENTREGABLE_ABIERTO, ENTREGABLES_ABIERTOS.

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
