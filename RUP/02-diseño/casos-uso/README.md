# FUNIBER > Casos de uso > Diseño

<div align="center">

[![](https://img.shields.io/badge/-Proceso_RUP-0A3B64?style=for-the-badge&logo=elsevier&logoColor=white)](/RUP/00-casos-uso/README.md)
[![](https://img.shields.io/badge/-Modelo_del_Dominio-0A3B64?style=for-the-badge&logo=diagramsdotnet&logoColor=white)](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
[![](https://img.shields.io/badge/-Casos_de_Uso-0A3B64?style=for-the-badge&logo=group&logoColor=white)](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
[![](https://img.shields.io/badge/-Diagramas_de_Contexto-0A3B64?style=for-the-badge&logo=flowchart&logoColor=white)](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)

[![](https://img.shields.io/badge/-Detalle-0A3B64?style=for-the-badge&logo=notepad&logoColor=white)](/RUP/00-casos-uso/02-detalle/README.md)
[![](https://img.shields.io/badge/-Análisis-0A3B64?style=for-the-badge&logo=multisim&logoColor=white)](/RUP/01-analisis/README.md)
[![](https://img.shields.io/badge/-Diseño-0A3B64?style=for-the-badge&logo=blueprint&logoColor=white)](/RUP/02-diseño/README.md)
[![](https://img.shields.io/badge/-Desarrollo-0A3B64?style=for-the-badge&logo=springboot&logoColor=white)](/RUP/03-desarrollo/README.md)
[![](https://img.shields.io/badge/-Pruebas-0A3B64?style=for-the-badge&logo=checkmarx&logoColor=white)](/RUP/04-pruebas/README.md)

[![](https://img.shields.io/badge/-Reuniones-0A3B64?style=for-the-badge&logo=googlemeet&logoColor=white)](/documents/reuniones.md)
[![](https://img.shields.io/badge/-Incidencias-0A3B64?style=for-the-badge&logo=bookstack&logoColor=white)](/incidencias_y_soluciones.md)
[![](https://img.shields.io/badge/-Log_de_conversación-0A3B64?style=for-the-badge&logo=gnometerminal&logoColor=white)](/conversation-log.md)

</div>

## Propósito

Organizar los diagramas de secuencia que convierten las colaboraciones de Análisis en interacciones técnicas concretas.

## Primera iteración

- [Coordinador](coordinador/README.md)
- [Investigador](investigador/README.md)

## Bloques diseñados

- **Gestión de sesión y navegación principal**: `iniciarSesion()`, `abrirPanelPrincipal()` y `cerrarSesion()`.
- **Gestión de perfil**: opciones de perfil, edición, solicitud de eliminación y revisión de solicitudes por Coordinador.
- **Gestión de carga de trabajo**: consulta y edición de cargas según rol, sede y condición docente.
- **Gestión de recompensas**: gestión global por Coordinador y consulta propia por Investigador para recompensas originadas por proyectos completados.
- **Gestión de proyectos**: gestión completa y composición de equipos por Coordinador, con consulta restringida de proyectos propios para Investigador.
- **Consulta e importación de convocatorias**: catálogo global, detalle e importación extensible por fuente para el Coordinador.

## Convenciones

Cada carpeta de caso de uso contiene:

- `README.md`: explicación del Diseño.
- `secuencia.puml`: diagrama de secuencia técnico.

Los SVG renderizados se almacenan en `/images/RUP/02-diseño/casos-uso`.

