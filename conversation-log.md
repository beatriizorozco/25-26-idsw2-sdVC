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

## [2026-06-06 16:54] Fin de sesión - Desarrollo del bloque 4 implementado y revisado

**Prompt:** cierre de la sesión solicitado posteriormente con la skill `session-memory`, debido a que el límite de la sesión anterior se agotó antes de poder completar el resumen.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se corrigió y amplió la colaboración de `crearRecompensa()` para representar correctamente la selección de proyectos completados pendientes, beneficiarios elegibles y tipos de recompensa permitidos según la condición docente y la sede. Después se completó y revisó el Diseño del bloque 4 de recompensas para Coordinador e Investigador, manteniendo la trazabilidad con Detalle, Análisis y los diagramas de contexto.

Se avanzó también al Desarrollo del bloque 4. En backend se incorporaron el modelo de proyectos completados y recompensas, sus repositorios, DTOs, servicio, controlador, migración de base de datos, datos demo y pruebas de integración. En frontend se implementó la pantalla de recompensas, con listado, filtrado, consulta propia y global, creación, edición y eliminación según el actor. Se añadieron igualmente los README de Desarrollo de los siete casos de uso del bloque y se actualizaron los índices y tareas relacionadas.

La revisión final confirmó que la base técnica funciona: las 25 pruebas backend terminaron sin fallos, `npm run build` generó correctamente el frontend de producción y `npm run lint` no encontró errores. No obstante, se detectaron varios puntos que deben corregirse antes de cerrar completamente el bloque: actualmente se muestran todos los proyectos completados aunque ya no tengan recompensas válidas pendientes; los perfiles demo desactivados pueden reactivarse al reiniciar por `DemoDataConfig`; la selección de proyecto en frontend no gestiona errores; la edición muestra el identificador numérico del proyecto; el frontend no consulta el endpoint diseñado para obtener tipos permitidos tras seleccionar beneficiario; y existe documentación antigua que afirma incorrectamente que `investigador.barcelona` no accede a recompensas.

**Decisión:** el bloque 4 queda implementado y con su primera revisión técnica completada, pero todavía no debe considerarse cerrado. La siguiente sesión debe corregir los problemas detectados, ampliar las pruebas de recompensas para cubrir edición, duplicados, beneficiarios ajenos, acceso propio y proyectos completamente recompensados, actualizar la documentación desfasada y realizar una nueva verificación integral antes de pasar al siguiente bloque.

---

## [2026-06-06 23:10] Inicio de sesión - Corrección del desarrollo del bloque 4

**Prompt:** el usuario inició una nueva sesión usando la skill `session-memory` y pidió corregir los errores detectados durante la revisión del Desarrollo del bloque 4.

**Resultado:** se utilizó la skill `session-memory`. Se revisaron el cierre anterior, `tareas_a_realizar.md`, la rama activa y los commits recientes. El repositorio está en `main`; al inicio únicamente aparece modificado `conversation-log.md` por el cierre pendiente completado. El bloque 4 está implementado y verificado inicialmente, pero conserva inconsistencias funcionales en proyectos pendientes de recompensa, reactivación de perfiles demo, manejo de errores y trazabilidad del frontend, documentación antigua y cobertura de pruebas.

**Decisión:** la sesión se dedicará a corregir los hallazgos del bloque 4, ampliar las pruebas automáticas y realizar una nueva verificación integral antes de considerarlo cerrado o pasar al bloque 5.

---

## [2026-06-07 01:02] Fin de sesión - Bloque 4 cerrado y preparado para bloque 5

**Prompt:** cierre de sesión solicitado mediante la skill `session-memory`, dejando como siguiente paso comenzar el bloque 5.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se corrigió y revisó integralmente el Desarrollo del bloque 4 de recompensas. Se ajustaron backend y frontend para mostrar únicamente proyectos, beneficiarios y tipos de recompensa todavía pendientes; se reforzaron los permisos, la consulta de detalle, la edición y la protección frente a duplicados; y se evitó que los perfiles demo eliminados se reactivaran al reiniciar. También se creó un proyecto completado adicional para comprobar correctamente la concesión de reducción docente.

Se incorporó la regla de dominio que exige que las reducciones docentes sean múltiplos de cuatro horas, equivalentes a asignaturas completas. Esta validación quedó aplicada en backend, base de datos mediante la migración `V6__validar_reduccion_docente.sql`, frontend, pruebas y documentación RUP. Los diagramas PlantUML afectados se actualizaron y sus SVG se regeneraron.

