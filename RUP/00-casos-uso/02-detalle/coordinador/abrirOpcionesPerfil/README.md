# FUNIBER > Coordinador > abrirOpcionesPerfil > Detalle y prototipado

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

Especificación detallada del caso de uso `abrirOpcionesPerfil()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el detalle de opciones de perfil y las acciones disponibles según su rol.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirOpcionesPerfil()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el detalle de opciones de perfil y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Coordinador visualiza el detalle de opciones de perfil y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## diagrama de especificación

<div align=center>

|![Caso de uso: abrirOpcionesPerfil()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesPerfil/abrirOpcionesPerfil.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - OPCIONES DE PERFIL
<div align=center>

|![Wireframe: abrirOpcionesPerfil](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesPerfil/abrirOpcionesPerfil-wireframe.svg)|
|-|
|**Estado**: MostrandoLista / FiltrandoLista|

</div>

**Correspondencia con especificación:**
- abrirOpcionesPerfil(ID)
- abrirOpcionesPerfil()
- abrirOpcionesPerfil()
- **Coordinador** solicita abrir opciones perfil

### validaciones del wireframe
- ¿El campo o bloque **Investigador seleccionado** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre** resulta claro para el Coordinador?
- ¿El campo o bloque **Campo** resulta claro para el Coordinador?
- ¿El campo o bloque **Opciones disponibles** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir opciones perfil|| |
||**Sistema**|presenta las opciones disponibles en perfil<br>- ID, nombre, campo de cada investigador<br>- Permite solicitar editar perfil<br>- Permite solicitar resolver solicitud eliminacion<br>- Permite solicitar volver al panel principal<br>- Permite solicitar volver al investigador| |
|**Coordinador**|solicita una de las opciones|| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoLista**|Estado interno asociado a mostrando lista.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**FiltrandoLista**|Estado interno asociado a filtrando lista.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### información tratada
  - Datos personales
  - Especialización
  - Preferencias
  - Acciones disponibles

## opciones de navegación

### operaciones relacionadas
- **editarPerfil()** -> Navegar a `editarPerfil()` cuando el actor solicita esa continuidad.
- **solicitarEliminacionPerfil()** -> Navegar a `solicitarEliminacionPerfil()` cuando el actor solicita esa continuidad.
- **abrirPanelPrincipal()** -> Navegar a `abrirPanelPrincipal()` cuando el actor solicita esa continuidad.

### navegación del sistema
- **Estado de entrada**: INVESTIGADOR_ABIERTO, PANEL_PRINCIPAL_ABIERTO, SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS.
- **Estado de salida**: OPCIONES_PERFIL_ABIERTO, SESION_CERRADA, PANEL_PRINCIPAL_ABIERTO.

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
