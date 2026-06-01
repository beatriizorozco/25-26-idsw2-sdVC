# Conversation log

## [HH:MM] Inicio de sesión

**Prompt:** *(lo que le dijo al AI para arrancar el proyecto)*

**Resultado:** *(lo que produjo)*

**Decisión:** *(qué aceptó, qué rechazó, qué modificó, y por qué)*

---

> El log no se reescribe. Se escribe mientras ocurre.
>
> Para cada intercambio relevante con el AI, añada una entrada con este formato:
>
> ```

> ## [HH:MM] Título breve de lo que se pidió
>
> **Prompt:** lo que le dijo al AI (textual o resumido fielmente)
>
> **Resultado:** lo que produjo
>
> **Decisión:** qué aceptó, qué rechazó, qué modificó, y por qué
> ```

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

## [02:10] Fin de sesión

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