Además, se corrigió de forma estable el error recurrente del proxy de Vite cuando el backend no estaba iniciado, incorporando `tools/start-dev.ps1` y un arranque coordinado desde `npm run dev`. La verificación final terminó con 32 pruebas backend correctas, `npm run lint` y `npm run build` sin errores. El usuario realizó pruebas manuales con Coordinador, `docente.santander` e `investigador.barcelona`, confirmando creación, filtrado, consulta y eliminación de recompensas, así como las diferencias entre recompensas económicas y reducciones docentes.

**Decisión:** el bloque 4 queda completamente revisado y cerrado. El siguiente paso será comenzar el bloque 5, identificando primero sus casos de uso y revisando su coherencia con los diagramas de contexto antes de desarrollar el Detalle y avanzar posteriormente a Análisis.

---

## [2026-06-08 13:05] Inicio de sesión - Detalle del bloque 5 de proyectos

**Prompt:** el usuario inició una nueva sesión mediante la skill `session-memory`, pidió leer `conversation-log.md` y `tareas_a_realizar.md` y continuar con el bloque 5.

**Resultado:** se utilizó la skill `session-memory`. Se reconstruyó el contexto desde el cierre anterior, se comprobó que el repositorio está limpio y sincronizado en `main` tras el commit de cierre del bloque 4, y se revisaron la priorización, los índices y los diagramas de contexto de Coordinador e Investigador. Se identificó el bloque 5 como la gestión de proyectos y composición de sus equipos. El alcance incluye para Coordinador `abrirProyectos()`, `abrirProyecto()`, `crearProyecto()`, `editarProyecto()`, `eliminarProyecto()`, `agregarInvestigador()` y `eliminarInvestigador()`; para Investigador incluye `abrirProyectos()` y `abrirProyecto()`.

La revisión inicial detectó dos incoherencias funcionales relevantes. `abrirProyecto()` repite actualmente el listado y filtrado de proyectos, aunque el diagrama de contexto indica que se invoca desde `PROYECTOS_ABIERTOS` sobre un proyecto seleccionado y debe presentar directamente su detalle. Además, `agregarInvestigador()` solicita nuevamente ID, nombre y campo como si crease un investigador, cuando debe asociar al proyecto un perfil ya existente y considerar su disponibilidad y carga de trabajo.

**Decisión:** la sesión se dedicará a corregir y revisar el Detalle completo del bloque 5, comenzando por los diagramas de especificación y alineando después los README y prototipos. Antes de avanzar a Análisis se solicitará una revisión manual del usuario.

---

## [2026-06-08 17:32] Fin de sesión - Análisis y Diseño del bloque 5 con archivado histórico

**Prompt:** cierre de sesión solicitado mediante la skill `session-memory`, indicando como hora final las 17:32.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se terminó de revisar el Análisis del bloque 5 de gestión de proyectos y composición de equipos. Se validaron los casos del Coordinador `abrirProyectos()`, `abrirProyecto()`, `crearProyecto()`, `editarProyecto()`, `eliminarProyecto()`, `agregarInvestigador()` y `eliminarInvestigador()`, además de `abrirProyectos()` y `abrirProyecto()` para el Investigador. Las colaboraciones se contrastaron con los diagramas de contexto y se corrigieron sus salidas, incluyendo la colaboración de `agregarInvestigador()` con `abrirInvestigadores()` y las acciones disponibles desde `PROYECTO_ABIERTO`.

También se creó el Diseño completo del bloque 5 para ambos actores, con sus README, diagramas `secuencia.puml` y SVG. El Diseño diferencia la gestión global del Coordinador de la consulta de proyectos propios del Investigador, asigna al servidor la creación del código, Coordinador y estado inicial del proyecto, y establece que agregar o retirar investigadores modifica únicamente su asociación con el proyecto, sin crear ni eliminar perfiles. Los diagramas PlantUML generados fueron validados correctamente.

Durante la revisión final se tomó una decisión relevante de dominio: `eliminarProyecto()` conservará su nombre por trazabilidad, pero realizará un archivado lógico en lugar de una eliminación física. El proyecto archivado mantiene su estado original, equipo, entregables, recompensas y relaciones históricas, registra quién y cuándo lo archivó y deja de aparecer en los listados activos. Esta decisión se propagó por el modelo del dominio, Detalle, Análisis, Diseño, diagramas de contexto, fuentes PlantUML, SVG, `incidencias_y_soluciones.md` y `tareas_a_realizar.md`.

