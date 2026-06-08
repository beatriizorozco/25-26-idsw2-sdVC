# FUNIBER > Coordinador > Crear Proyecto > Desarrollo

## Implementación

El formulario crea proyectos mediante `POST /api/proyectos`. El servidor valida código único, fechas, estado, sede y permisos del Coordinador.

## Código Relacionado

- [`ProyectoRequest.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/ProyectoRequest.java)
- [`ProyectoService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/ProyectoService.java)
