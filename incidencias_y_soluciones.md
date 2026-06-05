# Incidencias y soluciones

Este documento registra problemas encontrados durante el desarrollo de la Plataforma Interna de Investigación de FUNIBER, su causa y la solución aplicada. Su objetivo es conservar el razonamiento técnico del proyecto y facilitar futuras revisiones.

## Incidencias resueltas

### Error 403 al iniciar sesión desde el navegador integrado

**Síntoma:** El inicio de sesión funcionaba desde `http://localhost:5173`, pero devolvía `403 Forbidden` al abrir la aplicación desde `http://127.0.0.1:5173`.

**Causa:** La configuración CORS del backend solo autorizaba el origen `http://localhost:5173`. Aunque ambos apuntan al mismo equipo, el navegador los considera orígenes distintos.

**Solución:** Se permitió configurar varios orígenes mediante `FRONTEND_ORIGINS` y se añadieron los dos orígenes locales por defecto:

```properties
http://localhost:5173,http://127.0.0.1:5173
```

**Validación:** Se añadió una prueba de integración que comprueba el acceso desde `http://127.0.0.1:5173`.

### Token CSRF no actualizado antes de una mutación

**Síntoma:** Algunas peticiones protegidas podían fallar si reutilizaban un token CSRF anterior.

**Causa:** El frontend conservaba temporalmente el token obtenido en una petición previa. Tras crear o modificar una sesión, Spring Security puede renovar ese token.

**Solución:** El frontend solicita un token CSRF vigente antes de iniciar o cerrar sesión y renueva el token tras autenticar correctamente.

**Validación:** Se comprobó el inicio de sesión, el reintento tras credenciales incorrectas y el cierre de sesión mediante la suite backend y recorridos manuales.

### Mensaje técnico visible tras introducir credenciales incorrectas

**Síntoma:** La interfaz mostraba `Credenciales incorrectas (401 /auth/login)`.

**Causa:** Durante el diagnóstico se añadió temporalmente información HTTP al mensaje mostrado al usuario.

**Solución:** Se mantuvo el detalle técnico únicamente para depuración y se restauró el mensaje visible:

```text
Credenciales incorrectas
```

**Validación:** Se actualizó la captura de evidencia del formulario con credenciales inválidas.

### Hueco visual en el panel del Investigador

**Síntoma:** El panel del Investigador tenía siete módulos y dejaba una celda vacía al final de la cuadrícula de dos columnas.

**Causa:** La cuadrícula no contemplaba el caso de una cantidad impar de módulos.

**Solución:** El último módulo ocupa ambas columnas cuando es el último elemento impar.

**Validación:** Se sustituyó la captura del panel del Investigador con la nueva distribución.

### Diagramas de Diseño desalineados con la implementación

**Síntoma:** Los diagramas indicaban que el token CSRF se obtenía antes de presentar el formulario y afirmaban que el cierre de sesión expiraba la cookie.

**Causa:** El Diseño inicial reflejaba una aproximación previa al código definitivo.

**Solución:** Se actualizaron los diagramas de secuencia y sus README:

- El formulario se presenta antes de solicitar el token CSRF necesario para enviar credenciales.
- El cierre solicita un token vigente antes de ejecutar `POST /api/auth/logout`.
- La documentación garantiza la invalidación de la sesión del servidor sin afirmar que la cookie expira.
- El atributo `Secure` de la cookie se documenta como obligatorio en producción mediante HTTPS.

**Validación:** Se regeneraron los cuatro SVG afectados con PlantUML.

### Navegación documental incompleta

**Síntoma:** Algunos README del primer bloque enlazaban únicamente a índices generales o no incluían acceso directo a Pruebas.

**Causa:** Las cabeceras se habían creado antes de completar todas las disciplinas RUP.

**Solución:** Se añadieron enlaces directos por caso de uso entre Detalle, Análisis, Diseño, Desarrollo y Pruebas.

**Validación:** Se comprobaron las rutas internas modificadas y no quedan enlaces rotos en el bloque revisado.

### Credenciales correctas rechazadas por backend desactualizado

**Síntoma:** Las credenciales documentadas para `coordinador`, `investigador`, `docente.santander` e `investigador.barcelona` podían devolver error de autenticación aunque el usuario y la contraseña fueran correctos.

**Causa:** Seguía ejecutándose un proceso antiguo del backend. El frontend estaba enviando las peticiones a una versión previa de la aplicación, sin los datos demo y reglas actualizadas. Además, los usuarios demo ya existentes en la base H2 persistente podían conservar una contraseña anterior o un estado inactivo, porque `DemoDataConfig` solo actualizaba la sede cuando encontraba un usuario creado previamente.

**Solución:** Se detuvo el proceso anterior de Java, se arrancó de nuevo el backend desde `src/backend` y se verificó que Spring Boot cargara la versión actual del código. También se ajustó `DemoDataConfig` para que, en cada arranque, los usuarios demo existentes se reactiven y actualicen contraseña, perfil y sede. La entidad `Usuario` incorpora métodos explícitos para actualizar el hash de contraseña y reactivar la cuenta.

**Validación:** Se comprobó por API el inicio de sesión y la carga de trabajo con:

- `coordinador / coordinador123`
- `docente.santander / docente123`
- `investigador.barcelona / barcelona123`

Después de reiniciar el backend, `investigador.barcelona` vuelve a autenticarse correctamente y `GET /api/carga-trabajo/me` devuelve Barcelona como investigador sin docencia por sede.

### Regla de investigadores-docentes interpretada como compensación por exceso