**Decisión:** el Detalle y el Análisis del bloque 5 quedan revisados, y su Diseño queda creado y validado técnicamente. Antes de avanzar a Desarrollo se debe revisar manualmente el Diseño completo y diseñar una consulta de histórico de proyectos archivados para el Coordinador, garantizando que el archivado lógico pueda consultarse y mantenga toda la trazabilidad del proyecto.

---

## [2026-06-08 19:16] Inicio de sesión - Política transversal de bajas lógicas

**Prompt:** el usuario inició una nueva sesión mediante la skill `session-memory` y planteó si todos los casos de uso `eliminar...` deberían conservar un histórico, especialmente los investigadores que dejan de estar activos.

**Resultado:** se utilizó la skill `session-memory`. Se recuperó el cierre anterior, se revisó el estado del repositorio y se inventariaron los casos de eliminación existentes. Se confirmó que el backend ya implementa correctamente `eliminarPerfil()` como una desactivación del usuario mediante el atributo `activo`, conservando el perfil y su trazabilidad, aunque parte de la documentación todavía lo presenta como una eliminación irreversible. También se distinguieron tres comportamientos diferentes bajo nombres históricos `eliminar...`: baja lógica de entidades históricas, desasignación de relaciones y borrado físico excepcional de datos sin valor histórico.

**Decisión:** se adoptará como regla general que perfiles, proyectos, publicaciones, entregables y recompensas con valor de auditoría o relaciones históricas no se eliminan físicamente, sino que se desactivan, archivan o anulan. `eliminarInvestigador()` dentro de un proyecto seguirá siendo únicamente una desasignación. Antes de modificar transversalmente los bloques ya cerrados se revisará cada caso de eliminación para definir su semántica exacta y mantener coherentes Detalle, Análisis, Diseño, Desarrollo y Pruebas.

---

## [2026-06-08 22:34] Fin de sesión - Desarrollo del bloque 5 y adjuntos de proyectos

**Prompt:** cierre de sesión solicitado mediante la skill `session-memory` tras implementar y comprobar el Desarrollo del bloque 5.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se completó el Desarrollo del bloque 5 de gestión de proyectos para Coordinador e Investigador. Se implementaron la creación, edición, consulta, filtrado, archivado lógico e histórico de proyectos, además de la asignación y desasignación de investigadores con registro histórico de movimientos. El Coordinador puede alternar entre proyectos activos y archivados, mientras que el Investigador consulta exclusivamente aquellos en los que participa.

Se sincronizaron los conceptos de finalización y archivado: marcar un proyecto como completado provoca su archivado automático y archivar un proyecto lo marca como completado. Los proyectos archivados permanecen consultables y conservan participantes, relaciones y documentación. También se mejoró la visibilidad de los controles de los formularios de creación y edición.

Se incorporó la gestión de archivos adjuntos mediante la migración `V8__archivos_proyecto.sql`, modelo, repositorio, servicio, controlador, API y componentes frontend. Coordinador e Investigadores participantes pueden listar, subir y descargar documentos de proyectos activos o archivados; únicamente el Coordinador puede eliminarlos. Se corrigió la compatibilidad del contenido binario entre PostgreSQL y H2 mediante un mapeo `VARBINARY`.

Durante la prueba manual apareció un error al subir archivos porque el navegador utilizaba el frontend actualizado contra un proceso backend iniciado antes de incorporar los nuevos endpoints y la migración. Se reinició el backend, se aplicó correctamente la migración V8 y se comprobó por API una subida real con respuesta `201 Created` y su posterior eliminación por Coordinador con respuesta `204 No Content`.

Se actualizaron los README de Desarrollo, `tareas_a_realizar.md` e `incidencias_y_soluciones.md`. La validación final terminó con 38 pruebas backend correctas, `npm run build`, `npm run lint` y `git diff --check` sin errores.

**Decisión:** el Desarrollo del bloque 5 queda funcional y técnicamente verificado. Antes de pasar al bloque 6 se realizará una prueba manual completa desde el navegador con Coordinador e Investigador, comprobando proyectos activos y archivados, asignaciones, finalización automática y permisos de subida, descarga y eliminación de adjuntos.

