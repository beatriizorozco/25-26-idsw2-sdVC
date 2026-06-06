## [2026-05-22 15:44] Inicio de sesion con memoria persistente

**Prompt:** el usuario invoco la skill `session-memory` con "bienvenido" y aclaro que el archivo de memoria debe modificarse automaticamente al iniciar sesion.

**Resultado:** se leyo la skill `session-memory`, se reviso `conversation-log.md`, el estado de Git, la rama activa, los commits recientes y la estructura del repositorio. El repositorio esta limpio en la rama `develop`, sincronizada con `origin/develop`. La arquitectura actual sigue siendo documental/base: estructura RUP, carpetas `src`, `modelosUML`, `images` y `documents`, pero sin implementacion funcional todavia.

**Decisión:** se confirma que, a partir de este punto, los eventos relevantes de inicio/cierre de sesion y cambios tecnicos se registraran automaticamente en `conversation-log.md`, manteniendo el historial y evitando duplicados.

---

## [2026-05-23 01:39] Normalizacion de artefactos RUP

**Prompt:** el usuario inicio una nueva sesion con `session-memory`, indico que habia organizado `/RUP/00-casos-uso` y pidio que `images/` contenga solamente SVG, `modelosUML/` solamente PUML y `RUP/00-casos-uso/00-modelo-del-dominio` solamente MD o PUML.

**Resultado:** se utilizo la skill `session-memory`. Se reviso el estado del repositorio y se reorganizaron los artefactos por tipo: los `.svg` quedaron bajo `images/`, los `.puml` bajo `modelosUML/` y los `.md` bajo `RUP/`. Se eliminaron, con permiso previo del usuario, los archivos de cuarentena que no encajaban en esa estructura: `LogoGIPF.png`, `images/README.md` y `modelosUML/README.md`.

**Decisión:** se conserva la separacion estricta de artefactos por carpeta para facilitar la evaluacion: documentacion RUP en Markdown, fuentes UML en PlantUML e imagenes renderizadas en SVG.

---

## [2026-05-24 02:10] Fin de sesión

**Prompt:** cierre automatico de sesión usando la skill `session-memory`.

**Resultado:** se reorganizaron los artefactos RUP de casos de uso, prototipos, imagenes y modelos UML. Se movieron los prototipos `.puml` a sus respectivos casos de uso en `RUP/00-casos-uso/02-detalle` como `prototipo.puml`, y los SVG asociados quedaron bajo `images/RUP/00-casos-uso/02-detalle`. Se mantuvo la convencion de `README.md` para los documentos de detalle, camelCase para las carpetas de casos de uso y `especificacion.puml` para las especificaciones. Tambien se actualizaron los enlaces de imagenes para apuntar al repositorio `beatriizorozco/25-26-idsw2-sdVC` mediante URLs `raw.githubusercontent.com` sobre la rama `develop`, verificando que 153 enlaces de imagen apuntan a archivos locales existentes. Se anadio el badge de `Log_de_conversacion` a las cabeceras Markdown que ya usaban badges en RUP.

**Decisión:** se deja el repositorio con cambios pendientes sin commit automatico. El siguiente paso recomendado es revisar visualmente los Markdown principales, especialmente las cabeceras y diagramas de `RUP/00-casos-uso`, y despues agrupar los cambios en un commit documental/organizativo si todo renderiza correctamente. Queda como criterio de continuidad que `session-memory` solo se use al inicio y al cierre de sesion, no en cada prompt intermedio.

---

## [2026-05-25 00:09] Fin de sesión

**Prompt:** cierre de sesión solicitado por el usuario usando la skill `session-memory`.

**Resultado:** se utilizo la skill `session-memory`. Durante la sesión se limpiaron artefactos generados para mantener la separacion por tipo: en `modelosUML/rup/00-casos-uso/02-detalle` se eliminaron 75 archivos que no eran `.puml`, y en `images/rup/00-casos-uso/02-detalle/prototipado` se eliminaron 71 archivos que no eran `.svg`. Tambien se adaptó el README de `RUP/00-casos-uso/README.md` a la Plataforma Interna de Investigacion de FUNIBER, con la cabecera habitual y enlaces reales del proyecto. Posteriormente se actualizo `RUP/00-casos-uso/02-detalle/README.md` con la cabecera comun, la lista completa de 71 casos de uso detallados, separados en 44 del Coordinador y 27 del Investigador, y se verificó que no hubiera enlaces rotos.

**Decisión:** se mantiene la estructura documental RUP con cabeceras homogéneas, enlaces trazables y separacion estricta entre documentacion Markdown, fuentes PlantUML e imagenes SVG. Se da por terminada la actualizacion de `RUP/00-casos-uso`; la siguiente actualizacion del proyecto sera en la disciplina de analisis (`RUP/01-analisis`). El repositorio queda en la rama `develop`; existe el commit reciente `053cd6c docs: actualizar README principal de 00-casos-uso`, y queda pendiente sin commit `RUP/00-casos-uso/02-detalle/README.md`.

---

## [2026-05-25 17:47] Actualización de README de detalle de casos de uso

**Prompt:** el usuario inició sesión con `session-memory` y pidió terminar la parte de casos de uso actualizando todos los `README.md` de `RUP/00-casos-uso/02-detalle`, tanto para Coordinador como para Investigador, usando una plantilla de detalle y prototipado adaptada al proyecto.

**Resultado:** se utilizó la skill `session-memory`. Se regeneraron los 71 README de casos de uso detallados: 44 bajo `coordinador` y 27 bajo `investigador`. Cada README quedó adaptado a FUNIBER - Plataforma Interna de Investigación, con información del artefacto, propósito, datos del caso de uso, diagrama de especificación, prototipo, conversación detallada, estados internos, funcionalidad específica, navegación, conexión con diagramas de contexto, vocabulario, características metodológicas y referencias. La generación reutilizó los `especificacion.puml`, `prototipo.puml` y los SVG existentes en `images/RUP/00-casos-uso/02-detalle`.

**Decisión:** se mantiene el patrón documental de RUP para cerrar la disciplina de requisitos. Se verificó que existen los 71 README esperados, que todos los casos mantienen `README.md`, `especificacion.puml` y `prototipo.puml`, y que no quedan enlaces rotos a imágenes ni a referencias internas. Queda pendiente revisar visualmente una muestra amplia en GitHub/Markdown antes de confirmar esta actualización en un commit.

---

## [2026-05-25 21:39] Fin de sesión

