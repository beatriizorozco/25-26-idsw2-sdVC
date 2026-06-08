# FUNIBER > Casos de uso > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/README.md)|[Análisis](/RUP/01-analisis/casos-uso/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/README.md)|
> |-|-|-|-|-|-|-|

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

## Convenciones

Cada carpeta de caso de uso contiene:

- `README.md`: explicación del Diseño.
- `secuencia.puml`: diagrama de secuencia técnico.

Los SVG renderizados se almacenan en `/images/RUP/02-diseño/casos-uso`.

