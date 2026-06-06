# FUNIBER > Coordinador > editarRecompensa > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarRecompensa/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarRecompensa/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarRecompensa/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarRecompensa/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para actualizar los datos de recompensa. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `editarRecompensa()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: editarRecompensa()](/images/RUP/01-analisis/casos-uso/coordinador/editarRecompensa/editarRecompensa-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### EditarRecompensaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `editarRecompensa()` del Coordinador.
- Presentar la información de recompensas necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Coordinador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `editarRecompensa()` desde `RECOMPENSA_ABIERTA`.
- **Control**: Se comunica con `RecompensaController`.
- **Salida**: Mantiene `RECOMPENSA_ABIERTA` o navega a `RECOMPENSAS_ABIERTAS`.

### Clases de control

#### RecompensaController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `editarRecompensa()`.
- Aplicar reglas de permisos del Coordinador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarRecompensaView`.
- **Repositorio**: Delega operaciones de datos a `RecompensaRepository`.

### Clases de entidad (entity)

#### RecompensaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de recompensas.
- Proporcionar operaciones `obtenerPorId(id)` y `actualizar(entidad)`.
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
- Representar el proyecto completado asociado a la recompensa.
- Mantener la trazabilidad entre recompensa y proyecto de origen.

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar al beneficiario de la recompensa editada.
- Determinar si el tipo actualizado respeta la condición docente y la sede del beneficiario.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `EditarRecompensaView.editarRecompensa()`.
2. **Solicitud principal**: `EditarRecompensaView` -> `RecompensaController.editarRecompensa(id, datos)`.
3. **Acceso a datos**: `EditarRecompensaView` -> `RecompensaController.validarCambios(datos)`.
4. **Obtención actual**: `RecompensaController` -> `RecompensaRepository.obtenerPorId(id)`.
5. **Validación de origen**: `RecompensaController` -> `ProyectoRepository.verificarProyectoCompletado(recompensa)`.
6. **Validación de beneficiario**: `RecompensaController` -> `InvestigadorRepository.verificarBeneficiario(datos.investigadorId)`.
7. **Persistencia**: `RecompensaController` -> `RecompensaRepository.actualizar(entidad)`.
8. **Finalización**: `EditarRecompensaView` mantiene `RECOMPENSA_ABIERTA` o deriva a `abrirRecompensas()` / `eliminarRecompensa()`.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Coordinador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `editarRecompensa()`|`EditarRecompensaView`|Recibe la acción del Coordinador|
|Coordinar reglas del caso de uso|`RecompensaController`|`editarRecompensa(id, datos)`|
|Aplicar permisos y validaciones|`RecompensaController`|`validarCambios(datos)`|
|Acceder a datos de recompensas|`RecompensaRepository`|`obtenerPorId(id)`, `actualizar(entidad)`|
|Verificar proyecto completado|`ProyectoRepository`|`verificarProyectoCompletado(recompensa)`|
|Verificar beneficiario|`InvestigadorRepository`|`verificarBeneficiario(datos.investigadorId)`|
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

- **eliminarRecompensa()**: colaboración relacionada desde la navegación del caso de uso.
- **abrirRecompensas()**: colaboración relacionada desde la navegación del caso de uso.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidades de recompensa, proyecto e investigador.
- Editar recompensas sin perder la asociación al proyecto completado que las origina.
- Verificar que el beneficiario mantenga un tipo de recompensa permitido por su sede y condición docente.
- Permitir recompensa económica o reducción docente cuando el beneficiario es investigador-docente.
- Impedir reducción docente para investigadores de sedes sin docencia.
- Mantener la reducción docente en múltiplos de 4 horas, hasta un máximo de 16.

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

- **Origen**: Caso de uso detallado `editarRecompensa()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`RecompensaRepository` abstrae el acceso a datos de recompensas, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`EditarRecompensaView`), lógica de aplicación (`RecompensaController`) y datos (`Recompensa`, `RecompensaRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Coordinador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: editarRecompensa()](/RUP/00-casos-uso/02-detalle/coordinador/editarRecompensa/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
