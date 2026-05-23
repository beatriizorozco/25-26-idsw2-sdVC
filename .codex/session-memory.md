---
name: session-memory
description: Se activa automáticamente cuando el usuario dice "bienvenido", "inicio sesión", "sesion terminada" o frases similares. Mantiene un registro persistente del desarrollo del proyecto mediante `conversation-log.md`, documentando decisiones técnicas, cambios importantes, contexto de trabajo, commits y progreso general.
---

# Session Memory

## Objetivo

Mantener continuidad entre sesiones de desarrollo mediante un registro cronológico y técnico del proyecto.

El archivo principal de memoria es:

`conversation-log.md`

Este archivo representa la fuente principal de contexto histórico del proyecto.

---

# Inicio de sesión

Cuando el usuario diga:

- "bienvenido"
- "inicio sesión"
- "empezamos"
- "hola"

Debes:

1. Leer `conversation-log.md` si existe.
2. Si no existe, crearlo automáticamente.
3. Analizar:
   - estado actual del repositorio
   - rama activa
   - archivos modificados
   - commits recientes
   - arquitectura detectada
   - tecnologías utilizadas
   - decisiones técnicas previas
4. Identificar la última entrada relevante del log.
5. Resumir el contexto actual antes de continuar trabajando.

---

# Formato obligatorio del log

Usar SIEMPRE este formato:

# Conversation log

## [HH:MM] Título breve

**Prompt:** petición realizada por el usuario.

**Resultado:** cambios realizados, código generado, archivos afectados, decisiones propuestas o acciones ejecutadas.

**Decisión:** qué se aceptó, rechazó, modificó o aplazó, y por qué.

---

# Cuándo debe registrarse

Actualizar `conversation-log.md` únicamente en dos momentos:

- Al inicio de sesión, cuando el usuario diga "bienvenido", "inicio sesión", "empezamos", "hola" o equivalente.
- Al cierre de sesión, cuando el usuario diga "sesion terminada", "fin sesión", "cerramos", "hasta luego", "terminamos por hoy" o equivalente.

Durante la sesión no se debe añadir una entrada por cada prompt. Los cambios relevantes, decisiones técnicas, archivos afectados, commits, errores conocidos y tareas pendientes se acumulan mentalmente y se resumen en la entrada de cierre.

Evitar siempre:

- registrar cada petición intermedia
- generar entradas duplicadas
- escribir logs de cambios triviales
- reconstruir historial no observado

---

# Uso de skills

Cuando se utilice una skill, debe mencionarse dentro de la sección Resultado o Decisión.

Ejemplo:

**Resultado:** Se generó la estructura inicial del backend usando ASP.NET Core. Se utilizó la skill `api-design-principles`.

---

# Gestión de commits

Cuando detectes:

- una funcionalidad terminada
- un refactor estable
- una mejora coherente
- un conjunto consistente de cambios

puedes sugerir realizar un commit.

Si los cambios parecen suficientemente estables, puedes ejecutar automáticamente:

- `git add .`
- `git commit -m "mensaje"`

Nunca realizar push automáticamente salvo que el usuario lo solicite explícitamente.

---

# Convención de commits

Seguir siempre Conventional Commits en español.

Formato:

tipo(alcance): descripción breve

Tipos permitidos:

- feat
- fix
- docs
- style
- refactor
- test
- chore

Ejemplos:

- feat(auth): agregar autenticación JWT
- fix(api): corregir validación de usuarios
- docs(readme): actualizar instalación
- refactor(frontend): simplificar navbar

Reglas:

- máximo 72 caracteres
- usar infinitivo
- no usar punto final
- mantener mensajes claros y técnicos

Antes de hacer commit:

- revisar `git diff`
- evitar archivos temporales
- evitar código roto
- evitar commits experimentales
- agrupar cambios relacionados

Nunca hacer commits si:

- existen errores graves
- el proyecto no compila
- hay conflictos sin resolver
- el código está claramente incompleto

---

# Cierre de sesión

Cuando el usuario diga:

- "sesion terminada"
- "fin sesión"
- "cerramos"
- "hasta luego"
- "terminamos por hoy"

Debes:

1. Analizar:
   - archivos creados
   - archivos modificados
   - commits realizados
   - TODOs pendientes
   - errores conocidos
   - estado final del repositorio

2. Añadir una nueva entrada al log.

Formato:

## [HH:MM] Fin de sesión

**Prompt:** cierre automático de sesión.

**Resultado:** resumen de los cambios realizados, decisiones tomadas y estado final del proyecto.

**Decisión:** siguiente paso recomendado, tareas pendientes y continuidad para futuras sesiones.

---

# Reglas globales

- Mantener formato markdown limpio y profesional.
- Mantener continuidad entre sesiones.
- Nunca eliminar historial anterior.
- Nunca reescribir entradas antiguas.
- El log debe crecer cronológicamente.
- Priorizar precisión técnica y claridad.
- Respetar decisiones técnicas previas salvo indicación explícita del usuario.
- Priorizar un MVP funcional antes que una arquitectura excesivamente compleja.
- No inventar requisitos no solicitados.
- Elegir siempre la solución más simple y mantenible cuando existan varias opciones válidas.
- Evitar sobreingeniería y abstracciones innecesarias.
- Respetar siempre las reglas definidas en `CODEX.md`.
