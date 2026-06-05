# FUNIBER > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/README.md)|[Análisis](/RUP/01-analisis/casos-uso/README.md)|[Diseño](/RUP/02-diseño/README.md)|[Desarrollo](/RUP/03-desarrollo/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Propósito

Documentar la verificación incremental de la Plataforma Interna de Investigación de FUNIBER y mantener trazabilidad desde los casos de uso hasta sus evidencias de ejecución.

## Iteraciones verificadas

La primera iteración verifica sesión y navegación principal:

- [Casos de uso probados](casos-uso/README.md)
- [Suite de integración](/src/backend/src/test/java/es/funiber/investigacion/controller/FlujoSesionIntegrationTests.java)

La segunda iteración verifica perfil y solicitudes de eliminación:

- [Pruebas del Coordinador](casos-uso/coordinador/README.md)
- [Pruebas del Investigador](casos-uso/investigador/README.md)

La tercera iteración verifica carga de trabajo:

- [Carga de trabajo - Coordinador](casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)
- [Carga de trabajo - Investigador](casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)
- [Suite de carga de trabajo](/src/backend/src/test/java/es/funiber/investigacion/controller/CargaTrabajoIntegrationTests.java)

## Estrategia

- **Integración backend**: Verificar API, persistencia, sesión HTTP, CSRF y permisos mediante MockMvc.
- **Recorrido HTTP local**: Contrastar el comportamiento real del navegador contra backend y frontend en ejecución.
- **Interfaz**: Revisar manualmente los estados visuales que no generan una petición HTTP, como cancelar el cierre de sesión.

## Comandos comprobados

```powershell
cd src/backend
.\mvnw.cmd test

cd ../frontend
npm run build
npm run lint
```

## Resultado actual

|Verificación|Resultado|
|-|-|
|Suite Maven|21 pruebas correctas|
|Compilación frontend|Correcta|
|Lint frontend|Correcto|
|Token CSRF real|`X-XSRF-TOKEN` mediante cookie `XSRF-TOKEN`|
|Origen local del navegador integrado|`http://127.0.0.1:5173` autorizado|
|Credenciales incorrectas|`401 Unauthorized`|
|Reintento posterior|Sesión creada correctamente|
|Panel tras cerrar sesión|`401 Unauthorized`|

## Evidencias visuales

- [Formulario de inicio de sesión](/images/RUP/04-pruebas/iniciarSesion-formulario.png)
- [Credenciales incorrectas](/images/RUP/04-pruebas/iniciarSesion-credenciales-incorrectas.png)
- [Panel principal del Coordinador](/images/RUP/04-pruebas/coordinador-abrirPanelPrincipal.png)
- [Confirmación de cierre del Coordinador](/images/RUP/04-pruebas/coordinador-cerrarSesion-confirmacion.png)
- [Panel principal del Investigador](/images/RUP/04-pruebas/investigador-abrirPanelPrincipal.png)
- [Confirmación de cierre del Investigador](/images/RUP/04-pruebas/investigador-cerrarSesion-confirmacion.png)
