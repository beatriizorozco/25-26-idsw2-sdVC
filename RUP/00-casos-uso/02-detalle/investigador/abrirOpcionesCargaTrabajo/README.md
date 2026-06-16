# FUNIBER > Investigador > abrirOpcionesCargaTrabajo > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `abrirOpcionesCargaTrabajo()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para presentar al investigador el detalle de opciones de carga de trabajo y las acciones disponibles según su rol.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirOpcionesCargaTrabajo()|
|**Actor primario**|Investigador|
|**Objetivo**|Presentar al Investigador el detalle de opciones de carga de trabajo y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Investigador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Investigador visualiza el detalle de opciones de carga de trabajo y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirOpcionesCargaTrabajo()](/images/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/abrirOpcionesCargaTrabajo.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - CARGA DE TRABAJO
<div align=center>

|![Wireframe: abrirOpcionesCargaTrabajo](/images/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/abrirOpcionesCargaTrabajo-wireframe.svg)|
|-|
|**Estado**: CargandoCargaTrabajo|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita abrir las opciones de carga de trabajo
- **Sistema** muestra la información de carga de trabajo propia:<br>- Resumen personal<br>- Detalle semanal (docencia, investigación, actividades académicas)<br>- Total semanal y margen docente (si aplica)<br>y permite solicitar las siguientes acciones:<br>- Editar carga de trabajo<br>- Abrir panel principal
- Carga de trabajo propia visible
- editarCargaTrabajo()

### Validaciones del wireframe
- ¿El campo o bloque **Resumen personal (semanal)** resulta claro para el Investigador?
- ¿El campo o bloque **Docencia (h/sem)** resulta claro para el Investigador?
- ¿El campo o bloque **Investigación (h/sem)** resulta claro para el Investigador?
- ¿El campo o bloque **Actividades académicas (h/sem)** resulta claro para el Investigador?
- ¿El campo o bloque **Total (h/sem)** resulta claro para el Investigador?
- ¿El campo o bloque **Margen docencia** resulta claro para el Investigador?
- ¿El campo o bloque **Detalle propio** resulta claro para el Investigador?
- ¿El campo o bloque **Código FUNIBER** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita abrir las opciones de carga de trabajo|| |
||**Sistema**|muestra la información de carga de trabajo propia:<br>- Resumen personal<br>- Detalle semanal (docencia, investigación, actividades académicas)<br>- Total semanal y margen docente (si aplica)<br>y permite solicitar las siguientes acciones:<br>- Editar carga de trabajo<br>- Abrir panel principal| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**CargandoCargaTrabajo**|Estado interno asociado a cargando carga trabajo.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### Información tratada
  - Dedicación
  - Disponibilidad
  - Proyectos asociados
  - Observaciones

## Opciones de navegación

### Operaciones relacionadas
- **editarCargaTrabajo()** -> Navegar a `editarCargaTrabajo()` cuando el actor solicita esa continuidad.
- **abrirPanelPrincipal()** -> Navegar a `abrirPanelPrincipal()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PANEL_PRINCIPAL_ABIERTO.
- **Estado de salida**: OPCIONES_CARGA_TRABAJO_ABIERTAS, PANEL_PRINCIPAL_ABIERTO.

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