---

## [2026-06-09 00:00] Inicio de sesión - Revisión del bloque 6 de investigadores

**Prompt:** el usuario inició una nueva sesión mediante la skill `session-memory` y solicitó revisar el Detalle y realizar el Análisis del bloque 6 de investigadores.

**Resultado:** se utilizó la skill `session-memory`. Se recuperó el cierre del bloque 5, se comprobó que el repositorio está en `main` tras el commit `74ffc5b` y se contrastaron los casos existentes con los diagramas de contexto de Coordinador e Investigador. El bloque 6 queda delimitado como el directorio y la consulta de investigadores: para Coordinador incluye `abrirInvestigadores()`, `abrirInvestigador()` y `crearInvestigador()`; para Investigador incluye `abrirInvestigadores()` y `abrirInvestigador()`.

**Decisión:** `eliminarInvestigador()` no formará parte del bloque 6 porque representa la desasignación de un investigador desde un proyecto y ya pertenece al bloque 5. La revisión distinguirá la apertura global del directorio desde `PANEL_PRINCIPAL_ABIERTO` y la apertura contextual desde `PROYECTO_ABIERTO`, manteniendo un único caso de uso cuando el comportamiento interno pueda resolverse mediante un proyecto opcional.

---

## [2026-06-09 00:20] Fin de sesión - Detalle y Análisis del bloque 6 de investigadores

**Prompt:** cierre de sesión solicitado mediante la skill `session-memory` tras revisar el Detalle y completar el Análisis del bloque 6.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se revisaron y corrigieron los casos de uso de investigadores para Coordinador e Investigador. Para Coordinador se trabajaron `abrirInvestigadores()`, `abrirInvestigador()` y `crearInvestigador()`; para Investigador se revisaron `abrirInvestigadores()` y `abrirInvestigador()`. Se alinearon los README, diagramas de especificación y prototipos con los diagramas de contexto y con las reglas reales del proyecto.

Se modeló `abrirInvestigadores(idProyecto)` como un único caso de uso con dos alcances: sin identificador presenta el directorio global de investigadores activos al Coordinador; con identificador presenta los participantes del proyecto visible. `abrirInvestigador()` quedó definido como la consulta de un perfil individual, mostrando su condición docente según la sede y sus proyectos asociados o visibles. `crearInvestigador()` registra usuario, nombre, correo, sede y datos profesionales, comprueba duplicados y abre el detalle creado, sin redirigir a un caso de edición inexistente.

También se creó y revisó el Análisis MVC de los cinco casos, diferenciando actores, vistas, controladores, `InvestigadorRepository`, `ProyectoRepository` y entidades conceptuales. Se corrigió la ubicación de las validaciones y permisos, evitando delegarlos incorrectamente en los repositorios. Los quince SVG de especificación, prototipado y colaboración fueron regenerados mediante PlantUML y se comprobó su presencia en `images/RUP`. `tareas_a_realizar.md` quedó actualizado con el seguimiento del bloque 6. La verificación final incluyó validación PlantUML, búsqueda de contradicciones residuales y `git diff --check` sin errores. No se realizó ningún commit durante la sesión.

**Decisión:** el Detalle y el Análisis del bloque 6 quedan terminados y técnicamente verificados. `eliminarInvestigador()` continúa perteneciendo al bloque 5 porque representa una desasignación de proyecto, no la baja del perfil. El siguiente paso será revisar manualmente el bloque 6 y, si se aprueba, comenzar su Diseño.

---

## [2026-06-10 01:19] Inicio de sesión - Diseño del bloque 6 de investigadores

**Prompt:** inicio de sesión solicitado mediante la skill `session-memory` para comenzar el Diseño del bloque 6: investigadores.

**Resultado:** se utilizó la skill `session-memory`. Se revisaron `conversation-log.md`, `tareas_a_realizar.md`, la rama activa `main` y los commits recientes. El bloque 6 llega con Detalle y Análisis ya cerrados en primera iteración para Coordinador e Investigador. Se confirmó que el siguiente paso consiste en construir el Diseño de `abrirInvestigadores()`, `abrirInvestigador()` y `crearInvestigador()` para Coordinador, además de `abrirInvestigadores()` y `abrirInvestigador()` para Investigador, reutilizando el patrón ya asentado en el bloque 5.

