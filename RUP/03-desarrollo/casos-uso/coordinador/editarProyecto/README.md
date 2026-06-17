# FUNIBER > Coordinador > editarProyecto > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarProyecto/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarProyecto/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarProyecto/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Backend:** [ProyectoController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/ProyectoController.java) · [ProyectoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ProyectoService.java) · [SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java) · [ConvocatoriaRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ConvocatoriaRepository.java) · [ProyectoRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ProyectoRepository.java) · [Convocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/model/Convocatoria.java) · [Proyecto.java](/src/backend/src/main/java/es/funiber/investigacion/model/Proyecto.java)
- **Pruebas:** [ProyectoIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/ProyectoIntegrationTests.java)

## Descripción

`PATCH /api/proyectos/{id}` actualiza únicamente proyectos activos y vuelve a validar código y fechas. Al marcar un proyecto como completado, el sistema lo archiva automáticamente y conserva su información histórica. Los proyectos archivados quedan protegidos frente a cambios operativos.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `PATCH /api/proyectos/{id}`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Controlador REST|[ProyectoController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/ProyectoController.java)|
|Servicio de aplicación|[ProyectoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ProyectoService.java)|
|Servicio de aplicación|[SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)|
|Repositorio|[ConvocatoriaRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ConvocatoriaRepository.java)|
|Repositorio|[ProyectoRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ProyectoRepository.java)|
|Entidad de dominio|[Convocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/model/Convocatoria.java)|
|Entidad de dominio|[Proyecto.java](/src/backend/src/main/java/es/funiber/investigacion/model/Proyecto.java)|
|Prueba de integración|[ProyectoIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/ProyectoIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `editarProyecto()` desde el estado permitido por el diagrama de contexto.
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
2. Acceder al flujo `editarProyecto()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/editarProyecto/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/editarProyecto/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/editarProyecto/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
