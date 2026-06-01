# Tareas a realizar

Documento de seguimiento para no olvidar actualizaciones documentales y técnicas durante la evolución de la Plataforma Interna de Investigación de FUNIBER.

## Actualizaciones durante cada bloque

- [ ] Revisar los diagramas de especificación antes de aceptar el Análisis de cada familia funcional.
- [ ] Revisar los diagramas de colaboración antes de crear su Diseño.
- [ ] Crear los `README.md` y `secuencia.puml` de Diseño para cada caso revisado.
- [ ] Generar los SVG de Diseño en `images/RUP/02-diseño`.
- [ ] Crear la documentación de Desarrollo de cada caso en `RUP/03-desarrollo/casos-uso`.
- [ ] Añadir enlaces de Desarrollo a las cabeceras de Diseño cuando existan sus documentos.
- [ ] Crear la documentación de Pruebas de cada caso en `RUP/04-pruebas/casos-uso`.
- [ ] Añadir enlaces de Pruebas a las cabeceras cuando existan sus documentos.
- [ ] Verificar que las rutas internas de los README no estén rotas.

## Configuración del proyecto

Actualizar [`RUP/02-diseño/configuracion-proyecto.md`](RUP/02-diseño/configuracion-proyecto.md) conforme exista código real:

- [ ] Sustituir la estructura prevista de carpetas por la estructura definitiva.
- [ ] Documentar las dependencias exactas de `pom.xml`.
- [ ] Documentar las dependencias exactas de `package.json`.
- [ ] Añadir la configuración real de Spring Security.
- [ ] Añadir las variables de entorno necesarias.
- [ ] Documentar la conexión real con PostgreSQL.
- [ ] Documentar las migraciones de Flyway.
- [ ] Añadir los comandos comprobados para ejecutar backend y frontend.
- [ ] Añadir los comandos comprobados para ejecutar las pruebas.
- [ ] Mantener actualizado el mapeo entre artefactos de Diseño y archivos de código.
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

- [ ] Documentar la estructura real del código.
- [ ] Crear el índice de Desarrollo por actor y familia funcional.
- [ ] Añadir trazabilidad desde cada caso desarrollado hacia Detalle, Análisis y Diseño.
- [ ] Documentar endpoints, servicios y componentes implementados.
- [ ] Añadir capturas o evidencias visuales relevantes cuando exista frontend funcional.

## Pruebas

- [ ] Crear `RUP/04-pruebas/README.md`.
- [ ] Definir la estrategia de pruebas unitarias, de integración y de interfaz.
- [ ] Documentar pruebas por caso de uso.
- [ ] Añadir evidencias de ejecución y resultados.
- [ ] Verificar permisos diferenciados de Coordinador e Investigador.
- [ ] Probar credenciales incorrectas, reintento, sesión válida y cierre cancelado o confirmado.

## Despliegue y entrega final

- [ ] Crear el `Dockerfile` definitivo.
- [ ] Comprobar que la aplicación arranca desde un entorno limpio.
- [ ] Configurar PostgreSQL en el proveedor de despliegue.
- [ ] Publicar la aplicación en una URL accesible para evaluación.
- [ ] Verificar la URL pública desde un navegador sin sesión previa.
- [ ] Actualizar [`README.md`](README.md) con presentación, instalación, ejecución y acceso público.
- [ ] Documentar credenciales de demostración separadas de cualquier secreto real.
- [ ] Revisar que `conversation-log.md` refleje las decisiones relevantes.
- [ ] Comprobar que no quedan archivos temporales ni enlaces rotos.