**Prompt:** cierre de sesión solicitado por el usuario usando la skill `session-memory`.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se cerró la parte de requisitos y se inició la disciplina de análisis en `RUP/01-analisis`. Primero se revisó la coherencia funcional de los casos de uso detallados y se confirmaron las reglas pendientes del dominio: las convocatorias no tienen CRUD manual porque se importan, el Coordinador es quien puede ejecutar la eliminación de perfiles, no existe flujo de rechazo para esa solicitud, el Investigador solo puede crear, editar y eliminar sus propias publicaciones o entregables, y el Coordinador mantiene acceso global sobre esas entidades. Después se tomó como referencia la estructura de análisis del repositorio `pySigHor` del profesor, basada en clases Boundary, Control, Entity y Collaboration. Se sustituyó el contenido copiado desde `00-casos-uso/02-detalle` dentro de `RUP/01-analisis/casos-uso` por análisis MVC real, conservando las carpetas `coordinador` e `investigador` para diferenciar roles.

Se generaron 71 análisis de casos de uso: 44 para Coordinador y 27 para Investigador. Cada caso quedó con `README.md` y `colaboracion.puml`, incluyendo propósito, diagrama de colaboración, clases de análisis identificadas, responsabilidades, flujo de colaboración, correspondencia con requisitos, reglas funcionales, patrones aplicados y referencias. Se añadieron también los índices `RUP/01-analisis/casos-uso/README.md`, `RUP/01-analisis/casos-uso/coordinador/README.md` y `RUP/01-analisis/casos-uso/investigador/README.md`. Finalmente se renderizaron los 71 SVG de colaboración en `images/RUP/01-analisis/casos-uso`, respetando la estructura por actor y caso de uso, y se verificó que no quedaran archivos copiados de detalle (`especificacion.puml` o `prototipo.puml`) dentro de análisis.

**Decisión:** se mantiene la trazabilidad entre detalle de casos de uso, análisis MVC y diagramas SVG. La disciplina de requisitos queda suficientemente cerrada para continuar con análisis/diseño, y la primera iteración de análisis queda estructurada con el mismo patrón metodológico del ejemplo del profesor. Se validó que existen 71 carpetas de análisis, 71 `README.md`, 71 `colaboracion.puml`, 71 SVG esperados y 0 enlaces rotos hacia artefactos existentes. El repositorio estaba limpio al cerrar, con el commit reciente `4cf6ae0 feat: primera iteración de análisis`; tras esta entrada solo queda modificado `conversation-log.md`.

---

## [2026-05-29 00:39] Fin de sesión (inicio aproximado: 2026-05-28 23:50)

**Prompt:** el usuario inició sesión con `session-memory`, compartió una aclaración del profesor sobre casos de uso llamados desde varios estados y pidió ajustar los diagramas de colaboración de análisis teniendo esa regla en cuenta. Al cerrar, pidió registrar la sesión en `conversation-log.md`.

**Resultado:** se utilizó la skill `session-memory`. Se revisó el contexto de la sesión y se corrigieron los análisis de los casos de uso que tenían varias entradas desde el diagrama de contexto. Se identificaron 18 casos afectados en `RUP/01-analisis/casos-uso`, repartidos entre Coordinador e Investigador. En sus `colaboracion.puml` se sustituyó la entrada única por todas las entradas reales, y en los casos donde el comportamiento depende del origen se modeló un parámetro de contexto, siguiendo la aproximación explicada por el profesor: por ejemplo, `abrirInvestigadores()` sin identificador mantiene el listado base, mientras que `abrirInvestigadores(idProyecto)` acota la consulta al proyecto. También se añadieron llamadas como `listar...(contexto)` y `obtenerPorContexto(contexto)` cuando el alcance podía cambiar.

Además, se actualizaron los README de esos 18 análisis para explicar la entrada contextual y la decisión metodológica de no duplicar casos cuando basta con un parámetro de contexto. Se mantuvo el matiz funcional de roles: el Coordinador puede trabajar con alcance global, mientras que el Investigador conserva alcance propio cuando no recibe contexto. Finalmente se regeneraron los SVG afectados en `images/RUP/01-analisis/casos-uso`, se verificó que siguen existiendo 71 SVG de análisis, que hay 18 entradas contextuales documentadas y que no quedan enlaces rotos a imágenes. El repositorio estaba limpio antes de registrar este cierre.

**Decisión:** se adopta una solución intermedia coherente con la respuesta del profesor: los casos de uso no se duplican automáticamente por tener varios estados de entrada, sino que el análisis explicita el origen y usa contexto cuando la navegación modifica el alcance funcional. Solo tendría sentido separar casos en el futuro si la casuística interna deja de ser una variación de listado/consulta y pasa a representar conversaciones claramente distintas.

---

## [2026-05-31 15:45] Inicio de sesión - Día 1 de revisión por bloques

**Prompt:** el usuario inició sesión con `session-memory` y pidió comenzar el Día 1 del plan de trabajo, dedicado a pulir los casos de uso `iniciarSesion`, `abrirPanelPrincipal` y `cerrarSesion`. Solicitó revisar primero los diagramas de colaboración para detectar errores antes de continuar.

**Resultado:** se utilizó la skill `session-memory`. Se revisó `conversation-log.md`, el estado de Git, la rama activa y los commits recientes. El repositorio se encuentra limpio en la rama `develop`. La documentación de requisitos está cerrada y existe una primera iteración completa de análisis MVC con 71 casos de uso. La sesión parte de los commits recientes `8d6ae56 feat: actualizo algunos diagramas de colaboración con la recomendación del profesor` y `66f89fa docs: actualizo conversation-log y se acaba la sesión`.

**Decisión:** durante esta sesión se trabajará con un bloque manejable para evitar revisiones superficiales. Primero se auditarán los diagramas de colaboración de acceso y navegación principal para Coordinador e Investigador; después se corregirán los errores detectados y se dejará el bloque preparado para continuar con diseño o implementación.

---

## [2026-05-31 17:22] Fin de sesión - Revisión del bloque de sesión y navegación

**Prompt:** el usuario cerró la sesión usando la skill `session-memory` después de revisar el primer bloque de análisis, dedicado a `iniciarSesion`, `abrirPanelPrincipal` y `cerrarSesion` para Coordinador e Investigador.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se auditó el bloque inicial tomando como fuente principal los diagramas de especificación de `RUP/00-casos-uso/02-detalle`, además de los diagramas de contexto y el patrón de análisis MVC del repositorio `pySigHor` del profesor. Se detectaron y corrigieron simplificaciones de la primera generación automática: `iniciarSesion` debía representar el estado previo `SESION_CERRADA`, las credenciales incorrectas y el reintento; `cerrarSesion` debía distinguir confirmación y cancelación; y `abrirPanelPrincipal` debía incluir las acciones reales permitidas por cada rol.

