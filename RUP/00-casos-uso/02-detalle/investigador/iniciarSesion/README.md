# FUNIBER > Investigador > iniciarSesion > Detalle y prototipado

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

Especificación detallada del caso de uso `iniciarSesion()` mediante diagrama de estado, mostrando la conversación entre el Investigador y el Sistema para autenticar al investigador y abrir su acceso al panel principal.

## información del caso de uso

|Atributo|Valor|
|-|-|
|**Nombre**|iniciarSesion()|
|**Actor primario**|Investigador|
|**Objetivo**|Autenticar al Investigador y abrir su acceso al panel principal.|
|**Tipo**|Primario, esencial|
|**Nivel**|Objetivo de usuario|
|**Precondición**|Usuario no autenticado con credenciales válidas de Investigador.|
|**Postcondición exitosa**|El Investigador accede al panel principal.|
|**Postcondición de fallo**|El sistema no abre sesión y solicita revisar credenciales.|

## diagrama de especificación

<div align=center>

|![Caso de uso: iniciarSesion()](/images/RUP/00-casos-uso/02-detalle/investigador/iniciarSesion/iniciarSesion.svg)|
|-|
|Código fuente: [especificacion.puml](especificacion.puml)|

</div>

## prototipo de interfaz

### propósito del prototipo
**Objetivo:** Que te digan que NO lo antes posible - validar la especificación antes de invertir en desarrollo.

### wireframes

#### pantalla 1: GIPF - INICIAR SESIÓN
<div align=center>

|![Wireframe: iniciarSesion](/images/RUP/00-casos-uso/02-detalle/investigador/iniciarSesion/iniciarSesion-credenciales-correctas-wireframe.svg)|
|-|
|**Estado**: SolicitandoAcceso / ProporcionandoCredenciales|

</div>

**Correspondencia con especificación:**
- **Investigador** solicita acceder al sistema
- **Sistema** permite introducir<br>- usuario<br>- contraseña
- **Investigador** introduce<br>- usuario<br>- contraseña
- **Sistema** presenta credenciales incorrectas

### validaciones del wireframe
- ¿El campo o bloque **Iniciar sesión** resulta claro para el Investigador?
- ¿Las acciones disponibles mantienen una navegación coherente con el rol Investigador?
- ¿Falta información que el wireframe revela antes del análisis?

**Código fuente:** [prototipo.puml](prototipo.puml)

## conversación detallada

### flujo principal

|Actor|Acción|Sistema|Respuesta|
|-|-|-|-|
|**Investigador**|solicita acceder al sistema|| |
||**Sistema**|permite introducir<br>- usuario<br>- contraseña| |
|**Investigador**|introduce<br>- usuario<br>- contraseña|| |
||**Sistema**|presenta credenciales incorrectas| |
||**Sistema**|presenta acceso correcto| |
||**Sistema**|presenta panel principal| |

## estados internos del caso de uso

|Estado|Descripción|Responsabilidad|
|-|-|-|
|**SolicitandoAcceso**|Estado interno asociado a solicitando acceso.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|
|**ProporcionandoCredenciales**|Estado interno asociado a proporcionando credenciales.|Sistema debe mantener la conversación coherente con el objetivo del caso de uso.|

## funcionalidad específica

### conversación atómica

- El caso de uso tiene un objetivo concreto y completo.
- Actor y Sistema mantienen responsabilidades separadas.
- La especificación evita decisiones de implementación.

### información tratada
  - Credenciales
  - Estado de autenticación
  - Rol

## opciones de navegación

### operaciones relacionadas
- El caso de uso finaliza y devuelve el control al estado indicado por el diagrama de especificación.

### navegación del sistema
- **Estado de entrada**: estado previo definido en el diagrama de contexto.
- **Estado de salida**: PANEL_PRINCIPAL_ABIERTO.

## conexión con diagrama de contexto

Este caso de uso se integra en los diagramas de contexto del Investigador, manteniendo la trazabilidad entre navegación, estado del sistema y responsabilidad del actor.

## vocabulario utilizado

### actor (Investigador)
- **solicita**: expresa la intención de realizar una acción.
- **visualiza**: observa la información presentada por el sistema.
- **selecciona**: elige una entidad, acción o alternativa disponible.

### sistema
- **presenta**: muestra información organizada al actor.
- **permite**: habilita acciones disponibles sin imponer detalles de implementación.
- **registra**: conserva la información indicada por el actor cuando el caso de uso lo requiere.

## características metodológicas

### separación de responsabilidades
- **Actor**: usuario que consulta proyectos asociados, gestiona sus entregables, publicaciones, perfil y carga de trabajo.
- **Sistema**: presenta información, habilita acciones y mantiene la navegación del caso de uso.

### ausencia de detalles de implementación
- No especifica tecnología de interfaz.
- No incluye estructura de base de datos.
- No impone componentes concretos de desarrollo.

### conversación atómica
- El caso de uso representa una conversación completa.
- Tiene un objetivo claro para el actor Investigador.
- Termina con una acción, navegación o estado observable.

## referencias

- [Diagramas de contexto](../../../01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](../../../01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](../../../00-modelo-del-dominio/modelo-dominio.md)
- [Detalle y prototipado](../../README.md)
- [conversation-log.md](../../../../../conversation-log.md)
