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

# Casos de Uso - Disciplina de Requisitos

Esta sección contiene la especificación de casos de uso de la Plataforma Interna de Investigación de FUNIBER, siguiendo la disciplina de Requisitos del proceso RUP.

## Contenido de la disciplina

### [00 - Modelo del dominio](00-modelo-del-dominio/modelo-dominio.md)
Representa las entidades conceptuales del dominio del problema y sus relaciones, independiente de cualquier implementación tecnológica. El modelo se centra en la gestión de proyectos de investigación, convocatorias, entregables, publicaciones, recompensas e investigadores.

### [01 - Actores y casos de uso](01-actores-casos-uso/actores-casos-uso.md)
- **Identificación de actores**: Coordinador e Investigador como usuarios principales del sistema.
- **Casos de uso del sistema**: Funcionalidades principales organizadas por actor y por área funcional.
- **[Diagramas de contexto](01-actores-casos-uso/diagramas-contexto.md)**: Estados, transiciones y navegación principal del sistema para cada actor.
- **[Priorización de casos de uso](01-actores-casos-uso/priorizacion-cdu.md)**: Criterios de importancia y organización del trabajo funcional.

### [02 - Detalle y prototipado](02-detalle/README.md)
Especificación completa de cada caso de uso incluyendo:
- **Diagramas de especificación**: Conversación detallada entre actor y sistema.
- **Wireframes de prototipado**: Interfaces de usuario tecnología-agnósticas.
- **Trazabilidad por actor**: 44 casos de uso del Coordinador y 27 casos de uso del Investigador.
- **Vocabulario restringido**: Aplicación del lenguaje común definido para el proyecto.

## Metodología aplicada

### Filosofía C->U (Crear -> Usar)
- **Crear mínimo**: Los casos de creación recogen únicamente la información necesaria para iniciar una entidad.
- **Editar completo**: Los casos de edición concentran la gestión continua de la entidad creada.
- **Vinculación natural**: Crear una entidad conduce a su posterior apertura o edición cuando el flujo lo requiere.

### Patrones de casos de uso
- **Apertura de entidades**: Presentación de listas para seleccionar proyectos, convocatorias, publicaciones, entregables, investigadores o recompensas.
- **CRUD completo**: Crear, abrir, editar y eliminar entidades cuando el rol dispone de permisos.
- **Gestión diferenciada por rol**: El Coordinador opera con visión global; el Investigador trabaja principalmente sobre información propia.
- **Gestión de proyectos**: Seguimiento de proyectos, entregables, investigadores asociados, carga de trabajo y recompensas.
- **Red de investigación**: Consulta de investigadores, perfiles, publicaciones y conversación básica sobre publicaciones.

### Leyes del proyecto aplicadas
- **Vocabulario puro**: Uso de expresiones como "solicita", "presenta", "permite" y "registra".
- **Tecnología agnóstica**: Sin referencias a frameworks, base de datos o componentes concretos de implementación.
- **Separación de responsabilidades**: Actor y sistema aparecen diferenciados en cada especificación.
- **Coherencia documental**: Los casos de uso enlazan con modelos UML, prototipos y artefactos gráficos del repositorio.

## Cobertura funcional

El proyecto cubre:
- **Gestión de sesión**: Inicio, cierre y acceso al panel principal.
- **Gestión de perfil**: Edición de perfil y solicitud de eliminación.
- **Gestión de proyectos**: Consulta, creación, edición, eliminación y asociación de investigadores.
- **Gestión de convocatorias**: Consulta e importación de convocatorias.
- **Gestión de entregables**: Creación, edición, consulta y eliminación de entregables de proyecto.
- **Gestión de publicaciones**: Publicaciones generales, publicaciones propias y respuestas.
- **Gestión de recompensas**: Consulta y administración de recompensas asociadas al trabajo de investigación.
- **Gestión de carga de trabajo**: Registro y actualización de disponibilidad o dedicación.
- **Gestión de investigadores**: Consulta, alta, eliminación y revisión de perfiles.

## Referencias

- [Modelo del dominio](00-modelo-del-dominio/modelo-dominio.md)
- [Actores y casos de uso](01-actores-casos-uso/actores-casos-uso.md)
- [Diagramas de contexto](01-actores-casos-uso/diagramas-contexto.md)
- [Detalle y prototipado](02-detalle/README.md)
- [Glosario del proyecto](/documents/vocabulario.md)
- [Log de conversaciones](/conversation-log.md)
