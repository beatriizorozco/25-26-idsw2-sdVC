# FUNIBER > Coordinador > abrirProyecto > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirProyecto/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirProyecto/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Backend:** [ProyectoResponse.java](/src/backend/src/main/java/es/funiber/investigacion/dto/ProyectoResponse.java) · [ArchivoProyectoController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/ArchivoProyectoController.java)
- **Frontend:** [ProyectosPage.tsx](/src/frontend/src/pages/ProyectosPage.tsx)
- **Pruebas:** [ProyectoIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/ProyectoIntegrationTests.java)

## Descripción

`GET /api/proyectos/{id}` recupera el detalle, estado y participantes activos. Los proyectos archivados permanecen consultables por el Coordinador.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `GET /api/proyectos/{id}`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Página React|[ProyectosPage.tsx](/src/frontend/src/pages/ProyectosPage.tsx)|
|Contrato de datos|[ProyectoResponse.java](/src/backend/src/main/java/es/funiber/investigacion/dto/ProyectoResponse.java)|
|Controlador REST|[ArchivoProyectoController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/ArchivoProyectoController.java)|
|Prueba de integración|[ProyectoIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/ProyectoIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `abrirProyecto()` desde el estado permitido por el diagrama de contexto.
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
mvn test -Dtest=ProyectoIntegrationTests
```

### Frontend

```bash
cd src/frontend
npm run build
```

### Comprobación funcional

1. Iniciar sesión como Coordinador.
2. Acceder al flujo `abrirProyecto()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/abrirProyecto/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/abrirProyecto/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
