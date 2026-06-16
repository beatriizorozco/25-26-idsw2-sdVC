# FUNIBER > Coordinador > abrirConvocatoria > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirConvocatoria/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirConvocatoria/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirConvocatoria/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `abrirConvocatoria()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para presentar al coordinador el detalle de convocatoria y las acciones disponibles según su rol.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|abrirConvocatoria()|
|**Actor primario**|Coordinador|
|**Objetivo**|Presentar al Coordinador el detalle de convocatoria y las acciones disponibles según su rol.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|El Coordinador visualiza el detalle de convocatoria y puede continuar la navegación.|
|**Postcondición de fallo**|No se modifica la información del sistema; el actor permanece en el punto de navegación anterior.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: abrirConvocatoria()](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatoria/abrirConvocatoria.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - CONVOCATORIA
<div align=center>

|![Wireframe: abrirConvocatoria](/images/RUP/00-casos-uso/02-detalle/coordinador/abrirConvocatoria/convocatoriaAbierta-wireframe.svg)|
|-|
|**Estado**: CargandoDetalle|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita abrir una convocatoria
- **Sistema** presenta y visualiza la convocatoria solicitada<br>**Sistema** muestra:<br>- Título<br>- Área<br>- Estado<br>- Fechas relevantes<br>- Descripción<br>- Requisitos y condiciones<br>- Criterios de evaluación y dotación<br>- Documentación asociada<br>- Información de contacto<br>**Sistema** permite solicitar:<br>- Importar convocatoria<br>- Volver a convocatorias
- Convocatoria mostrada
- abrirConvocatorias()

### Validaciones del wireframe
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Área** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿El campo o bloque **Apertura** resulta claro para el Coordinador?
- ¿El campo o bloque **Cierre** resulta claro para el Coordinador?
- ¿El campo o bloque **Publica** resulta claro para el Coordinador?
- ¿El campo o bloque **Descripción** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita abrir una convocatoria|| |
||**Sistema**|presenta y visualiza la convocatoria solicitada<br>**Sistema** muestra:<br>- Título<br>- Área<br>- Estado<br>- Fechas relevantes<br>- Descripción<br>- Requisitos y condiciones<br>- Criterios de evaluación y dotación<br>- Documentación asociada<br>- Información de contacto<br>**Sistema** permite solicitar:<br>- Importar convocatoria<br>- Volver a convocatorias| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**CargandoDetalle**|Estado interno asociado a cargando detalle.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### Información tratada
  - Título
  - Entidad convocante
  - Plazos
  - Requisitos

## Opciones de navegación

### Operaciones relacionadas
- **abrirConvocatorias()** -> Navegar a `abrirConvocatorias()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: CONVOCATORIAS_ABIERTAS.
- **Estado de salida**: CONVOCATORIA_ABIERTA, CONVOCATORIAS_ABIERTAS.

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
