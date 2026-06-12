# FUNIBER > Coordinador > abrirPublicacion > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirPublicacion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirPublicacion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirPublicacion/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Backend:** [PublicacionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/PublicacionService.java)
- **Frontend:** [PublicacionesPage.tsx](/src/frontend/src/pages/PublicacionesPage.tsx)
- **Pruebas:** [PublicacionIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/PublicacionIntegrationTests.java)

## Descripción

Abre el detalle global mediante `GET /api/publicaciones/{id}` y presenta contenido, archivo y respuestas.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `GET /api/publicaciones/{id}`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Servicio de aplicación|[PublicacionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/PublicacionService.java)|
|Página React|[PublicacionesPage.tsx](/src/frontend/src/pages/PublicacionesPage.tsx)|
|Prueba de integración|[PublicacionIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/PublicacionIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `abrirPublicacion()` desde el estado permitido por el diagrama de contexto.
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
mvn test -Dtest=PublicacionIntegrationTests
```

### Frontend

```bash
cd src/frontend
npm run build
```

### Comprobación funcional

1. Iniciar sesión como Coordinador.
2. Acceder al flujo `abrirPublicacion()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/abrirPublicacion/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/abrirPublicacion/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/abrirPublicacion/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