Se revisaron seis análisis: tres para Coordinador y tres para Investigador. Los `colaboracion.puml` quedaron con actores explícitos, estados de entrada y salida trazables, clases Boundary, Control y Entity ajustadas al caso y flujos alternativos relevantes. En `iniciarSesion`, `UsuarioNoAutenticado` introduce las credenciales mientras `SESION_CERRADA` permanece como estado de entrada; tras validar correctamente se crea `Sesion` y se alcanza `PANEL_PRINCIPAL_ABIERTO`. En `cerrarSesion`, el actor puede cancelar y volver a `PANEL_PRINCIPAL_ABIERTO` o confirmar y alcanzar `SESION_CERRADA`. En `abrirPanelPrincipal`, se conservaron los estados reales de retorno y las navegaciones específicas de cada rol.

También se actualizaron los seis README de análisis para eliminar expresiones genéricas o subjetivas, se corrigieron sus referencias mediante rutas absolutas desde `/RUP`, y se reorganizaron los índices `RUP/01-analisis/casos-uso/coordinador/README.md` y `RUP/01-analisis/casos-uso/investigador/README.md` por familias funcionales. Se regeneraron los seis SVG correspondientes y se validó que no hubiera errores de PlantUML, que los enlaces del bloque fueran válidos y que los índices incluyeran los 44 casos del Coordinador y los 27 del Investigador sin omisiones.

**Decisión:** queda cerrado el primer bloque de revisión manual de análisis. A partir de ahora cada familia debe revisarse contra sus `especificacion.puml` antes de aceptar o regenerar diagramas de colaboración, evitando reutilizar plantillas genéricas cuando oculten estados, alternativas o responsabilidades específicas. El siguiente bloque recomendado es la gestión de perfil. El repositorio queda limpio en la rama `develop`, con los commits recientes `2f89ab6 refactor(analisis): alinear bloque de sesión con especificaciones`, `a7f60bc refactor(analisis): recuperar estados y organizar índices por dominio` y `4eb3496 fix(analisis): corregir referencias del bloque de sesión`.

---

## [2026-06-01 21:23] Inicio de sesión - Diseño del bloque de sesión y navegación

**Prompt:** el usuario inició sesión con `session-memory` y pidió continuar con el primer bloque, comenzando la disciplina de diseño y tomando como base la metodología del repositorio `pySigHor` del profesor antes de abordar el desarrollo funcional.

**Resultado:** se utilizó la skill `session-memory`. Se revisó `conversation-log.md`, el estado de Git, la rama activa y los commits recientes. El repositorio se encuentra limpio en la rama `develop`. La disciplina de requisitos está cerrada, existe una primera iteración completa de análisis MVC con 71 casos de uso y el primer bloque de análisis ya fue revisado manualmente contra sus diagramas de especificación: `iniciarSesion`, `abrirPanelPrincipal` y `cerrarSesion`, tanto para Coordinador como para Investigador. La carpeta `RUP/02-diseño` todavía contiene únicamente su README inicial.

**Decisión:** durante esta sesión se diseñará la primera iteración técnica del sistema manteniendo trazabilidad completa desde Detalle y Análisis. Se adaptará el patrón del profesor a la Plataforma Interna de Investigación de FUNIBER: arquitectura general, clases de diseño, configuración del proyecto y diagramas de secuencia del bloque de sesión y navegación. Después de validar estos artefactos se podrá iniciar el desarrollo funcional del mismo bloque.

---

## [2026-06-01 23:56] Fin de sesión - Desarrollo funcional del bloque inicial

**Prompt:** el usuario cerró la sesión usando la skill `session-memory` después de completar el Diseño y el Desarrollo del primer bloque funcional: `iniciarSesion`, `abrirPanelPrincipal` y `cerrarSesion`, tanto para Coordinador como para Investigador.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se terminó de auditar el Diseño del bloque inicial y se reforzaron sus decisiones de seguridad: sesión HTTP gestionada por Spring Security, cookie con `HttpOnly`, `Secure` en producción y `SameSite=Lax`, protección CSRF para las operaciones que modifican estado, renovación del token después del acceso y conservación de la protección frente a fijación de sesión. También se alinearon los diagramas de secuencia y README de Diseño con los estados reales `SESION_CERRADA` y `PANEL_PRINCIPAL_ABIERTO`, los reintentos de acceso, la cancelación del cierre y las respuestas `401` cuando no existe una sesión válida.

Se construyó la primera vertical funcional del proyecto. El backend quedó implementado en `src/backend` con Spring Boot, Java 17, Spring Security, Spring Data JPA, Flyway, H2 para desarrollo local y PostgreSQL para producción. Se añadieron la entidad `Usuario`, roles `COORDINADOR` e `INVESTIGADOR`, repositorio, servicios de autenticación, sesión y panel principal, controladores REST, tratamiento de errores, configuración de seguridad, migración inicial de la tabla `usuarios` y usuarios locales de demostración. La API expone `GET /api/auth/csrf`, `POST /api/auth/login`, `GET /api/auth/me`, `POST /api/auth/logout` y `GET /api/panel-principal`.

El frontend quedó implementado en `src/frontend` con React, TypeScript y Vite. Se desarrollaron el formulario de acceso, el panel principal diferenciado por rol y el modal de confirmación para cerrar sesión. Se aplicó la identidad visual corporativa mediante el azul FUNIBER `#00689d` y se incorporó el logotipo GIPF en el login y en la cabecera del panel. El Coordinador dispone de la acción `Convocatorias`, mientras que el Investigador no la recibe.

Se generó la documentación de Desarrollo bajo `RUP/03-desarrollo/casos-uso`, manteniendo trazabilidad con Detalle, Análisis y Diseño. También se actualizó `RUP/02-diseño/configuracion-proyecto.md`, `src/README.md` y `tareas_a_realizar.md`. Se verificaron seis pruebas Maven, la compilación de producción del frontend, el lint, los enlaces Markdown, las credenciales incorrectas, la sesión válida, el cierre confirmado, el rechazo posterior con `401` y los permisos diferenciados por rol. La base de datos de pruebas quedó aislada en memoria para evitar colisiones con la instancia H2 local.

**Decisión:** se considera completado y estable el primer bloque funcional. El repositorio queda limpio en la rama `develop`, con los commits recientes `5f405fb refactor(diseño): reforzar seguridad del bloque de sesión` y `dd92fc4 feat(desarrollo): implementar bloque inicial de sesión y panel principal`. Para la siguiente sesión conviene realizar una comprobación manual desde la interfaz del reintento de acceso y de la cancelación del cierre, añadir capturas o evidencias visuales y comenzar la documentación de Pruebas en `RUP/04-pruebas`. Después se podrá continuar con el siguiente bloque funcional, recomendado: gestión de perfil. El despliegue público, Docker y PostgreSQL remoto permanecen aplazados hasta que avance el MVP.

