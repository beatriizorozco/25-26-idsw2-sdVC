# Incidencias y soluciones

## Un investigador-docente solo muestra recompensa económica al crear

**Síntoma:** Al seleccionar un investigador-docente, el selector de tipo puede
mostrar únicamente la recompensa económica.

**Causa:** Ese beneficiario ya tiene concedida una reducción docente para el
proyecto seleccionado. El sistema evita duplicar una recompensa del mismo tipo
para la misma combinación de proyecto y beneficiario.

**Solución:** El formulario explica que solo presenta tipos todavía pendientes.
Los datos demo incluyen además `PRY-SAN-COM-02`, un proyecto completado sin
recompensas previas que permite probar la creación de una reducción docente.

## La reducción docente admitía horas que no representaban asignaturas completas

**Síntoma:** Era posible registrar reducciones docentes como 2 o 6 horas.

**Causa:** El valor de la recompensa se trataba como una cantidad decimal libre,
aunque cada asignatura representa 4 horas semanales.

**Solución:** El frontend ofrece únicamente 4, 8, 12 o 16 horas. El backend
valida la regla y Flyway añade una restricción de base de datos, normalizando
previamente los datos antiguos incompatibles.

## El proxy de Vite devuelve `ECONNREFUSED` al probar un bloque

**Síntoma:** La interfaz abre en el puerto `5173`, pero Vite muestra errores
`http proxy error: /api/auth/csrf` y `ECONNREFUSED`.

**Causa:** El frontend se iniciaba sin que el backend Spring Boot estuviera
disponible en el puerto `8080`. El procedimiento anterior requería mantener dos
terminales y no garantizaba el orden de arranque.

**Solución:** `npm run dev` ejecuta `tools/start-dev.ps1`, que reutiliza el
backend si ya responde o lo inicia automáticamente y espera antes de arrancar
Vite. `npm run dev:frontend` queda reservado para depurar solo el frontend.

Este documento registra problemas encontrados durante el desarrollo de la Plataforma Interna de Investigación de FUNIBER, su causa y la solución aplicada. Su objetivo es conservar el razonamiento técnico del proyecto y facilitar futuras revisiones.

## Incidencias resueltas

### Error 403 al iniciar sesión desde el navegador integrado

**Síntoma:** El inicio de sesión funcionaba desde `http://localhost:5173`, pero devolvía `403 Forbidden` al abrir la aplicación desde `http://127.0.0.1:5173`.

**Causa:** La configuración CORS del backend solo autorizaba el origen `http://localhost:5173`. Aunque ambos apuntan al mismo equipo, el navegador los considera orígenes distintos.

**Solución:** Se permitió configurar varios orígenes mediante `FRONTEND_ORIGINS` y se añadieron los dos orígenes locales por defecto:

```properties
http://localhost:5173,http://127.0.0.1:5173
```

**Validación:** Se añadió una prueba de integración que comprueba el acceso desde `http://127.0.0.1:5173`.

### Token CSRF no actualizado antes de una mutación

**Síntoma:** Algunas peticiones protegidas podían fallar si reutilizaban un token CSRF anterior.

**Causa:** El frontend conservaba temporalmente el token obtenido en una petición previa. Tras crear o modificar una sesión, Spring Security puede renovar ese token.

**Solución:** El frontend solicita un token CSRF vigente antes de iniciar o cerrar sesión y renueva el token tras autenticar correctamente.

**Validación:** Se comprobó el inicio de sesión, el reintento tras credenciales incorrectas y el cierre de sesión mediante la suite backend y recorridos manuales.

### Mensaje técnico visible tras introducir credenciales incorrectas

**Síntoma:** La interfaz mostraba `Credenciales incorrectas (401 /auth/login)`.

**Causa:** Durante el diagnóstico se añadió temporalmente información HTTP al mensaje mostrado al usuario.

**Solución:** Se mantuvo el detalle técnico únicamente para depuración y se restauró el mensaje visible:

```text
Credenciales incorrectas
```

**Validación:** Se actualizó la captura de evidencia del formulario con credenciales inválidas.

### Hueco visual en el panel del Investigador

**Síntoma:** El panel del Investigador tenía siete módulos y dejaba una celda vacía al final de la cuadrícula de dos columnas.

**Causa:** La cuadrícula no contemplaba el caso de una cantidad impar de módulos.

