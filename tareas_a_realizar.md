# Tareas a realizar

Documento de seguimiento para no olvidar actualizaciones documentales y técnicas durante la evolución de la Plataforma Interna de Investigación de FUNIBER.

## Actualizaciones durante cada bloque

- [ ] Revisar los diagramas de especificación antes de aceptar el Análisis de cada familia funcional.
- [ ] Revisar los diagramas de colaboración antes de crear su Diseño.
- [x] Revisar transversalmente todos los casos `eliminar...` y clasificarlos como baja lógica, archivado, anulación, desasignación o borrado físico excepcional.
- [x] Alinear la documentación de `eliminarPerfil()` con el backend existente: desactivar el acceso y conservar el histórico del usuario.
- [ ] Actualizar [`incidencias_y_soluciones.md`](incidencias_y_soluciones.md) con los problemas relevantes y su resolución.
- [ ] Crear los `README.md` y `secuencia.puml` de Diseño para cada caso revisado.
- [ ] Revisar que los casos de uso `crear*` en Diseño sigan el patrón de `crearAula`: mostrar formulario, enviar `POST`, crear entidad desde el servicio/repositorio, devolver respuesta creada y notificar éxito.
- [ ] Generar los SVG de Diseño en `images/RUP/02-diseño`.
- [x] Crear la documentación de Desarrollo de cada caso en `RUP/03-desarrollo/casos-uso`.
- [x] Añadir enlaces de Desarrollo a las cabeceras de Diseño cuando existan sus documentos.
- [x] Crear la documentación de Pruebas de cada caso en `RUP/04-pruebas/casos-uso`.
- [x] Añadir enlaces de Pruebas a las cabeceras cuando existan sus documentos.
- [x] Verificar que las rutas internas de los README no estén rotas.

## Configuración del proyecto

Actualizar [`RUP/02-diseño/configuracion-proyecto.md`](RUP/02-diseño/configuracion-proyecto.md) conforme exista código real:

- [x] Sustituir la estructura prevista de carpetas por la estructura definitiva.
- [x] Documentar las dependencias exactas de `pom.xml`.
- [x] Documentar las dependencias exactas de `package.json`.
- [x] Añadir la configuración real de Spring Security.
- [x] Añadir las variables de entorno necesarias.
- [x] Documentar la conexión real con PostgreSQL.
- [x] Documentar las migraciones de Flyway.
- [x] Añadir los comandos comprobados para ejecutar backend y frontend.
- [x] Añadir los comandos comprobados para ejecutar las pruebas.
- [x] Mantener actualizado el mapeo entre artefactos de Diseño y archivos de código.
- [ ] Documentar Docker cuando se incorpore al proyecto.
- [ ] Documentar el despliegue público y su URL cuando esté disponible.
- [ ] Añadir los usuarios de demostración sin publicar contraseñas sensibles.

## Diseño general

Actualizar [`RUP/02-diseño/README.md`](RUP/02-diseño/README.md) y sus diagramas generales:

- [ ] Ampliar el índice de casos de uso conforme se diseñen nuevos bloques.
- [ ] Actualizar `arquitectura.puml` si cambia la arquitectura desplegada.
- [ ] Actualizar `clases-diseño.puml` con las entidades y servicios de cada iteración.
- [ ] Regenerar `images/RUP/02-diseño/arquitectura.svg`.
- [ ] Regenerar `images/RUP/02-diseño/clases-diseño.svg`.

## Desarrollo

Actualizar [`RUP/03-desarrollo/README.md`](RUP/03-desarrollo/README.md) durante la construcción:

- [x] Documentar la estructura real del código.
- [x] Crear el índice de Desarrollo por actor y familia funcional.
- [x] Añadir trazabilidad desde cada caso desarrollado hacia Detalle, Análisis y Diseño.
- [x] Documentar endpoints, servicios y componentes implementados.
- [x] Aplicar la identidad visual azul corporativa de FUNIBER.
- [x] Incorporar el logotipo GIPF al login y a la cabecera del panel.
- [x] Añadir capturas o evidencias visuales relevantes cuando exista frontend funcional.
- [x] Sustituir la captura del panel del Investigador tras ajustar el último módulo a ancho completo.

## Pruebas

- [x] Crear `RUP/04-pruebas/README.md`.
- [x] Definir la estrategia de pruebas unitarias, de integración y de interfaz.
- [x] Documentar pruebas por caso de uso.
- [x] Añadir evidencias de ejecución y resultados.
- [x] Verificar permisos diferenciados de Coordinador e Investigador.
- [x] Probar credenciales incorrectas, sesión válida y cierre confirmado.
- [x] Probar desde la interfaz el reintento de acceso y la cancelación del cierre de sesión.

## Despliegue y entrega final

