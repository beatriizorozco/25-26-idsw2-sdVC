# Política de bajas, archivado y conservación histórica

Esta política define el significado funcional de los casos de uso cuyo nombre histórico comienza por `eliminar...`. El nombre se conserva para mantener la trazabilidad con los diagramas de contexto, pero no implica necesariamente un borrado físico.

## Principio general

La plataforma debe conservar la información necesaria para reconstruir la actividad investigadora, justificar decisiones y auditar relaciones históricas. Una entidad con actividad, relaciones o valor probatorio no se elimina físicamente.

## Clasificación

|Caso de uso|Operación funcional|Información conservada|
|-|-|-|
|`eliminarPerfil()`|Desactivar el perfil y revocar su acceso|Datos del perfil, proyectos, publicaciones, entregables, recompensas, solicitudes y actividad histórica|
|`eliminarProyecto()`|Archivar el proyecto|Estado, equipo, entregables, publicaciones, recompensas y trazabilidad completa|
|`eliminarInvestigador()`|Marcar la participación como desasignada|Perfil del investigador, periodo de pertenencia e historial de participación|
|`eliminarPublicacion()`|Retirar o archivar la publicación|Autoría, proyecto relacionado, metadatos y motivo de retirada|
|`eliminarMiPublicacion()`|Retirar o archivar la publicación propia|Autoría, proyecto relacionado, metadatos y motivo de retirada|
|`eliminarEntregable()`|Retirar el entregable de la gestión activa|Autoría, proyecto, versiones, estado y motivo de retirada|
|`eliminarRecompensa()`|Anular la recompensa|Beneficiario, proyecto, tipo, valor, concesión y motivo de anulación|

## Reglas comunes

- Toda baja, retirada, anulación, desasignación o archivado requiere confirmación explícita.
- La operación registra fecha, actor responsable y motivo cuando corresponda.
- Los listados operativos excluyen los elementos inactivos, retirados, anulados o archivados.
- El histórico solo es accesible para actores autorizados.
- Las relaciones históricas no deben romperse.
- El borrado físico queda reservado para datos erróneos sin relaciones ni valor histórico, mediante una operación administrativa excepcional fuera de los casos de uso ordinarios.

## Estados resultantes

- **Activo**: disponible para la operativa habitual.
- **Inactivo**: perfil sin acceso, conservado para consulta histórica.
- **Archivado**: proyecto fuera de la gestión activa.
- **Retirado**: publicación o entregable fuera de la operativa activa.
- **Anulado**: recompensa sin vigencia, conservada para auditoría.
- **Desasignado**: investigador retirado de un proyecto sin afectar a su perfil.

## Trazabilidad

Los casos de uso mantienen sus nombres históricos para evitar romper enlaces, diagramas y referencias existentes. Cada especificación debe explicar expresamente la operación funcional realizada.
