# FUNIBER > Coordinador > abrirOpcionesCargaTrabajo > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

- **Backend:** [CargaTrabajoController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/CargaTrabajoController.java) · [CargaTrabajoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/CargaTrabajoService.java) · [SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java) · [CargaTrabajoRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/CargaTrabajoRepository.java) · [ProyectoRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ProyectoRepository.java) · [UsuarioRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/UsuarioRepository.java) · [CargaTrabajo.java](/src/backend/src/main/java/es/funiber/investigacion/model/CargaTrabajo.java) · [Proyecto.java](/src/backend/src/main/java/es/funiber/investigacion/model/Proyecto.java) · [Usuario.java](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)
- **Frontend:** [CargaTrabajoPage.tsx](/src/frontend/src/pages/CargaTrabajoPage.tsx) · [api.ts](/src/frontend/src/services/api.ts)
- **Pruebas:** [CargaTrabajoIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/CargaTrabajoIntegrationTests.java)

## Descripción

El Coordinador abre el módulo `Carga de trabajo` desde el panel principal. El frontend consulta `GET /api/carga-trabajo`, presenta el listado de personas activas, identifica sedes con investigador-docente y muestra sugerencias para proyectos libres priorizando a quienes tienen menor carga semanal.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `GET /api/carga-trabajo`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Controlador REST|[CargaTrabajoController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/CargaTrabajoController.java)|
|Servicio de aplicación|[CargaTrabajoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/CargaTrabajoService.java)|
|Servicio de aplicación|[SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)|
|Repositorio|[CargaTrabajoRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/CargaTrabajoRepository.java)|
|Repositorio|[ProyectoRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ProyectoRepository.java)|
|Repositorio|[UsuarioRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/UsuarioRepository.java)|
|Entidad de dominio|[CargaTrabajo.java](/src/backend/src/main/java/es/funiber/investigacion/model/CargaTrabajo.java)|
|Entidad de dominio|[Proyecto.java](/src/backend/src/main/java/es/funiber/investigacion/model/Proyecto.java)|
|Entidad de dominio|[Usuario.java](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)|
|Contrato de datos|[PanelCargaTrabajoResponse.java](/src/backend/src/main/java/es/funiber/investigacion/dto/PanelCargaTrabajoResponse.java)|
|Cliente HTTP|[api.ts](/src/frontend/src/services/api.ts)|
|Página React|[CargaTrabajoPage.tsx](/src/frontend/src/pages/CargaTrabajoPage.tsx)|
|Modelo de dominio|[SedeFuniber.java](/src/backend/src/main/java/es/funiber/investigacion/model/SedeFuniber.java)|
|Prueba de integración|[CargaTrabajoIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/CargaTrabajoIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `abrirOpcionesCargaTrabajo()` desde el estado permitido por el diagrama de contexto.
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
mvn test -Dtest=CargaTrabajoIntegrationTests
```

### Frontend

```bash
cd src/frontend
npm run build
```

### Comprobación funcional

1. Iniciar sesión como Coordinador.
2. Acceder al flujo `abrirOpcionesCargaTrabajo()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesCargaTrabajo/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
