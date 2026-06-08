# FUNIBER > Coordinador > Editar Proyecto > Desarrollo

## Implementación

`PATCH /api/proyectos/{id}` actualiza únicamente proyectos activos y vuelve a validar código y fechas. Al marcar un proyecto como completado, el sistema lo archiva automáticamente y conserva su información histórica. Los proyectos archivados quedan protegidos frente a cambios operativos.

## Código Relacionado

- [`ProyectoService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/ProyectoService.java)
- [`Proyecto.java`](/src/backend/src/main/java/es/funiber/investigacion/model/Proyecto.java)