- [ ] Crear el `Dockerfile` definitivo.
- [ ] Comprobar que la aplicación arranca desde un entorno limpio.
- [ ] Configurar PostgreSQL en el proveedor de despliegue.
- [ ] Sustituir las contraseñas de demostración incluidas en el código por variables de entorno o una inicialización administrativa.
- [ ] Publicar la aplicación en una URL accesible para evaluación.
- [ ] Verificar la URL pública desde un navegador sin sesión previa.
- [ ] Actualizar [`README.md`](README.md) con presentación, instalación, ejecución y acceso público.
- [ ] Documentar credenciales de demostración separadas de cualquier secreto real.
- [ ] Revisar que `conversation-log.md` refleje las decisiones relevantes.
- [ ] Comprobar que no quedan archivos temporales ni enlaces rotos.

## Próxima sesión

- [x] Revisar una última vez el Análisis del bloque 3 de carga de trabajo.
- [x] Confirmar que la regla de proyectos libres queda aplicada: asignar o sugerir investigadores-docentes con menor carga.
- [x] Confirmar que el límite de 16 horas semanales de docencia queda separado de las recompensas, que pertenecen a proyectos completados.
- [x] Empezar el Diseño del bloque 3 siguiendo el patrón del profesor: `README.md`, `secuencia.puml` y SVG por caso.
- [x] Preparar los casos de Diseño de `abrirOpcionesCargaTrabajo` y `editarCargaTrabajo` para Coordinador e Investigador.
- [x] Añadir o completar capturas pendientes del bloque de perfil si se quiere dejar la evidencia visual cerrada.

## Pendiente inmediato del bloque 3

- [x] Ejecutar `mvnw.cmd test` para validar backend tras la corrección de carga de trabajo.
- [x] Ejecutar `npm run build` para validar frontend tras la corrección de carga de trabajo.
- [x] Arrancar backend local y aplicar la migración `V4__retirar_recompensas_carga_trabajo.sql`.
- [x] Comprobar por API el flujo de carga con Coordinador, `docente.santander` e `investigador.barcelona`.
- [x] Regenerar los SVG de Análisis y Diseño del bloque 3 cuando esté disponible PlantUML local o una vía autorizada.
- [x] Probar manualmente en navegador el flujo visual de carga de trabajo con Coordinador, `docente.santander` e `investigador.barcelona`.
- [x] Revisar el bloque 3 entero de carga de trabajo: Detalle, Análisis, Diseño, Desarrollo, Pruebas, diagramas, SVG, capturas y trazabilidad.
- [x] Empezar el módulo de recompensas/proyectos completados con la regla correcta: investigadores-docentes pueden elegir recompensa económica o reducción docente tras proyecto completado; investigadores sin docencia solo tienen recompensa económica.

## Pendiente inmediato del bloque 4

- [x] Actualizar Detalle de recompensas para Coordinador e Investigador con las reglas de proyecto completado y tipo de recompensa según docencia/sede.
- [x] Actualizar Análisis de recompensas para Coordinador e Investigador con README, `colaboracion.puml` y SVG.
- [x] Revisar manualmente el Análisis del bloque 4 completo.
- [x] Confirmar que los diagramas distinguen correctamente Coordinador e Investigador.
- [x] Confirmar que las recompensas nacen de proyectos completados y no de exceso de carga docente.
- [x] Confirmar que el Investigador solo consulta recompensas propias.
- [x] Confirmar que los investigadores-docentes pueden recibir recompensa económica o reducción docente, y que los investigadores sin docencia solo reciben recompensa económica.
- [x] Completar `crearRecompensa()` con selección de proyecto completado, beneficiario elegible, tipos permitidos y cancelación coherente.
- [x] Tras la revisión, pasar a Diseño del bloque 4 con `README.md`, `secuencia.puml` y SVG por caso.
- [x] Revisar el Diseño completo del bloque 4 antes de comenzar Desarrollo.
- [x] Confirmar que las rutas globales de recompensas quedan restringidas al Coordinador.
- [x] Confirmar que las rutas `/api/recompensas/me` impiden al Investigador consultar recompensas ajenas.
- [x] Confirmar que crear y editar validan proyecto completado, beneficiario elegible y tipo permitido.
- [x] Tras la revisión, pasar a Desarrollo del bloque 4.
- [x] Implementar persistencia de proyectos completados, participantes elegibles y recompensas.
- [x] Implementar CRUD global de recompensas para Coordinador.
- [x] Implementar consulta de recompensas propias para Investigador.
- [x] Validar tipos de recompensa según docencia y sede.
- [x] Añadir pruebas de integración del bloque 4 y verificar compilación de producción.
- [x] Corregir la selección para mostrar solo proyectos, beneficiarios y tipos con recompensas pendientes.
- [x] Evitar que los perfiles demo eliminados se reactiven automáticamente al reiniciar.
- [x] Alinear el frontend con los endpoints de detalle y tipos permitidos del Diseño.
- [x] Ampliar pruebas de recompensas para edición, duplicados, beneficiarios ajenos, acceso propio y proyectos agotados.
- [x] Actualizar documentación desfasada sobre el acceso de `investigador.barcelona` a recompensas.
- [x] Probar manualmente en navegador el bloque 4 con Coordinador, `docente.santander` e `investigador.barcelona`.
- [x] Revisar el bloque 4 completo antes de pasar al bloque 5.

## Pendiente inmediato del bloque 5

