# FUNIBER > Investigador > solicitarEliminacionPerfil > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/solicitarEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigación
- **Fase RUP**: Elaboration (Elaboración)
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Equipo de desarrollo

## Propósito

Analizar la colaboración necesaria para registrar una solicitud de eliminación de perfil. El análisis identifica clases Boundary, Control y Entity, sus responsabilidades y colaboraciones necesarias para cumplir con el caso de uso `solicitarEliminacionPerfil()`.

## Diagrama de colaboración

<div align=center>

|![Análisis: solicitarEliminacionPerfil()](/images/RUP/01-analisis/casos-uso/investigador/solicitarEliminacionPerfil/solicitarEliminacionPerfil-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de análisis identificadas

### Clases de vista (boundary)

#### SolicitarEliminacionPerfilView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `solicitarEliminacionPerfil()` del Investigador.
- Presentar la información de perfiles necesaria para el caso de uso.
- Capturar datos, criterios o confirmaciones introducidos por el Investigador.
- Invocar al controlador para ejecutar la operación de análisis.
- Mantener la navegación hacia el estado siguiente o colaboraciones relacionadas.

**Colaboraciones**:
- **Entrada**: Recibe `solicitarEliminacionPerfil()` desde el estado de contexto correspondiente.
- **Control**: Se comunica con `PerfilController`.
- **Salida**: Si confirma, pasa a `SESION_CERRADA`; si cancela, vuelve a `OPCIONES_PERFIL_ABIERTO`.

### Clases de control

#### PerfilController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la ejecución del caso de uso `solicitarEliminacionPerfil()`.
- Aplicar reglas de permisos del Investigador.
- Validar datos o criterios antes de acceder a las entidades.
- Servir como intermediario entre la vista y el repositorio.

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `SolicitarEliminacionPerfilView`.
- **Repositorio**: Consulta `PerfilRepository` y registra la solicitud en `SolicitudEliminacionPerfilRepository`.

### Clases de entidad (entity)

#### PerfilRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Abstraer el acceso a datos de perfiles.
- Proporcionar la operación `existeSolicitudPendiente(perfil)`.
- Mantener la consistencia conceptual de perfiles.
- Encapsular restricciones de consulta o modificación asociadas al rol.

**Colaboraciones**:
- **Control**: Responde a `PerfilController`.
- **Entidad**: Gestiona instancias de `Perfil`.

#### SolicitudEliminacionPerfilRepository
**Estereotipo**: Entidad
**Responsabilidades**:
- Abstraer el registro de solicitudes de eliminación de perfil.
- Proporcionar la operación `guardar(solicitud)`.
- Mantener la consistencia conceptual de las solicitudes pendientes.

**Colaboraciones**:
- **Control**: Responde a `PerfilController`.
- **Entidad**: Gestiona instancias de `SolicitudEliminacionPerfil`.

#### SolicitudEliminacionPerfil
**Estereotipo**: Entidad
**Responsabilidades**:
- Representar la solicitud de eliminación de perfil.
- Encapsular solicitante, motivo, fecha y estado de la solicitud.
- Mantener la integridad de los datos usados para revisión posterior.

**Colaboraciones**:
- **Repositorio**: Es gestionada por `SolicitudEliminacionPerfilRepository`.

#### Perfil
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de perfil.
- Encapsular atributos relevantes del dominio.
- Mantener la integridad de los datos usados por el caso de uso.

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PerfilRepository`.

## Flujo de colaboración

### Secuencia de operaciones

1. **Inicio**: Estado de contexto -> `SolicitarEliminacionPerfilView.solicitarEliminacionPerfil()`.
2. **Solicitud principal**: `SolicitarEliminacionPerfilView` -> `PerfilController.solicitarEliminacionPerfil(datos)`.
3. **Validación previa**: `SolicitarEliminacionPerfilView` -> `PerfilController.validarSolicitud(datos)`.
4. **Consulta de consistencia**: `PerfilController` -> `PerfilRepository.existeSolicitudPendiente(perfil)`.
5. **Registro de solicitud**: `PerfilController` -> `SolicitudEliminacionPerfilRepository.guardar(solicitud)`.
6. **Finalización**: `SolicitarEliminacionPerfilView` dirige a `SESION_CERRADA` si confirma o a `OPCIONES_PERFIL_ABIERTO` si cancela.

### Patrón de colaboración establecido

- **Entrada estándar**: Desde el estado activo del diagrama de contexto del Investigador.
- **Análisis MVC completo**: Vista, Control y Entidad claramente separados.
- **Salida estándar**: Retorno a la navegación permitida o a una colaboración relacionada.

## Correspondencia con requisitos

### Mapeado con especificación detallada

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Atender la solicitud `solicitarEliminacionPerfil()`|`SolicitarEliminacionPerfilView`|Recibe la acción del Investigador|
|Coordinar reglas del caso de uso|`PerfilController`|`solicitarEliminacionPerfil(datos)`|
|Aplicar permisos y validaciones|`PerfilController`|`validarSolicitud(datos)`|
|Consultar solicitudes pendientes|`PerfilRepository`|`existeSolicitudPendiente(perfil)`|
|Registrar solicitud|`SolicitudEliminacionPerfilRepository`|`guardar(solicitud)`|
|Representar atributos de dominio|`Perfil`|Entidad conceptual|

### Atributos tratados

|Atributo conceptual|Entidad responsable|Observación|
|-|-|-|
|nombre|`Perfil`|Atributo conceptual tratado por la entidad de dominio.|
|correo|`Perfil`|Atributo conceptual tratado por la entidad de dominio.|
|especialización|`Perfil`|Atributo conceptual tratado por la entidad de dominio.|
|preferencias|`Perfil`|Atributo conceptual tratado por la entidad de dominio.|

## Colaboraciones relacionadas

- No requiere colaboraciones adicionales; finaliza en `SESION_CERRADA` o vuelve a `OPCIONES_PERFIL_ABIERTO` según la decisión del Investigador.

## Reglas funcionales consideradas

- Mantener la separación entre presentación, coordinación y entidad para el rol Investigador.
- Restringir operaciones de creación, edición y eliminación a publicaciones y entregables propios del Investigador.

## Características del análisis

### Separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador.
- **Control**: Solo coordinación, permisos y lógica de aplicación.
- **Entidad**: Solo datos, repositorios y reglas conceptuales del dominio.

### Agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario.
- No asume implementación concreta de base de datos.
- Mantiene independencia de frameworks.

### Trazabilidad completa

- **Origen**: Caso de uso detallado `solicitarEliminacionPerfil()`.
- **Destino**: Base para diseño arquitectónico posterior.
- **Conexión**: Diagrama de contexto -> Análisis de colaboración -> Diseño.

## Patrones aplicados

### Repository pattern
`PerfilRepository` abstrae el acceso a datos de perfiles, permitiendo cambiar la implementación sin afectar al controlador.

### MVC pattern
Separación clara entre presentación (`SolicitarEliminacionPerfilView`), lógica de aplicación (`PerfilController`) y datos (`Perfil`, `PerfilRepository`).

### Sistema de estados
Mantiene coherencia con el diagrama de contexto del Investigador, respetando las transiciones de estado establecidas.

## Referencias

- [Especificación detallada: solicitarEliminacionPerfil()](/RUP/00-casos-uso/02-detalle/investigador/solicitarEliminacionPerfil/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Actores y casos de uso](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [Log de conversaciones](/conversation-log.md)
