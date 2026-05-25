# FUNIBER > Coordinador > importarConvocatoria > Detalle y prototipado

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

Especificación detallada del caso de uso `importarConvocatoria()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador importar una convocatoria para incorporarla al seguimiento del sistema.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|importarConvocatoria()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador importar una convocatoria para incorporarla al seguimiento del sistema.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|La convocatoria queda incorporada al sistema.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: importarConvocatoria()](/images/RUP/00-casos-uso/02-detalle/coordinador/importarConvocatoria/importarConvocatoria.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - IMPORTAR CONVOCATORIA
<div align=center>

|![Wireframe: importarConvocatoria](/images/RUP/00-casos-uso/02-detalle/coordinador/importarConvocatoria/importarConvocatoria-wireframe.svg)|
|-|
|**Estado**: SolicitandoImportacion / IntroduciendoFuente / ConfirmandoImportacion|

</div>

**Correspondencia con especificación:**
- **Coordinador** solicita importar la convocatoria
- **Sistema** presenta la opción de importación<br>**Sistema** permite introducir la fuente de importación:<br>- Archivo (PDF / DOC / XLS)<br>- Enlace externo<br>**Sistema** muestra información a incorporar (si aplica):<br>- Título<br>- Área<br>- Estado<br>- Fechas relevantes<br>- Descripción<br>- Requisitos y condiciones<br>- Criterios de evaluación y dotación<br>- Documentación asociada<br>- Información de contacto<br>**Sistema** permite solicitar:<br>- Importar convocatoria<br>- Cancelar
- **Coordinador** solicita importar la información
- **Sistema** permite solicitar la importación<br>**Sistema** presenta y visualiza la convocatoria con la información importada

### Validaciones del wireframe
- ¿El campo o bloque **Fuente de importación** resulta claro para el Coordinador?
- ¿El campo o bloque **Archivo (opcional)** resulta claro para el Coordinador?
- ¿El campo o bloque **Enlace externo (opcional)** resulta claro para el Coordinador?
- ¿El campo o bloque **Información general** resulta claro para el Coordinador?
- ¿El campo o bloque **Título** resulta claro para el Coordinador?
- ¿El campo o bloque **Área** resulta claro para el Coordinador?
- ¿El campo o bloque **Estado** resulta claro para el Coordinador?
- ¿El campo o bloque **Apertura** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita importar la convocatoria|| |
||**Sistema**|presenta la opción de importación<br>**Sistema** permite introducir la fuente de importación:<br>- Archivo (PDF / DOC / XLS)<br>- Enlace externo<br>**Sistema** muestra información a incorporar (si aplica):<br>- Título<br>- Área<br>- Estado<br>- Fechas relevantes<br>- Descripción<br>- Requisitos y condiciones<br>- Criterios de evaluación y dotación<br>- Documentación asociada<br>- Información de contacto<br>**Sistema** permite solicitar:<br>- Importar convocatoria<br>- Cancelar| |
|**Coordinador**|solicita importar la información|| |
||**Sistema**|permite solicitar la importación<br>**Sistema** presenta y visualiza la convocatoria con la información importada| |
|**Coordinador**|solicita cancelar la importación<br>|| |
||**Sistema**|presenta y visualiza la convocatoria sin cambios| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoImportacion**|Estado interno asociado a solicitando importacion.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**IntroduciendoFuente**|Estado interno asociado a introduciendo fuente.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**ConfirmandoImportacion**|Estado donde el sistema valida o confirma la eliminación de convocatoria.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

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
- El caso de uso finaliza y devuelve el control al estado indicado por el diagrama de especificación.

### Navegación del sistema
- **Estado de entrada**: CONVOCATORIA_ABIERTA.
- **Estado de salida**: CONVOCATORIA_ABIERTA.

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
