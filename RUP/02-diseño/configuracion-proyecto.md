# FUNIBER > Configuración y estructura del proyecto

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/README.md)|[Análisis](/RUP/01-analisis/casos-uso/README.md)|[Diseño](/RUP/02-diseño/README.md)|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Definir las decisiones técnicas que permiten transformar el Diseño en una aplicación web funcional, mantenible y desplegable. Este documento sirve como puente entre la documentación RUP y el código de la Plataforma Interna de Investigación de FUNIBER.

## Principios aplicados

1. **Trazabilidad**: Cada componente técnico deriva de una responsabilidad identificada en Análisis.
2. **Separación de responsabilidades**: El frontend presenta; la API coordina; los servicios aplican reglas; los repositorios acceden a datos.
3. **Despliegue sencillo**: El frontend compilado y la API se sirven desde un único origen.
4. **Persistencia real**: PostgreSQL se utiliza en producción para evitar depender del disco efímero del servidor.
5. **MVP evolutivo**: La primera iteración resuelve sesión y navegación antes de ampliar el dominio.

## Estructura prevista

```text
src/
├── backend/
│   ├── src/main/java/.../
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   ├── src/main/resources/
│   │   ├── db/migration/
│   │   └── application.yml
│   ├── src/test/java/.../
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── context/
│   │   ├── pages/
│   │   ├── services/
│   │   └── types/
│   ├── package.json
│   └── vite.config.ts
└── Dockerfile
```

## Flujo técnico

```text
Página React → Cliente HTTP → Controller REST → Service → Repository → PostgreSQL
      ↓              ↓               ↓
 Estado visual   Cookie segura    DTO de entrada y salida
```

## Autenticación

### Decisión

La autenticación utiliza una sesión HTTP gestionada por Spring Security. El navegador recibe una cookie de sesión y la envía automáticamente en las peticiones posteriores. En producción, la cookie se configura con los atributos `HttpOnly`, `Secure` y `SameSite=Lax`. El perfil local puede desactivar `Secure` mientras se trabaje mediante HTTP.

Las operaciones que modifican estado requieren un token CSRF. El frontend obtiene este token al presentar el formulario de acceso y lo envía en las peticiones `POST`. Después de autenticar correctamente debe solicitar un token renovado, porque Spring Security invalida el token previo al cambiar el contexto de seguridad. También se conserva la protección automática frente a fijación de sesión.

### Motivos

- Evita almacenar credenciales o tokens de acceso en `localStorage`.
- Simplifica el cierre de sesión mediante invalidación del servidor.
- Encaja con un despliegue unificado de frontend y backend.
- Permite proteger endpoints según los roles `COORDINADOR` e `INVESTIGADOR`.
- Protege las operaciones autenticadas frente a peticiones maliciosas iniciadas desde otros sitios.

## API de la primera iteración

|Método|Ruta|Responsabilidad|
|-|-|-|
|`GET`|`/api/auth/csrf`|Obtener el token CSRF necesario para las operaciones que modifican estado.|
|`POST`|`/api/auth/login`|Validar credenciales y crear la sesión.|
|`POST`|`/api/auth/logout`|Invalidar la sesión activa.|
|`GET`|`/api/auth/me`|Recuperar el usuario y rol de la sesión activa.|
|`GET`|`/api/panel-principal`|Obtener las acciones disponibles para el rol activo.|

## Modelo inicial de datos

### Tabla `usuarios`

|Campo|Tipo|Restricción|
|-|-|-|
|`id`|`BIGSERIAL`|Clave primaria.|
|`nombre_usuario`|`VARCHAR(80)`|Único y obligatorio.|
|`contrasena_hash`|`VARCHAR(255)`|Obligatorio.|
|`rol`|`VARCHAR(30)`|`COORDINADOR` o `INVESTIGADOR`.|
|`activo`|`BOOLEAN`|Obligatorio.|

La sesión HTTP no necesita una tabla propia en la primera iteración. Si el despliegue futuro requiere varias instancias o persistencia compartida de sesiones, se podrá incorporar Spring Session.

## Mapeo entre Diseño y código

|Artefacto de Diseño|Ubicación prevista|
|-|-|
|`Usuario`|`src/backend/src/main/java/.../model/Usuario.java`|
|`UsuarioRepository`|`src/backend/src/main/java/.../repository/UsuarioRepository.java`|
|`AuthService`|`src/backend/src/main/java/.../service/AuthService.java`|
|`SesionService`|`src/backend/src/main/java/.../service/SesionService.java`|
|`PanelPrincipalService`|`src/backend/src/main/java/.../service/PanelPrincipalService.java`|
|`SecurityConfig`|`src/backend/src/main/java/.../config/SecurityConfig.java`|
|`AuthController`|`src/backend/src/main/java/.../controller/AuthController.java`|
|`PanelPrincipalController`|`src/backend/src/main/java/.../controller/PanelPrincipalController.java`|
|`LoginPage`|`src/frontend/src/pages/LoginPage.tsx`|
|`PanelPrincipalPage`|`src/frontend/src/pages/PanelPrincipalPage.tsx`|

## Despliegue previsto

1. Vite compila el frontend.
2. Docker copia los recursos compilados al backend.
3. Spring Boot sirve la SPA y la API desde el mismo dominio.
4. El contenedor utiliza una variable `DATABASE_URL` para conectarse a PostgreSQL.
5. Flyway aplica migraciones versionadas al arrancar.

## Alcance de la primera iteración

La primera iteración implementará exclusivamente:

- `iniciarSesion()`
- `abrirPanelPrincipal()`
- `cerrarSesion()`

El resto de módulos se incorporará por familias funcionales después de revisar sus especificaciones y colaboraciones.