---

## [2026-06-02 14:29] Inicio de sesión - Revisión final del primer desarrollo

**Prompt:** el usuario inició sesión con `session-memory`, indicó que el pull request de `develop` ya se integró en `main` y pidió retomar las instrucciones finales de `conversation-log.md` para repasar el Desarrollo del primer bloque.

**Resultado:** se utilizó la skill `session-memory`. Se revisó `conversation-log.md`, el estado de Git, la rama activa, los commits recientes y `tareas_a_realizar.md`. El repositorio está limpio en `main` tras el merge `aec941a Merge pull request #2 from beatriizorozco/develop`. El primer bloque funcional de sesión y panel principal está integrado; quedan pendientes la comprobación manual del reintento de acceso y de la cancelación del cierre, la recopilación de evidencias visuales y el inicio de la documentación bajo `RUP/04-pruebas`.

**Decisión:** antes de comenzar la gestión de perfil se rematará el bloque inicial con una revisión breve pero completa de interfaz y trazabilidad de pruebas. Se mantendrán aplazados Docker, PostgreSQL remoto y el despliegue público hasta que avance el MVP.

---

## [2026-06-02 16:26] Fin de sesión - Cierre verificado del primer bloque funcional

**Prompt:** el usuario cerró la sesión usando la skill `session-memory` después de revisar de extremo a extremo el primer bloque funcional, completar su documentación de Pruebas y resolver las incidencias encontradas durante la comprobación manual.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se completó la disciplina de Pruebas del primer bloque mediante `RUP/04-pruebas`, con índices por actor, README trazables para `iniciarSesion`, `abrirPanelPrincipal` y `cerrarSesion`, evidencias visuales y resultados de ejecución. Se confirmó manualmente el reintento tras credenciales incorrectas, la cancelación del cierre y el cierre confirmado. También se incorporaron pruebas de integración para el reintento posterior a un acceso fallido y para el origen local usado por el navegador integrado.

La revisión detectó un error `403 Forbidden` al acceder desde `http://127.0.0.1:5173`: la configuración CORS solo autorizaba `http://localhost:5173`. Se corrigió `SecurityConfig.java` para aceptar varios valores configurables mediante `FRONTEND_ORIGINS` y se autorizaron ambos orígenes locales por defecto. El frontend se ajustó para solicitar un token CSRF vigente antes de iniciar o cerrar sesión y para renovarlo después de autenticar correctamente. Se retiró del mensaje visible de credenciales incorrectas el detalle HTTP utilizado temporalmente durante el diagnóstico.

Se auditó de nuevo la trazabilidad del bloque desde Detalle hasta Desarrollo. Los diagramas de Diseño se alinearon con el flujo real: el formulario se presenta antes de obtener el token CSRF necesario para enviar credenciales; el cierre obtiene un token vigente antes de ejecutar `POST /api/auth/logout`; la documentación garantiza la invalidación de la sesión sin afirmar que la cookie expira; y `Secure` se documenta como obligatorio en producción mediante HTTPS. Se regeneraron los cuatro SVG afectados y se completaron los enlaces directos entre Detalle, Análisis, Diseño, Desarrollo y Pruebas.

En la interfaz se corrigió la cuadrícula impar del panel del Investigador para que `Recompensas` ocupe toda la última fila. Se sustituyó su captura de evidencia visual. También se creó `incidencias_y_soluciones.md`, que registra síntoma, causa, corrección y validación de los problemas relevantes, y se añadió a `tareas_a_realizar.md` el recordatorio de mantenerlo actualizado en cada bloque.

La verificación final concluyó correctamente: 8 pruebas Maven superadas, lint del frontend correcto, build de producción correcto, SVG sincronizados, enlaces modificados válidos y `git diff --check` sin errores. El usuario recibió una descripción Conventional Commit para agrupar el cierre del bloque. Al registrar esta entrada quedan cambios pendientes de commit, incluido `conversation-log.md`.

**Decisión:** el primer bloque funcional queda cerrado y suficientemente pulido para continuar con el segundo bloque recomendado: gestión de perfil. La aplicación está preparada arquitectónicamente para desplegarse mediante frontend compilado, backend Spring Boot, PostgreSQL y cookies seguras por HTTPS, pero el despliegue público permanece aplazado hasta avanzar el MVP. Antes de publicar se deberá crear el `Dockerfile`, configurar PostgreSQL remoto y sustituir las contraseñas de demostración incluidas en `DemoDataConfig.java` por variables de entorno o una inicialización administrativa; la base de datos ya almacena únicamente hashes BCrypt.

---

## [2026-06-03 13:14] Inicio de sesión - Preparación del segundo bloque

**Prompt:** el usuario inició sesión con `session-memory` y pidió continuar desde el punto anterior: terminar lo que quede por revisar del Desarrollo del primer bloque y pasar al bloque 2.

**Resultado:** se utilizó la skill `session-memory`. Se revisó `conversation-log.md`, `tareas_a_realizar.md`, la rama activa, el estado de Git y los commits recientes. El repositorio está en `main` y parte del commit reciente `3a6274b docs(dominio): añadir diagramas de estados y objetos`, después de haber añadido al modelo del dominio los diagramas de estados y objetos con sus SVG. El primer bloque funcional (`iniciarSesion`, `abrirPanelPrincipal`, `cerrarSesion`) ya quedó verificado con 8 pruebas Maven, lint, build de frontend, evidencias visuales, documentación de Pruebas e incidencias registradas.

**Decisión:** el Desarrollo del primer bloque no requiere nuevas correcciones funcionales antes de avanzar. La sesión se enfocará en delimitar y revisar el segundo bloque recomendado, gestión de perfil, manteniendo la metodología acordada: revisar primero Detalle y Análisis contra los diagramas de especificación, después crear Diseño, Desarrollo y Pruebas en un bloque pequeño.

---

## [2026-06-03 16:25] Fin de sesión

**Prompt:** el usuario cerró la sesión usando la skill `session-memory` después de avanzar desde la revisión del primer bloque hacia el segundo bloque funcional, dedicado a la gestión de perfil.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se revisó y cerró el Diseño del bloque de perfil antes de pasar a Desarrollo. Se reforzaron los diagramas de secuencia de `solicitarEliminacionPerfil` para Coordinador e Investigador, añadiendo confirmación explícita, cancelación sin llamada a la API, confirmación del actor y comprobación de sesión local antes de registrar la solicitud. También se añadió comprobación de sesión local antes del `DELETE` en `eliminarPerfil` del Coordinador, se actualizaron los README afectados, se regeneraron los SVG correspondientes y se verificó que todos los casos del bloque tenían `README.md`, `secuencia.puml` y `secuencia.svg`.

