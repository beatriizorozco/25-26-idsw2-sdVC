<h1 align="center">GIPF - Gestor de Investigación de FUNIBER</h1>

<p align="center">
  <img src="/src/frontend/public/gipf-logo.png" alt="Logotipo de GIPF" width="150">
</p>

<p align="center">
  Plataforma web interna para coordinar proyectos, investigadores y resultados de investigación.
</p>

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

## La aplicación

GIPF nace para centralizar la actividad investigadora de las distintas sedes de FUNIBER. La plataforma permite coordinar proyectos, consultar la disponibilidad de los investigadores y conservar la trazabilidad de publicaciones, entregables, recompensas y decisiones administrativas.

> **Un Coordinador supervisa la actividad investigadora global, mientras que cada Investigador gestiona su información y participa en los proyectos que tiene asignados.**

<div align="center">

|Coordinador|Investigador|
|:-:|:-:|
|![Panel principal del Coordinador](/images/RUP/04-pruebas/coordinador-abrirPanelPrincipal.png)|![Panel principal del Investigador](/images/RUP/04-pruebas/investigador-abrirPanelPrincipal.png)|

</div>

## Actores

|Actor|Responsabilidad|
|-|-|
|**Coordinador**|Gestiona proyectos, investigadores, cargas de trabajo, entregables, publicaciones, convocatorias, recompensas y solicitudes de eliminación de perfil.|
|**Investigador**|Gestiona su perfil, carga de trabajo, publicaciones y entregables propios; consulta proyectos, investigadores y recompensas permitidas.|

Los permisos y recorridos completos se encuentran en [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md) y en los [diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md).

## Funcionalidades principales

- **Sesión y perfiles**: autenticación, edición del perfil y solicitudes de eliminación.
- **Proyectos**: creación, edición, participantes, histórico, archivos adjuntos y archivado lógico.
- **Carga de trabajo**: control de dedicación por sede y límite de 16 horas docentes semanales.
- **Asignación responsable**: recomendación de investigadores compatibles con menor carga.
- **Entregables**: creación, revisión, versionado de archivos y retirada lógica.
- **Publicaciones**: publicación, conversación, edición y retirada según autoría y permisos.
- **Recompensas**: reconocimiento económico o reducción docente por proyectos completados.
- **Convocatorias**: consulta e importación extensible desde fuentes externas.

## Reglas destacadas del dominio

- No todos los investigadores son docentes: esta condición depende de su sede.
- Los investigadores-docentes no pueden superar **16 horas semanales de docencia**.
- La reducción docente se concede en múltiplos de **4 horas**, correspondientes a asignaturas completas.
- Los investigadores sin docencia únicamente pueden recibir recompensas económicas.
- Los proyectos completados se archivan automáticamente y conservan su histórico.
- Las operaciones `eliminar...` priorizan bajas lógicas, retirada, anulación o archivado para preservar la trazabilidad.
- Coordinador e Investigadores participantes pueden subir y consultar archivos de proyecto; solo el Coordinador puede eliminarlos.

## El proyecto

GIPF se construye mediante un proceso RUP incremental. Cada comportamiento parte de un caso de uso y mantiene trazabilidad hasta su análisis, diseño, código y pruebas.

|Disciplina|Contenido|
|-|-|
|[Requisitos](/RUP/00-casos-uso/README.md)|Modelo del dominio, actores, diagramas de contexto, detalle y prototipos.|
|[Análisis](/RUP/01-analisis/README.md)|Colaboraciones MVC, responsabilidades y entidades conceptuales.|
|[Diseño](/RUP/02-diseño/README.md)|Arquitectura, clases de diseño y secuencias técnicas con acceso a datos.|
|[Desarrollo](/RUP/03-desarrollo/README.md)|Implementación incremental por familias funcionales.|
|[Pruebas](/RUP/04-pruebas/README.md)|Pruebas de integración, permisos, recorridos HTTP y evidencias visuales.|

### Principios aplicados

- **Responsabilidad única**: presentación, coordinación, reglas del dominio y persistencia permanecen separadas.
- **Open/Closed Principle**: políticas e importadores se amplían mediante nuevas implementaciones sin modificar los flujos estables.
- **Seguridad por rol**: cada operación valida la identidad, autoría y alcance del usuario autenticado.
- **Trazabilidad histórica**: los datos relevantes no desaparecen mediante borrados físicos ordinarios.
- **Tecnología agnóstica en Requisitos y Análisis**: los frameworks aparecen únicamente al llegar a Diseño.

