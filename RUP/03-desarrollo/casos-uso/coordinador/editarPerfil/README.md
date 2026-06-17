# FUNIBER > Coordinador > editarPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarPerfil/README.md)|
> |-|-|-|-|-|-|-|

- **Backend:** [PerfilController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/PerfilController.java) · [PerfilService.java](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java) · [SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java) · [UsuarioRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/UsuarioRepository.java) · [Usuario.java](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)
- **Frontend:** [PerfilPage.tsx](/src/frontend/src/pages/PerfilPage.tsx) · [api.ts](/src/frontend/src/services/api.ts)
- **Pruebas:** [PerfilIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/PerfilIntegrationTests.java)

## Descripción

La edición usa un formulario precargado con `PerfilResponse` del perfil propio del Coordinador. Al guardar, el frontend envía `PATCH /api/perfil` con `PerfilUpdateRequest`; el backend valida la sesión, actualiza los campos del usuario autenticado y devuelve el perfil actualizado.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `PATCH /api/perfil`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Controlador REST|[PerfilController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/PerfilController.java)|
|Servicio de aplicación|[PerfilService.java](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java)|
|Servicio de aplicación|[SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)|
|Repositorio|[UsuarioRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/UsuarioRepository.java)|
|Entidad de dominio|[Usuario.java](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)|
|Contrato de datos|[PerfilUpdateRequest.java](/src/backend/src/main/java/es/funiber/investigacion/dto/PerfilUpdateRequest.java)|
|Cliente HTTP|[api.ts](/src/frontend/src/services/api.ts)|
|Página React|[PerfilPage.tsx](/src/frontend/src/pages/PerfilPage.tsx)|
|Prueba de integración|[PerfilIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/PerfilIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `editarPerfil()` desde el estado permitido por el diagrama de contexto.
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

1. Iniciar sesión como Coordinador.
2. Acceder al flujo `editarPerfil()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/editarPerfil/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/editarPerfil/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
