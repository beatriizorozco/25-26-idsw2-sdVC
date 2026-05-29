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
