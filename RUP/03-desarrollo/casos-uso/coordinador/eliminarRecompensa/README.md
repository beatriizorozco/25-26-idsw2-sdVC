# FUNIBER > Coordinador > eliminarRecompensa > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarRecompensa/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Frontend:** [RecompensasPage.tsx](/src/frontend/src/pages/RecompensasPage.tsx)
- **Pruebas:** [RecompensaIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/RecompensaIntegrationTests.java)

## Descripción

El Diseño actualizado exige confirmación y motivo antes de `PATCH /api/recompensas/{id}/anulacion`. La operación debe anular la recompensa y conservar proyecto, participantes, carga de trabajo y datos originales de concesión.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- `PATCH /api/recompensas/{id}/anulacion`

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Página React|[RecompensasPage.tsx](/src/frontend/src/pages/RecompensasPage.tsx)|
|Prueba de integración|[RecompensaIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/RecompensaIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `eliminarRecompensa()` desde el estado permitido por el diagrama de contexto.
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
2. Acceder al flujo `eliminarRecompensa()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/eliminarRecompensa/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/eliminarRecompensa/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
