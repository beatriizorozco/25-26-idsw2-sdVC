<h1 align="center">FUNIBER — Plataforma Interna de Investigación</h1>

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
# Actores y Casos de Uso

## Información del artefacto

- **Proyecto**: Plataforma Interna de Investigación de FUNIBER (GIPF)

---

## Introducción

Este documento describe los **actores del sistema** y los **casos de uso asociados** de la Plataforma Interna de Investigación de FUNIBER.  
El objetivo es definir de forma clara las **interacciones fundamentales** entre los actores *Coordinador* e *Investigador* y el sistema, mediante **casos de uso atómicos**, coherentes con los diagramas de contexto y alineados con un enfoque CRUD cuando aplica.

La identificación y estructuración de estos casos de uso sirve como base para la posterior **especificación detallada**, el **prototipado** y la validación funcional del sistema.

---

## Propósito

- Identificar los actores que interactúan con el sistema.
- Definir los casos de uso asociados a cada actor.
- Establecer claramente las responsabilidades de cada rol.
- Proporcionar una base estructurada para la especificación detallada de requisitos.
- Facilitar la trazabilidad entre actores, diagramas de contexto y casos de uso.

---

## Diagramas
- Diagrama de actores y casos de uso del **Investigador**:
  
![Diagrama de actores y casos de uso del Investigador](https://raw.githubusercontent.com/beatriizorozco/25-26-idsw2-sdVC/develop/images/RUP/00-casos-uso/01-actores-casos-uso/cdu-investigador.svg)

  - Código fuente: [especificacion.puml](/modelosUML/rup/00-casos-uso/01-actores-casos-uso/cdu-investigador.puml)
---
- Diagrama de actores y casos de uso del **Coordinador**:
  
![Diagrama de actores y casos de uso del Coordinador](https://raw.githubusercontent.com/beatriizorozco/25-26-idsw2-sdVC/develop/images/RUP/00-casos-uso/01-actores-casos-uso/cdu-coordinador.svg)

  - Código fuente: [especificacion.puml](/modelosUML/rup/00-casos-uso/01-actores-casos-uso/cdu-coordinador.puml)
---

  > Los **diagramas de contexto** se documentan en el apartado:  
  > [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)

---

## Actores identificados

| Actor | Descripción | Responsabilidades principales |
|--|--|--|
| **Investigador** | Usuario que participa activamente en proyectos de investigación y genera contenidos propios. | Gestionar su perfil y publicaciones.<br>Consultar proyectos propios y entregables.<br>Registrar y actualizar resultados de investigación. |
| **Coordinador** | Usuario con funciones de coordinación y supervisión del departamento de proyectos de investigación. | Supervisar proyectos y convocatorias.<br>Gestionar investigadores y su carga de trabajo.<br>Controlar recompensas, entregables y seguimiento global. |

---

## Casos de uso por actor

### Investigador

El Investigador opera sobre **información propia**, con acceso limitado a consultas globales cuando procede.

#### Gestión de sesión
- iniciarSesion()
- cerrarSesion()

#### Navegación general
- abrirPanelPrincipal()

#### Perfil
- abrirOpcionesPerfil()
- editarPerfil()
- solicitarEliminacionPerfil()

#### Carga de trabajo (individual)
- abrirOpcionesCargaTrabajo()
- editarCargaTrabajo()

#### Publicaciones (común)
- abrirPublicaciones()
- abrirPublicacion()
- responderPublicacion()

#### Mis publicaciones
- abrirMisPublicaciones()
- abrirMiPublicacion()
- crearPublicacion()
- editarPublicacion()
- eliminarPublicacion()

#### Proyectos propios
- abrirProyectos()
- abrirProyecto()
- abrirEntregables()
- abrirEntregable()
- crearEntregable()
- editarEntregable()
- eliminarEntregable()

#### Recompensas
- abrirRecompensas()
- abrirRecompensa()

#### Investigadores (consulta)
- abrirInvestigadores()
- abrirInvestigador()

---
### Coordinador

El Coordinador dispone de una visión **global del sistema** y puede actuar sobre todas las entidades relevantes del dominio.

#### Gestión de sesión
- iniciarSesion()
- cerrarSesion()

#### Navegación general
- abrirPanelPrincipal()

#### Perfil
- abrirOpcionesPerfil()
- editarPerfil()
- solicitarEliminacionPerfil()
- abrirSolicitudesEliminacionPerfil()
- abrirSolicitudEliminacionPerfil()

#### Carga de trabajo
- abrirOpcionesCargaTrabajo()
- editarCargaTrabajo()

#### Publicaciones
- abrirPublicaciones()
- abrirPublicacion()
- responderPublicacion()
- editarPublicacion()
- eliminarPublicacion()

#### Mis publicaciones
- abrirMisPublicaciones()
- abrirMiPublicacion()
- crearPublicacion()
- editarPublicacion()
- eliminarPublicacion()

#### Convocatorias
- abrirConvocatorias()
- abrirConvocatoria()
- importarConvocatoria()

#### Recompensas
- abrirRecompensas()
- abrirRecompensa()
- crearRecompensa()
- editarRecompensa()
- eliminarRecompensa()

#### Proyectos
- abrirProyectos()
- abrirProyecto()
- crearProyecto()
- editarProyecto()
- eliminarProyecto()
- agregarInvestigador()
- eliminarInvestigador()
- abrirEntregables()
- abrirEntregable()
- crearEntregable()
- editarEntregable()
- eliminarEntregable()

#### Investigadores
- abrirInvestigadores()
- abrirInvestigador()
- crearInvestigador()

---

## Características de los casos de uso

### Atomicidad
- Cada caso de uso representa una interacción completa actor–sistema.
- Tiene un inicio y un fin claramente definidos.
- Produce un resultado observable para el actor.
- No se descompone en subcasos de uso.

### Nomenclatura
- Los casos de uso siguen una convención basada en verbos en infinitivo.
- Los nombres reflejan directamente la acción realizada.
- Existe coherencia con el vocabulario del dominio del proyecto.

### Trazabilidad
- Todos los casos de uso están alineados con los diagramas de contexto.
- Cada transición de salida se corresponde con un estado existente.
- Los casos de uso detallados y los prototipos mantienen una correspondencia 1:1.

---

## Consideraciones de diseño

### Separación de responsabilidades
- **Coordinador**: control global, supervisión y toma de decisiones.
- **Investigador**: gestión de información propia y participación en proyectos.

### Cobertura funcional
Los casos de uso cubren el ciclo completo de la actividad investigadora:
1. Identificación de convocatorias.
2. Propuestas y proyectos.
3. Seguimiento de entregables.
4. Publicaciones y resultados.
5. Recompensas y carga de trabajo.

### Extensibilidad
La estructura definida permite:
- Incorporar nuevos roles.
- Añadir nuevos casos de uso.
- Ampliar funcionalidades sin afectar al núcleo del sistema.
