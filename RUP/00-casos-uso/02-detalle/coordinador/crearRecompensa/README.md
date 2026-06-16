# FUNIBER > Coordinador > crearRecompensa > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/crearRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/crearRecompensa/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/crearRecompensa/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `crearRecompensa()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para registrar una recompensa vinculada a un proyecto completado y a un investigador beneficiario elegible.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|crearRecompensa()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador registrar una recompensa para un investigador elegible de un proyecto completado.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador, listado de recompensas abierto y existencia de al menos un proyecto completado con beneficiarios elegibles.|
|**Postcondición exitosa**|La recompensa queda registrada, vinculada al proyecto completado y al investigador beneficiario, y disponible para consulta o edición.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: crearRecompensa()](/images/RUP/00-casos-uso/02-detalle/coordinador/crearRecompensa/crearRecompensa.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - CREAR RECOMPENSA
<div align=center>

|![Wireframe: crearRecompensa](/images/RUP/00-casos-uso/02-detalle/coordinador/crearRecompensa/crearRecompensa-wireframe.svg)|
|-|
|**Estado**: IntroduciendoDatos / RecompensaCreada|

</div>

**Correspondencia con especificación:**
- crearRecompensa()
- **Coordinador** solicita crear recompensa
- **Sistema** presenta proyectos completados pendientes de recompensa
- **Coordinador** selecciona un proyecto completado
- **Sistema** presenta investigadores elegibles del proyecto
- **Coordinador** selecciona el investigador beneficiario
- **Sistema** presenta los tipos de recompensa permitidos según la condición docente y la sede
- **Coordinador** introduce concepto, tipo y valor, y solicita guardar
- **Sistema** valida y presenta el registro de la recompensa
- **Coordinador** solicita cancelar la operación

### Validaciones del wireframe
- ¿El campo o bloque **Formulario de nueva recompensa** resulta claro para el Coordinador?
- ¿La selección de proyecto muestra únicamente proyectos completados pendientes de recompensa?
- ¿La selección de beneficiario muestra únicamente investigadores elegibles del proyecto?
- ¿Los tipos disponibles cambian correctamente según la condición docente y la sede?
- ¿El campo **Valor** permite comprender si se solicita un importe o una reducción de horas?
- ¿El campo o bloque **Resultado** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita crear recompensa|| |
||**Sistema**|presenta proyectos completados pendientes de recompensa|Permite seleccionar un proyecto o cancelar|
|**Coordinador**|selecciona un proyecto completado|| |
||**Sistema**|presenta investigadores elegibles del proyecto|Permite seleccionar al beneficiario|
|**Coordinador**|selecciona al investigador beneficiario|| |
||**Sistema**|presenta los tipos permitidos y permite introducir concepto y valor|Económica o reducción docente para investigador-docente; solo económica para investigador sin docencia|
|**Coordinador**|introduce los datos y solicita guardar|| |
||**Sistema**|valida proyecto, beneficiario, tipo y duplicados; registra y presenta la recompensa|La recompensa queda abierta|
|**Coordinador**|solicita cancelar la operación||No se aplican cambios|

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SeleccionandoProyecto**|Se muestran proyectos completados pendientes de recompensa.|Sistema debe impedir seleccionar proyectos no completados o no elegibles.|
|**SeleccionandoBeneficiario**|Se muestran investigadores elegibles del proyecto seleccionado.|Sistema debe limitar los beneficiarios a participantes elegibles del proyecto.|
|**IntroduciendoDatos**|Se muestran los tipos permitidos y se introducen concepto y valor.|Sistema debe adaptar los tipos a la condición docente y sede del beneficiario.|
|**RecompensaCreada**|La recompensa ha sido validada y registrada.|Sistema debe presentar el registro creado y permitir abrirlo.|

## Funcionalidad específica

### Patrón crear -> usar

- **Crear mínimo**: solicita solo los datos necesarios para registrar recompensa.
- **Continuidad**: tras la creación, el actor puede abrir o editar la entidad creada.
- **Validación temprana**: el prototipo permite detectar si faltan datos antes del desarrollo.

### Información tratada
  - Proyecto completado.
  - Investigador beneficiario elegible.
  - Condición docente y sede del beneficiario.
  - Tipo de recompensa permitido.
  - Concepto.
  - Importe económico o reducción docente.

### Reglas de dominio
- Solo se puede crear una recompensa para un proyecto completado.
- La recompensa debe indicar el investigador beneficiario.
- Si el beneficiario es investigador-docente, el Coordinador puede registrar recompensa económica o reducción docente del siguiente cuatrimestre.
- Si el beneficiario pertenece a una sede sin docencia, el Coordinador solo puede registrar recompensa económica.
- La reducción docente se concede por asignaturas completas: cada asignatura equivale a 4 horas semanales y la reducción debe ser de 4, 8, 12 o 16 horas.

## Opciones de navegación

### Operaciones relacionadas
- **abrirRecompensa()** -> Navegar a `abrirRecompensa()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: RECOMPENSAS_ABIERTAS.
- **Estado de salida exitosa**: RECOMPENSA_ABIERTA.
- **Estado de salida por cancelación**: RECOMPENSAS_ABIERTAS.

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