- [x] Identificar y revisar los casos de uso que forman el bloque 5.
- [x] Comenzar el bloque 5 por Detalle, validando su coherencia con los diagramas de contexto antes de avanzar a Análisis.
- [x] Revisar el Detalle de proyectos para Coordinador: `abrirProyectos()`, `abrirProyecto()`, `crearProyecto()`, `editarProyecto()` y `eliminarProyecto()`.
- [x] Revisar el Detalle de composición del equipo para Coordinador: `agregarInvestigador()` y `eliminarInvestigador()`.
- [x] Revisar el Detalle de consulta de proyectos propios para Investigador: `abrirProyectos()` y `abrirProyecto()`.
- [x] Confirmar que `abrirProyecto()` recibe un proyecto seleccionado y presenta directamente su detalle, sin repetir el listado.
- [x] Confirmar que la asignación de investigadores utiliza perfiles existentes, disponibilidad y carga de trabajo, sin volver a crear sus datos personales.
- [x] Revisar manualmente el Detalle completo del bloque 5 antes de avanzar a Análisis.
- [x] Revisar el Análisis del bloque 5 para Coordinador: `abrirProyectos()`, `abrirProyecto()`, `crearProyecto()`, `editarProyecto()`, `eliminarProyecto()`, `agregarInvestigador()` y `eliminarInvestigador()`.
- [x] Revisar el Análisis del bloque 5 para Investigador: `abrirProyectos()` y `abrirProyecto()`.
- [x] Validar que las colaboraciones de proyectos respetan el Detalle aprobado y no eliminan perfiles al desasignar investigadores.
- [x] Revisar manualmente el Análisis completo del bloque 5 antes de avanzar a Diseño.
- [x] Crear el Diseño del bloque 5 para Coordinador e Investigador con `README.md` y `secuencia.puml`.
- [x] Revisar manualmente el Diseño completo del bloque 5 antes de avanzar a Desarrollo.
- [x] Diseñar una consulta de histórico de proyectos archivados para el Coordinador antes de cerrar definitivamente la gestión de proyectos.
- [x] Integrar el histórico como alcance de `abrirProyectos()` mediante las vistas Activos y Archivados.
- [x] Implementar el Desarrollo del bloque 5 para gestión y consulta de proyectos.
- [x] Implementar altas y bajas de participantes con registro histórico de movimientos.
- [x] Implementar archivado lógico de proyectos con motivo, fecha y responsable.
- [x] Sincronizar finalización y archivado: completar archiva automáticamente y archivar marca el proyecto como completado.
- [x] Implementar archivos adjuntos de proyecto: Coordinador e Investigadores participantes pueden subir, consultar y descargar; solo el Coordinador puede eliminar.
- [ ] Probar manualmente el bloque 5 en navegador con Coordinador e Investigador.
- [ ] Revisar el bloque 5 completo antes de avanzar al bloque 6.

## Pendiente de bajas lógicas

## Pendiente inmediato del bloque 6

- [x] Identificar los casos del bloque 6 de investigadores y separarlos de la desasignacion de participantes del bloque 5.
- [x] Revisar y corregir el Detalle de investigadores para Coordinador e Investigador.
- [x] Modelar `abrirInvestigadores(idProyecto)` como un unico caso con alcance global o contextual.
- [x] Corregir `abrirInvestigador()` para presentar un perfil individual y sus proyectos asociados o visibles.
- [x] Corregir `crearInvestigador()` para registrar un perfil nuevo sin prometer una edicion inexistente.
- [x] Crear y revisar el Analisis del bloque 6 para Coordinador e Investigador.
- [x] Crear el Diseño del bloque 6 para Coordinador e Investigador con `README.md`, `secuencia.puml` y SVG.
- [ ] Revisar manualmente el bloque 6 antes de avanzar a Desarrollo.

## Pendiente inmediato del bloque 7

- [x] Identificar el bloque 7 como gestión de entregables para Coordinador e Investigador.
- [x] Revisar y corregir las colaboraciones de Análisis de los diez casos de entregables.
- [x] Confirmar que `abrirEntregables()` siempre queda acotado al proyecto abierto.
- [x] Confirmar que el Investigador puede consultar entregables del proyecto, pero solo modificar o retirar los propios.
- [x] Diseñar la retirada lógica conservando autoría, proyecto, archivos y versiones.
- [x] Crear el Diseño del bloque 7 con `README.md` y `secuencia.puml` para los diez casos.
- [ ] Revisar manualmente el Diseño del bloque 7 antes de comenzar Desarrollo.

- [ ] Migrar `eliminarRecompensa()` en Desarrollo desde el borrado físico actual hacia una anulación lógica con fecha, Coordinador responsable y motivo.
- [ ] Implementar la retirada lógica de publicaciones y entregables cuando sus bloques alcancen Desarrollo.
- [ ] Diseñar consultas administrativas de perfiles inactivos, proyectos archivados, publicaciones y entregables retirados, y recompensas anuladas.
- [x] Implementar las desasignaciones de investigadores conservando un movimiento histórico de asignación y desasignación.
- [ ] Reservar el borrado físico para correcciones administrativas excepcionales de registros erróneos sin valor histórico.
