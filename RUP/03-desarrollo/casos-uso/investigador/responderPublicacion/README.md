# FUNIBER > Investigador > responderPublicacion > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/responderPublicacion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/responderPublicacion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/responderPublicacion/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Backend:** [RespuestaPublicacionRequest.java](/src/backend/src/main/java/es/funiber/investigacion/dto/RespuestaPublicacionRequest.java) · [RespuestaPublicacion.java](/src/backend/src/main/java/es/funiber/investigacion/model/RespuestaPublicacion.java)
- **Pruebas:** [PublicacionIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/PublicacionIntegrationTests.java)

## Descripción

Responde cualquier publicación activa mediante `POST /api/publicaciones/{id}/respuestas`.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `POST /api/publicaciones/{id}/respuestas`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Contrato de datos|[RespuestaPublicacionRequest.java](/src/backend/src/main/java/es/funiber/investigacion/dto/RespuestaPublicacionRequest.java)|
|Modelo de dominio|[RespuestaPublicacion.java](/src/backend/src/main/java/es/funiber/investigacion/model/RespuestaPublicacion.java)|
|Prueba de integración|[PublicacionIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/PublicacionIntegrationTests.java)|

## Flujo de datos

1. El Investigador inicia `responderPublicacion()` desde el estado permitido por el diagrama de contexto.
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

1. Iniciar sesión como Investigador.
2. Acceder al flujo `responderPublicacion()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/investigador/responderPublicacion/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/investigador/responderPublicacion/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/investigador/responderPublicacion/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