**Solución:** El último módulo ocupa ambas columnas cuando es el último elemento impar.

**Validación:** Se sustituyó la captura del panel del Investigador con la nueva distribución.

### Diagramas de Diseño desalineados con la implementación

**Síntoma:** Los diagramas indicaban que el token CSRF se obtenía antes de presentar el formulario y afirmaban que el cierre de sesión expiraba la cookie.

**Causa:** El Diseño inicial reflejaba una aproximación previa al código definitivo.

**Solución:** Se actualizaron los diagramas de secuencia y sus README:

- El formulario se presenta antes de solicitar el token CSRF necesario para enviar credenciales.
- El cierre solicita un token vigente antes de ejecutar `POST /api/auth/logout`.
- La documentación garantiza la invalidación de la sesión del servidor sin afirmar que la cookie expira.
- El atributo `Secure` de la cookie se documenta como obligatorio en producción mediante HTTPS.

**Validación:** Se regeneraron los cuatro SVG afectados con PlantUML.

### Navegación documental incompleta

**Síntoma:** Algunos README del primer bloque enlazaban únicamente a índices generales o no incluían acceso directo a Pruebas.

**Causa:** Las cabeceras se habían creado antes de completar todas las disciplinas RUP.

**Solución:** Se añadieron enlaces directos por caso de uso entre Detalle, Análisis, Diseño, Desarrollo y Pruebas.

**Validación:** Se comprobaron las rutas internas modificadas y no quedan enlaces rotos en el bloque revisado.

### Credenciales correctas rechazadas por backend desactualizado

**Síntoma:** Las credenciales documentadas para `coordinador`, `investigador`, `docente.santander` e `investigador.barcelona` podían devolver error de autenticación aunque el usuario y la contraseña fueran correctos.

**Causa:** Seguía ejecutándose un proceso antiguo del backend. El frontend estaba enviando las peticiones a una versión previa de la aplicación, sin los datos demo y reglas actualizadas. Además, los usuarios demo ya existentes en la base H2 persistente podían conservar una contraseña anterior o un estado inactivo, porque `DemoDataConfig` solo actualizaba la sede cuando encontraba un usuario creado previamente.

**Solución:** Se detuvo el proceso anterior de Java, se arrancó de nuevo el backend desde `src/backend` y se verificó que Spring Boot cargara la versión actual del código. También se ajustó `DemoDataConfig` para que, en cada arranque, los usuarios demo existentes actualicen contraseña, perfil y sede, pero respeten su estado activo o eliminado. La entidad `Usuario` conserva métodos explícitos para actualizar el hash y administrar el estado cuando un flujo funcional lo requiera.

**Validación:** Se comprobó por API el inicio de sesión y la carga de trabajo con:

- `coordinador / coordinador123`
- `docente.santander / docente123`
- `investigador.barcelona / barcelona123`

Después de reiniciar el backend, un usuario demo activo conserva credenciales actualizadas y `GET /api/carga-trabajo/me` devuelve Barcelona como investigador sin docencia por sede. Un perfil eliminado no se reactiva automáticamente.

### Proyectos completados sin recompensas pendientes seguían apareciendo

**Síntoma:** El formulario de creación mostraba todos los proyectos completados, incluso cuando todos sus participantes ya habían recibido cada tipo de recompensa permitido.

**Causa:** `RecompensaService.prepararCreacion()` filtraba únicamente por estado `COMPLETADO`, sin comprobar las combinaciones pendientes de proyecto, beneficiario y tipo.

**Solución:** Las opciones de creación ahora incluyen solo proyectos con alguna combinación válida pendiente. También se excluyen beneficiarios que ya han agotado sus tipos permitidos y el frontend consulta los tipos disponibles al seleccionar cada participante.

**Validación:** Se ampliaron las pruebas de integración para comprobar la exclusión progresiva de beneficiarios y la desaparición del proyecto cuando ya no quedan recompensas pendientes.

### Regla de investigadores-docentes interpretada como compensación por exceso

**Síntoma:** El bloque de carga de trabajo se había modelado inicialmente como si un investigador-docente pudiera superar 16 horas semanales de docencia y recibir una compensación económica por ese exceso.

**Causa:** La regla de dominio estaba incompleta. La interpretación correcta es que un investigador-docente no debe superar 16 horas semanales de docencia. Las recompensas no se originan por exceso de carga docente, sino al completar proyectos.

