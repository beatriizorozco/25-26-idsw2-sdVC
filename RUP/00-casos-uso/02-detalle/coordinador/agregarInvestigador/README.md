# FUNIBER > Coordinador > agregarInvestigador > Detalle y prototipado

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

Especificación detallada del caso de uso `agregarInvestigador()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador asociar un investigador a un proyecto.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|agregarInvestigador()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador asociar un investigador a un proyecto.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El investigador queda asociado al proyecto.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: agregarInvestigador()](/images/RUP/00-casos-uso/02-detalle/coordinador/agregarInvestigador/agregarInvestigador.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - AGREGAR INVESTIGADOR A PROYECTO
<div align=center>

|![Wireframe: agregarInvestigador](/images/RUP/00-casos-uso/02-detalle/coordinador/agregarInvestigador/agregarInvestigador-wireframe.svg)|
|-|
|**Estado**: SolicitandoDatos / CreandoInvestigador|

</div>

**Correspondencia con especificación:**
- agregarInvestigador()
- **Coordinador** solicita agregar un investigador a un proyecto
- **Sistema** presenta solicitud de datos mínimos del investigador<br>- ID del investigador (obligatorio)<br>- Nombre del investigador (obligatorio)<br>- Campo del investigador (obligatorio)<br>- Permite solicitar agregar investigador<br>- Permite solicitar cancelar la agregacion
- **Coordinador** proporciona datos mínimos<br>**Sistema** agrega investigador

### Validaciones del wireframe
- ¿El campo o bloque **Datos mínimos del investigador** resulta claro para el Coordinador?
- ¿El campo o bloque **ID investigador *** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre *** resulta claro para el Coordinador?
- ¿El campo o bloque **Campo *** resulta claro para el Coordinador?
- ¿El campo o bloque **Acciones** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita agregar un investigador a un proyecto|| |
||**Sistema**|presenta solicitud de datos mínimos del investigador<br>- ID del investigador (obligatorio)<br>- Nombre del investigador (obligatorio)<br>- Campo del investigador (obligatorio)<br>- Permite solicitar agregar investigador<br>- Permite solicitar cancelar la agregacion| |
|**Coordinador**|proporciona datos mínimos<br>|| |
||**Sistema**|agrega investigador| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoDatos**|Estado interno asociado a solicitando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**CreandoInvestigador**|Estado interno asociado a creando investigador.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### Información tratada
  - Nombre
  - Perfil
  - Especialización
  - Proyectos asociados

## Opciones de navegación

### Operaciones relacionadas
- **abrirEntregables()** -> Navegar a `abrirEntregables()` cuando el actor solicita esa continuidad.
- **abrirProyectos()** -> Navegar a `abrirProyectos()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PROYECTO_ABIERTO.
- **Estado de salida**: PROYECTO_ABIERTO, ENTREGABLES_ABIERTOS, PROYECTOS_ABIERTOS.

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
