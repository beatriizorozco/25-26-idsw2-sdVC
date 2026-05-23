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

## [2026-05-23 01:50] Ajuste de nombres y enlaces locales

**Prompt:** el usuario pidio normalizar nombres de archivos y carpetas, adaptar enlaces Markdown para que no apunten al repositorio anterior y, posteriormente, mantener `02-detalle` en camelCase con los Markdown de caso llamados `README.md`.

**Resultado:** se renombraron las carpetas de casos de uso en `RUP/00-casos-uso/02-detalle`, `images/rup/00-casos-uso/02-detalle` y `modelosUML/rup/00-casos-uso/02-detalle` a camelCase. Los Markdown de cada caso en RUP pasaron a `README.md`; los SVG y PUML de `images` y `modelosUML` adoptaron el nombre camelCase del caso. Se reescribieron enlaces internos para usar rutas locales del repositorio y se eliminaron referencias a assets externos o no presentes.

**Decisión:** se adopta una convencion mixta: `02-detalle` usa camelCase para casos de uso por legibilidad con nombres de operaciones, mientras el resto de artefactos generales mantiene nombres descriptivos en kebab-case. Los `README.md` se mantienen en mayusculas.

---

## [2026-05-23 01:55] Convencion de especificacion PUML

**Prompt:** el usuario pidio que todos los PUML de detalle se llamen `especificacion.puml`.

**Resultado:** se renombraron los archivos `.puml` de detalle en `RUP/00-casos-uso/02-detalle` y `modelosUML/rup/00-casos-uso/02-detalle` a `especificacion.puml`. Se actualizaron los enlaces de los `README.md` de detalle para apuntar a la nueva ruta local en `modelosUML`.

**Decisión:** cada carpeta de caso de uso mantiene el nombre del caso en camelCase, el documento textual se llama `README.md` y la fuente PlantUML asociada se llama `especificacion.puml`.

---

## [02:10] Fin de sesion

**Prompt:** cierre automatico de sesion usando la skill `session-memory`.

**Resultado:** se reorganizaron los artefactos RUP de casos de uso, prototipos, imagenes y modelos UML. Se movieron los prototipos `.puml` a sus respectivos casos de uso en `RUP/00-casos-uso/02-detalle` como `prototipo.puml`, y los SVG asociados quedaron bajo `images/RUP/00-casos-uso/02-detalle`. Se mantuvo la convencion de `README.md` para los documentos de detalle, camelCase para las carpetas de casos de uso y `especificacion.puml` para las especificaciones. Tambien se actualizaron los enlaces de imagenes para apuntar al repositorio `beatriizorozco/25-26-idsw2-sdVC` mediante URLs `raw.githubusercontent.com` sobre la rama `develop`, verificando que 153 enlaces de imagen apuntan a archivos locales existentes. Se anadio el badge de `Log_de_conversacion` a las cabeceras Markdown que ya usaban badges en RUP.

**Decision:** se deja el repositorio con cambios pendientes sin commit automatico. El siguiente paso recomendado es revisar visualmente los Markdown principales, especialmente las cabeceras y diagramas de `RUP/00-casos-uso`, y despues agrupar los cambios en un commit documental/organizativo si todo renderiza correctamente. Queda como criterio de continuidad que `session-memory` solo se use al inicio y al cierre de sesion, no en cada prompt intermedio.

---