**Solución:** Se corrigió el Análisis, Diseño, Desarrollo y código del bloque 3:

- La carga docente queda limitada a 16 horas semanales cuando la sede aplica docencia investigadora.
- Las sedes diferencian investigadores-docentes e investigadores sin docencia.
- Santander se usa como sede con docencia investigadora.
- Barcelona se usa como sede sin docencia investigadora.
- Las recompensas se separan del caso de carga de trabajo y quedan asociadas a proyectos completados.

**Validación:** Se añadió prueba de integración para rechazar más de 16 horas docentes en `docente.santander`. También se comprobó por API que `docente.santander` aparece como investigador-docente con margen docente y `investigador.barcelona` aparece como investigador sin docencia.

### Investigador de sede sin docencia podía declarar horas docentes

**Síntoma:** Un investigador de Barcelona podía llegar a intentar registrar horas de docencia, aunque Barcelona está modelada como sede sin docencia investigadora.

**Causa:** La restricción de 16 horas solo se aplicaba a investigadores-docentes. Para investigadores sin docencia por sede no existía una prohibición explícita de docencia mayor que cero.

**Solución:** Se bloqueó el campo de docencia en el frontend cuando el perfil no es investigador-docente y se añadió validación backend para rechazar cualquier hora docente en sedes sin docencia. En investigadores-docentes, el frontend normaliza valores superiores a 16 para que al escribir `17` el campo quede en `16`.

**Validación:** La API confirma que `investigador.barcelona / barcelona123` puede iniciar sesión, consultar su perfil, ver Barcelona como sede sin docencia y recibe `400 Bad Request` si intenta guardar docencia. La suite backend mantiene 21 pruebas correctas.

### Tabla de recompensas de carga creada por una migración anterior

**Síntoma:** Tras corregir la regla de dominio, seguía existiendo en el modelo la tabla `recompensas_carga_trabajo`, que ya no representaba una regla válida del sistema.

**Causa:** La migración `V3__carga_trabajo.sql` se había creado antes de separar recompensas y carga de trabajo.

**Solución:** Se retiraron el modelo y repositorio `RecompensaCargaTrabajo`, se eliminó la exposición de `excesoDocente` y `compensacionPendiente` en el DTO de carga, y se añadió la migración `V4__retirar_recompensas_carga_trabajo.sql` para eliminar la tabla obsoleta.

**Validación:** Flyway aplicó correctamente la migración V4 sobre la base local H2 y la suite backend terminó con 21 pruebas correctas.

### SVG del bloque 3 no regenerados por falta de PlantUML local

**Síntoma:** Los `.puml` de Análisis y Diseño del bloque 3 estaban corregidos, pero los SVG no se pudieron regenerar en una sesión anterior.

**Causa:** No había comando `plantuml` ni `plantuml.jar` disponible en el entorno, y el uso de un servidor externo de PlantUML no fue autorizado por la ejecución.

**Solución:** Se descargó `plantuml.jar` en `tools/plantuml.jar` para uso local, se añadió esa ruta a `.gitignore` para no subir el binario al repositorio y se regeneraron los SVG afectados:

- Análisis de `abrirOpcionesCargaTrabajo` y `editarCargaTrabajo` para Coordinador e Investigador.
- Diseño de `abrirOpcionesCargaTrabajo` y `editarCargaTrabajo` para Coordinador e Investigador.

**Validación:** Los SVG quedaron actualizados en `images/RUP/01-analisis` e `images/RUP/02-diseño`. Se comprobó que los archivos esperados se modificaran y que no quedaran SVG temporales con nombres derivados de `@startuml`.

### Casos de uso de proyectos con responsabilidades ambiguas

**Síntoma:** El detalle inicial del bloque 5 trataba algunas operaciones de proyectos como listados o eliminaciones genéricas. `abrirProyecto()` repetía el filtrado de proyectos aunque ya existía un proyecto seleccionado, `agregarInvestigador()` solicitaba datos propios de la creación de un perfil y `eliminarInvestigador()` podía interpretarse como la eliminación completa de la persona.

**Causa:** Los casos de uso habían heredado patrones CRUD generales sin distinguir suficientemente entre gestionar una entidad y gestionar su asociación con un proyecto.

**Solución:** Se revisó el detalle completo del bloque 5:

