# FUNIBER > Coordinador > editarPerfil > Detalle y prototipado

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|**Detalle**|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Inception (Inicio)
- **Disciplina**: Requisitos
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Especificación detallada del caso de uso `editarPerfil()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador actualizar la información de su propio perfil manteniendo la trazabilidad del sistema.

## Información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|editarPerfil()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador actualizar la información de su propio perfil manteniendo la trazabilidad del sistema.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|La información de perfil queda actualizada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## Diagrama de especificación

<div align=center>

|![Caso de uso: editarPerfil()](/images/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/editarPerfil.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## Prototipo de interfaz

### Propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### Wireframes

#### Pantalla 1: GIPF - EDITAR PERFIL DEL COORDINADOR
<div align=center>

|![Wireframe: editarPerfil](/images/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/editarPerfil-wireframe.svg)|
|-|
|**Estado**: EditandoDatos / GuardandoDatos|

</div>

**Correspondencia con especificación:**
- editarPerfil()
- **Coordinador** solicita editar perfil
- **Sistema** presenta datos de perfil propio<br>- nombre completo, correo, unidad, línea de investigación y biografía<br>**Sistema** permite solicitar:<br>- modificar atributos<br>- guardar cambios<br>- cancelar edición
- **Coordinador** solicita modificar atributos

### Validaciones del wireframe
- ¿El campo o bloque **Datos del perfil propio** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre completo** resulta claro para el Coordinador?
- ¿El campo o bloque **Correo** resulta claro para el Coordinador?
- ¿El campo o bloque **Unidad** resulta claro para el Coordinador?
- ¿El campo o bloque **Línea de investigación** resulta claro para el Coordinador?
- ¿El campo o bloque **Biografía** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## Conversación detallada

### Flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita editar perfil|| |
||**Sistema**|presenta datos de perfil propio<br>- nombre completo, correo, unidad, línea de investigación y biografía<br>**Sistema** permite solicitar:<br>- modificar atributos<br>- guardar cambios<br>- cancelar edición| |
|**Coordinador**|solicita modificar atributos|| |
|**Coordinador**|solicita guardar y salir|| |
|**Coordinador**|solicita cancelar edición|| |

## Estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**EditandoDatos**|Estado donde el sistema permite modificar la información de perfil.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**GuardandoDatos**|Estado interno asociado a guardando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## Funcionalidad específica

### Patrón de edición completa

- **Editar completo**: concentra el mantenimiento de perfil.
- **Persistencia conceptual**: la especificación describe la actualización sin entrar en tecnología.
- **Retorno controlado**: el actor conserva la navegación hacia la entidad o listado relacionado.

### Información tratada
  - Datos personales
  - Especialización
  - Contacto
  - Preferencias

## Opciones de navegación

### Operaciones relacionadas
- **abrirOpcionesPerfil()** -> Navegar a `abrirOpcionesPerfil()` cuando el actor solicita esa continuidad.

### Navegación del sistema
- **Estado de entrada**: OPCIONES_PERFIL_ABIERTO.
- **Estado de salida**: OPCIONES_PERFIL_ABIERTO.

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
