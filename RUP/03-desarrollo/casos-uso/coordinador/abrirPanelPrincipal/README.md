# FUNIBER > Coordinador > abrirPanelPrincipal > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirPanelPrincipal/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirPanelPrincipal/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirPanelPrincipal/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirPanelPrincipal/README.md)|
> |-|-|-|-|-|-|-|

- **Backend:** [PanelPrincipalController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/PanelPrincipalController.java) · [PanelPrincipalService.java](/src/backend/src/main/java/es/funiber/investigacion/service/PanelPrincipalService.java)
- **Frontend:** [PanelPrincipalPage.tsx](/src/frontend/src/pages/PanelPrincipalPage.tsx)
- **Pruebas:** [FlujoSesionIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/FlujoSesionIntegrationTests.java)

## Descripción

El panel obtiene las acciones disponibles desde la sesión autenticada. El Coordinador recibe el conjunto global, incluyendo convocatorias.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- La operación se ejecuta mediante los componentes enlazados, manteniendo el contrato definido en Diseño.

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Página React|[PanelPrincipalPage.tsx](/src/frontend/src/pages/PanelPrincipalPage.tsx)|
|Controlador REST|[PanelPrincipalController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/PanelPrincipalController.java)|
|Servicio de aplicación|[PanelPrincipalService.java](/src/backend/src/main/java/es/funiber/investigacion/service/PanelPrincipalService.java)|
|Prueba de integración|[FlujoSesionIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/FlujoSesionIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `abrirPanelPrincipal()` desde el estado permitido por el diagrama de contexto.
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
2. Acceder al flujo `abrirPanelPrincipal()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/abrirPanelPrincipal/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/abrirPanelPrincipal/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/abrirPanelPrincipal/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