**Síntoma:** El bloque de carga de trabajo se había modelado inicialmente como si un investigador-docente pudiera superar 16 horas semanales de docencia y recibir una compensación económica por ese exceso.

**Causa:** La regla de dominio estaba incompleta. La interpretación correcta es que un investigador-docente no debe superar 16 horas semanales de docencia. Las recompensas no se originan por exceso de carga docente, sino al completar proyectos.

**Solución:** Se corrigió el Análisis, Diseño, Desarrollo y código del bloque 3:

- La carga docente queda limitada a 16 horas semanales cuando la sede aplica docencia investigadora.
- Las sedes diferencian investigadores-docentes e investigadores sin docencia.
- Santander se usa como sede con docencia investigadora.
- Barcelona se usa como sede sin docencia investigadora.
- Las recompensas se separan del caso de carga de trabajo y quedan asociadas a proyectos completados.

**Validación:** Se añadió prueba de integración para rechazar más de 16 horas docentes en `docente.santander`. También se comprobó por API que `docente.santander` aparece como investigador-docente con margen docente y `investigador.barcelona` aparece como investigador sin docencia.

### Investigador de sede sin docencia podía declarar horas docentes

**Síntoma:** Un investigador de Barcelona podía llegar a intentar registrar horas de docencia, aunque Barcelona está modelada como sede sin docencia investigadora.

**Causa:** La restricción de 16 horas solo se aplicaba a investigadores-docentes. Para investigadores sin docencia por sede no existía una prohibición explícita de docencia mayor que cero.

**Solución:** Se bloqueó el campo de docencia en el frontend cuando el perfil no es investigador-docente y se añadió validación backend para rechazar cualquier hora docente en sedes sin docencia. En investigadores-docentes, el frontend normaliza valores superiores a 16 para que al escribir `17` el campo quede en `16`.

**Validación:** La API confirma que `investigador.barcelona / barcelona123` puede iniciar sesión, consultar su perfil, ver Barcelona como sede sin docencia y recibe `400 Bad Request` si intenta guardar docencia. La suite backend mantiene 21 pruebas correctas.

### Tabla de recompensas de carga creada por una migración anterior

**Síntoma:** Tras corregir la regla de dominio, seguía existiendo en el modelo la tabla `recompensas_carga_trabajo`, que ya no representaba una regla válida del sistema.

**Causa:** La migración `V3__carga_trabajo.sql` se había creado antes de separar recompensas y carga de trabajo.

**Solución:** Se retiraron el modelo y repositorio `RecompensaCargaTrabajo`, se eliminó la exposición de `excesoDocente` y `compensacionPendiente` en el DTO de carga, y se añadió la migración `V4__retirar_recompensas_carga_trabajo.sql` para eliminar la tabla obsoleta.

**Validación:** Flyway aplicó correctamente la migración V4 sobre la base local H2 y la suite backend terminó con 21 pruebas correctas.

### SVG del bloque 3 no regenerados por falta de PlantUML local

**Síntoma:** Los `.puml` de Análisis y Diseño del bloque 3 estaban corregidos, pero los SVG no se pudieron regenerar en una sesión anterior.

**Causa:** No había comando `plantuml` ni `plantuml.jar` disponible en el entorno, y el uso de un servidor externo de PlantUML no fue autorizado por la ejecución.

**Solución:** Se descargó `plantuml.jar` en `tools/plantuml.jar` para uso local, se añadió esa ruta a `.gitignore` para no subir el binario al repositorio y se regeneraron los SVG afectados:

- Análisis de `abrirOpcionesCargaTrabajo` y `editarCargaTrabajo` para Coordinador e Investigador.
- Diseño de `abrirOpcionesCargaTrabajo` y `editarCargaTrabajo` para Coordinador e Investigador.

**Validación:** Los SVG quedaron actualizados en `images/RUP/01-analisis` e `images/RUP/02-diseño`. Se comprobó que los archivos esperados se modificaran y que no quedaran SVG temporales con nombres derivados de `@startuml`.

## Decisiones de seguridad

### Almacenamiento de contraseñas

Los usuarios de demostración se crean desde el backend durante el arranque local. La base de datos no almacena contraseñas en texto plano: guarda hashes BCrypt en la columna `contrasena_hash`.

Antes del despliegue público se sustituirán las contraseñas de demostración incluidas en el código por variables de entorno o por una inicialización administrativa.

### Sesiones HTTP

La autenticación utiliza sesiones HTTP. El navegador conserva una cookie de sesión y la envía automáticamente al backend. En producción se activa `Secure` para que la cookie solo viaje mediante HTTPS.

## Verificación actual

|Comprobación|Resultado|
|-|-|
|Suite backend|21 pruebas correctas|
|Lint frontend|Correcto|
|Build frontend de producción|Correcto|
|Reintento tras credenciales incorrectas|Comprobado|
|Cancelación del cierre de sesión|Comprobada|
|Cierre confirmado|Comprobado|
|Permisos diferenciados por rol|Comprobados|
|Carga de trabajo por sede|Comprobada por API|
|Migración V4 de carga de trabajo|Aplicada en local|
|SVG del bloque 3|Regenerados con PlantUML local|

## Mejoras pendientes antes del despliegue

- Crear el `Dockerfile` definitivo.
- Configurar PostgreSQL en el proveedor de despliegue.
- Extraer las contraseñas de demostración del código.
- Publicar la aplicación mediante una URL accesible para evaluación.
- Validar el recorrido completo desde un navegador externo sin sesión previa.
