# FUNIBER > Coordinador > iniciarSesion > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/iniciarSesion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/iniciarSesion/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/iniciarSesion/README.md)|
> |-|-|-|-|-|-|-|

- **Backend:** [AuthController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/AuthController.java) · [AuthService.java](/src/backend/src/main/java/es/funiber/investigacion/service/AuthService.java) · [SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java) · [UsuarioRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/UsuarioRepository.java) · [Usuario.java](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)
- **Frontend:** [LoginPage.tsx](/src/frontend/src/pages/LoginPage.tsx) · [api.ts](/src/frontend/src/services/api.ts)
- **Pruebas:** [FlujoSesionIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/FlujoSesionIntegrationTests.java)

## Descripción

El acceso se implementa mediante `LoginPage`, `AuthController`, `AuthService`, `UsuarioRepository` y `SesionService`. El frontend obtiene un token CSRF, envía las credenciales y renueva el token después de crear la sesión.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- La operación se ejecuta mediante los componentes enlazados, manteniendo el contrato definido en Diseño.

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Controlador REST|[AuthController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/AuthController.java)|
|Servicio de aplicación|[AuthService.java](/src/backend/src/main/java/es/funiber/investigacion/service/AuthService.java)|
|Servicio de aplicación|[SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)|
|Repositorio|[UsuarioRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/UsuarioRepository.java)|
|Entidad de dominio|[Usuario.java](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)|
|Cliente HTTP|[api.ts](/src/frontend/src/services/api.ts)|
|Página React|[LoginPage.tsx](/src/frontend/src/pages/LoginPage.tsx)|
|Prueba de integración|[FlujoSesionIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/FlujoSesionIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `iniciarSesion()` desde el estado permitido por el diagrama de contexto.
2. La interfaz React captura la solicitud y utiliza el cliente HTTP común.
3. El controlador REST valida sesión, permisos y datos de entrada.
4. El servicio de aplicación coordina las reglas del caso de uso.
5. Los repositorios consultan o persisten únicamente la información necesaria.
6. La interfaz presenta el resultado y conserva la navegación definida.

## Decisiones de implementación

- **Responsabilidad única:** La presentación, coordinación, reglas y persistencia permanecen separadas.
- **Abierto y cerrado:** Las reglas variables se delegan en servicios, políticas o estrategias extensibles.
- **Seguridad:** El backend vuelve a validar permisos y propiedad aunque la interfaz oculte acciones.
- **Trazabilidad:** La implementación mantiene correspondencia con Detalle, Análisis y Diseño.

## Validación

### Backend

```bash
cd src/backend
mvn test -Dtest=FlujoSesionIntegrationTests
```

### Frontend

```bash
cd src/frontend
npm run build
```

### Comprobación funcional

1. Iniciar sesión como Coordinador.
2. Acceder al flujo `iniciarSesion()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/iniciarSesion/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/iniciarSesion/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
