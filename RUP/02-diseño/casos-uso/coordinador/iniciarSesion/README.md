# FUNIBER > Coordinador > iniciarSesion > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/iniciarSesion/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/iniciarSesion/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/iniciarSesion/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la interacción técnica para autenticar al Coordinador. El Diseño conserva `SESION_CERRADA` como estado previo, permite reintentar tras credenciales incorrectas y alcanza `PANEL_PRINCIPAL_ABIERTO` cuando la autenticación y el rol son válidos.

## Diagrama de secuencia

|![Diseño: iniciarSesion()](/images/RUP/02-diseño/casos-uso/coordinador/iniciarSesion/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **LoginPage**: Presenta el formulario, captura las credenciales, obtiene el token CSRF al enviarlas y muestra el resultado.
- **AuthController**: Expone `GET /api/auth/csrf` y `POST /api/auth/login`.
- **Spring Security**: Proporciona el token CSRF utilizado por la petición de autenticación.
- **AuthService**: Valida la existencia, contraseña y estado activo del usuario.
- **UsuarioRepository**: Consulta el usuario en PostgreSQL.
- **SesionService**: Crea la sesión HTTP tras autenticar correctamente.

## Decisiones de Diseño

- El actor es `Coordinador` para mantener trazabilidad con la especificación funcional.
- La lógica técnica se comparte con el inicio de sesión del Investigador, aunque cada carpeta conserva su actor correspondiente.
- Las credenciales incorrectas producen `401 Unauthorized` y permiten reintentar.
- La sesión utiliza una cookie `HttpOnly` y `SameSite=Lax`; en producción también se activa `Secure`.
- El token CSRF protege la petición que crea la sesión.
- Tras autenticar correctamente, el frontend solicita un token CSRF renovado para las operaciones posteriores.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/iniciarSesion/README.md)
