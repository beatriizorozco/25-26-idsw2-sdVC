# Análisis del resultado frente a la asignatura

## Propósito

Este documento evalúa el resultado construido frente a los contenidos de Diseño de Software: diseño general, diseño modular y diseño orientado a objetos mediante SOLID. El análisis se apoya en el código, los modelos UML, la trazabilidad RUP, las pruebas y el historial de construcción.

## Resultado construido

GIPF es una plataforma web funcional para coordinar la actividad investigadora de FUNIBER. Diferencia los permisos del Coordinador y del Investigador y cubre sesión, perfil, carga de trabajo, recompensas, proyectos, investigadores, entregables, publicaciones y convocatorias importadas.

La solución utiliza React y TypeScript en presentación, Spring Boot en backend, repositorios JPA para persistencia, Flyway para evolución del esquema y SQLite en ejecución local. Los casos de uso enlazan Requisitos, Análisis, Diseño, Desarrollo y Pruebas.

## Diseño modular

### Acoplamiento

El backend reduce el acoplamiento mediante una arquitectura por capas. Los controladores HTTP delegan en servicios, y estos acceden a persistencia mediante interfaces de repositorio. Las reglas variables se extraen a políticas, proveedores, registros y estrategias inyectadas.

Ejemplos:

- `ImportadorConvocatoria` permite añadir fuentes de importación sin acoplarlas al controlador.
- `ReglaTipoRecompensa` separa la elegibilidad de cada recompensa.
- `ProveedorAccionesPanel` separa las acciones disponibles según rol.
- Los repositorios Spring Data aíslan la lógica de aplicación de las consultas y la base de datos.

El principal riesgo residual está en el frontend: `api.ts`, `types.ts` y algunas páginas concentran varias operaciones de una familia funcional. Funcionan, pero una evolución grande aconsejaría dividirlos por característica.

### Cohesión

Los paquetes backend están agrupados por responsabilidad técnica y de dominio: controladores, servicios, modelos, repositorios, políticas e importadores. Cada clase especializada mantiene una razón de cambio reconocible.

La cohesión es menor en páginas frontend extensas como proyectos, recompensas y carga de trabajo, porque combinan carga de datos, estado del formulario y representación. Es una deuda conocida, no un impedimento funcional.

### Tamaño

La mayoría de controladores y componentes tienen un tamaño contenido. Los módulos de mayor tamaño corresponden a coordinadores de dominio con más alternativas:

|Módulo|Tamaño aproximado|Valoración|
|-|-|-|
|`RecompensaService.java`|282 líneas|Aceptable, aunque candidato a separar más operaciones|
|`ProyectoService.java`|224 líneas|Coordina varias reglas del agregado Proyecto|
|`ProyectosPage.tsx`|514 líneas|Candidato prioritario a dividir en componentes y hooks|
|`api.ts`|509 líneas|Candidato a separar por familia funcional|
|`RecompensasPage.tsx`|423 líneas|Candidato a dividir por flujos de consulta y edición|

El tamaño no se ha reducido mediante fragmentación artificial: las separaciones existentes responden a responsabilidades o variaciones reales.

## Diseño orientado a objetos y SOLID

|Principio|Aplicación en el proyecto|Limitación observada|
|-|-|-|
|Responsabilidad única|Controladores coordinan HTTP; servicios aplican casos; repositorios persisten; políticas autorizan; importadores transforman fuentes.|Algunos servicios y páginas todavía coordinan muchos subflujos.|
|Abierto/cerrado|Importadores, reglas de recompensa y proveedores de acciones se amplían mediante nuevas implementaciones.|No todas las variaciones simples justifican todavía una interfaz propia.|
|Sustitución de Liskov|Las implementaciones de importadores, reglas y proveedores respetan sus contratos y pueden sustituirse desde sus registros.|La cobertura automática se centra más en comportamiento HTTP que en contratos unitarios.|
|Segregación de interfaces|Las interfaces especializadas exponen responsabilidades pequeñas y concretas.|Spring Data aporta interfaces amplias en algunos repositorios por conveniencia.|
|Inversión de dependencias|Los servicios reciben repositorios, políticas y estrategias mediante inyección de dependencias.|El frontend depende directamente del módulo común `api.ts`.|

## Calidad funcional y pruebas

La regresión automática del 12 de junio de 2026 produjo:

- `48` pruebas backend correctas, sin fallos ni errores.
- Compilación de producción del frontend correcta.
- Lint frontend sin errores y con un aviso no bloqueante en `ConvocatoriasPage.tsx`.
- `11` migraciones Flyway aplicadas durante las pruebas de integración.

Las pruebas cubren sesión, perfil, carga de trabajo, recompensas, proyectos, investigadores, entregables, publicaciones y convocatorias. Además, durante el desarrollo se realizaron recorridos manuales incrementales documentados mediante capturas.

## Proceso y trazabilidad

- El repositorio conserva un historial incremental de commits.
- `conversation-log.md` documenta decisiones, correcciones, incidencias y cierres de sesión.
- Los `.puml` se conservan en `modelosUML` y sus representaciones SVG en `images`.
- Los casos de uso mantienen relaciones entre requisitos, análisis, diseño y desarrollo.

## Limitaciones y riesgos honestos

- `QUE_HACE.md` estuvo presente en el primer commit, pero fue completado en un commit posterior. Esto incumple literalmente la regla de no modificarlo después; corregirlo requeriría reescribir el historial, lo que sería menos honesto.
- Falta preparar un despliegue público reproducible para acceder a la aplicación sin ejecutar el entorno local.
- Existen enlaces de cabecera hacia documentos de Pruebas por caso de uso todavía no creados o heredados.
- La regresión automática no sustituye una última prueba manual completa de todos los recorridos.
- Algunos módulos frontend son grandes y deberían separarse si el producto continúa creciendo.

## Conclusión

El proyecto cumple de forma sólida los objetivos funcionales y demuestra la aplicación consciente de diseño modular y SOLID. La arquitectura backend presenta responsabilidades claras, bajo acoplamiento respecto a persistencia y extensiones reales mediante OCP. Las principales mejoras pendientes están en el despliegue, la documentación de pruebas por caso de uso y la modularización futura del frontend.
