# FUNIBER > Investigador > abrirMisPublicaciones > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirMisPublicaciones/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirMisPublicaciones/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirMisPublicaciones/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `abrirMisPublicaciones()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para presentar al investigador el listado de publicaciones propias con opciones de consulta, filtrado y navegación.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirMisPublicaciones()|
|**Actor primario**|Investigador|
|**Objetivo**|Presentar al Investigador el listado de publicaciones propias con opciones de consulta, filtrado y navegación.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Investigador autenticado en `PANEL_PRINCIPAL_ABIERTO` o `MI_PUBLICACION_ABIERTA`.|
|**Postcondición exitosa**|El Investigador visualiza el listado de publicaciones propias y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirMisPublicaciones()](/images/RUP/00-casos-uso/02-detalle/investigador/abrirMisPublicaciones/abrirMisPublicaciones.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - MIS PUBLICACIONES
<div align=center>

|![Wireframe: abrirMisPublicaciones](/images/RUP/00-casos-uso/02-detalle/investigador/abrirMisPublicaciones/abrirMisPublicaciones-wireframe.svg)|
|-|
|**Estado**: MostrandoLista / FiltrandoLista|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita abrir el listado de sus publicaciones
- **Sistema** presenta el bloque de filtrar / buscar con:<br>- Título<br>- Estado<br>- Fecha (desde)<br>- Fecha (hasta)<br>**Sistema** presenta el listado de mis publicaciones con:<br>- ID<br>- Título<br>- Estado<br>- Fecha<br>- Resumen<br>- Permite abrir una publicación<br>y permite solicitar las siguientes acciones:<br>- Aplicar filtros<br>- Limpiar filtros<br>- Abrir publicación<br>- Crear publicación<br>- Abrir panel principal
- **Investigador** solicita introducir criterios de filtrado o búsqueda<br>**Sistema** muestra el listado actualizado
- **Sistema** deja el listado de publicaciones visible

### Validaciones del wireframe
- ¿El campo o bloque **Filtrar / buscar publicaciones** resulta claro para el Investigador?
- ¿El campo o bloque **Título** resulta claro para el Investigador?
- ¿El campo o bloque **Estado** resulta claro para el Investigador?
- ¿El campo o bloque **Fecha (desde)** resulta claro para el Investigador?
- ¿El campo o bloque **Fecha (hasta)** resulta claro para el Investigador?
- ¿El campo o bloque **Listado de mis publicaciones** resulta claro para el Investigador?
- ¿El campo o bloque **ID** resulta claro para el Investigador?
- ¿El campo o bloque **Fecha** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita abrir el listado de sus publicaciones|| |
||**Sistema**|presenta el bloque de filtrar / buscar con:<br>- Título<br>- Estado<br>- Fecha (desde)<br>- Fecha (hasta)<br>**Sistema** presenta el listado de mis publicaciones con:<br>- ID<br>- Título<br>- Estado<br>- Fecha<br>- Resumen<br>- Permite abrir una publicación<br>y permite solicitar las siguientes acciones:<br>- Aplicar filtros<br>- Limpiar filtros<br>- Abrir publicación<br>- Crear publicación<br>- Abrir panel principal| |
|**Investigador**|solicita introducir criterios de filtrado o búsqueda<br>|| |
||**Sistema**|muestra el listado actualizado| |
||**Sistema**|deja el listado de publicaciones visible| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoLista**|Estado interno asociado a mostrando lista.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**FiltrandoLista**|Estado interno asociado a filtrando lista.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Funcionalidad unificada: listar = filtrar = buscar

- **Listar**: muestra publicaciones propias sin criterio aplicado.
- **Filtrar/Buscar**: permite localizar publicaciones propias por título, tipo, estado, fecha.
- **Navegar**: permite abrir detalles u operaciones relacionadas según el rol.

### Información tratada
  - Título
  - Tipo
  - Estado
  - Fecha

## Opciones de navegación

### Operaciones relacionadas
- **abrirMiPublicacion()** -> Navegar a `abrirMiPublicacion()` cuando el actor solicita esa continuidad.
- **crearPublicacion()** -> Navegar a `crearPublicacion()` cuando el actor solicita esa continuidad.
- **abrirPanelPrincipal()** -> Navegar a `abrirPanelPrincipal()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PANEL_PRINCIPAL_ABIERTO, MI_PUBLICACION_ABIERTA.
- **Estado de salida**: MIS_PUBLICACIONES_ABIERTAS, MI_PUBLICACION_ABIERTA, PANEL_PRINCIPAL_ABIERTO.

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
