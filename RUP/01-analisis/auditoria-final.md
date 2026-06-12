# Auditoría Final de Análisis

## Propósito

Este documento recoge el bloque 10 de cierre transversal del Análisis. Su objetivo es comprobar que las colaboraciones de los casos de uso cubren los requisitos, respetan los diagramas de contexto y distribuyen sus responsabilidades de acuerdo con SOLID antes de cerrar el proyecto.

## Cobertura comprobada

|Elemento|Resultado|
|-|-|
|Casos de uso de Coordinador con Detalle y Análisis|44 de 44|
|Casos de uso de Investigador con Detalle y Análisis|27 de 27|
|Diagramas de colaboración disponibles|71 de 71|
|Entradas y salidas respaldadas por diagramas de contexto|Comprobadas|
|Bajas con conservación histórica|Clasificadas y documentadas|

Las nueve familias funcionales cubren todos los casos definidos en los diagramas de contexto:

1. Sesión y navegación.
2. Perfil.
3. Carga de trabajo.
4. Recompensas.
5. Proyectos.
6. Investigadores.
7. Entregables.
8. Publicaciones.
9. Convocatorias.

El bloque 10 no introduce casos de uso nuevos. Actúa como control final de coherencia antes de revisar Diseño, Desarrollo, Pruebas y despliegue.

## Aplicación de SOLID

### Responsabilidad única

- Las vistas reciben solicitudes, capturan información y presentan resultados.
- Los controladores coordinan cada conversación y sus alternativas.
- Las políticas y validadores concentran permisos y reglas variables cuando el dominio lo requiere.
- Los repositorios abstraen consultas y persistencia, sin asumir presentación ni navegación.

### Abierto y cerrado

- Los importadores de convocatorias pueden ampliarse mediante nuevas implementaciones.
- Los tipos de recompensa se modelan como reglas independientes.
- Las acciones disponibles del panel se obtienen mediante proveedores extensibles.
- Las políticas de autorización permiten incorporar nuevas reglas sin modificar las vistas.

### Sustitución, segregación e inversión

- Las colaboraciones dependen de responsabilidades conceptuales, no de tecnologías concretas.
- Los actores solo reciben las operaciones permitidas para su rol y contexto.
- Los controladores dependen de repositorios, políticas y validadores especializados en lugar de asumir todas sus responsabilidades.

## Decisiones transversales

- `eliminarPerfil()` desactiva el acceso y conserva el perfil histórico.
- `eliminarProyecto()` archiva el proyecto.
- `eliminarInvestigador()` desasigna la participación sin eliminar el perfil.
- Las publicaciones y entregables se retiran lógicamente.
- Las recompensas se anulan conservando su trazabilidad.
- Las convocatorias se importan y no disponen de CRUD manual.
- Los investigadores sin docencia solo pueden recibir recompensa económica.
- La reducción docente se concede en múltiplos de cuatro horas.

## Riesgos residuales

- La revisión documental no sustituye la regresión manual completa de todos los flujos.
- La auditoría detectó 27 enlaces de cabecera hacia documentos de Pruebas todavía no creados o alias históricos que deben canonizarse durante el cierre transversal.
- Las consultas administrativas de históricos pueden ampliarse en futuras iteraciones.
- El despliegue público y la configuración de secretos permanecen pendientes.

## Conclusión

El Análisis cubre completamente los casos de uso definidos y proporciona una base coherente para el Diseño y el Desarrollo existentes. Las extensiones variables se encuentran separadas de los coordinadores principales y las operaciones de eliminación conservan la trazabilidad requerida por el dominio.
