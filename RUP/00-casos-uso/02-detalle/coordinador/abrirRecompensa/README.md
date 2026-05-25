# FUNIBER > Coordinador > abrirRecompensa > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/README.md)|[Diseño](/RUP/02-diseño/README.md)|[Desarrollo](/RUP/03-desarrollo/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## propósito

Especificación detallada del caso de uso `abrirRecompensa()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el detalle de recompensa y las acciones disponibles según su rol.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirRecompensa()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el detalle de recompensa y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Coordinador visualiza el detalle de recompensa y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## diagrama de especificación

<div align=center>

|![Caso de uso: abrirRecompensa()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirRecompensa/abrirRecompensa.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - RECOMPENSA
<div align=center>

|![Wireframe: abrirRecompensa](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirRecompensa/abrirRecompensas-wireframe.svg)|
|-|
|**Estado**: MostrandoRecompensa|

</div>

**Correspondencia con especificación:**
- abrirRecompensa()
- **Coordinador** solicita abrir una recompensa
- **Sistema** presenta información de la recompensa<br>- Tipo<br>- Cantidad<br>**Sistema** permite solicitar:<br>- editar recompensa<br>- eliminar recompensa
- abrirRecompensas()

### validaciones del wireframe
- ¿El campo o bloque **Información de la recompensa** resulta claro para el Coordinador?
- ¿El campo o bloque **Tipo** resulta claro para el Coordinador?
- ¿El campo o bloque **Cantidad** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir una recompensa|| |
||**Sistema**|presenta información de la recompensa<br>- Tipo<br>- Cantidad<br>**Sistema** permite solicitar:<br>- editar recompensa<br>- eliminar recompensa| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoRecompensa**|Estado interno asociado a mostrando recompensa.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### información tratada
  - Concepto
  - Importe o reconocimiento
  - Estado
  - Proyecto

## opciones de navegación

### operaciones relacionadas
- **abrirRecompensas()** -> Navegar a `abrirRecompensas()` cuando el actor solicita esa continuidad.
- **eliminarRecompensa()** -> Navegar a `eliminarRecompensa()` cuando el actor solicita esa continuidad.
- **editarRecompensa()** -> Navegar a `editarRecompensa()` cuando el actor solicita esa continuidad.

### navegación del sistema
- **Estado de entrada**: RECOMPENSAS_ABIERTAS.
- **Estado de salida**: RECOMPENSAS_ABIERTAS, RECOMPENSA_ABIERTA.

## conexión con diagrama de contexto

Este caso de uso se integra en los diagramas de contexto del Coordinador, manteniendo la trazabilidad entre navegación, estado del sistema y responsabilidad del actor.

## vocabulario utilizado

### actor (Coordinador)
- **solicita**: expresa la intención de realizar una acción.
- **visualiza**: observa la información presentada por el sistema.
- **selecciona**: elige una entidad, acción o alternativa disponible.

### sistema
- **presenta**: muestra información organizada al actor.
- **permite**: habilita acciones disponibles sin imponer detalles de implementación.
- **registra**: conserva la información indicada por el actor cuando el caso de uso lo requiere.

## características metodológicas

### separación de responsabilidades
- **Actor**: usuario con visión global sobre proyectos, investigadores, convocatorias, publicaciones, entregables, recompensas y solicitudes de perfil.
- **Sistema**: presenta información, habilita acciones y mantiene la navegación del caso de uso.

### ausencia de detalles de implementación
- No especifica tecnología de interfaz.
- No incluye estructura de base de datos.
- No impone componentes concretos de desarrollo.

### conversación atómica
- El caso de uso representa una conversación completa.
- Tiene un objetivo claro para el actor Coordinador.
- Termina con una acción, navegación o estado observable.

## referencias

- [Diagramas de contexto](../../../01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](../../../01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](../../../00-modelo-del-dominio/modelo-dominio.md)
- [Detalle y prototipado](../../README.md)
- [conversation-log.md](../../../../../conversation-log.md)