- `abrirProyecto(proyectoId)` presenta directamente el proyecto seleccionado.
- `abrirProyectos()` conserva un único caso de uso, pero adapta su alcance según el actor y el contexto de entrada.
- `agregarInvestigador()` selecciona un investigador existente y muestra compatibilidad, sede, línea, carga y disponibilidad.
- La recomendación de asignación prioriza al investigador compatible con menor carga de trabajo.
- `eliminarInvestigador()` desasigna al investigador del proyecto sin eliminar su perfil.
- `eliminarProyecto()` comprueba dependencias y preserva la trazabilidad antes de permitir la eliminación.
- `crearProyecto()` registra los datos mínimos y asigna automáticamente código, coordinador y estado inicial.
- `editarProyecto()` valida fechas, datos obligatorios y transiciones de estado permitidas.
- Las colaboraciones de Análisis muestran todas las acciones disponibles desde cada estado resultante del diagrama de contexto, no solo la operación principal.

**Validación:** Se actualizaron los README, diagramas de especificación, prototipos y colaboraciones de Análisis afectados. Todos los PlantUML de Detalle y Análisis del bloque 5 superaron `-checkonly`, se regeneraron sus SVG y `git diff --check` no detectó errores.

### Diseño de proyectos sin separación suficiente de permisos y asociaciones

**Síntoma:** Al trasladar el bloque 5 a Diseño existía el riesgo de tratar la consulta del Investigador como una consulta global, crear perfiles durante la asignación o eliminar perfiles al retirar miembros de un proyecto.

**Causa:** Las operaciones de proyectos comparten entidades y navegación, pero sus permisos y efectos cambian según el actor y según se gestione el proyecto o únicamente su composición.

**Solución:** El Diseño del bloque 5 establece endpoints y servicios diferenciados:

- El Coordinador consulta y gestiona proyectos mediante rutas globales de `/api/proyectos`.
- El Investigador consulta exclusivamente proyectos visibles mediante `/api/proyectos/me`, tomando su identidad de la sesión.
- `agregarInvestigador()` asocia un perfil existente y recomienda al candidato compatible y disponible con menor carga.
- `eliminarInvestigador()` elimina únicamente la asociación con el proyecto.
- `eliminarProyecto()` exige comprobación previa de dependencias y trazabilidad.
- `crearProyecto()` asigna en servidor código, Coordinador y estado inicial.
- La apertura y edición operativa recuperan únicamente proyectos activos.
- La asignación revalida carga, compatibilidad y disponibilidad al confirmar.
- La desasignación comprueba responsabilidades pendientes antes y durante la confirmación, registra motivo y conserva la participación histórica.
- El archivado utiliza una operación explícita `PATCH`, registra motivo y revalida que el proyecto siga activo.

**Validación:** Se revisaron los nueve diseños del bloque 5 con README, secuencia PlantUML y SVG. Todos los diagramas superaron `plantuml -checkonly`, los enlaces utilizan el nombre normalizado `secuencia.svg` y no quedan operaciones `DELETE` ni desasociaciones físicas dentro del Diseño del bloque.

### Eliminación física de proyectos incompatible con la trazabilidad histórica

**Síntoma:** `eliminarProyecto()` se había diseñado como un borrado físico condicionado por entregables y recompensas, lo que podía impedir la operación o hacer perder información histórica.

**Causa:** El nombre histórico del caso de uso se interpretó literalmente como eliminación de la entidad.

**Solución:** `eliminarProyecto()` pasa a realizar una baja lógica. El proyecto se marca como archivado, registra fecha y Coordinador responsable, deja de aparecer en los listados activos y conserva estado, equipo, entregables, recompensas y demás relaciones. Solo se rechaza la operación si el proyecto ya estaba archivado o no existe.

**Validación:** Se alinearon Detalle, Análisis, Diseño, diagramas y listados activos. Se dejó pendiente diseñar una consulta específica del histórico para el Coordinador.

### Semántica destructiva inconsistente en los casos `eliminar...`

**Síntoma:** Varios casos de uso llamados `eliminar...` podían interpretarse como borrados físicos, aunque perfiles, proyectos, publicaciones, entregables y recompensas conservan valor histórico, de auditoría o de trazabilidad.

