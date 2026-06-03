# FUNIBER > Investigador > solicitarEliminacionPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El Investigador solicita la eliminación desde un modal con motivo obligatorio. El backend registra la solicitud pendiente, evita duplicados y cierra la sesión después de confirmar.

## Código relacionado

- [`PerfilPage.tsx`](/src/frontend/src/pages/PerfilPage.tsx)
- [`PerfilController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/PerfilController.java)
- [`SolicitudEliminacionPerfil.java`](/src/backend/src/main/java/es/funiber/investigacion/model/SolicitudEliminacionPerfil.java)
- [`SolicitudEliminacionPerfilRepository.java`](/src/backend/src/main/java/es/funiber/investigacion/repository/SolicitudEliminacionPerfilRepository.java)
