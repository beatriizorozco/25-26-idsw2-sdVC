# FUNIBER > Coordinador > crearRecompensa > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearRecompensa/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/crearRecompensa/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/crearRecompensa/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para registrar una nueva recompensa. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `crearRecompensa()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: crearRecompensa()](/images/RUP/01-analisis/casos-uso/coordinador/crearRecompensa/crearRecompensa-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### CrearRecompensaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `crearRecompensa()` del Coordinador.
- Presentar proyectos completados pendientes de recompensa.
- Presentar investigadores elegibles del proyecto seleccionado.
- Presentar únicamente los tipos de recompensa permitidos para el beneficiario.
- Capturar proyecto, beneficiario, concepto, tipo y valor introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `crearRecompensa()` desde `RECOMPENSAS_ABIERTAS`.
- **Control**: Se comunica con `RecompensaController`.
- **Salida**: Navega a `RECOMPENSA_ABIERTA` con la recompensa creada o conserva `RECOMPENSAS_ABIERTAS` si el Coordinador cancela.

### Clases de control

#### RecompensaController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `crearRecompensa()`.
- Preparar los proyectos y beneficiarios disponibles para la creación.
- Determinar los tipos de recompensa permitidos según condición docente y sede.
- Validar los datos y evitar recompensas duplicadas antes de registrar.
- Servir como intermediario entre la vista y los repositorios implicados.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CrearRecompensaView`.
- **Repositorio**: Delega operaciones de datos a `ProyectoRepository`, `InvestigadorRepository` y `RecompensaRepository`.

### Clases de entidad (entity)

#### RecompensaRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Abstraer el acceso a datos de recompensas.
- Proporcionar operaciones `existeDuplicado(datos)` y `guardar(entidad)`.
- Mantener la consistencia conceptual de recompensas.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `RecompensaController`.
- **Entidad**: Gestiona instancias de `Recompensa`.

#### Recompensa
**Estereotipo**: Entidad
**Responsabilidades**:
- Representar la información de recompensa.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `RecompensaRepository`.

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar el proyecto que origina la recompensa.
- Validar que el proyecto se encuentre completado antes de crear la recompensa.

#### ProyectoRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Obtener proyectos completados pendientes de recompensa.
- Garantizar que el proyecto seleccionado puede originar una recompensa.

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar al beneficiario seleccionado para la recompensa.
- Determinar si el beneficiario admite recompensa económica, reducción docente o solo recompensa económica.

#### InvestigadorRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Obtener investigadores elegibles del proyecto seleccionado.
- Recuperar la condición docente y la sede del beneficiario.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: `RECOMPENSAS_ABIERTAS` -> `CrearRecompensaView.crearRecompensa()`.
2. **Preparación**: `CrearRecompensaView` -> `RecompensaController.prepararCreacion()`.
3. **Proyectos disponibles**: `RecompensaController` -> `ProyectoRepository.obtenerCompletadosPendientesRecompensa()`.
4. **Selección de proyecto**: `CrearRecompensaView` -> `RecompensaController.seleccionarProyecto(proyectoId)`.
5. **Beneficiarios disponibles**: `RecompensaController` -> `InvestigadorRepository.obtenerElegiblesPorProyecto(proyectoId)`.
6. **Selección de beneficiario**: `CrearRecompensaView` -> `RecompensaController.seleccionarBeneficiario(investigadorId)`.
7. **Tipos permitidos**: `RecompensaController` recupera al beneficiario y determina los tipos permitidos según condición docente y sede.
8. **Solicitud de creación**: `CrearRecompensaView` -> `RecompensaController.crearRecompensa(datos)`.
9. **Validación previa**: `RecompensaController.validarRecompensa(datos)`.
10. **Consulta de consistencia**: `RecompensaController` -> `RecompensaRepository.existeDuplicado(datos)`.
11. **Persistencia**: `RecompensaController` -> `RecompensaRepository.guardar(entidad)`.
12. **Finalización**: si se registra, `CrearRecompensaView` deriva a `abrirRecompensa()` y queda en `RECOMPENSA_ABIERTA`; si se cancela, conserva `RECOMPENSAS_ABIERTAS`.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `crearRecompensa()`|`CrearRecompensaView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`RecompensaController`|`crearRecompensa(datos)`|
|Preparar la creación|`RecompensaController`|`prepararCreacion()`|
|Listar proyectos completados pendientes|`ProyectoRepository`|`obtenerCompletadosPendientesRecompensa()`|
|Listar beneficiarios elegibles|`InvestigadorRepository`|`obtenerElegiblesPorProyecto(proyectoId)`|
|Determinar tipos permitidos|`RecompensaController`|`obtenerTiposPermitidos(beneficiario)`|
|Aplicar validaciones|`RecompensaController`|`validarRecompensa(datos)`|
|Acceder a datos de recompensas|`RecompensaRepository`|`existeDuplicado(datos)`, `guardar(entidad)`|
|Verificar beneficiario|`InvestigadorRepository`|`obtenerBeneficiario(datos.investigadorId)`|
|Representar atributos de dominio|`Recompensa`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|concepto|`Recompensa`|Atributo conceptual tratado por la entidad de dominio.|
|proyecto|`Recompensa`|Atributo conceptual tratado por la entidad de dominio.|
|investigador|`Recompensa`|Atributo conceptual tratado por la entidad de dominio.|
|estado|`Recompensa`|Atributo conceptual tratado por la entidad de dominio.|
|valor|`Recompensa`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- **abrirRecompensa()**: colaboración relacionada desde la navegación del caso de uso.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidades de recompensa, proyecto e investigador.
- Crear recompensas solo desde proyectos completados.
- Asociar siempre la recompensa a un investigador beneficiario.
- Permitir recompensa económica o reducción docente cuando el beneficiario es investigador-docente.
- Restringir a recompensa económica cuando el beneficiario pertenece a una sede sin docencia.
- Validar que la reducción docente represente entre una y cuatro asignaturas completas de 4 horas.
- Evitar duplicados para el mismo proyecto, beneficiario y tipo de recompensa.

## Características del análisis

### Separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador.
- **Control**: Solo coordinación, permisos y lógica de aplicación.
- **Entidad**: Solo datos, repositorios y reglas conceptuales del dominio.

### Agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario.
- No asume implementación concreta de base de datos.
- Mantiene independencia de frameworks.

### Trazabilidad completa

- **Origen**: Caso de uso detallado `crearRecompensa()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`RecompensaRepository` abstrae el acceso a datos de recompensas, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`CrearRecompensaView`), lógica de aplicación (`RecompensaController`) y datos (`Recompensa`, `RecompensaRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: crearRecompensa()](/RUP/00-casos-uso/02-detalle/coordinador/crearRecompensa/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