**Causa:** El nombre funcional del caso de uso se había trasladado literalmente a algunas responsabilidades y operaciones técnicas sin distinguir qué debía desaparecer de los listados activos y qué debía conservarse en el sistema.

**Solución:** Se definió una política transversal de bajas lógicas y se clasificaron los efectos:

- `eliminarPerfil()` desactiva el acceso y conserva el perfil histórico.
- `eliminarProyecto()` archiva el proyecto.
- `eliminarInvestigador()` marca la participación como desasignada y conserva su histórico en el proyecto.
- `eliminarPublicacion()` y `eliminarMiPublicacion()` retiran la publicación.
- `eliminarEntregable()` retira el entregable.
- `eliminarRecompensa()` anula la recompensa y conserva su auditoría.

Se alinearon Detalle, Análisis, los Diseños existentes, los diagramas de contexto y la documentación de Desarrollo disponible. El borrado físico queda reservado para correcciones administrativas excepcionales sin valor histórico.

**Validación:** Los PlantUML afectados superaron la validación local, se regeneraron sus SVG y se comprobó que `eliminarPerfil()` ya realiza una desactivación lógica en el backend. Se dejó registrada como tarea pendiente la migración del borrado físico actual de recompensas hacia una anulación lógica.

## Decisiones de seguridad

### Almacenamiento de contraseñas

Los usuarios de demostración se crean desde el backend durante el arranque local. La base de datos no almacena contraseñas en texto plano: guarda hashes BCrypt en la columna `contrasena_hash`.

Antes del despliegue público se sustituirán las contraseñas de demostración incluidas en el código por variables de entorno o por una inicialización administrativa.

### Sesiones HTTP

La autenticación utiliza sesiones HTTP. El navegador conserva una cookie de sesión y la envía automáticamente al backend. En producción se activa `Secure` para que la cookie solo viaje mediante HTTPS.

## Verificación actual

|Comprobación|Resultado|
|-|-|
|Suite backend|45 pruebas correctas|
|Lint frontend|Correcto|
|Build frontend de producción|Correcto|
|Reintento tras credenciales incorrectas|Comprobado|
|Cancelación del cierre de sesión|Comprobada|
|Cierre confirmado|Comprobado|
|Permisos diferenciados por rol|Comprobados|
|Carga de trabajo por sede|Comprobada por API|
|Migración V4 de carga de trabajo|Aplicada en local|
|SVG del bloque 3|Regenerados con PlantUML local|
|Flujo manual del bloque 8|Comprobado con Coordinador e Investigador|
|Análisis y SVG del bloque 9|Validados|

## Mejoras pendientes antes del despliegue

- Crear el `Dockerfile` definitivo.
- Configurar PostgreSQL en el proveedor de despliegue.
- Extraer las contraseñas de demostración del código.
- Publicar la aplicación mediante una URL accesible para evaluación.
- Validar el recorrido completo desde un navegador externo sin sesión previa.

### Gestión de proyectos sin consulta histórica funcional

**Síntoma:** El Diseño del bloque 5 definía el archivado lógico, pero el Coordinador todavía no disponía de una consulta funcional para recuperar los proyectos archivados.

**Causa:** La apertura de proyectos solo contemplaba inicialmente el listado operativo de proyectos activos.

**Solución:** El Desarrollo integra el histórico dentro de `abrirProyectos()` mediante un control Activos/Archivados. `eliminarProyecto()` ejecuta `PATCH /api/proyectos/{id}/archivado`, registra motivo, fecha y Coordinador responsable, y nunca borra la entidad. Las asignaciones y desasignaciones registran movimientos históricos independientes mientras la relación operativa conserva únicamente participantes activos.

**Validación:** La migración `V7__gestion_historica_proyectos.sql` se aplica correctamente, la suite previa continúa verde y se añadieron pruebas específicas de creación, edición, archivado, permisos y trazabilidad de participantes.

### Finalización, archivado y documentación de proyectos desconectados

**Síntoma:** Un proyecto podía marcarse como completado sin pasar al histórico, o archivarse conservando un estado operativo. Además, el detalle no permitía conservar y compartir los documentos generados durante el proyecto.

**Causa:** El estado funcional y la baja lógica se habían implementado como decisiones independientes, y el bloque de proyectos todavía no incluía persistencia de archivos adjuntos.

