# FUNIBER > Coordinador > editarPerfil > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarPerfil/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante `PerfilIntegrationTests.coordinadorPuedeActualizarSuPerfilPropio`.

## Resultado

- Se valida que `PATCH /api/perfil` actualiza el perfil propio del Coordinador.
- Se comprueba que la respuesta devuelve los datos modificados.
- Queda pendiente completar evidencia visual manual del formulario.
