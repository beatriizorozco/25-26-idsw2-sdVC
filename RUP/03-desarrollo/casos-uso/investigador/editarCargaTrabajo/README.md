# FUNIBER > Investigador > editarCargaTrabajo > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/editarCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/editarCargaTrabajo/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/editarCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

- **Backend:** [CargaTrabajoController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/CargaTrabajoController.java) · [CargaTrabajoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/CargaTrabajoService.java) · [CargaTrabajoUpdateRequest.java](/src/backend/src/main/java/es/funiber/investigacion/dto/CargaTrabajoUpdateRequest.java)
- **Frontend:** [CargaTrabajoPage.tsx](/src/frontend/src/pages/CargaTrabajoPage.tsx)
- **Pruebas:** [CargaTrabajoIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/CargaTrabajoIntegrationTests.java)

## Descripción

El Investigador actualiza su carga semanal mediante `PATCH /api/carga-trabajo/me`. El backend solo modifica la carga asociada a la sesión actual, por lo que no puede editar cargas de otros perfiles.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `PATCH /api/carga-trabajo/me`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Página React|[CargaTrabajoPage.tsx](/src/frontend/src/pages/CargaTrabajoPage.tsx)|
|Controlador REST|[CargaTrabajoController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/CargaTrabajoController.java)|
|Servicio de aplicación|[CargaTrabajoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/CargaTrabajoService.java)|
|Contrato de datos|[CargaTrabajoUpdateRequest.java](/src/backend/src/main/java/es/funiber/investigacion/dto/CargaTrabajoUpdateRequest.java)|
|Prueba de integración|[CargaTrabajoIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/CargaTrabajoIntegrationTests.java)|

## Flujo de datos

1. El Investigador inicia `editarCargaTrabajo()` desde el estado permitido por el diagrama de contexto.
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

1. Iniciar sesión como Investigador.
2. Acceder al flujo `editarCargaTrabajo()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/investigador/editarCargaTrabajo/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/investigador/editarCargaTrabajo/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
