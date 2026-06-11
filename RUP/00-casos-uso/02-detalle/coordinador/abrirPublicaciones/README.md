# FUNIBER > Coordinador > abrirPublicaciones > Detalle y prototipado

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

Especificación detallada del caso de uso `abrirPublicaciones()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el listado de publicaciones con opciones de consulta, filtrado y navegación.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirPublicaciones()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el listado de publicaciones con opciones de consulta, filtrado y navegación.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Coordinador autenticado en `PANEL_PRINCIPAL_ABIERTO` o `PUBLICACION_ABIERTA`.|
|**Postcondición exitosa**|El Coordinador visualiza el listado de publicaciones y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirPublicaciones()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirPublicaciones/abrirPublicaciones.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - LISTADO DE PUBLICACIONES
<div align=center>

|![Wireframe: abrirPublicaciones](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirPublicaciones/abrirPublicaciones-wireframe.svg)|
|-|
|**Estado**: MostrandoListado / FiltrandoListado|

</div>

**Correspondencia con especificación:**
- abrirPublicaciones()
- abrirPublicaciones()
- **Coordinador** solicita abrir publicaciones
- **Sistema** presenta listado de publicaciones<br>- Titulo<br>- Autor<br>- Fecha<br>**Sistema** permite solicitar:<br>- abrir publicación<br>- abrirPanelPrincipal

### Validaciones del wireframe
- ¿El campo o bloque **Filtrar / buscar publicaciones** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Autor** resulta claro para el Coordinador?
- ¿El campo o bloque **Fecha** resulta claro para el Coordinador?
- ¿El campo o bloque **Listado de publicaciones** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir publicaciones|| |
||**Sistema**|presenta listado de publicaciones<br>- Titulo<br>- Autor<br>- Fecha<br>**Sistema** permite solicitar:<br>- abrir publicación<br>- abrirPanelPrincipal| |
|**Coordinador**|solicita una opción del listado|| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**MostrandoListado**|Estado interno asociado a mostrando listado.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**FiltrandoListado**|Estado interno asociado a filtrando listado.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Funcionalidad unificada: listar = filtrar = buscar

- **Listar**: muestra publicaciones sin criterio aplicado.
- **Filtrar/Buscar**: permite localizar publicaciones por título, autor, fecha, estado.
- **Navegar**: permite abrir detalles u operaciones relacionadas según el rol.

### Información tratada
  - Título
  - Autor
  - Fecha
  - Estado

## Opciones de navegación

### Operaciones relacionadas
- **abrirPublicacion()** -> Navegar a `abrirPublicacion()` cuando el actor solicita esa continuidad.
- **abrirPanelPrincipal()** -> Navegar a `abrirPanelPrincipal()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: PANEL_PRINCIPAL_ABIERTO, PUBLICACION_ABIERTA.
- **Estado de salida**: PUBLICACIONES_ABIERTAS, PUBLICACION_ABIERTA, PANEL_PRINCIPAL_ABIERTO.

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
