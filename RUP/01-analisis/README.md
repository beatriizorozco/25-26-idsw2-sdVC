# FUNIBER > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/README.md)|[Desarrollo](/RUP/03-desarrollo/README.md)|[Pruebas](/RUP/04-pruebas/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Esta sección contiene el análisis de colaboración de los casos de uso de la Plataforma Interna de Investigación de FUNIBER. El objetivo es transformar las especificaciones de Requisitos en clases de análisis, responsabilidades y colaboraciones que preparan el paso a Diseño.

El análisis aplica el patrón MVC de forma sistemática, separando vistas de interacción, controladores de coordinación y entidades del dominio.

## Contenido de la disciplina

### [Casos de uso - Análisis MVC](casos-uso/README.md)

Análisis de cada caso de uso mediante:

- **Clases Boundary**: vistas que reciben acciones de Coordinador o Investigador.
- **Clases Control**: controladores que coordinan la conversación del caso de uso.
- **Clases Entity**: entidades y repositorios del dominio.
- **Diagramas de colaboración**: relaciones entre estado de entrada, vista, control, entidades y colaboraciones de salida.
- **Responsabilidades definidas**: separación clara entre presentación, coordinación y datos.

### Actores analizados

- [Coordinador](casos-uso/coordinador/README.md): casos con permisos globales sobre proyectos, investigadores, publicaciones, entregables, convocatorias, recompensas y solicitudes de eliminación de perfil.
- [Investigador](casos-uso/investigador/README.md): casos asociados a perfil propio, carga de trabajo, consulta de información permitida y gestión de publicaciones o entregables propios.

## Metodología de análisis aplicada

### Patrón MVC sistemático

- **View / Boundary**: clases de interfaz que reciben solicitudes del actor y presentan información.
- **Controller / Control**: clases que coordinan validaciones, permisos, flujo de caso de uso y navegación.
- **Model / Entity**: entidades del dominio y repositorios responsables del acceso conceptual a datos.

### Estereotipos de análisis

- **Boundary (Vista)**: `#629EF9` - clases de interacción actor-sistema.
- **Control (Controlador)**: `#b5bd68` - clases de coordinación del caso de uso.
- **Entity (Entidad)**: `#F2AC4E` - entidades y repositorios del dominio.
- **Estado o Collaboration**: `#CDEBA5` - estados del diagrama de contexto y referencias a otras colaboraciones.

### Diagramas de colaboración

- **Paquete por caso de uso**: cada caso se modela como colaboración independiente.
- **Entrada explícita**: el diagrama parte del estado real definido en los diagramas de contexto.
- **Salida concreta**: el caso termina en el estado o colaboración indicada por la especificación.
- **Trazabilidad con Detalle**: las colaboraciones respetan los diagramas de especificación de `RUP/00-casos-uso/02-detalle`.

## Cobertura de análisis

### Bloques revisados

- **Gestión de sesión y navegación principal**: `iniciarSesion()`, `abrirPanelPrincipal()` y `cerrarSesion()`.
- **Gestión de perfil**: opciones de perfil, edición, solicitud de eliminación y revisión de solicitudes por Coordinador.

### Bloques documentados para revisión progresiva

- **Gestión de carga de trabajo**: consulta y edición de carga de trabajo.
- **Gestión de proyectos**: consulta, creación, edición, eliminación y asignación de investigadores.
- **Gestión de investigadores**: consulta, creación y eliminación de investigadores.
- **Gestión de entregables**: consulta y CRUD según permisos del actor.
- **Gestión de publicaciones**: consulta, respuesta y CRUD de publicaciones propias o globales.
- **Gestión de convocatorias**: consulta e importación de convocatorias.
- **Gestión de recompensas**: consulta y CRUD según permisos del Coordinador.

## Patrones de colaboración identificados

### Patrón de apertura

Casos `abrirXXX()` donde una vista solicita datos, el controlador coordina permisos y el repositorio devuelve entidades o listados.

### Patrón de edición

Casos `editarXXX()` donde una vista captura cambios, el controlador valida reglas del dominio y el repositorio persiste la actualización.

### Patrón de creación

Casos `crearXXX()` donde el sistema registra una nueva entidad y deriva después al estado de detalle o edición correspondiente.

### Patrón de eliminación segura

Casos `eliminarXXX()` donde la vista solicita confirmación, el controlador valida permisos y dependencias, y el repositorio ejecuta la eliminación.

### Patrón de solicitud de eliminación de perfil

El Investigador o Coordinador registra una solicitud pendiente. El Coordinador revisa la solicitud y puede ejecutar la eliminación, sin flujo de rechazo.

## Arquitectura emergente

### Separación de responsabilidades por capas

- **Capa de presentación**: clases Boundary como `LoginView`, `PanelPrincipalView`, `OpcionesPerfilView` o vistas de listado y detalle.
- **Capa de coordinación**: clases Control como `SesionController`, `PerfilController`, `ProyectoController` o controladores específicos por familia funcional.
- **Capa de dominio y datos**: entidades y repositorios como `Perfil`, `SolicitudEliminacionPerfil`, `Proyecto`, `Publicacion`, `Entregable` y sus repositorios.

### Controladores por familia funcional

- **Sesión**: autenticación, recuperación de sesión y cierre.
- **Perfil**: consulta, edición y solicitud de eliminación.
- **Solicitudes de eliminación**: listado, detalle y resolución por Coordinador.
- **Dominio investigador**: proyectos, publicaciones, entregables, carga de trabajo y recompensas.

### Repositorios especializados

- Repositorios por entidad principal.
- Consultas por identificador, usuario autenticado, criterio de búsqueda o estado pendiente.
- Validaciones de integridad y permisos trasladadas al controlador o servicio de aplicación en Diseño.

## Trazabilidad

### De Requisitos a Análisis

- Cada colaboración parte de un caso de uso especificado en [Detalle y prototipado](/RUP/00-casos-uso/02-detalle/README.md).
- Los estados de entrada y salida se mantienen alineados con los diagramas de contexto.
- Los nombres de acciones conservan el vocabulario definido en Requisitos.

### De Análisis a Diseño

- Las clases Boundary, Control y Entity sirven como base para páginas React, controladores REST, servicios y repositorios.
- Los diagramas de colaboración se refinan en diagramas de secuencia dentro de [Diseño](/RUP/02-diseño/README.md).
- Los bloques se trabajan de forma progresiva para revisar funcionalidad antes de implementar.

## Referencias

- [Casos de uso especificados](/RUP/00-casos-uso/02-detalle/README.md)
- [Casos de uso - Análisis MVC](casos-uso/README.md)
- [Coordinador](casos-uso/coordinador/README.md)
- [Investigador](casos-uso/investigador/README.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Log de conversaciones](/conversation-log.md)