**Decisión:** la sesión se centrará en crear los README de Diseño, sus diagramas `secuencia.puml` y los SVG correspondientes, manteniendo la trazabilidad con Detalle y Análisis antes de pasar al Desarrollo del bloque 6.

---

## [2026-06-10 01:32] Fin de sesión - Diseño del bloque 6 de investigadores completado

**Prompt:** cierre de sesión solicitado mediante la skill `session-memory`.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se creó el Diseño completo del bloque 6 de investigadores. Se añadieron los README y los diagramas `secuencia.puml` para `abrirInvestigadores()`, `abrirInvestigador()` y `crearInvestigador()` del Coordinador, además de `abrirInvestigadores()` y `abrirInvestigador()` del Investigador. El diseño se apoyó en el patrón del bloque 5 para mantener consistencia de estructura, rutas, estilo documental y nivel de detalle.

Las secuencias definen el comportamiento esperado entre frontend, controlador, `SesionService`, servicios y repositorios, diferenciando correctamente el acceso global del Coordinador frente al acceso contextual del Investigador. También se mantuvo la decisión previa de no introducir `eliminarInvestigador()` en este bloque, ya que sigue perteneciendo al bloque 5 como desasignación dentro de proyecto. Los cinco diagramas se validaron con PlantUML y sus SVG quedaron publicados en `images/RUP/02-diseño/casos-uso/...`. Además, se actualizaron los índices de Diseño de Coordinador e Investigador para incluir los nuevos casos y `tareas_a_realizar.md` quedó alineado con el estado actual. No se realizó ningún commit durante la sesión.

**Decisión:** el bloque 6 queda con Detalle, Análisis y Diseño terminados. El siguiente paso recomendado es revisar manualmente este bloque completo y, si se aprueba, comenzar el Desarrollo de investigadores para Coordinador e Investigador.

---

## [2026-06-11 02:43] Inicio de sesión - Revisión del diseño del bloque 6

**Prompt:** inicio de sesión solicitado mediante la skill `session-memory` para revisar si el Diseño del bloque 6 de investigadores está bien resuelto y, si lo está, pasar a Desarrollo.

**Resultado:** se utilizó la skill `session-memory`. Se revisaron `conversation-log.md`, `tareas_a_realizar.md`, la rama activa `main`, el estado del repositorio y los commits recientes. El bloque 6 llega con Detalle, Análisis y Diseño ya creados, incluyendo el commit `b37558f`, centrado en el Diseño de investigadores para Coordinador e Investigador.

**Decisión:** la sesión se dedicará primero a contrastar los cinco casos de Diseño con sus artefactos previos y con los diagramas de contexto. Si la revisión confirma coherencia funcional y trazabilidad suficiente, el siguiente paso será comenzar el Desarrollo del bloque 6; si aparecen inconsistencias, se corregirán antes de avanzar.

---

## [04:00] Fin de sesión

**Prompt:** cierre automático de sesión solicitado mediante la skill `session-memory`.

**Resultado:** se utilizó la skill `session-memory`. Durante la sesión se revisó y ajustó el Diseño del bloque 6 de investigadores para alinearlo con la implementación real del backend y la navegación existente. Se corrigieron los README y secuencias de `abrirInvestigadores()`, `abrirInvestigador()` y `crearInvestigador()` para Coordinador, además de `abrirInvestigadores()` y `abrirInvestigador()` para Investigador, afinando participantes, repositorios utilizados, alcance global o contextual y la creación inicial de `CargaTrabajo` al registrar un nuevo investigador. También se añadieron los README de Desarrollo del bloque 6 para Coordinador e Investigador y se actualizaron sus índices.

En paralelo se avanzó en la implementación del módulo de investigadores: controlador, servicio, DTOs, pruebas de integración backend y la nueva pantalla frontend de directorio y detalle. Sin embargo, la verificación manual quedó bloqueada por un problema de arranque del backend. Se intentó estabilizar `tools/start-dev.ps1`, pero el fallo persistente terminó localizándose en el arranque de Spring Boot bajo el entorno actual, con errores relacionados con la JVM y con `JAVA_TOOL_OPTIONS`, además de ejecuciones fallidas de `spring-boot:run`. El frontend siguió compilando correctamente con `npm run build`, por lo que el bloqueo actual afecta al proceso backend y al script de arranque, no a la estructura del bloque 6.

