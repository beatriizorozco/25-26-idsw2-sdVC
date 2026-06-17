# FUNIBER > Coordinador > abrirRecompensas > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirRecompensas/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirRecompensas/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirRecompensas/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Backend:** [RecompensaController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/RecompensaController.java) · [RecompensaService.java](/src/backend/src/main/java/es/funiber/investigacion/service/RecompensaService.java) · [SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java) · [ProyectoRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ProyectoRepository.java) · [RecompensaRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/RecompensaRepository.java) · [UsuarioRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/UsuarioRepository.java) · [Proyecto.java](/src/backend/src/main/java/es/funiber/investigacion/model/Proyecto.java) · [Recompensa.java](/src/backend/src/main/java/es/funiber/investigacion/model/Recompensa.java) · [Usuario.java](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)
- **Frontend:** [RecompensasPage.tsx](/src/frontend/src/pages/RecompensasPage.tsx)
- **Pruebas:** [RecompensaIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/RecompensaIntegrationTests.java)

## Descripción

El Coordinador consulta `GET /api/recompensas` y `RecompensasPage` presenta el listado global, filtrado y acceso al detalle.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `GET /api/recompensas`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Controlador REST|[RecompensaController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/RecompensaController.java)|
|Servicio de aplicación|[RecompensaService.java](/src/backend/src/main/java/es/funiber/investigacion/service/RecompensaService.java)|
|Servicio de aplicación|[SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)|
|Repositorio|[ProyectoRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ProyectoRepository.java)|
|Repositorio|[RecompensaRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/RecompensaRepository.java)|
|Repositorio|[UsuarioRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/UsuarioRepository.java)|
|Entidad de dominio|[Proyecto.java](/src/backend/src/main/java/es/funiber/investigacion/model/Proyecto.java)|
|Entidad de dominio|[Recompensa.java](/src/backend/src/main/java/es/funiber/investigacion/model/Recompensa.java)|
|Entidad de dominio|[Usuario.java](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)|
|Página React|[RecompensasPage.tsx](/src/frontend/src/pages/RecompensasPage.tsx)|
|Prueba de integración|[RecompensaIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/RecompensaIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `abrirRecompensas()` desde el estado permitido por el diagrama de contexto.
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
mvn test -Dtest=RecompensaIntegrationTests
```

### Frontend

```bash
cd src/frontend
npm run build
```

### Comprobación funcional

1. Iniciar sesión como Coordinador.
2. Acceder al flujo `abrirRecompensas()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/abrirRecompensas/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/abrirRecompensas/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/abrirRecompensas/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
