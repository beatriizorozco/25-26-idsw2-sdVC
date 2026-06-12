# FUNIBER > Coordinador > eliminarInvestigador > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarInvestigador/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarInvestigador/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Backend:** [MovimientoParticipacionProyecto.java](/src/backend/src/main/java/es/funiber/investigacion/model/MovimientoParticipacionProyecto.java) · [ProyectoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ProyectoService.java)
- **Pruebas:** [InvestigadorIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/InvestigadorIntegrationTests.java)

## Descripción

La operación desasigna al investigador del equipo activo, exige un motivo y conserva un movimiento histórico. Nunca elimina el perfil del investigador.

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- La operación se ejecuta mediante los componentes enlazados, manteniendo el contrato definido en Diseño.

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Modelo de dominio|[MovimientoParticipacionProyecto.java](/src/backend/src/main/java/es/funiber/investigacion/model/MovimientoParticipacionProyecto.java)|
|Servicio de aplicación|[ProyectoService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ProyectoService.java)|
|Prueba de integración|[InvestigadorIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/InvestigadorIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `eliminarInvestigador()` desde el estado permitido por el diagrama de contexto.
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
mvn test -Dtest=InvestigadorIntegrationTests
```

### Frontend

```bash
cd src/frontend
npm run build
```

### Comprobación funcional

1. Iniciar sesión como Coordinador.
2. Acceder al flujo `eliminarInvestigador()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/eliminarInvestigador/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/eliminarInvestigador/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/eliminarInvestigador/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
