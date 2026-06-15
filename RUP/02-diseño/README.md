# FUNIBER > Diseño

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

Los bloques diseñados cubren sesión, navegación principal, perfil, carga de trabajo, recompensas, proyectos, investigadores, entregables, publicaciones y convocatorias:

- [Casos de uso de Diseño](casos-uso/README.md)
- [Coordinador](casos-uso/coordinador/README.md)
- [Investigador](casos-uso/investigador/README.md)

## Criterios metodológicos

- El Diseño parte de los diagramas de especificación y colaboración revisados.
- Cada secuencia concreta participantes técnicos, llamadas, respuestas y alternativas.
- Las decisiones de seguridad y despliegue se definen antes de desarrollar.
- Los casos se trabajan por bloques pequeños para mantener una revisión razonada.