Después se implementó el Desarrollo funcional del bloque de perfil. En backend se añadieron campos de perfil a `Usuario`, la entidad `SolicitudEliminacionPerfil`, el enum `EstadoSolicitudEliminacion`, DTOs de perfil y solicitudes, repositorio de solicitudes, excepciones específicas, `PerfilService`, `PerfilController`, `SolicitudEliminacionPerfilController` y la migración `V2__perfil_y_solicitudes_eliminacion.sql`. También se amplió CORS para permitir `PATCH` y `DELETE`, se completó el manejador de errores REST y se ajustó `DemoDataConfig` para crear los usuarios demo con datos de perfil útiles.

En frontend se añadió `PerfilPage`, navegación desde `PanelPrincipalPage` al módulo `Perfil`, llamadas API para consultar y editar perfil, solicitar eliminación, listar solicitudes pendientes, abrir detalle de solicitud y eliminar perfiles desde solicitud. Se incorporaron formularios, modales de confirmación, listado filtrable para Coordinador y estilos coherentes con la interfaz FUNIBER/GIPF. Se actualizó `App.tsx`, `api.ts`, `types.ts`, `PanelPrincipalPage.tsx` y `styles.css`.

La documentación de Desarrollo se amplió en `RUP/03-desarrollo`, con README por actor y por caso del bloque de perfil: `abrirOpcionesPerfil`, `editarPerfil`, `solicitarEliminacionPerfil`, `abrirSolicitudesEliminacionPerfil`, `abrirSolicitudEliminacionPerfil` y `eliminarPerfil` cuando aplica. Para evitar enlaces rotos en las cabeceras, se prepararon también páginas mínimas de Pruebas en `RUP/04-pruebas` para los casos del bloque, marcadas como pendientes de verificación específica.

La verificación frontend quedó correcta: `npm run build` y `npm run lint` terminaron sin errores. También se comprobó que las rutas internas de Desarrollo y Pruebas existieran y que `git diff --check` no mostrara errores reales, solo avisos normales de CRLF. No se pudieron ejecutar pruebas backend porque `src/backend/mvnw.cmd` falla antes de arrancar Maven con `No se puede indizar en una matriz nula`, y no hay `mvn` global disponible en el entorno.

**Decisión:** el bloque de perfil ya tiene Diseño cerrado, Desarrollo implementado y documentación de Desarrollo trazable. El siguiente paso recomendado es resolver o sustituir el wrapper de Maven para poder validar backend, arrancar la aplicación completa y probar manualmente el flujo de perfil: abrir perfil, editar datos, solicitar eliminación, listar solicitudes como Coordinador, abrir detalle y confirmar eliminación. Después conviene completar la documentación real de Pruebas del bloque con evidencias y resultados, sustituyendo las páginas provisionales creadas durante esta sesión.

---

## [2026-06-03 23:20] Inicio de sesión - Revisión del Desarrollo del bloque de perfil

**Prompt:** el usuario inició sesión usando la skill `session-memory` y pidió revisar la parte de Desarrollo del segundo bloque, dedicado a la gestión de perfil.

**Resultado:** se utilizó la skill `session-memory`. Se revisó el estado del repositorio, los commits recientes, la última entrada de `conversation-log.md` y la implementación del bloque de perfil en frontend, backend y documentación RUP. El repositorio estaba limpio antes de iniciar las correcciones. La revisión detectó tres puntos a corregir antes de cerrar el bloque: la trazabilidad de `editarPerfil` del Coordinador no coincidía entre Detalle/Diseño y Desarrollo, la solicitud de eliminación documentaba `201 Created` pero el controlador devolvía `200 OK`, y la eliminación de perfil podía desactivar al único Coordinador activo.

**Decisión:** la sesión se centrará en corregir esos errores, reforzar las pruebas del bloque de perfil y dejar el Desarrollo alineado con la documentación antes de avanzar al siguiente bloque funcional.

---

## [2026-06-03 23:55] Fin de sesión - Revisión corregida del bloque de perfil

**Prompt:** cierre de sesión solicitado con la skill `session-memory` después de revisar el Desarrollo del segundo bloque y de perder el límite antes de poder registrar el resumen completo.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se revisó el bloque funcional de perfil de extremo a extremo, comparando Desarrollo con Detalle, Análisis, Diseño y Pruebas. Se corrigió la trazabilidad de `abrirOpcionesPerfil` y `editarPerfil` del Coordinador para dejar claro que, en el MVP actual, trabajan sobre el perfil propio del Coordinador y no sobre un perfil de investigador seleccionado. Con ese ajuste se actualizaron los README de Detalle, Análisis, Diseño, Desarrollo y Pruebas, los diagramas `colaboracion.puml`, los diagramas `secuencia.puml`, el prototipo del caso y los SVG asociados en `images/RUP`.

En backend se corrigieron tres incidencias relevantes. Primero, `POST /api/perfil/solicitud-eliminacion` quedó alineado con la documentación y ahora devuelve `201 Created`. Segundo, se añadió protección para impedir la eliminación del único Coordinador activo del sistema, evitando dejar la aplicación sin actor con permisos de administración. Tercero, se corrigió `mvnw.cmd`, que fallaba con `No se puede indizar en una matriz nula`, permitiendo volver a ejecutar las pruebas de Maven desde Windows.

También se añadieron pruebas de integración del bloque de perfil en `PerfilIntegrationTests`. Estas cubren la actualización del perfil propio del Coordinador, la solicitud de eliminación del Investigador con cierre de sesión, la restricción de acceso del Investigador al listado de solicitudes, el listado de solicitudes pendientes por parte del Coordinador, la resolución de solicitudes, la eliminación segura de perfiles y el bloqueo de la eliminación del único Coordinador activo. Se completaron además las páginas de Pruebas que todavía estaban demasiado provisionales, distinguiendo entre verificación automática ya cubierta y evidencia visual manual pendiente.

La verificación final quedó correcta: `mvnw.cmd test` ejecutó 15 pruebas backend sin fallos ni errores. También se comprobó que no quedaran referencias contradictorias del antiguo flujo del Coordinador sobre perfiles de investigador en los casos corregidos, que no hubiera SVG sueltos dentro de `RUP` y que `git diff --check` no mostrara errores reales, solo avisos normales de CRLF.

