# FUNIBER > Desarrollo

<div align="center">

[![](https://img.shields.io/badge/-Proceso_RUP-0A3B64?style=for-the-badge&logo=elsevier&logoColor=white)](/RUP/00-casos-uso/README.md)
[![](https://img.shields.io/badge/-Modelo_del_Dominio-0A3B64?style=for-the-badge&logo=diagramsdotnet&logoColor=white)](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
[![](https://img.shields.io/badge/-Casos_de_Uso-0A3B64?style=for-the-badge&logo=group&logoColor=white)](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
[![](https://img.shields.io/badge/-Diagramas_de_Contexto-0A3B64?style=for-the-badge&logo=flowchart&logoColor=white)](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)

[![](https://img.shields.io/badge/-Detalle-0A3B64?style=for-the-badge&logo=notepad&logoColor=white)](/RUP/00-casos-uso/02-detalle/README.md)
[![](https://img.shields.io/badge/-Análisis-0A3B64?style=for-the-badge&logo=multisim&logoColor=white)](/RUP/01-analisis/README.md)
[![](https://img.shields.io/badge/-Diseño-0A3B64?style=for-the-badge&logo=blueprint&logoColor=white)](/RUP/02-diseño/README.md)
[![](https://img.shields.io/badge/-Desarrollo-0A3B64?style=for-the-badge&logo=springboot&logoColor=white)](/RUP/03-desarrollo/README.md)
[![](https://img.shields.io/badge/-Pruebas-0A3B64?style=for-the-badge&logo=checkmarx&logoColor=white)](/RUP/04-pruebas/README.md)

[![](https://img.shields.io/badge/-Reuniones-0A3B64?style=for-the-badge&logo=googlemeet&logoColor=white)](/documents/reuniones.md)
[![](https://img.shields.io/badge/-Incidencias-0A3B64?style=for-the-badge&logo=bookstack&logoColor=white)](/incidencias_y_soluciones.md)
[![](https://img.shields.io/badge/-Log_de_conversación-0A3B64?style=for-the-badge&logo=gnometerminal&logoColor=white)](/conversation-log.md)

</div>

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

## Tercera iteración

La tercera iteración implementa la carga de trabajo por sede, el límite docente y las sugerencias de asignación.

## Cuarta iteración

La cuarta iteración implementa recompensas vinculadas a proyectos completados:

- CRUD global para Coordinador.
- Consulta propia para Investigador.
- Recompensa económica para cualquier Investigador elegible.
- Reducción docente únicamente para investigadores-docentes.

## Séptima iteración

La séptima iteración implementa entregables vinculados a proyectos:

- Creación y consulta por participantes.
- Versionado y descarga de archivos.
- Gestión global por Coordinador.
- Edición y retirada limitada a la autoría para Investigadores.
- Retirada lógica con conservación del histórico.

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