**Solución:** Completar un proyecto provoca su archivado automático, mientras que archivarlo lo marca como completado. Se añadió la migración `V8__archivos_proyecto.sql` y una API de archivos adjuntos. Coordinador e Investigadores participantes pueden listar, subir y descargar documentos de proyectos activos o archivados; únicamente el Coordinador puede eliminarlos.

**Validación:** La suite backend completa supera 38 pruebas, incluidas las reglas de sincronización de estado, subida, consulta, permisos de descarga y eliminación exclusiva por Coordinador. La compilación de producción y el lint del frontend finalizan correctamente.

### Spring Boot falla antes de iniciar y el frontend muestra errores genéricos

**Síntoma:** `npm run dev` indicaba que Spring Boot no podía iniciarse y mostraba únicamente el final genérico del error de Maven. Al abrir funcionalidades como el bloque 8, el frontend mostraba `No se pudo completar la solicitud`.

**Causa:** El registro completo de `src/backend/backend-run.log` mostraba un `UnsatisfiedLinkError` en `java.io.Console.istty()` durante la preparación del entorno de Spring Boot. El fallo ocurría antes de ejecutar la aplicación cuando el backend se iniciaba desde el script con la salida redirigida. El mensaje del bloque 8 era una consecuencia de que la API no estaba disponible.

**Solución:** Se fijó explícitamente `CONSOLE_LOG_CHARSET=UTF-8` dentro de `JAVA_TOOL_OPTIONS` en `tools/start-dev.ps1`, manteniendo las opciones de codificación y `-Xshare:off`. Esto evita que Spring Boot intente resolver automáticamente el juego de caracteres mediante la consola problemática.

**Validación:** El endpoint `/api/auth/csrf` y el frontend responden con estado `200`. También se comprobó manualmente el bloque 8 con Coordinador e Investigador: creación, respuesta, edición, consulta global, permisos diferenciados y retirada lógica de publicaciones.

### Análisis de convocatorias acoplado a formatos y comportamientos inexistentes

**Síntoma:** El Análisis inicial del bloque 9 trataba `abrirConvocatorias()` como una lista dependiente del contexto e importaba convocatorias sin separar autorización, validación, extracción y persistencia.

**Causa:** Se habían heredado patrones genéricos de otros módulos sin respetar que las convocatorias siempre forman un catálogo global importado y que pueden incorporarse desde fuentes distintas.

**Solución:** Se revisaron `abrirConvocatorias()`, `abrirConvocatoria()` e `importarConvocatoria()` aplicando SOLID:

- `ConvocatoriaController` coordina el flujo sin asumir responsabilidades de autorización, validación o extracción.
- `PoliticaConvocatoria` concentra los permisos del Coordinador.
- `ValidadorConvocatoria` valida integridad y duplicados.
- `ImportadorConvocatoria` actúa como interfaz extensible para añadir formatos o proveedores sin modificar el controlador.
- `ConvocatoriaRepository` limita su responsabilidad al acceso y persistencia.
- `abrirConvocatorias()` presenta siempre el catálogo global, independientemente del estado de entrada.

**Validación:** Se actualizaron los README y colaboraciones de Análisis del bloque 9, se regeneraron sus SVG y los PlantUML resultantes son válidos.

### Importación de convocatorias sin confirmación técnica ni trazabilidad completa con el contexto

**Síntoma:** La colaboración inicial de `importarConvocatoria()` extraía y guardaba la convocatoria en una única interacción, aunque el Detalle exigía presentar una previsualización y esperar la confirmación del Coordinador. Además, `abrirConvocatoria()` ofrecía volver directamente al panel pese a que esa transición no existe en el diagrama de contexto.

**Causa:** El flujo funcional se había simplificado al trasladarlo a Análisis y conservaba una opción de navegación heredada que no estaba respaldada por el contexto.

**Solución:** Se separaron explícitamente la previsualización y la confirmación. La previsualización selecciona un importador compatible, extrae y valida los datos sin persistirlos; solo la confirmación revalida y ejecuta el guardado. Se incorporó un selector o registro de importadores para aplicar OCP y se eliminó la navegación directa al panel desde el detalle.

**Validación:** El Análisis y el Diseño del bloque 9 respetan las entradas y salidas del diagrama de contexto. Los tres diagramas de secuencia incluyen consultas a Base de Datos, los PlantUML superan `-checkonly` y sus SVG fueron regenerados.
