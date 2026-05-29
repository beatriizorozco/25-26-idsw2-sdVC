# FUNIBER > Coordinador > crearInvestigador > Detalle y prototipado

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

Especificación detallada del caso de uso `crearInvestigador()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador registrar un nuevo investigador dentro de la plataforma.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|crearInvestigador()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador registrar un nuevo investigador dentro de la plataforma.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El nuevo investigador queda registrado y disponible para consulta o edición.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: crearInvestigador()](/images/RUP/00-casos-uso/02-detalle/coordinador/crearInvestigador/crearInvestigador.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - CREAR INVESTIGADOR
<div align=center>

|![Wireframe: crearInvestigador](/images/RUP/00-casos-uso/02-detalle/coordinador/crearInvestigador/crearInvestigador-wireframe.svg)|
|-|
|**Estado**: SolicitandoDatos / CreandoInvestigador|

</div>

**Correspondencia con especificación:**
- crearInvestigador()
- **Coordinador** solicita crear investigador nuevo
- **Sistema** presenta solicitud de datos mínimos del investigador<br>- ID del investigador (obligatorio)<br>- Nombre del investigador (obligatorio)<br>- Campo del investigador (obligatorio)<br>- Permite solicitar crear investigador<br>- Permite solicitar cancelar creación
- **Coordinador** proporciona datos mínimos<br>**Sistema** crea investigador y transfiere a edición

### Validaciones del wireframe
- ¿El campo o bloque **Datos mínimos del investigador** resulta claro para el Coordinador?
- ¿El campo o bloque **ID del investigador *** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre *** resulta claro para el Coordinador?
- ¿El campo o bloque **Campo de investigación *** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita crear investigador nuevo|| |
||**Sistema**|presenta solicitud de datos mínimos del investigador<br>- ID del investigador (obligatorio)<br>- Nombre del investigador (obligatorio)<br>- Campo del investigador (obligatorio)<br>- Permite solicitar crear investigador<br>- Permite solicitar cancelar creación| |
|**Coordinador**|proporciona datos mínimos<br>|| |
||**Sistema**|crea investigador y transfiere a edición| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoDatos**|Estado interno asociado a solicitando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**CreandoInvestigador**|Estado interno asociado a creando investigador.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Patrón crear -> usar

- **Crear mínimo**: solicita solo los datos necesarios para registrar investigador.
- **Continuidad**: tras la creación, el actor puede abrir o editar la entidad creada.
- **Validación temprana**: el prototipo permite detectar si faltan datos antes del desarrollo.

### Información tratada
  - Nombre
  - Perfil
  - Especialización
  - Proyectos asociados

## Opciones de navegación

### Operaciones relacionadas
- **abrirOpcionesPerfil()** -> Navegar a `abrirOpcionesPerfil()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: INVESTIGADORES_ABIERTOS.
- **Estado de salida**: INVESTIGADOR_ABIERTO, OPCIONES_PERFIL_ABIERTO.

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
