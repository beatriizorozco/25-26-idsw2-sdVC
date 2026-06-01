# FUNIBER > Coordinador > iniciarSesion > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/iniciarSesion/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la interacción técnica para autenticar a una persona antes de conocer su rol. El Diseño conserva `SESION_CERRADA` como estado previo, permite reintentar tras credenciales incorrectas y alcanza `PANEL_PRINCIPAL_ABIERTO` cuando la autenticación es válida.

## Diagrama de secuencia

|![Diseño: iniciarSesion()](/images/RUP/02-diseño/casos-uso/coordinador/iniciarSesion/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **LoginPage**: Captura las credenciales y presenta el resultado.
- **AuthController**: Expone `POST /api/auth/login`.
- **AuthService**: Valida la existencia, contraseña y estado activo del usuario.
- **UsuarioRepository**: Consulta el usuario en PostgreSQL.
- **SesionService**: Crea la sesión HTTP tras autenticar correctamente.

## Decisiones de Diseño

- La secuencia es común a ambos roles porque el rol aún no se conoce al iniciar.
- Las credenciales incorrectas producen `401 Unauthorized` y permiten reintentar.
- La cookie segura evita almacenar tokens de acceso en el navegador.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/iniciarSesion/README.md)
