# FUNIBER > Coordinador > importarConvocatoria > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/importarConvocatoria/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/importarConvocatoria/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/importarConvocatoria/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

- **Backend:** [ImportacionConvocatoriaService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ImportacionConvocatoriaService.java) · [ImportadorConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/ImportadorConvocatoria.java) · [RegistroImportadoresConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/RegistroImportadoresConvocatoria.java) · [ImportadorJsonConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/ImportadorJsonConvocatoria.java) · [ImportadorReferenciaConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/ImportadorReferenciaConvocatoria.java) · [ValidadorConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/ValidadorConvocatoria.java)
- **Frontend:** [ConvocatoriasPage.tsx](/src/frontend/src/pages/ConvocatoriasPage.tsx)
- **Pruebas:** [ConvocatoriaIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/ConvocatoriaIntegrationTests.java)

## Descripción

Importa convocatorias externas mediante un flujo de previsualización y confirmación:

## Estado

**Completado** - Implementación integrada en Spring Boot y React.

## Contrato HTTP

- La operación se ejecuta mediante los componentes enlazados, manteniendo el contrato definido en Diseño.

## Implementación por capas

|Responsabilidad|Código relacionado|
|-|-|
|Servicio de aplicación|[ImportacionConvocatoriaService.java](/src/backend/src/main/java/es/funiber/investigacion/service/ImportacionConvocatoriaService.java)|
|Servicio de aplicación|[ImportadorConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/ImportadorConvocatoria.java)|
|Servicio de aplicación|[RegistroImportadoresConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/RegistroImportadoresConvocatoria.java)|
|Servicio de aplicación|[ImportadorJsonConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/ImportadorJsonConvocatoria.java)|
|Servicio de aplicación|[ImportadorReferenciaConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/ImportadorReferenciaConvocatoria.java)|
|Servicio de aplicación|[ValidadorConvocatoria.java](/src/backend/src/main/java/es/funiber/investigacion/service/ValidadorConvocatoria.java)|
|Página React|[ConvocatoriasPage.tsx](/src/frontend/src/pages/ConvocatoriasPage.tsx)|
|Prueba de integración|[ConvocatoriaIntegrationTests.java](/src/backend/src/test/java/es/funiber/investigacion/controller/ConvocatoriaIntegrationTests.java)|

## Flujo de datos

1. El Coordinador inicia `importarConvocatoria()` desde el estado permitido por el diagrama de contexto.
2. La interfaz React captura la solicitud y utiliza el cliente HTTP común.
3. El controlador REST valida sesión, permisos y datos de entrada.
4. El servicio de aplicación coordina las reglas del caso de uso.
5. Los repositorios consultan o persisten únicamente la información necesaria.
6. La interfaz presenta el resultado y conserva la navegación definida.

## Decisiones de implementación

- Previsualizar no persiste ningún cambio.

- **Responsabilidad única:** La presentación, coordinación, reglas y persistencia permanecen separadas.
- **Abierto y cerrado:** Las reglas variables se delegan en servicios, políticas o estrategias extensibles.
- **Seguridad:** El backend vuelve a validar permisos y propiedad aunque la interfaz oculte acciones.
- **Trazabilidad:** La implementación mantiene correspondencia con Detalle, Análisis y Diseño.

## Validación

### Backend

```bash
cd src/backend
mvn test -Dtest=ConvocatoriaIntegrationTests
```

### Frontend

```bash
cd src/frontend
npm run build
```

### Comprobación funcional

1. Iniciar sesión como Coordinador.
2. Acceder al flujo `importarConvocatoria()` desde un estado permitido.
3. Ejecutar la operación principal y comprobar su resultado visible.
4. Verificar las alternativas de cancelación, validación o falta de permisos aplicables.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/importarConvocatoria/README.md)
- [Análisis de colaboración](/RUP/01-analisis/casos-uso/coordinador/importarConvocatoria/README.md)
- [Diseño y secuencia](/RUP/02-diseño/casos-uso/coordinador/importarConvocatoria/README.md)
- [Diagrama de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
