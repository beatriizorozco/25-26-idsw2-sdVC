# FUNIBER > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/README.md)|[Análisis](/RUP/01-analisis/casos-uso/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/README.md)|[Pruebas](/RUP/04-pruebas/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Definir la arquitectura técnica de la Plataforma Interna de Investigación de FUNIBER y transformar las colaboraciones de análisis en componentes concretos que guíen una implementación funcional y desplegable.

## Stack tecnológico seleccionado

La aplicación se plantea como una SPA con API REST y despliegue unificado.

### Backend: Java y Spring Boot

- **Framework**: Spring Boot.
- **Seguridad**: Spring Security con sesión HTTP y cookie segura.
- **Persistencia**: Spring Data JPA.
- **Rol**: Exponer la lógica de aplicación y el acceso a datos mediante una API REST.

### Frontend: React y TypeScript

- **Framework**: React con Vite.
- **Lenguaje**: TypeScript.
- **Rol**: Presentar una interfaz web clara, gestionar el estado visual y consumir la API.

### Base de datos: PostgreSQL

- **Motor**: PostgreSQL.
- **Migraciones**: Flyway.
- **Rol**: Mantener los datos persistentes del sistema en desarrollo y producción.

### Despliegue

- **Contenedor**: Docker.
- **Destino previsto**: Servicio público con PostgreSQL gestionado.
- **Criterio**: Servir el frontend compilado y la API desde el mismo origen para simplificar el despliegue y la seguridad.

## Artefactos generales

### Arquitectura del sistema

<div align=center>

|![Diagrama de arquitectura](/images/RUP/02-diseño/arquitectura.svg)|
|-|
|Código fuente: [arquitectura.puml](arquitectura.puml)|

</div>

### Clases de diseño de la primera iteración

<div align=center>

|![Diagrama de clases de diseño](/images/RUP/02-diseño/clases-diseño.svg)|
|-|
|Código fuente: [clases-diseño.puml](clases-diseño.puml)|

</div>

### Configuración del proyecto

[Configuración y estructura del proyecto](configuracion-proyecto.md)

## Diseño de casos de uso

Los bloques diseñados cubren sesión, navegación principal, perfil, carga de trabajo y recompensas asociadas a proyectos completados:

- [Casos de uso de Diseño](casos-uso/README.md)
- [Coordinador](casos-uso/coordinador/README.md)
- [Investigador](casos-uso/investigador/README.md)

## Criterios metodológicos

- El Diseño parte de los diagramas de especificación y colaboración revisados.
- Cada secuencia concreta participantes técnicos, llamadas, respuestas y alternativas.
- Las decisiones de seguridad y despliegue se definen antes de desarrollar.
- Los casos se trabajan por bloques pequeños para mantener una revisión razonada.