**Decisión:** el contenido funcional y documental del bloque 6 queda bastante avanzado, pero la sesión no cierra la validación manual porque el backend no está arrancando de forma estable. El siguiente paso recomendado es aislar el problema del entorno Java y del lanzamiento de Spring Boot en una terminal limpia, recuperar un arranque estable y, en cuanto funcione, revisar visualmente el bloque 6 en navegador antes de continuar.

---

## [10:05] Inicio de sesión - Decidir revisión del bloque 6 o salto al bloque 7

**Prompt:** inicio de sesión solicitado mediante la skill `session-memory` para decidir si conviene revisar globalmente el bloque 6 o pasar a revisar Desarrollo y Análisis del bloque 7.

**Resultado:** se utilizó la skill `session-memory`. Se revisó el cierre anterior, `conversation-log.md` y `tareas_a_realizar.md`. El bloque 6 de investigadores llega con Detalle, Análisis, Diseño y gran parte del Desarrollo ya preparados, pero con una validación manual pendiente por el problema de arranque del backend detectado al final de la sesión anterior.

**Decisión:** antes de avanzar al bloque 7 conviene cerrar una revisión global breve del bloque 6, centrada en confirmar consistencia documental y, sobre todo, recuperar un arranque estable del backend para poder validar manualmente la funcionalidad. Solo después de esa comprobación tendrá sentido pasar a Análisis y Desarrollo del bloque 7 con tranquilidad.
---

## [10:58] Fin de sesión - Análisis corregido y Diseño del bloque 7 completado

**Prompt:** el usuario decidió aplazar la reparación del arranque de Spring Boot y solicitó avanzar al bloque 7. Al finalizar el Diseño, pidió cerrar la sesión utilizando la skill `session-memory`.

**Resultado:** se utilizó la skill `session-memory`. Se identificó el bloque 7 como la gestión de entregables, compuesto por `abrirEntregables()`, `abrirEntregable()`, `crearEntregable()`, `editarEntregable()` y `eliminarEntregable()` para Coordinador e Investigador. Se revisaron los diagramas de contexto, el Detalle y el Análisis existentes. Las diez colaboraciones de Análisis se corrigieron para establecer que el listado siempre pertenece al proyecto abierto, eliminar el supuesto listado global, comprobar la participación del Investigador para consultar y exigir autoría para editar o retirar.

Se creó el Diseño completo de los diez casos mediante sus `README.md` y `secuencia.puml`. Las secuencias distinguen el acceso global del Coordinador y los permisos del Investigador, incorporan archivos y versiones, validación del proyecto, prevención de duplicados y retirada lógica con motivo, fecha y responsable. También se actualizaron los índices de Diseño por actor y `tareas_a_realizar.md`. Finalmente se regeneraron los diez SVG de Análisis y se generaron los diez SVG de Diseño mediante PlantUML local. La verificación confirmó que todos los casos cuentan con README, fuente PlantUML y SVG, y `git diff --check` no detectó errores. No se realizó ningún commit durante la sesión.

**Decisión:** el Análisis corregido y el Diseño del bloque 7 quedan terminados. El problema local de arranque de Spring Boot queda aplazado por decisión del usuario para priorizar la finalización documental del trabajo. El siguiente paso será revisar manualmente el Diseño del bloque 7 y, si se aprueba, comenzar su Desarrollo.

---

## [17:54] Inicio de sesión - Revisión del Análisis del bloque 7

**Prompt:** el usuario inició una nueva sesión mediante la skill `session-memory` y solicitó revisar el Análisis para confirmar si puede pasar a Diseño.

**Resultado:** se utilizó la skill `session-memory`. Se revisaron `conversation-log.md`, `tareas_a_realizar.md`, la rama activa `main`, el estado limpio del repositorio y los commits recientes. El bloque 7 de entregables ya cuenta con Detalle, Análisis y una primera versión de Diseño incluida en el commit `d35bcb5`.

**Decisión:** antes de considerar aprobado el paso a Diseño, se auditarán las diez colaboraciones de Análisis y sus README contra los diagramas de contexto y las especificaciones detalladas. Se corregirá cualquier texto genérico, permiso incorrecto o contradicción de navegación encontrada.

---

## [18:56] Fin de sesión - Diseño transversal y Desarrollo del bloque 7 completados

**Prompt:** el usuario solicitó completar y revisar el Desarrollo del bloque 7 y, una vez terminado, cerrar la sesión mediante la skill `session-memory`.

