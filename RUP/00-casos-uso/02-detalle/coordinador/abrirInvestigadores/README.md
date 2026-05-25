# FUNIBER > Coordinador > abrirInvestigadores > Detalle y prototipado

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

Especificación detallada del caso de uso `abrirInvestigadores()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el listado de investigadores con opciones de consulta, filtrado y navegación.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirInvestigadores()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el listado de investigadores con opciones de consulta, filtrado y navegación.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Coordinador visualiza el listado de investigadores y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## diagrama de especificación

<div align=center>

|![Caso de uso: abrirInvestigadores()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirInvestigadores/abrirInvestigadores.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - INVESTIGADORES
<div align=center>

|![Wireframe: abrirInvestigadores](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirInvestigadores/abrirInvestigadores-wireframe.svg)|
|-|
|**Estado**: MostrandoLista / FiltrandoLista|

</div>

**Correspondencia con especificación:**
- abrirInvestigadores()
- **Coordinador** solicita abrir investigadores
- **Sistema** presenta lista de investigadores<br>- ID, nombre, campo de cada investigador<br>- Permite solicitar filtrar lista<br>- Permite solicitar abrir investigador<br>- Permite solicitar crear investigador nuevo<br>- Permite solicitar abrir panel principal
- **Coordinador** solicita filtrar lista<br>**Sistema** presenta lista filtrada

### validaciones del wireframe
- ¿El campo o bloque **Filtrar / buscar** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre** resulta claro para el Coordinador?
- ¿El campo o bloque **Campo** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿El campo o bloque **Lista de investigadores** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir investigadores|| |
||**Sistema**|presenta lista de investigadores<br>- ID, nombre, campo de cada investigador<br>- Permite solicitar filtrar lista<br>- Permite solicitar abrir investigador<br>- Permite solicitar crear investigador nuevo<br>- Permite solicitar abrir panel principal| |
|**Coordinador**|solicita filtrar lista<br>|| |
||**Sistema**|presenta lista filtrada| |
|**Coordinador**|solicita una de las opciones|| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoLista**|Estado interno asociado a mostrando lista.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**FiltrandoLista**|Estado interno asociado a filtrando lista.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### funcionalidad unificada: listar = filtrar = buscar

- **Listar**: muestra investigadores sin criterio aplicado.
- **Filtrar/Buscar**: permite localizar investigadores por nombre, perfil, área, disponibilidad.
- **Navegar**: permite abrir detalles u operaciones relacionadas según el rol.

### información tratada
  - Nombre
  - Perfil
  - Área
  - Disponibilidad

## opciones de navegación

### operaciones relacionadas
- **abrirInvestigador()** -> Navegar a `abrirInvestigador()` cuando el actor solicita esa continuidad.
- **crearInvestigador()** -> Navegar a `crearInvestigador()` cuando el actor solicita esa continuidad.
- **abrirPanelPrincipal()** -> Navegar a `abrirPanelPrincipal()` cuando el actor solicita esa continuidad.

### navegación del sistema
- **Estado de entrada**: PANEL_PRINCIPAL_ABIERTO.
- **Estado de salida**: INVESTIGADOR_ABIERTO, PANEL_PRINCIPAL_ABIERTO.

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
