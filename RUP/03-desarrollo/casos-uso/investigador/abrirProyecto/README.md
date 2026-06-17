# FUNIBER > Investigador > abrirProyecto > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirProyecto/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirProyecto/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirProyecto/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Backend:** [ProyectoController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/ProyectoController.java) · [ProyectoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ProyectoService.java) · [SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java) · [EntregableRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/EntregableRepository.java) · [ProyectoRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ProyectoRepository.java) · [UsuarioRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/UsuarioRepository.java) · [Entregable.java](/src/backend/src/main/java/es/funiber/investigacion/model/Entregable.java) · [Proyecto.java](/src/backend/src/main/java/es/funiber/investigacion/model/Proyecto.java) · [Usuario.java](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)
- **Frontend:** [ProyectosPage.tsx](/src/frontend/src/pages/ProyectosPage.tsx)
- **Pruebas:** [ProyectoIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/ProyectoIntegrationTests.java)

## Descripción

El detalle comprueba en servidor que el Investigador participa en el proyecto. Puede consultar proyectos propios activos o archivados, listar sus archivos adjuntos, subir nuevos documentos y descargarlos. Solo el Coordinador puede eliminar archivos; los proyectos ajenos devuelven acceso denegado.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- La operación se ejecuta mediante los componentes enlazados, manteniendo el contrato definido en Diseño.

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Controlador REST|[ProyectoController.java](/src/backend/src/main/java/es/funiber/investigacion/controller/ProyectoController.java)|
|Servicio de aplicación|[ArchivoProyectoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ArchivoProyectoService.java)|
|Servicio de aplicación|[ProyectoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ProyectoService.java)|
|Servicio de aplicación|[SesionService.java](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)|
|Repositorio|[EntregableRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/EntregableRepository.java)|
|Repositorio|[ProyectoRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/ProyectoRepository.java)|
|Repositorio|[UsuarioRepository.java](/src/backend/src/main/java/es/funiber/investigacion/repository/UsuarioRepository.java)|
|Entidad de dominio|[Entregable.java](/src/backend/src/main/java/es/funiber/investigacion/model/Entregable.java)|
|Entidad de dominio|[Proyecto.java](/src/backend/src/main/java/es/funiber/investigacion/model/Proyecto.java)|
|Entidad de dominio|[Usuario.java](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)|
|Página React|[ProyectosPage.tsx](/src/frontend/src/pages/ProyectosPage.tsx)|
|Prueba de integración|[ProyectoIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/ProyectoIntegrationTests.java)|

## Flujo de datos

1. El Investigador inicia `abrirProyecto()` desde el estado permitido por el diagrama de contexto.
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

1. Iniciar sesión como Investigador.
2. Acceder al flujo `abrirProyecto()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/investigador/abrirProyecto/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/investigador/abrirProyecto/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/investigador/abrirProyecto/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