**Resultado:** se utilizó la skill `session-memory`. Primero se completó una revisión transversal de los 50 diagramas de secuencia de Diseño: 46 documentan ahora el acceso a base de datos con consultas SQL concretas y los cuatro casos de navegación o sesión sin persistencia lo indican expresamente. Se corrigieron consultas genéricas o incoherentes, se regeneraron todos los SVG de Diseño y se comprobó su correspondencia con las fuentes PlantUML.

Después se implementó el Desarrollo completo del bloque 7 de entregables. El backend incorpora la migración `V9__entregables.sql`, entidades, repositorios, DTOs, servicio y controlador para gestionar entregables, archivos y versiones. Se aplicaron permisos diferenciados para Coordinador e Investigador, edición y retirada restringidas por autoría, conservación histórica mediante retirada lógica y bloqueo de modificaciones en proyectos archivados. El frontend integra la gestión de entregables dentro del detalle de proyecto, incluyendo listado, consulta, creación, edición, retirada y descarga de versiones. También se añadieron los diez README de Desarrollo y se actualizaron los índices y `tareas_a_realizar.md`.

La revisión final confirmó que las 43 pruebas del backend terminan con 0 fallos y 0 errores, que el frontend genera correctamente la compilación de producción y que `git diff --check` no detecta errores. No se realizó ningún commit durante la sesión.

**Decisión:** el Diseño transversal y el Desarrollo automático del bloque 7 quedan terminados y verificados. La próxima sesión debe realizar la prueba manual en navegador con Coordinador e Investigador, comprobando permisos, creación, versionado, descarga y retirada lógica de entregables antes de continuar con el siguiente bloque o con la revisión global final.

---

## [20:44] Fin de sesión - Arranque estabilizado y presentación de recursos del proyecto mejorada

**Prompt:** el usuario solicitó corregir el fallo de inicio de Spring Boot, revisar la convivencia entre archivos adjuntos y entregables, aplicar los cambios visuales necesarios y cerrar la sesión mediante la skill `session-memory`.

**Resultado:** se utilizó la skill `session-memory`. Se localizó la causa real del fallo de Spring Boot en una caché CDS incompatible que mezclaba archivos del JDK 25 integrado por una extensión de VS Code con la ejecución requerida en Java 17, provocando `java.lang.UnsatisfiedLinkError: boolean java.io.Console.istty()`. Se actualizó `tools/start-dev.ps1` para validar y forzar un JDK 17 disponible, priorizarlo en `PATH` y desactivar la caché problemática mediante `-Xshare:off`. La verificación confirmó que Spring Boot responde correctamente en `/api/auth/csrf`.

También se revisó la distinción funcional entre documentación y entregables. Los antiguos archivos adjuntos se renombraron visualmente como **Documentación del proyecto**, definidos como material compartido de consulta y apoyo, mientras que los entregables permanecen como resultados formales con estado, autoría y versiones. Ambas secciones quedaron separadas visualmente, con textos explicativos y acciones generales del proyecto diferenciadas. Los proyectos archivados ya no permiten subir ni eliminar documentación.

La revisión final confirmó 43 pruebas backend con 0 fallos y 0 errores, compilación de producción del frontend correcta y `git diff --check` sin errores. Quedan modificados `tools/start-dev.ps1`, `ProyectosPage.tsx`, `EntregablesProyecto.tsx` y `styles.css`. No se realizó ningún commit durante la sesión.

**Decisión:** el arranque local queda estabilizado y la separación conceptual entre documentación y entregables queda clara y coherente. La próxima sesión debe comprobar visualmente la pantalla de proyectos con ambos roles, probar creación, versionado, descarga y retirada de entregables, y después continuar con el siguiente bloque o con la revisión global final.

---

## [22:59] Inicio de sesión - Preparación del bloque 8 de publicaciones

**Prompt:** el usuario inició una nueva sesión mediante la skill `session-memory` y preguntó si el proyecto está preparado para pasar al bloque 8.

**Resultado:** se utilizó la skill `session-memory`. Se revisaron `conversation-log.md`, `tareas_a_realizar.md`, la rama activa `main`, los commits recientes, el estado del repositorio y los diagramas de contexto. El bloque 7 de entregables cuenta con Detalle, Análisis, Diseño y Desarrollo terminados, 43 pruebas backend superadas y compilación de producción del frontend correcta. Su única comprobación pendiente es la prueba manual completa en navegador. También permanecen sin commit los últimos ajustes visuales que distinguen la documentación del proyecto de sus entregables y la estabilización de `tools/start-dev.ps1`.