## Arquitectura

<div align="center">

![Arquitectura de GIPF](/images/RUP/02-diseño/arquitectura.svg)

</div>

```text
React + TypeScript
        |
        v
API REST + Spring Security
        |
        v
Servicios de aplicación y dominio
        |
        v
Spring Data JPA + Flyway
        |
        v
H2 local / PostgreSQL en producción
```

### Stack

|Capa|Tecnologías|
|-|-|
|Frontend|React 18, TypeScript, Vite y Lucide React|
|Backend|Java 17, Spring Boot 3.5, Spring Security, Spring Data JPA y Bean Validation|
|Persistencia|H2 local, PostgreSQL previsto para producción y migraciones Flyway|
|Modelado|PlantUML y Salt|
|Pruebas|JUnit, Spring Boot Test, Spring Security Test y MockMvc|

## Ejecución local

### Requisitos

- Java 17.
- Node.js y npm.

### Aplicación completa

```powershell
cd src/frontend
npm install
npm run dev
```

El comando inicia Spring Boot cuando el backend no está disponible y después abre el frontend:

- Frontend: `http://127.0.0.1:5173`
- API: `http://127.0.0.1:8080`

### Usuarios de demostración

|Perfil|Usuario|Contraseña|
|-|-|-|
|Coordinador|`coordinador`|`coordinador123`|
|Investigador|`investigador`|`investigador123`|
|Investigador-docente de Santander|`docente.santander`|`docente123`|
|Investigador sin docencia de Barcelona|`investigador.barcelona`|`barcelona123`|

Las contraseñas son exclusivamente datos locales de demostración. La base de datos conserva hashes BCrypt, nunca contraseñas en texto plano.

## Verificación

```powershell
cd src/backend
.\mvnw.cmd test

cd ../frontend
npm run build
npm run lint
```

|Comprobación actual|Resultado|
|-|-|
|Suite backend|48 pruebas correctas|
|Compilación frontend de producción|Correcta|
|Lint frontend|Sin errores; un aviso no bloqueante|
|Autenticación y permisos por rol|Comprobados|
|Recorridos manuales principales|Comprobados incrementalmente|
|Diagramas PlantUML|Fuentes y SVG versionados|

## Estado actual

El proyecto ha completado las nueve familias funcionales y el bloque transversal de cierre:

- Sesión, perfil, carga de trabajo, recompensas, proyectos, investigadores, entregables, publicaciones y convocatorias disponen de trazabilidad RUP y desarrollo funcional.
- La auditoría transversal de Análisis y SOLID cubre los 71 casos de uso.
- Permanecen pendientes el despliegue público, la regresión manual final y completar documentación de Pruebas por caso de uso.

El seguimiento vivo se mantiene en [tareas_a_realizar.md](/tareas_a_realizar.md) y las decisiones técnicas relevantes en [incidencias_y_soluciones.md](/incidencias_y_soluciones.md).

## Estructura del repositorio

```text
25-26-idsw2-sdVC/
├── RUP/                         # Requisitos, Análisis, Diseño, Desarrollo y Pruebas
├── modelosUML/                  # Fuentes PlantUML
├── images/                      # SVG generados y evidencias visuales
├── documents/                   # Reuniones y vocabulario del dominio
├── src/
│   ├── backend/                 # API Spring Boot
│   └── frontend/                # Aplicación React
├── tools/                       # Automatización de desarrollo
├── conversation-log.md          # Registro cronológico del proceso
├── incidencias_y_soluciones.md  # Problemas encontrados y soluciones aplicadas
├── tareas_a_realizar.md         # Seguimiento de trabajo pendiente
└── README.md                    # Presentación del proyecto
```

## Trazabilidad y documentación

- [Qué hace el sistema](/QUE_HACE.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Detalle y prototipado](/RUP/00-casos-uso/02-detalle/README.md)
- [Configuración técnica](/RUP/02-diseño/configuracion-proyecto.md)
- [Análisis del resultado frente a la asignatura](/documents/analisis-resultado-asignatura.md)
- [Glosario](/documents/vocabulario.md)
- [Reuniones con la cliente](/documents/reuniones.md)
- [Log de conversación](/conversation-log.md)
