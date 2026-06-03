# FUNIBER > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/README.md)|[Análisis](/RUP/01-analisis/casos-uso/README.md)|[Diseño](/RUP/02-diseño/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Documentar la construcción incremental de la Plataforma Interna de Investigación de FUNIBER y mantener trazabilidad entre requisitos, análisis, diseño y código ejecutable.

## Primera iteración

La primera iteración implementa sesión y navegación principal:

- [Casos de uso desarrollados](casos-uso/README.md)
- [Código fuente](/src/README.md)

## Segunda iteración

La segunda iteración implementa la gestión de perfil:

- Consulta y edición del perfil propio.
- Solicitud de eliminación de perfil con cierre de sesión.
- Consulta y resolución de solicitudes de eliminación por Coordinador.

## Stack implementado

- **Backend**: Java 17, Spring Boot, Spring Security, Spring Data JPA y Flyway.
- **Frontend**: React, TypeScript, Vite y Lucide React.
- **Persistencia local**: H2 en modo compatible con PostgreSQL.
- **Persistencia prevista en producción**: PostgreSQL.

## Verificación

```powershell
cd src/backend
.\mvnw.cmd test

cd ../frontend
npm run build
npm run lint
```

