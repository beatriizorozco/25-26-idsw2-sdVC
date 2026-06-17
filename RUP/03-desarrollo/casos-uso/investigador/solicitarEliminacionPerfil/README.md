# FUNIBER > Investigador > solicitarEliminacionPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

- **Backend:** [PerfilController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/PerfilController.java) · [PerfilService.java](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java) · [SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java) · [SolicitudEliminacionPerfilRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/SolicitudEliminacionPerfilRepository.java) · [SolicitudEliminacionPerfil.java](/src/backend/src/main/java/es/funiber/investigacion/model/SolicitudEliminacionPerfil.java)
- **Frontend:** [PerfilPage.tsx](/src/frontend/src/pages/PerfilPage.tsx)
- **Pruebas:** [PerfilIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/PerfilIntegrationTests.java)

## Descripción

El Investigador solicita la eliminación desde un modal con motivo obligatorio. El backend registra la solicitud pendiente, evita duplicados y cierra la sesión después de confirmar.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- La operación se ejecuta mediante los componentes enlazados, manteniendo el contrato definido en Diseño.

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Controlador REST|[PerfilController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/PerfilController.java)|
|Servicio de aplicación|[PerfilService.java](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java)|
|Servicio de aplicación|[SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)|
|Repositorio|[SolicitudEliminacionPerfilRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/SolicitudEliminacionPerfilRepository.java)|
|Entidad de dominio|[SolicitudEliminacionPerfil.java](/src/backend/src/main/java/es/funiber/investigacion/model/SolicitudEliminacionPerfil.java)|
|Página React|[PerfilPage.tsx](/src/frontend/src/pages/PerfilPage.tsx)|
|Prueba de integración|[PerfilIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/PerfilIntegrationTests.java)|

## Flujo de datos

1. El Investigador inicia `solicitarEliminacionPerfil()` desde el estado permitido por el diagrama de contexto.
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
mvn test -Dtest=PerfilIntegrationTests
```

### Frontend

```bash
cd src/frontend
npm run build
```

### Comprobación funcional

1. Iniciar sesión como Investigador.
2. Acceder al flujo `solicitarEliminacionPerfil()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/investigador/solicitarEliminacionPerfil/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/investigador/solicitarEliminacionPerfil/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
