# FUNIBER > Investigador > abrirProyectos > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirProyectos/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirProyectos/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirProyectos/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `abrirProyectos()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para presentar al investigador el listado de proyectos con opciones de consulta, filtrado y navegación.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirProyectos(investigadorId?)|
|**Actor primario**|Investigador|
|**Objetivo**|Presentar los proyectos activos propios o los activos visibles asociados al investigador de contexto.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Investigador autenticado en `PANEL_PRINCIPAL_ABIERTO`, `PROYECTO_ABIERTO` o `INVESTIGADOR_ABIERTO`.|
|**Postcondición exitosa**|El estado queda en `PROYECTOS_ABIERTOS` mostrando únicamente proyectos visibles para el actor.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirProyectos()](/images/RUP/00-casos-uso/02-detalle/investigador/abrirProyectos/abrirProyectos.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - MIS PROYECTOS
<div align=center>

|![Wireframe: abrirProyectos](/images/RUP/00-casos-uso/02-detalle/investigador/abrirProyectos/abrirProyectos-wireframe.svg)|
|-|
|**Estado**: MostrandoLista / FiltrandoLista / SolicitandoAccion|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita abrir proyectos
- **Sistema** presenta los proyectos activos propios o, desde `INVESTIGADOR_ABIERTO`, los proyectos activos visibles asociados a ese perfil.
- **Investigador** solicita introducir filtros y/o solicita introducir búsqueda<br>**Sistema** muestra el listado actualizado
- **Investigador** solicita una acción disponible en el listado

### Validaciones del wireframe
- ¿El campo o bloque **Listado de mis proyectos** resulta claro para el Investigador?
- ¿El campo o bloque **ID** resulta claro para el Investigador?
- ¿El campo o bloque **Título** resulta claro para el Investigador?
- ¿El campo o bloque **Estado** resulta claro para el Investigador?
- ¿El campo o bloque **Inicio** resulta claro para el Investigador?
- ¿El campo o bloque **Fin** resulta claro para el Investigador?
- ¿El campo o bloque **Acción** resulta claro para el Investigador?
- ¿El campo o bloque **Filtrar / buscar proyectos** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita abrir proyectos|| |
||**Sistema**|presenta los proyectos activos propios o los proyectos activos visibles asociados al investigador de contexto, con sus datos principales y opciones de consulta y filtrado| |
|**Investigador**|solicita introducir filtros y/o solicita introducir búsqueda<br>|| |
||**Sistema**|muestra el listado actualizado| |
|**Investigador**|solicita una acción disponible en el listado|| |
||**Sistema**|presenta la navegación solicitada| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**PresentandoProyectos**|Presenta los proyectos activos visibles correspondientes al contexto.|Sistema debe impedir que el Investigador consulte proyectos no visibles o archivados.|
|**FiltrandoProyectos**|Aplica o limpia criterios sobre el conjunto visible.|Sistema debe conservar las restricciones de visibilidad al filtrar.|

## Funcionalidad específica

### Funcionalidad unificada: listar = filtrar = buscar

- **Listar**: muestra proyectos sin criterio aplicado.
- **Filtrar/Buscar**: permite localizar proyectos por ID, título, estado, inicio y fin.
- **Navegar**: permite abrir detalles u operaciones relacionadas según el rol.

### Información tratada
  - ID
  - Título
  - Estado
  - Inicio y fin

## Opciones de navegación

### Operaciones relacionadas
- **abrirProyecto()** -> Navegar a `abrirProyecto()` cuando el actor solicita esa continuidad.
- **abrirPanelPrincipal()** -> Navegar a `abrirPanelPrincipal()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PANEL_PRINCIPAL_ABIERTO, PROYECTO_ABIERTO, INVESTIGADOR_ABIERTO.
- **Estado de salida**: PROYECTOS_ABIERTOS, PROYECTO_ABIERTO, PANEL_PRINCIPAL_ABIERTO.

## Conexión con diagrama de contexto

Este caso de uso corresponde a `PANEL_PRINCIPAL_ABIERTO`, `PROYECTO_ABIERTO` o `INVESTIGADOR_ABIERTO` → `abrirProyectos(investigadorId?)` → `PROYECTOS_ABIERTOS`. Sin parámetro muestra los proyectos propios; con investigador de contexto muestra únicamente sus proyectos visibles.

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