**Decisión:** el bloque de perfil queda mucho más consistente y preparado para continuar. Antes de avanzar al siguiente bloque conviene, si se quiere dejarlo redondo visualmente, probar manualmente la pantalla de perfil en navegador y añadir capturas de evidencia para `abrirOpcionesPerfil`, `editarPerfil`, `solicitarEliminacionPerfil`, `abrirSolicitudesEliminacionPerfil`, `abrirSolicitudEliminacionPerfil` y `eliminarPerfil`. Después se puede pasar al siguiente bloque funcional manteniendo el método recomendado: revisar pocos casos por tanda, validar su especificación y solo entonces avanzar a Diseño, Desarrollo y Pruebas.

---

## [2026-06-04 02:02] Inicio de sesión - Diagnóstico de credenciales

**Prompt:** el usuario inició una nueva sesión usando la skill `session-memory` e indicó que las credenciales de la plataforma no funcionan.

**Resultado:** se utilizó la skill `session-memory`. Se revisó el estado inicial del repositorio, la rama activa y la última entrada de memoria. El repositorio está en `main`, sincronizado con `origin/main`, y únicamente `conversation-log.md` estaba modificado por el cierre de sesión pendiente. La sesión parte del bloque de perfil corregido y validado, con el objetivo inmediato de diagnosticar el fallo de autenticación en la plataforma.

**Decisión:** se revisará primero la configuración de autenticación, los usuarios demo, el estado de la base H2 local, CSRF y CORS antes de modificar código, porque el fallo puede estar causado por datos persistentes locales y no por las credenciales documentadas.

---

## [2026-06-04 03:20] Fin de sesión - Cierre del diagnóstico y avance del bloque 3

**Prompt:** cierre de sesión solicitado con la skill `session-memory`, pidiendo actualizar también las tareas para continuar mañana.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se diagnosticó el problema de credenciales de la plataforma, diferenciando entre fallo real de autenticación y estado local de ejecución. Se verificó que los usuarios demo de Coordinador e Investigador funcionan contra el backend y a través del proxy del frontend cuando la aplicación está correctamente arrancada. También se revisó el estado del segundo bloque de perfil y se concluyó que el Desarrollo está funcionalmente cerrado, quedando solo evidencias visuales opcionales para reforzar Pruebas.

Después se avanzó al Análisis del tercer bloque, dedicado a carga de trabajo. Se actualizaron los casos `abrirOpcionesCargaTrabajo` y `editarCargaTrabajo` para Coordinador e Investigador, con sus README de Análisis, diagramas `colaboracion.puml` y SVG en `images/RUP/01-analisis`. A partir de la aclaración del usuario sobre el dominio, el análisis incorporó dos reglas centrales: si existe un proyecto libre, debe sugerirse o asignarse a investigadores-docentes con menor carga de trabajo; y si un investigador-docente supera el límite ordinario de 16 horas semanales de docencia, debe quedar reflejada la compensación económica correspondiente.

Finalmente se actualizó `tareas_a_realizar.md` con el plan inmediato para mañana: revisar una última vez el Análisis del bloque 3, confirmar la coherencia de las reglas de carga, proyectos libres y compensaciones, y empezar el Diseño del bloque con `README.md`, `secuencia.puml` y SVG por caso.

**Decisión:** mañana se recomienda cerrar formalmente la revisión del Análisis del bloque 3 y pasar al Diseño de carga de trabajo, manteniendo la pauta de bloques pequeños. Las capturas pendientes del bloque de perfil pueden completarse antes o después, pero no bloquean avanzar si el objetivo es mantener ritmo.

---

## [2026-06-04 23:01] Inicio de sesión - Credenciales y continuidad del bloque 3

**Prompt:** el usuario inició sesión usando la skill `session-memory`, pidió continuar con las tareas de la próxima sesión recogidas en `tareas_a_realizar.md`, pero priorizando corregir de nuevo el problema de credenciales.

**Resultado:** se utilizó la skill `session-memory`. Se revisó la última entrada de `conversation-log.md`, el plan de `tareas_a_realizar.md` y el estado inicial del repositorio, que estaba limpio al empezar. La prioridad inmediata queda fijada en diagnosticar el acceso a la plataforma antes de continuar con el Diseño del bloque 3.

**Decisión:** se revisarán primero backend, frontend, datos demo, estado de la base local, CSRF y CORS. Cuando el login funcione de forma estable, se retomará el plan de la próxima sesión: cerrar Análisis del bloque 3 y pasar a Diseño de carga de trabajo.

---

## [2026-06-05 01:38] Fin de sesión

**Prompt:** cierre de sesión solicitado con la skill `session-memory`, dejando ya indicada la fecha y hora y pidiendo completar únicamente el resumen.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se corrigió de nuevo el problema de credenciales de la plataforma, detectando que el fallo no estaba en los usuarios introducidos por el usuario sino en un backend antiguo que seguía ejecutándose. Se detuvo el proceso anterior, se arrancó el backend actualizado y se verificó por API que las credenciales demo funcionaban correctamente. Quedaron confirmados los accesos `docente.santander / docente123` e `investigador.barcelona / barcelona123`, además de la distinción funcional entre investigador-docente por sede y solo investigador.

También se revisó y ajustó el bloque 3 de carga de trabajo. Se incorporó la regla de dominio de que no todos los investigadores son docentes: sedes como Santander pueden operar con investigadores-docentes, mientras que sedes como Barcelona representan investigadores sin docencia. Se añadió un perfil demo de investigador no docente de Barcelona y se ajustó el panel principal para que el acceso a `Recompensas` dependa del rol y de la sede. Se avanzó el Desarrollo del bloque con backend, frontend y documentación RUP para `abrirOpcionesCargaTrabajo` y `editarCargaTrabajo` en Coordinador e Investigador.

Tras la aclaración final del usuario, se corrigió el enfoque de recompensas: un investigador-docente no puede superar 16 horas semanales de docencia, por lo que la carga de trabajo ya no genera compensaciones por exceso. Las recompensas quedan separadas del bloque de carga y pasan a estar asociadas a proyectos completados, pudiendo resolverse como recompensa económica o como reducción de horas docentes en el siguiente cuatrimestre. Se retiró del código el modelo y repositorio de `RecompensaCargaTrabajo`, se añadió la migración `V4__retirar_recompensas_carga_trabajo.sql`, se actualizaron DTOs, tests, frontend, README de Análisis/Diseño/Desarrollo y diagramas `.puml` relacionados.

La verificación quedó parcialmente realizada: `npx tsc -b` terminó correctamente y `git diff --check` no mostró errores reales, solo avisos normales de CRLF en Windows. No se pudieron ejecutar las pruebas Maven porque el entorno no permitió descargar el parent de Spring, y tampoco se pudieron regenerar los SVG de PlantUML porque no había PlantUML local y el uso del servidor externo no fue autorizado por el entorno.

