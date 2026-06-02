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

## Decisiones de seguridad

### Almacenamiento de contraseñas

Los usuarios de demostración se crean desde el backend durante el arranque local. La base de datos no almacena contraseñas en texto plano: guarda hashes BCrypt en la columna `contrasena_hash`.

Antes del despliegue público se sustituirán las contraseñas de demostración incluidas en el código por variables de entorno o por una inicialización administrativa.

### Sesiones HTTP

La autenticación utiliza sesiones HTTP. El navegador conserva una cookie de sesión y la envía automáticamente al backend. En producción se activa `Secure` para que la cookie solo viaje mediante HTTPS.

## Verificación actual

|Comprobación|Resultado|
|-|-|
|Suite backend|8 pruebas correctas|
|Lint frontend|Correcto|
|Build frontend de producción|Correcto|
|Reintento tras credenciales incorrectas|Comprobado|
|Cancelación del cierre de sesión|Comprobada|
|Cierre confirmado|Comprobado|
|Permisos diferenciados por rol|Comprobados|

## Mejoras pendientes antes del despliegue

- Crear el `Dockerfile` definitivo.
- Configurar PostgreSQL en el proveedor de despliegue.
- Extraer las contraseñas de demostración del código.
- Publicar la aplicación mediante una URL accesible para evaluación.
- Validar el recorrido completo desde un navegador externo sin sesión previa.

