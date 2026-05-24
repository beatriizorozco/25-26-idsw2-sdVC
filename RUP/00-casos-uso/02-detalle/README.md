<h1 align="center">FUNIBER - Plataforma Interna de Investigación</h1>

<div align="center">

[![](https://img.shields.io/badge/-Inicio-0A3B64?style=for-the-badge&logo=github&logoColor=white)](/README.md)
[![](https://img.shields.io/badge/-Modelo_del_Dominio-0A3B64?style=for-the-badge&logo=drawio&logoColor=white)](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
[![](https://img.shields.io/badge/-Actores_y_casos_de_uso-0A3B64?style=for-the-badge&logo=group&logoColor=white)](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
[![](https://img.shields.io/badge/-Glosario-0A3B64?style=for-the-badge&logo=notepad&logoColor=white)](/documents/vocabulario.md)

[![](https://img.shields.io/badge/-Detallado_y_Prototipos-0A3B64?style=for-the-badge&logo=notepad&logoColor=white)](/RUP/00-casos-uso/02-detalle/README.md)
[![](https://img.shields.io/badge/-Diagrama_de_Contexto-0A3B64?style=for-the-badge&logo=flowchart&logoColor=white)](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
[![](https://img.shields.io/badge/-Reuniones-0A3B64?style=for-the-badge&logo=google-meet&logoColor=white)](/documents/reuniones.md)
[![](https://img.shields.io/badge/-Priorización-0A3B64?style=for-the-badge&logo=priority&logoColor=white)](/RUP/00-casos-uso/01-actores-casos-uso/priorizacion-cdu.md)
[![](https://img.shields.io/badge/-Rúbrica-0A3B64?style=for-the-badge&logo=checklist&logoColor=white)](https://github.com/mmasias/25-26-IdSw1-SdR/blob/main/documents/l'Rubrica.md)

[![](https://img.shields.io/badge/-Log_de_conversación-0A3B64?style=for-the-badge&logo=gnometerminal&logoColor=white)](/conversation-log.md)

</div>

# Detalle y Prototipo de Casos de Uso

Esta carpeta contiene la especificación detallada y el prototipado de los casos de uso de la Plataforma Interna de Investigación de FUNIBER. Los casos están organizados por actor para distinguir las responsabilidades del Coordinador y del Investigador.

## Casos de uso especificados

### Coordinador

#### Gestión del sistema
- [iniciarSesion](coordinador/iniciarSesion/) - Autenticación del Coordinador.
- [cerrarSesion](coordinador/cerrarSesion/) - Cierre de sesión.
- [abrirPanelPrincipal](coordinador/abrirPanelPrincipal/) - Acceso al panel principal del sistema.

#### Perfil y carga de trabajo
- [abrirOpcionesPerfil](coordinador/abrirOpcionesPerfil/) - Acceso a las opciones del perfil.
- [editarPerfil](coordinador/editarPerfil/) - Actualización de datos del perfil.
- [solicitarEliminacionPerfil](coordinador/solicitarEliminacionPerfil/) - Solicitud de eliminación del perfil propio.
- [eliminarPerfil](coordinador/eliminarPerfil/) - Eliminación de perfil.
- [abrirSolicitudesEliminacionPerfil](coordinador/abrirSolicitudesEliminacionPerfil/) - Consulta de solicitudes de eliminación.
- [abrirSolicitudEliminacionPerfil](coordinador/abrirSolicitudEliminacionPerfil/) - Revisión de una solicitud de eliminación.
- [abrirOpcionesCargaTrabajo](coordinador/abrirOpcionesCargaTrabajo/) - Acceso a opciones de carga de trabajo.
- [editarCargaTrabajo](coordinador/editarCargaTrabajo/) - Actualización de carga de trabajo.

#### Investigadores
- [abrirInvestigadores](coordinador/abrirInvestigadores/) - Consulta del listado de investigadores.
- [abrirInvestigador](coordinador/abrirInvestigador/) - Consulta del detalle de un investigador.
- [crearInvestigador](coordinador/crearInvestigador/) - Alta de investigador.
- [agregarInvestigador](coordinador/agregarInvestigador/) - Asociación de investigador a un proyecto.
- [eliminarInvestigador](coordinador/eliminarInvestigador/) - Eliminación de investigador.

#### Convocatorias
- [abrirConvocatorias](coordinador/abrirConvocatorias/) - Consulta de convocatorias.
- [abrirConvocatoria](coordinador/abrirConvocatoria/) - Consulta del detalle de una convocatoria.
- [importarConvocatoria](coordinador/importarConvocatoria/) - Importación de convocatoria.

#### Proyectos
- [abrirProyectos](coordinador/abrirProyectos/) - Consulta del listado de proyectos.
- [abrirProyecto](coordinador/abrirProyecto/) - Consulta del detalle de un proyecto.
- [crearProyecto](coordinador/crearProyecto/) - Creación de proyecto.
- [editarProyecto](coordinador/editarProyecto/) - Edición de proyecto.
- [eliminarProyecto](coordinador/eliminarProyecto/) - Eliminación de proyecto.

#### Entregables
- [abrirEntregables](coordinador/abrirEntregables/) - Consulta de entregables.
- [abrirEntregable](coordinador/abrirEntregable/) - Consulta del detalle de un entregable.
- [crearEntregable](coordinador/crearEntregable/) - Creación de entregable.
- [editarEntregable](coordinador/editarEntregable/) - Edición de entregable.
- [eliminarEntregable](coordinador/eliminarEntregable/) - Eliminación de entregable.

#### Publicaciones
- [abrirPublicaciones](coordinador/abrirPublicaciones/) - Consulta de publicaciones generales.
- [abrirPublicacion](coordinador/abrirPublicacion/) - Consulta del detalle de una publicación.
- [responderPublicacion](coordinador/responderPublicacion/) - Respuesta a una publicación.
- [editarPublicacion](coordinador/editarPublicacion/) - Edición de publicación.
- [eliminarPublicacion](coordinador/eliminarPublicacion/) - Eliminación de publicación.
- [abrirMisPublicaciones](coordinador/abrirMisPublicaciones/) - Consulta de publicaciones propias.
- [abrirMiPublicacion](coordinador/abrirMiPublicacion/) - Consulta del detalle de una publicación propia.
- [crearPublicacion](coordinador/crearPublicacion/) - Creación de publicación propia.
- [editarMiPublicacion](coordinador/editarMiPublicacion/) - Edición de publicación propia.
- [eliminarMiPublicacion](coordinador/eliminarMiPublicacion/) - Eliminación de publicación propia.

#### Recompensas
- [abrirRecompensas](coordinador/abrirRecompensas/) - Consulta de recompensas.
- [abrirRecompensa](coordinador/abrirRecompensa/) - Consulta del detalle de una recompensa.
- [crearRecompensa](coordinador/crearRecompensa/) - Creación de recompensa.
- [editarRecompensa](coordinador/editarRecompensa/) - Edición de recompensa.
- [eliminarRecompensa](coordinador/eliminarRecompensa/) - Eliminación de recompensa.

### Investigador

#### Gestión del sistema
- [iniciarSesion](investigador/iniciarSesion/) - Autenticación del Investigador.
- [cerrarSesion](investigador/cerrarSesion/) - Cierre de sesión.
- [abrirPanelPrincipal](investigador/abrirPanelPrincipal/) - Acceso al panel principal del sistema.

#### Perfil y carga de trabajo
- [abrirOpcionesPerfil](investigador/abrirOpcionesPerfil/) - Acceso a las opciones del perfil.
- [editarPerfil](investigador/editarPerfil/) - Actualización de datos del perfil.
- [solicitarEliminacionPerfil](investigador/solicitarEliminacionPerfil/) - Solicitud de eliminación del perfil propio.
- [abrirOpcionesCargaTrabajo](investigador/abrirOpcionesCargaTrabajo/) - Acceso a opciones de carga de trabajo.
- [editarCargaTrabajo](investigador/editarCargaTrabajo/) - Actualización de carga de trabajo.

#### Proyectos y entregables
- [abrirProyectos](investigador/abrirProyectos/) - Consulta de proyectos asociados.
- [abrirProyecto](investigador/abrirProyecto/) - Consulta del detalle de un proyecto.
- [abrirEntregables](investigador/abrirEntregables/) - Consulta de entregables.
- [abrirEntregable](investigador/abrirEntregable/) - Consulta del detalle de un entregable.
- [crearEntregable](investigador/crearEntregable/) - Creación de entregable.
- [editarEntregable](investigador/editarEntregable/) - Edición de entregable.
- [eliminarEntregable](investigador/eliminarEntregable/) - Eliminación de entregable.

#### Publicaciones
- [abrirPublicaciones](investigador/abrirPublicaciones/) - Consulta de publicaciones generales.
- [abrirPublicacion](investigador/abrirPublicacion/) - Consulta del detalle de una publicación.
- [responderPublicacion](investigador/responderPublicacion/) - Respuesta a una publicación.
- [abrirMisPublicaciones](investigador/abrirMisPublicaciones/) - Consulta de publicaciones propias.
- [abrirMiPublicacion](investigador/abrirMiPublicacion/) - Consulta del detalle de una publicación propia.
- [crearPublicacion](investigador/crearPublicacion/) - Creación de publicación propia.
- [editarPublicacion](investigador/editarPublicacion/) - Edición de publicación propia.
- [eliminarPublicacion](investigador/eliminarPublicacion/) - Eliminación de publicación propia.

#### Recompensas e investigadores
- [abrirRecompensas](investigador/abrirRecompensas/) - Consulta de recompensas.
- [abrirRecompensa](investigador/abrirRecompensa/) - Consulta del detalle de una recompensa.
- [abrirInvestigadores](investigador/abrirInvestigadores/) - Consulta del listado de investigadores.
- [abrirInvestigador](investigador/abrirInvestigador/) - Consulta del detalle de un investigador.

## Estructura de cada caso de uso

Cada carpeta de caso de uso contiene:

- **README.md** - Especificación visual del caso de uso y enlaces a sus artefactos.
- **especificacion.puml** - Diagrama de especificación en PlantUML.
- **prototipo.puml** - Wireframe de prototipado en PlantUML/Salt.

Los diagramas renderizados se mantienen en `/images/RUP/00-casos-uso/02-detalle/` y los modelos fuente se mantienen en `/modelosUML/rup/00-casos-uso/02-detalle/`, siguiendo la separación de artefactos del proyecto.

## Metodología aplicada

- **Filosofía C->U** - Los casos de creación se vinculan con la apertura o edición posterior de la entidad creada.
- **Crear mínimo y editar completo** - La creación solicita la información imprescindible y la edición concentra el mantenimiento posterior.
- **Separación por actor** - El Coordinador dispone de acciones globales y el Investigador opera principalmente sobre información propia o consultable.
- **Leyes del proyecto** - Vocabulario restringido, conversación actor-sistema y diseño sin detalles de implementación.
- **Tecnología agnóstica** - Las especificaciones describen comportamiento y prototipo sin depender del stack técnico.