Se identificó el bloque 8 como la familia funcional de publicaciones para Coordinador e Investigador: consulta de publicaciones globales, apertura de una publicación, respuesta, consulta de publicaciones propias, apertura de publicación propia, creación, edición y retirada lógica. Las convocatorias se mantienen separadas para un bloque posterior porque son entidades importadas y no siguen el mismo ciclo de gestión.

**Decisión:** el proyecto está preparado para comenzar el bloque 8 sin bloquearse por la prueba manual pendiente del bloque 7. Antes de avanzar profundamente conviene conservar los últimos ajustes en un commit independiente y comenzar el bloque 8 revisando su Detalle contra los diagramas de contexto, prestando especial atención a la diferencia entre publicaciones globales y propias y a la retirada lógica.

---

## [00:25] Fin de sesión - Desarrollo del bloque 8 de publicaciones completado

**Prompt:** el usuario solicitó implementar el Desarrollo del bloque 8, valorar si el 57 % de capacidad semanal restante permite terminar el proyecto y cerrar la sesión mediante la skill `session-memory`.

**Resultado:** se utilizó la skill `session-memory`. Se implementó el bloque 8 completo de publicaciones para Coordinador e Investigador. El backend incorpora la migración `V10__publicaciones.sql`, entidades separadas para publicaciones, respuestas y archivos, repositorios específicos, DTOs, `PublicacionService`, `PublicacionController` y `PoliticaPublicacion`. La política desacoplada centraliza las autorizaciones y permite que el Coordinador gestione cualquier publicación mientras que el Investigador solo modifica o retira las propias, cumpliendo responsabilidad única, OCP y separación de responsabilidades. La retirada es lógica y conserva autoría, contenido, respuestas, archivos, fecha, responsable y motivo.

El frontend incorpora `PublicacionesPage.tsx`, rutas para publicaciones globales y propias, filtrado, detalle, creación con archivo opcional, edición, respuestas, descarga y confirmación de retirada. Se añadieron los dieciséis README de Desarrollo para ambos actores, se actualizaron sus índices, se enlazó Desarrollo desde los README de Diseño y se actualizó `tareas_a_realizar.md`.

La validación final confirmó que las 45 pruebas backend terminan con 0 fallos y 0 errores, que Flyway aplica correctamente las diez migraciones, que el frontend genera la compilación de producción y que `git diff --check` no detecta errores. No se realizó ningún commit durante la sesión.

**Decisión:** el Desarrollo automático y la documentación del bloque 8 quedan terminados. La próxima sesión debe probar manualmente el bloque 8 en navegador con Coordinador e Investigador, comprobar permisos, archivos, respuestas y retirada lógica, y después continuar con el siguiente bloque. Con un 57 % de capacidad semanal restante existe margen razonable para completar el proyecto y corregir errores si se priorizan los bloques pendientes, las pruebas manuales críticas y la revisión final.

---

## [11:22] Inicio de sesión - Corrección del bloque 8 y preparación del bloque 9

**Prompt:** el usuario inició una nueva sesión mediante la skill `session-memory`, informó de que Spring Boot no arranca y de que el bloque 8 muestra un error al completar solicitudes, y pidió continuar después con el Análisis del bloque 9 aplicando SOLID, especialmente responsabilidad única y OCP.

**Resultado:** se utilizó la skill `session-memory`. Se recuperó el contexto de la sesión anterior: el bloque 8 está implementado y validado automáticamente, pero permanece pendiente su prueba manual real. Se identificó el bloque 9 como la familia de convocatorias importadas del Coordinador, formada inicialmente por `abrirConvocatorias()`, `abrirConvocatoria()` e `importarConvocatoria()`.

**Decisión:** antes de crear el Análisis del bloque 9 se diagnosticará y corregirá el fallo real de arranque de Spring Boot, se validará el bloque 8 contra el backend en ejecución y se evitará aceptar únicamente las pruebas automatizadas como evidencia suficiente. Después se modelará el bloque 9 separando vista, coordinación, importación y persistencia para respetar responsabilidad única y permitir extensiones mediante OCP.
