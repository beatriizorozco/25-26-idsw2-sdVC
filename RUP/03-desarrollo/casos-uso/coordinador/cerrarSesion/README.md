# FUNIBER > Coordinador > cerrarSesion > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/cerrarSesion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/cerrarSesion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/cerrarSesion/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/cerrarSesion/README.md)|
> |-|-|-|-|-|-|-|

- **Backend:** [AuthController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/AuthController.java) · [SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)
- **Frontend:** [ConfirmarCierreSesionModal.tsx](/src/frontend/src/components/ConfirmarCierreSesionModal.tsx) · [api.ts](/src/frontend/src/services/api.ts)
- **Pruebas:** [FlujoSesionIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/FlujoSesionIntegrationTests.java)

## Descripción

El cierre comienza en un modal de confirmación. Cancelar conserva el panel; confirmar obtiene un token CSRF vigente, envía `POST /api/auth/logout` e invalida la sesión.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `POST /api/auth/logout`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Controlador REST|[AuthController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/AuthController.java)|
|Servicio de aplicación|[SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)|
|Cliente HTTP|[api.ts](/src/frontend/src/services/api.ts)|
|Componente React|[ConfirmarCierreSesionModal.tsx](/src/frontend/src/components/ConfirmarCierreSesionModal.tsx)|
|Prueba de integración|[FlujoSesionIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/FlujoSesionIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `cerrarSesion()` desde el estado permitido por el diagrama de contexto.
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
2. Acceder al flujo `cerrarSesion()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/cerrarSesion/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/cerrarSesion/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/cerrarSesion/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
