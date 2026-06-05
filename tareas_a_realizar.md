# Tareas a realizar

Documento de seguimiento para no olvidar actualizaciones documentales y técnicas durante la evolución de la Plataforma Interna de Investigación de FUNIBER.

## Actualizaciones durante cada bloque

- [ ] Revisar los diagramas de especificación antes de aceptar el Análisis de cada familia funcional.
- [ ] Revisar los diagramas de colaboración antes de crear su Diseño.
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
- [ ] Empezar el módulo de recompensas/proyectos completados con la regla correcta: investigadores-docentes pueden elegir recompensa económica o reducción docente tras proyecto completado; investigadores sin docencia solo tienen recompensa económica.
