# FUNIBER > Investigador > abrirPanelPrincipal > Detalle y prototipado

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

Especificación detallada del caso de uso `abrirPanelPrincipal()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para presentar al investigador el detalle de panel principal y las acciones disponibles según su rol.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirPanelPrincipal()|
|**Actor primario**|Investigador|
|**Objetivo**|Presentar al Investigador el detalle de panel principal y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Investigador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Investigador visualiza el detalle de panel principal y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirPanelPrincipal()](/images/RUP/00-casos-uso/02-detalle/investigador/abrirPanelPrincipal/abrirPanelPrincipal.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - PANEL PRINCIPAL
<div align=center>

|![Wireframe: abrirPanelPrincipal](/images/RUP/00-casos-uso/02-detalle/investigador/abrirPanelPrincipal/abrirPanelPrincipal-wireframe.svg)|
|-|
|**Estado**: MostrandoPanel|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita abrir el panel principal
- **Sistema** presenta el panel principal y permite solicitar las siguientes acciones:<br>- Abrir proyectos<br>- Abrir investigadores<br>- Abrir recompensas<br>- Abrir mis publicaciones<br>- Abrir publicaciones<br>- Abrir opciones de perfil<br>- Abrir opciones de carga de trabajo<br>- Cerrar sesión
- Panel principal visible
- abrirOpcionesPerfil()

### Validaciones del wireframe
- ¿El campo o bloque **Investigador** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita abrir el panel principal|| |
||**Sistema**|presenta el panel principal y permite solicitar las siguientes acciones:<br>- Abrir proyectos<br>- Abrir investigadores<br>- Abrir recompensas<br>- Abrir mis publicaciones<br>- Abrir publicaciones<br>- Abrir opciones de perfil<br>- Abrir opciones de carga de trabajo<br>- Cerrar sesión| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoPanel**|Estado interno asociado a mostrando panel.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### Información tratada
  - Accesos principales
  - Resumen de actividad
  - Opciones disponibles

## Opciones de navegación

### Operaciones relacionadas
- **abrirOpcionesPerfil()** -> Navegar a `abrirOpcionesPerfil()` cuando el actor solicita esa continuidad.
- **abrirOpcionesCargaTrabajo()** -> Navegar a `abrirOpcionesCargaTrabajo()` cuando el actor solicita esa continuidad.
- **abrirProyectos()** -> Navegar a `abrirProyectos()` cuando el actor solicita esa continuidad.
- **abrirInvestigadores()** -> Navegar a `abrirInvestigadores()` cuando el actor solicita esa continuidad.
- **abrirMisPublicaciones()** -> Navegar a `abrirMisPublicaciones()` cuando el actor solicita esa continuidad.
- **abrirPublicaciones()** -> Navegar a `abrirPublicaciones()` cuando el actor solicita esa continuidad.
- **abrirRecompensas()** -> Navegar a `abrirRecompensas()` cuando el actor solicita esa continuidad.
- **cerrarSesion()** -> Navegar a `cerrarSesion()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: INVESTIGADORES_ABIERTOS, OPCIONES_CARGA_TRABAJO_ABIERTAS, OPCIONES_PERFIL_ABIERTO, MIS_PUBLICACIONES_ABIERTAS, PUBLICACIONES_ABIERTAS, RECOMPENSAS_ABIERTAS, PROYECTOS_ABIERTOS.
- **Estado de salida**: PANEL_PRINCIPAL_ABIERTO, OPCIONES_PERFIL_ABIERTO, OPCIONES_CARGA_TRABAJO_ABIERTAS, PROYECTOS_ABIERTOS, INVESTIGADORES_ABIERTOS, MIS_PUBLICACIONES_ABIERTAS, PUBLICACIONES_ABIERTAS, RECOMPENSAS_ABIERTAS, SESION_CERRADA.

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
