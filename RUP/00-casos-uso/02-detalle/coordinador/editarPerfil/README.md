# FUNIBER > Coordinador > editarPerfil > Detalle y prototipado

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

Especificación detallada del caso de uso `editarPerfil()` mediante diagrama de estado, mostrando la conversación entre el Coordinador y el Sistema para permitir al coordinador actualizar la información de perfil manteniendo la trazabilidad del sistema.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|editarPerfil()|
|**Actor primario**|Coordinador|
|**Objetivo**|Permitir al Coordinador actualizar la información de perfil manteniendo la trazabilidad del sistema.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario autenticado como Coordinador y sistema disponible para navegación.|
|**Postcondición exitosa**|La información de perfil queda actualizada.|
|**Postcondición de fallo**|No se aplican cambios si la información solicitada no es válida o el actor cancela la operación.|

## diagrama de especificación

<div align=center>

|![Caso de uso: editarPerfil()](/images/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/editarPerfil.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - EDITAR PERFIL DE INVESTIGADOR
<div align=center>

|![Wireframe: editarPerfil](/images/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/editarPerfil-wireframe.svg)|
|-|
|**Estado**: EditandoDatos / GuardandoDatos|

</div>

**Correspondencia con especificación:**
- editarPerfil()
- **Coordinador** solicita editar perfil
- **Sistema** presenta datos de perfil<br>- ID, nombre, apellido, campo, carrera, master, rol<br>**Sistema** permite solicitar:<br>- modificar atributos<br>- guardar cambios<br>- cancelar edición
- **Coordinador** solicita modificar atributos

### validaciones del wireframe
- ¿El campo o bloque **Datos del investigador** resulta claro para el Coordinador?
- ¿El campo o bloque **ID** resulta claro para el Coordinador?
- ¿El campo o bloque **Nombre** resulta claro para el Coordinador?
- ¿El campo o bloque **Apellido** resulta claro para el Coordinador?
- ¿El campo o bloque **Campo** resulta claro para el Coordinador?
- ¿El campo o bloque **Carrera** resulta claro para el Coordinador?
- ¿El campo o bloque **Máster** resulta claro para el Coordinador?
- ¿El campo o bloque **Rol** resulta claro para el Coordinador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Coordinador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Coordinador**|solicita editar perfil|| |
||**Sistema**|presenta datos de perfil<br>- ID, nombre, apellido, campo, carrera, master, rol<br>**Sistema** permite solicitar:<br>- modificar atributos<br>- guardar cambios<br>- cancelar edición| |
|**Coordinador**|solicita modificar atributos|| |
|**Coordinador**|solicita guardar y salir|| |
|**Coordinador**|solicita cancelar edición|| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**EditandoDatos**|Estado donde el sistema permite modificar la información de perfil.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**GuardandoDatos**|Estado interno asociado a guardando datos.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### patrón de edición completa

- **Editar completo**: concentra el mantenimiento de perfil.
- **Persistencia conceptual**: la especificación describe la actualización sin entrar en tecnología.
- **Retorno controlado**: el actor conserva la navegación hacia la entidad o listado relacionado.

### información tratada
  - Datos personales
  - Especialización
  - Contacto
  - Preferencias

## opciones de navegación

### operaciones relacionadas
- **abrirOpcionesPerfil()** -> Navegar a `abrirOpcionesPerfil()` cuando el actor solicita esa continuidad.

### navegación del sistema
- **Estado de entrada**: OPCIONES_PERFIL_ABIERTO.
- **Estado de salida**: OPCIONES_PERFIL_ABIERTO.

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
