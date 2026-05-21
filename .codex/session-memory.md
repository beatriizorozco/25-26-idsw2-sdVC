---
name: session-memory
description: Se activa automáticamente cuando el usuario dice "bienvenido", "sesion terminada", "inicio sesión" o frases similares relacionadas con comenzar o cerrar una sesión de trabajo. Mantiene un conversation-log persistente del proyecto, prompts utilizados, decisiones técnicas, cambios realizados y skills usadas.
---

# Session Memory

Cuando el usuario diga:

- "bienvenido"
- "empezamos"
- "inicio sesión"
- "hola"

Debes:

1. Leer `conversation-log.md` si existe
2. Analizar el estado actual del repositorio
3. Crear una nueva entrada de inicio de sesión
4. Detectar:
  - rama actual
  - stack tecnológico
  - archivos modificados
  - estado general del proyecto
  - últimas decisiones técnicas relevantes

Formato obligatorio:

# Conversation log

## [HH:MM] Inicio de sesión

**Prompt:** Inicio automático de sesión y análisis del estado actual del proyecto.

**Resultado:** Resumen del estado actual del repositorio, tecnologías detectadas, archivos modificados y contexto previo.

**Decisión:** Se continúa el desarrollo manteniendo la arquitectura y decisiones técnicas existentes salvo indicación contraria.

---

Durante toda la sesión:

Durante la sesión, añade entradas únicamente para cambios relevantes o decisiones importantes, siguiendo el mismo formato:

## [HH:MM] Título breve de lo que se pidió

**Prompt:** lo que el usuario pidió (textual o resumido fielmente)

**Resultado:** lo que produjo Codex, cambios realizados, archivos afectados o decisiones propuestas

**Decisión:** qué se aceptó, qué se rechazó, qué se modificó y por qué

---

Considera relevantes:
- generación de código
- refactors
- creación de arquitectura
- decisiones técnicas
- cambios de stack
- diseño de APIs
- debugging
- generación de frontend
- cambios de base de datos
- uso de skills
- cambios importantes en configuración
- decisiones sobre librerías o frameworks

Cuando se use una skill, añádela dentro del apartado Resultado o Decisión.

Ejemplo:

**Resultado:** Se generó la estructura inicial del backend usando ASP.NET Core. Se utilizó la skill `api-design-principles`.

---

Cuando el usuario diga:

- "sesion terminada"
- "fin sesión"
- "cerramos"
- "hasta luego"

Debes:

1. Analizar:
  - git diff
  - archivos creados
  - archivos modificados
  - commits realizados
  - TODOs pendientes
  - errores conocidos
  - estado general del proyecto

2. Añadir una entrada final siguiendo el mismo formato:

## [HH:MM] Fin de sesión

**Prompt:** Cierre y documentación automática de la sesión.

**Resultado:** Resumen de todos los cambios realizados durante la sesión, archivos modificados, decisiones técnicas y estado final del repositorio.

**Decisión:** Se documenta el estado final para poder continuar el desarrollo en futuras sesiones sin perder contexto.

---

Cuando detectes una funcionalidad terminada, un refactor estable o un conjunto coherente de cambios, puedes sugerir realizar un commit.

El commit debe:
- seguir Conventional Commits
- ser breve y descriptivo
- agrupar cambios relacionados
- evitar commits excesivamente grandes
- evitar commits temporales o experimentales

Ejemplos:
- feat(auth): agregar autenticación JWT
- fix(api): corregir validación de usuario
- refactor(frontend): simplificar manejo del navbar

Si los cambios parecen estables y completos, puedes ejecutar automáticamente:
- git add .
- git commit -m "mensaje"

Nunca hagas push automáticamente salvo que el usuario lo pida explícitamente.
Nunca hagas commits si:
- existen errores graves
- hay código claramente incompleto
- el proyecto no compila
- hay conflictos sin resolver

---

Evita documentar cambios triviales o irrelevantes.
Prioriza decisiones técnicas, cambios estructurales y avances importantes.

Respetar siempre las reglas definidas en `AGENTS.md`.

---

Reglas obligatorias:

- Nunca elimines entradas anteriores
- Nunca reescribas el historial
- El log debe crecer cronológicamente
- Mantén formato markdown limpio y profesional
- Sé preciso técnicamente
- Resume sin perder información importante
- Mantén continuidad entre sesiones
- Respeta decisiones técnicas anteriores salvo que el usuario indique cambios
- Si existe ambigüedad, prioriza fidelidad al desarrollo real realizado
- El log debe escribirse mientras ocurren las interacciones relevantes, no únicamente al final
- No generar entradas duplicadas para el mismo cambio o decisión.