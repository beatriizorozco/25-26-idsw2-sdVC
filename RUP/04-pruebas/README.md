# FUNIBER > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/README.md)|[Análisis](/RUP/01-analisis/casos-uso/README.md)|[Diseño](/RUP/02-diseño/README.md)|[Desarrollo](/RUP/03-desarrollo/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Propósito

Documentar la verificación incremental de GIPF y mantener trazabilidad desde los casos de uso hasta sus evidencias de ejecución.

## Cobertura verificada

La suite de integración backend cubre las familias funcionales implementadas:

1. Sesión y navegación principal.
2. Perfil y solicitudes de eliminación.
3. Carga de trabajo.
4. Recompensas.
5. Proyectos y archivos adjuntos.
6. Investigadores.
7. Entregables y versiones.
8. Publicaciones y respuestas.
9. Convocatorias importadas.

## Estrategia

- **Integración backend**: API, persistencia, sesión HTTP, CSRF, permisos y reglas de dominio mediante MockMvc.
- **Migraciones**: Aplicación de las 11 migraciones Flyway sobre H2 durante las pruebas.
- **Frontend**: Compilación de producción y lint.
- **Recorrido manual**: Comprobación incremental en navegador de estados visuales y flujos completos.

## Comandos

```powershell
cd src/backend
.\mvnw.cmd test

cd ../frontend
npm run build
npm run lint
```

## Resultado actual

Verificación ejecutada el 12 de junio de 2026:

|Verificación|Resultado|
|-|-|
|Suite Maven|48 pruebas correctas; 0 fallos y 0 errores|
|Migraciones Flyway|11 aplicadas correctamente|
|Compilación frontend|Correcta|
|Lint frontend|0 errores; 1 aviso no bloqueante|
|Autenticación y permisos por rol|Comprobados|
|Recorridos manuales principales|Comprobados incrementalmente|

## Suites de integración

- [Sesión](/src/backend/src/test/java/es/funiber/investigacion/controller/FlujoSesionIntegrationTests.java)
- [Perfil](/src/backend/src/test/java/es/funiber/investigacion/controller/PerfilIntegrationTests.java)
- [Carga de trabajo](/src/backend/src/test/java/es/funiber/investigacion/controller/CargaTrabajoIntegrationTests.java)
- [Recompensas](/src/backend/src/test/java/es/funiber/investigacion/controller/RecompensaIntegrationTests.java)
- [Proyectos](/src/backend/src/test/java/es/funiber/investigacion/controller/ProyectoIntegrationTests.java)
- [Investigadores](/src/backend/src/test/java/es/funiber/investigacion/controller/InvestigadorIntegrationTests.java)
- [Entregables](/src/backend/src/test/java/es/funiber/investigacion/controller/EntregableIntegrationTests.java)
- [Publicaciones](/src/backend/src/test/java/es/funiber/investigacion/controller/PublicacionIntegrationTests.java)
- [Convocatorias](/src/backend/src/test/java/es/funiber/investigacion/controller/ConvocatoriaIntegrationTests.java)

## Riesgos pendientes

- Completar documentos de Pruebas individuales para los casos de uso cuyos enlaces de cabecera aún no existen.
- Ejecutar una última regresión manual global antes de la entrega.
- Preparar el despliegue público reproducible.
