# FUNIBER > Coordinador > editarEntregable > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarEntregable/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarEntregable/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarEntregable/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Backend:** [EntregableController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/EntregableController.java) · [EntregableService.java](/src/backend/src/main/java/es/funiber/investigacion/service/EntregableService.java) · [SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java) · [ArchivoEntregableRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ArchivoEntregableRepository.java) · [EntregableRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/EntregableRepository.java) · [ArchivoEntregable.java](/src/backend/src/main/java/es/funiber/investigacion/model/ArchivoEntregable.java) · [Entregable.java](/src/backend/src/main/java/es/funiber/investigacion/model/Entregable.java)
- **Frontend:** [EntregablesProyecto.tsx](/src/frontend/src/components/EntregablesProyecto.tsx)
- **Pruebas:** [EntregableIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/EntregableIntegrationTests.java)

## Descripción

`PATCH /api/entregables/{id}` actualiza los datos y añade una nueva versión cuando se adjunta otro archivo. Las versiones anteriores permanecen disponibles.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `PATCH /api/entregables/{id}`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Controlador REST|[EntregableController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/EntregableController.java)|
|Servicio de aplicación|[EntregableService.java](/src/backend/src/main/java/es/funiber/investigacion/service/EntregableService.java)|
|Servicio de aplicación|[SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)|
|Repositorio|[ArchivoEntregableRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ArchivoEntregableRepository.java)|
|Repositorio|[EntregableRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/EntregableRepository.java)|
|Entidad de dominio|[ArchivoEntregable.java](/src/backend/src/main/java/es/funiber/investigacion/model/ArchivoEntregable.java)|
|Entidad de dominio|[Entregable.java](/src/backend/src/main/java/es/funiber/investigacion/model/Entregable.java)|
|Componente React|[EntregablesProyecto.tsx](/src/frontend/src/components/EntregablesProyecto.tsx)|
|Prueba de integración|[EntregableIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/EntregableIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `editarEntregable()` desde el estado permitido por el diagrama de contexto.
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
mvn test -Dtest=EntregableIntegrationTests
```

### Frontend

```bash
cd src/frontend
npm run build
```

### Comprobación funcional

1. Iniciar sesión como Coordinador.
2. Acceder al flujo `editarEntregable()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/editarEntregable/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/editarEntregable/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/editarEntregable/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
