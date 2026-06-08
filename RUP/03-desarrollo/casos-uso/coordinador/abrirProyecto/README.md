# FUNIBER > Coordinador > Abrir Proyecto > Desarrollo

> |[Inicio](/README.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirProyecto/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirProyecto/README.md)|**Desarrollo**|
> |-|-|-|-|-|

## Implementación

`GET /api/proyectos/{id}` recupera el detalle, estado y participantes activos. Los proyectos archivados permanecen consultables por el Coordinador.

El detalle incorpora los archivos adjuntos del proyecto. Coordinador e Investigadores participantes pueden listar, subir y descargar documentos mediante `/api/proyectos/{id}/archivos`; únicamente el Coordinador puede eliminarlos.

## Código Relacionado

- [`ProyectosPage.tsx`](/src/frontend/src/pages/ProyectosPage.tsx)
- [`ProyectoResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/ProyectoResponse.java)
- [`ArchivoProyectoController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/ArchivoProyectoController.java)
