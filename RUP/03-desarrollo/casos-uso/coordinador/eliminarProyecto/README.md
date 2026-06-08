# FUNIBER > Coordinador > Eliminar Proyecto > Desarrollo

## Implementación

La eliminación es una baja lógica. `PATCH /api/proyectos/{id}/archivado` exige motivo, registra fecha y Coordinador responsable, y marca el proyecto como completado. El proyecto desaparece del listado activo y permanece en el histórico con sus participantes y archivos adjuntos.

## Código Relacionado

- [`V7__gestion_historica_proyectos.sql`](/src/backend/src/main/resources/db/migration/V7__gestion_historica_proyectos.sql)
- [`Proyecto.java`](/src/backend/src/main/java/es/funiber/investigacion/model/Proyecto.java)
