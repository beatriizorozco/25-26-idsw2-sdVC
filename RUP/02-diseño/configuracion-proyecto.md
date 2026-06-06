# FUNIBER > Configuración y estructura del proyecto

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/README.md)|[Análisis](/RUP/01-analisis/casos-uso/README.md)|[Diseño](/RUP/02-diseño/README.md)|[Desarrollo](/RUP/03-desarrollo/README.md)|[Pruebas](/RUP/04-pruebas/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Definir las decisiones técnicas que permiten transformar el Diseño en una aplicación web funcional, mantenible y desplegable. Este documento sirve como puente entre la documentación RUP y el código de la Plataforma Interna de Investigación de FUNIBER.

## Principios aplicados

1. **Trazabilidad**: Cada componente técnico deriva de una responsabilidad identificada en Análisis.
2. **Separación de responsabilidades**: El frontend presenta; la API coordina; los servicios aplican reglas; los repositorios acceden a datos.
3. **Despliegue sencillo**: El frontend compilado y la API se sirven desde un único origen.
4. **Persistencia real**: PostgreSQL se utiliza en producción para evitar depender del disco efímero del servidor.
5. **MVP evolutivo**: La primera iteración resuelve sesión y navegación antes de ampliar el dominio.

## Estructura implementada

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
│   │   ├── pages/
│   │   ├── services/
│   │   └── types.ts
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

## Dependencias implementadas

### Backend

Las dependencias se encuentran en `src/backend/pom.xml`:

- `spring-boot-starter-web`
- `spring-boot-starter-security`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-validation`
- `flyway-core`
- `flyway-database-postgresql`
- `postgresql`
- `h2`
- `spring-boot-starter-test`
- `spring-security-test`

### Frontend

Las dependencias se encuentran en `src/frontend/package.json`:

- `react`
- `react-dom`
- `lucide-react`
- `typescript`
- `vite`
- `eslint`

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

Flyway crea la tabla mediante `src/backend/src/main/resources/db/migration/V1__crear_tabla_usuarios.sql`. El perfil local utiliza H2 en modo compatible con PostgreSQL para facilitar el arranque inmediato. El perfil de producción utiliza PostgreSQL.

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

## Variables de entorno

|Variable|Uso|
|-|-|
|`SPRING_PROFILES_ACTIVE`|Seleccionar `local` o `prod`.|
|`DATABASE_URL`|URL JDBC de PostgreSQL en producción.|
|`DATABASE_USERNAME`|Usuario de PostgreSQL.|
|`DATABASE_PASSWORD`|Contraseña de PostgreSQL.|
|`FRONTEND_ORIGINS`|Orígenes autorizados, separados por comas, durante el desarrollo separado.|

## Comandos comprobados

### Pruebas del backend

```powershell
cd src/backend
.\mvnw.cmd test
```

### Aplicación completa y pruebas del frontend

```powershell
cd src/frontend
npm install
npm run build
npm run lint
npm run dev
```

`npm run dev` comprueba si Spring Boot responde en el puerto `8080`, lo inicia
automáticamente cuando es necesario y espera antes de arrancar Vite. En
desarrollo, la SPA queda disponible en `http://127.0.0.1:5173` y delega las
rutas `/api` al backend local en `http://127.0.0.1:8080`.

Para una depuración aislada del frontend puede utilizarse `npm run
dev:frontend`, que no inicia ni comprueba el backend.

## Despliegue pendiente

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