**Decisión:** el siguiente paso recomendado es regenerar los SVG de los diagramas de Análisis y Diseño del bloque 3 cuando esté disponible PlantUML, ejecutar `mvnw.cmd test` con dependencias accesibles y reiniciar el backend para que aplique la migración V4. Después conviene probar manualmente el flujo de carga de trabajo con Coordinador, `docente.santander` e `investigador.barcelona`, capturar evidencias y pasar al módulo de recompensas/proyectos completados con la nueva regla ya aclarada.

---

## [2026-06-05 12:37] Inicio de sesión - Continuidad del bloque 3

**Prompt:** el usuario inició una nueva sesión usando la skill `session-memory` y pidió continuar desde el punto donde quedó la sesión anterior.

**Resultado:** se utilizó la skill `session-memory`. Se revisó `conversation-log.md`, `tareas_a_realizar.md`, la rama activa y el estado de Git. El repositorio está en `main` y, al inicio, solo aparece modificado `conversation-log.md` por el registro de memoria. La última decisión relevante deja el bloque 3 de carga de trabajo con la regla de dominio corregida: los investigadores-docentes no pueden superar 16 horas semanales de docencia y las recompensas no nacen por exceso de carga, sino por proyectos completados, pudiendo resolverse como recompensa económica o reducción docente en el siguiente cuatrimestre.

**Decisión:** la sesión continuará cerrando flecos del bloque 3: regenerar SVG de Análisis/Diseño si hay PlantUML disponible, validar backend con Maven cuando las dependencias estén accesibles, reiniciar backend para aplicar la migración V4, probar manualmente carga de trabajo con Coordinador, `docente.santander` e `investigador.barcelona`, y preparar el paso al módulo de recompensas/proyectos completados.

---

## [2026-06-05 13:45] Fin de sesión - Bloque 3 probado y pendiente de revisión final

**Prompt:** cierre de sesión solicitado con la skill `session-memory`, pidiendo actualizar `tareas_a_realizar.md` y dejar como siguiente paso revisar el bloque 3 entero.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se cerraron los flecos técnicos del bloque 3 de carga de trabajo. Se descargó PlantUML para uso local en `tools/plantuml.jar`, se añadió esa ruta a `.gitignore` para evitar subir el binario y se regeneraron los SVG de Análisis y Diseño de `abrirOpcionesCargaTrabajo` y `editarCargaTrabajo` para Coordinador e Investigador.

También se corrigió el problema persistente de credenciales demo. La causa no era solo un backend antiguo, sino que la base H2 local podía conservar usuarios demo ya existentes con contraseña antigua o estado inactivo. Se ajustó `DemoDataConfig` para reactivar y actualizar contraseña, perfil y sede en cada arranque, y se añadieron métodos explícitos en `Usuario` para actualizar el hash de contraseña y reactivar la cuenta. Tras reiniciar backend, quedaron validados por API los accesos `docente.santander / docente123` e `investigador.barcelona / barcelona123`.

Se revisó además la regla funcional de sedes: Santander representa investigadores-docentes y Barcelona investigadores sin docencia por sede. El frontend normaliza la docencia de investigadores-docentes para que no supere 16 horas semanales y bloquea la docencia cuando no aplica por sede. El backend refuerza la misma regla rechazando cualquier docencia mayor que cero para investigadores no docentes. Se actualizaron las pruebas de integración de carga de trabajo, `incidencias_y_soluciones.md` y `tareas_a_realizar.md`.

La verificación final quedó correcta: `mvnw.cmd test` ejecutó 21 pruebas sin fallos, `npm run build` generó el build de producción correctamente, `git diff --check` no mostró errores reales y el backend quedó arrancado en el puerto 8080. El usuario comprobó manualmente en navegador los flujos de `docente.santander`, `investigador.barcelona` y el panel del Coordinador, incluyendo actualización de carga y sugerencias de asignación.

**Decisión:** el siguiente paso será revisar el bloque 3 entero antes de pasar al módulo de recompensas/proyectos completados. Esa revisión debe cubrir Detalle, Análisis, Diseño, Desarrollo, Pruebas, diagramas, SVG, capturas, trazabilidad y coherencia con la regla final: los investigadores-docentes no superan 16 horas de docencia semanal, los investigadores de sedes sin docencia no registran docencia, y las recompensas nacen de proyectos completados, no de exceso de carga.

---

## [2026-06-05 13:46] Inicio de sesión - Revisión integral del bloque 3

**Prompt:** el usuario inició una nueva sesión usando la skill `session-memory` y pidió empezar la revisión completa del bloque 3 antes de pasar al bloque 4.

**Resultado:** se utilizó la skill `session-memory`. Se revisó el cierre anterior, `tareas_a_realizar.md`, la rama activa y los commits recientes. El último commit es `fix(carga-trabajo): consolidar reglas por sede`, por lo que el bloque 3 parte ya implementado, probado y con SVG regenerados.

**Decisión:** la sesión se dedicará a auditar el bloque 3 completo: Detalle, Análisis, Diseño, Desarrollo, Pruebas, diagramas, SVG, capturas, trazabilidad y coherencia de dominio antes de empezar recompensas/proyectos completados.

---

## [2026-06-05 18:48] Fin de sesión

**Prompt:** cierre de sesión solicitado con la skill `session-memory`, indicando que el siguiente paso será la revisión de Análisis del cuarto bloque.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se cerró la revisión integral del bloque 3 de carga de trabajo y se pasó al bloque 4, centrado en recompensas asociadas a proyectos completados. Se actualizaron los README de Detalle de recompensas para Coordinador e Investigador, incorporando la regla final del dominio: las recompensas no nacen por exceso de carga docente, sino por proyectos completados; los investigadores-docentes pueden recibir recompensa económica o reducción docente en el siguiente cuatrimestre; y los investigadores de sedes sin docencia solo pueden recibir recompensa económica.

También se avanzó el Análisis del bloque 4. Se actualizaron los README de Análisis y los diagramas `colaboracion.puml` de `abrirRecompensas`, `abrirRecompensa`, `crearRecompensa`, `editarRecompensa` y `eliminarRecompensa` para Coordinador, y de `abrirRecompensas` y `abrirRecompensa` para Investigador. Los diagramas incorporan explícitamente las entidades `Proyecto`, `Investigador` y `Recompensa`, diferenciando que el Coordinador mantiene las recompensas globales mientras que el Investigador solo consulta recompensas propias. Se regeneraron los SVG correspondientes en `images/RUP/01-analisis/casos-uso`.

