# Auditoría de trazabilidad entre Diseño y Desarrollo

## Objetivo

Comprobar que las operaciones representadas como llamadas a métodos en los diagramas de secuencia de `RUP/02-diseño/casos-uso` existen en las clases Java de `src/backend`.

La auditoría diferencia entre:

- Métodos declarados explícitamente en el código.
- Métodos heredados de `JpaRepository`, como `save()` y `findById()`.
- Tipos de respuesta representados en los diagramas, que no son métodos.
- Operaciones conceptuales de Diseño que no están declaradas literalmente en Desarrollo.

## Resultado inicial

Se revisaron los **69 diagramas de secuencia** del proyecto.

La trazabilidad literal todavía no es completa:

- **134 pares clase/método** aparecen como no declarados en una primera comparación.
- **5 pares** corresponden a tipos `SesionResponse`, no a métodos.
- **4 pares** corresponden a métodos heredados de Spring Data.
- Permanecen **125 pares clase/método** que requieren alineación semántica entre Diseño y Desarrollo.

Esto no significa que falten 125 funcionalidades. La mayoría de discrepancias proceden de diagramas que describen responsabilidades internas como métodos independientes, mientras el código las implementa dentro de operaciones públicas más cohesionadas.

## Discrepancias prioritarias

|Diseño|Desarrollo actual|Decisión recomendada|
|-|-|-|
|`AuthService.validarCredenciales()`|`AuthService.autenticar()`|Actualizar Diseño.|
|`SesionService.exigirCoordinador()` y `exigirInvestigador()`|`SesionService.obtenerSesionActual()` y validación de rol en servicios de acceso|Actualizar Diseño sin duplicar métodos por rol.|
|`ConsultaConvocatoriaService.obtenerDetalle()`|`ConsultaConvocatoriaService.obtener()`|Actualizar Diseño.|
|`PoliticaConvocatoria.permitirConsulta()` y `permitirImportacion()`|`exigirConsulta()` y `exigirImportacion()`|Actualizar Diseño.|
|`PerfilService.editarPerfilPropio()`|`PerfilService.actualizarPerfilPropio()`|Actualizar Diseño.|
|Variantes `listar...`, `obtenerDetalle...`, `crearPropio...` y `actualizarPropio...`|Operaciones cohesionadas `listar()`, `obtener()`, `crear()` y `actualizar()`|Actualizar Diseño y representar permisos como reglas, no como duplicación de métodos.|
|Operaciones de repositorio como `guardar()` u `obtenerActivoPorId()`|Métodos heredados o consultas derivadas de Spring Data|Usar los nombres reales `save()`, `findById...()` o representar el filtrado en el servicio.|
|Validaciones internas como `validarDatosYFechas()`|Lógica integrada en servicios o entidades|Representarlas como responsabilidad interna si no existe un método real.|

## Criterio de corrección

No se deben añadir métodos alias al código únicamente para hacer coincidir los diagramas. Esa solución aumentaría el tamaño de las clases, duplicaría responsabilidades y perjudicaría SRP y OCP.

La corrección debe realizarse por familias funcionales:

1. Sesión, perfil y carga de trabajo.
2. Proyectos, investigadores y entregables.
3. Publicaciones, respuestas y archivos.
4. Recompensas y convocatorias.

En cada familia se debe conservar la intención del diagrama, pero utilizar los nombres públicos reales del código. Las operaciones puramente internas deben mostrarse como responsabilidades o notas cuando no sean métodos declarados.

## Resultado final

Se alinearon los **69 diagramas de secuencia** con las clases y operaciones reales de Desarrollo:

- Los nombres antiguos se sustituyeron por las operaciones públicas o privadas realmente declaradas.
- Los repositorios conceptuales se sustituyeron por los repositorios reales y sus consultas de Spring Data.
- Las validaciones y cálculos integrados que no constituyen métodos independientes se conservaron como notas de responsabilidad.
- Las acciones puramente visuales y las operaciones internas de Spring Security dejaron de representarse como métodos propios del proyecto.
- Se regeneraron los **69 SVG** de Diseño.

La ejecución final de la auditoría revisa **355 llamadas** y termina con:

```text
Llamadas a metodos no declarados: 0
```

## Repetición de la auditoría

Desde la raíz del repositorio:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File tools/auditar-trazabilidad-diseno.ps1
```

El script termina con código `1` mientras existan llamadas a métodos no declarados y con código `0` cuando la trazabilidad sea completa.

Para reaplicar de forma reproducible el mapeo semántico:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File tools/alinear-metodos-diseno.ps1
```
