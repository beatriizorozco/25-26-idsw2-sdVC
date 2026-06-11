# FUNIBER > Coordinador > abrirMisPublicaciones > Detalle y prototipado

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

Especificación detallada del caso de uso `abrirMisPublicaciones()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el listado de publicaciones propias con opciones de consulta, filtrado y navegación.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirMisPublicaciones()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el listado de publicaciones propias con opciones de consulta, filtrado y navegación.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Coordinador autenticado en `PANEL_PRINCIPAL_ABIERTO` o `MI_PUBLICACION_ABIERTA`.|
|**Postcondición exitosa**|El Coordinador visualiza el listado de publicaciones propias y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirMisPublicaciones()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirMisPublicaciones/abrirMisPublicaciones.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - MIS PUBLICACIONES
<div align=center>

|![Wireframe: abrirMisPublicaciones](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirMisPublicaciones/abrirMisPublicaciones-wireframe.svg)|
|-|
|**Estado**: MostrandoLista / FiltrandoLista|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita abrir el listado de sus publicaciones
- **Sistema** presenta el bloque de filtrar / buscar con:<br>- Título<br>- Estado<br>- Fecha (desde)<br>- Fecha (hasta)<br>**Sistema** presenta el listado de mis publicaciones con:<br>- ID<br>- Título<br>- Estado<br>- Fecha<br>- Resumen<br>- abrirMiPublicacion()<br>y permite solicitar las siguientes acciones:<br>- Aplicar filtros<br>- Limpiar filtros<br>- Abrir publicación<br>- Crear publicación<br>- Abrir panel principal
- **Coordinador** solicita introducir criterios de filtrado o búsqueda<br>**Sistema** muestra el listado actualizado
- **Sistema** deja el listado de publicaciones visible

### Validaciones del wireframe
- ¿El campo o bloque **Filtrar / buscar publicaciones** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿El campo o bloque **Fecha (desde)** resulta claro para el Coordinador?
- ¿El campo o bloque **Fecha (hasta)** resulta claro para el Coordinador?
- ¿El campo o bloque **Listado de mis publicaciones** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Fecha** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir el listado de sus publicaciones|| |
||**Sistema**|presenta el bloque de filtrar / buscar con:<br>- Título<br>- Estado<br>- Fecha (desde)<br>- Fecha (hasta)<br>**Sistema** presenta el listado de mis publicaciones con:<br>- ID<br>- Título<br>- Estado<br>- Fecha<br>- Resumen<br>- abrirMiPublicacion()<br>y permite solicitar las siguientes acciones:<br>- Aplicar filtros<br>- Limpiar filtros<br>- Abrir publicación<br>- Crear publicación<br>- Abrir panel principal| |
|**Coordinador**|solicita introducir criterios de filtrado o búsqueda<br>|| |
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