Se actualizó `tareas_a_realizar.md` dejando el bloque 4 como pendiente inmediato. El repositorio queda con cambios documentales y de diagramas pendientes de revisión/commit: Detalle del bloque 4, Análisis del bloque 4, SVG de Análisis y este cierre de sesión.

**Decisión:** el siguiente paso recomendado es revisar manualmente el Análisis completo del cuarto bloque antes de pasar a Diseño. Esa revisión debe confirmar que los casos distinguen correctamente Coordinador e Investigador, que las recompensas proceden de proyectos completados, que el Investigador solo consulta las propias y que la regla de tipos queda clara: investigador-docente puede tener recompensa económica o reducción docente; investigador sin docencia solo recompensa económica.

---

## [2026-06-05 19:04] Fin de sesión - Verificación del contexto de recompensas

**Prompt:** el usuario detectó una posible incoherencia porque no recordaba ver `crearRecompensa()` en el diagrama de contexto y pidió confirmar si el Análisis se estaba siguiendo desde ese artefacto.

**Resultado:** se revisó el PlantUML fuente de los diagramas de contexto en `modelosUML/rup/00-casos-uso/01-actores-casos-uso`. Se confirmó que el diagrama de Coordinador sí contiene la transición `RECOMPENSAS_ABIERTAS --> RECOMPENSA_ABIERTA: crearRecompensa()`, además de `editarRecompensa()` y `eliminarRecompensa()`. También se confirmó que el diagrama de Investigador no incluye creación, edición ni eliminación de recompensas, lo cual es coherente con la regla de que solo consulta recompensas propias. Se regeneraron los SVG de contexto en `images/RUP/00-casos-uso/01-actores-casos-uso` para evitar desfases entre el fuente y la imagen visible.

**Decisión:** se mantiene `crearRecompensa()` en Detalle y Análisis del Coordinador porque está respaldado por el diagrama de contexto. La próxima sesión debe empezar revisando el Análisis del bloque 4, prestando especial atención a que todos los casos sigan las transiciones permitidas por el contexto.

## [2026-06-05 21:10] Inicio de sesión - Revisión del Análisis del bloque 4

**Prompt:** el usuario inició una nueva sesión usando la skill `session-memory` y pidió repasar el Análisis completo del bloque 4 antes de continuar con Diseño.

**Resultado:** se utilizó la skill `session-memory`. Se revisaron la última entrada de `conversation-log.md`, las tareas pendientes del bloque 4, el estado del repositorio y las transiciones de los diagramas de contexto. Se confirmó que el Coordinador dispone de `abrirRecompensas()`, `abrirRecompensa()`, `crearRecompensa()`, `editarRecompensa()` y `eliminarRecompensa()`, mientras que el Investigador únicamente dispone de los casos de consulta `abrirRecompensas()` y `abrirRecompensa()`.

**Decisión:** la sesión se dedicará a revisar los README y diagramas de colaboración del bloque 4, corrigiendo cualquier estado abstracto, método genérico o incoherencia con las reglas de dominio: las recompensas proceden de proyectos completados, el Investigador solo consulta recompensas propias, los investigadores-docentes pueden recibir recompensa económica o reducción docente y los investigadores sin docencia solo pueden recibir recompensa económica.

---

## [2026-06-05 21:44] Fin de sesión - Análisis del bloque 4 revisado

**Prompt:** cierre de la sesión dedicada a revisar el Análisis del bloque 4, cuya fecha y hora quedaron indicadas previamente en `conversation-log.md`.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se revisó el Análisis de recompensas para Coordinador e Investigador y se corrigieron los README de `abrirRecompensas`, `abrirRecompensa`, `crearRecompensa`, `editarRecompensa` y `eliminarRecompensa`. Se sustituyeron estados y salidas genéricas por las transiciones concretas del diagrama de contexto, se eliminaron referencias antiguas a permisos abstractos y se ajustaron las colaboraciones con `RecompensaRepository`, `ProyectoRepository` e `InvestigadorRepository`.

El análisis quedó alineado con las reglas finales del dominio. El Coordinador mantiene acceso global y puede crear, editar y eliminar recompensas. El Investigador únicamente puede listar y consultar sus propias recompensas. Todas las recompensas conservan su trazabilidad hacia un proyecto completado y un investigador beneficiario. Los investigadores-docentes pueden recibir recompensa económica o reducción docente, mientras que los investigadores sin docencia solo pueden recibir recompensa económica.

También se corrigió el flujo de `eliminarRecompensa()` para recuperar la recompensa antes de validar su proyecto de origen y eliminarla, se actualizó su `colaboracion.puml` y se regeneró el SVG asociado. La validación final de los siete diagramas PlantUML del bloque 4 terminó correctamente y no se encontraron operaciones CRUD de recompensas dentro del Análisis del Investigador. Finalmente, se actualizó `tareas_a_realizar.md`, marcando como completada la revisión del Análisis del bloque 4.

**Decisión:** el Análisis del bloque 4 queda revisado y preparado para avanzar. El siguiente paso recomendado es comenzar el Diseño del bloque 4, creando y revisando los README, diagramas `secuencia.puml` y SVG de recompensas por actor, manteniendo la trazabilidad con Detalle, Análisis y diagrama de contexto.

---

## [2026-06-06 14:59] Inicio de sesión - Revisión de colaboraciones de crearRecompensa

**Prompt:** el usuario inició una nueva sesión usando la skill `session-memory` y planteó que el caso de uso `crearRecompensa()` podría necesitar más colaboraciones antes de comenzar el Diseño del bloque 4.

**Resultado:** se utilizó la skill `session-memory`. Se revisaron el Detalle, la especificación y el diagrama de colaboración actuales de `crearRecompensa()`, comparándolos con otros casos de creación y con el diagrama de contexto. Se detectó que el Análisis ya incluye acceso a `ProyectoRepository` e `InvestigadorRepository` para validar el proyecto completado y el beneficiario, pero el Detalle únicamente solicita tipo y cantidad. Por ello, no queda explicado cómo el Coordinador obtiene y selecciona el proyecto completado, el investigador beneficiario ni los tipos de recompensa permitidos antes de guardar.

**Decisión:** antes de pasar a Diseño se revisará `crearRecompensa()` para distinguir entre colaboraciones externas con otros casos de uso y colaboraciones internas con clases de análisis. Según el contexto actual, la única colaboración externa posterior necesaria es `abrirRecompensa()`. Sin embargo, el flujo interno debe ampliarse para listar proyectos completados pendientes de recompensa, obtener beneficiarios elegibles y restringir el tipo de recompensa según la condición docente y la sede.

---

## [2026-06-06 16:54] Fin de sesión
