<h1 align="center">FUNIBER — Plataforma Interna de Investigación</h1>

<div align="center">

[![](https://img.shields.io/badge/-Inicio-0A3B64?style=for-the-badge&logo=github&logoColor=white)](/README.md)
[![](https://img.shields.io/badge/-Modelo_del_Dominio-0A3B64?style=for-the-badge&logo=drawio&logoColor=white)](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
[![](https://img.shields.io/badge/-Actores_y_casos_de_uso-0A3B64?style=for-the-badge&logo=group&logoColor=white)](/RUP/00-casos-uso/01-actores-casos-uso/actores-casos-uso.md)
[![](https://img.shields.io/badge/-Glosario-0A3B64?style=for-the-badge&logo=notepad&logoColor=white)](/documents/vocabulario.md)

[![](https://img.shields.io/badge/-Detallado_y_Prototipos-0A3B64?style=for-the-badge&logo=notepad&logoColor=white)](/RUP/00-casos-uso/02-detalle/README.md)
[![](https://img.shields.io/badge/-Diagrama_de_Contexto-0A3B64?style=for-the-badge&logo=flowchart&logoColor=white)](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
[![](https://img.shields.io/badge/-Reuniones-0A3B64?style=for-the-badge&logo=google-meet&logoColor=white)](/documents/reuniones.md)
[![](https://img.shields.io/badge/-Priorización-0A3B64?style=for-the-badge&logo=priority&logoColor=white)](/RUP/00-casos-uso/01-actores-casos-uso/priorizacion-cdu.md)
[![](https://img.shields.io/badge/-Rúbrica-0A3B64?style=for-the-badge&logo=checklist&logoColor=white)](https://github.com/mmasias/25-26-IdSw1-SdR/blob/main/documents/l'Rubrica.md)

[![](https://img.shields.io/badge/-Log_de_conversación-0A3B64?style=for-the-badge&logo=gnometerminal&logoColor=white)](/conversation-log.md)

</div>
# Diagramas de contexto

Este documento recoge los **diagramas de contexto** del sistema para los dos actores principales: **Coordinador** e **Investigador**.  
Cada diagrama define los **estados de pantalla** y las **transiciones** permitidas mediante acciones (casos de uso) coherentes con el contexto del actor.

---

## Coordinador — Diagrama de contexto

- **Actor**: Coordinador  
- **Objetivo**: Visualizar la navegación completa del coordinador (visión global del sistema) y las acciones disponibles desde cada pantalla.

<div align=center>

|![Diagrama de contexto: Coordinador](https://raw.githubusercontent.com/beatriizorozco/25-26-idsw2-sdVC/develop/images/RUP/00-casos-uso/01-actores-casos-uso/diagrama-contexto-coordinador.svg)|
|-|
|Código fuente: [especificacion.puml](/modelosUML/rup/00-casos-uso/01-actores-casos-uso/diagrama-contexto-coordinador.puml)|


</div>
### Estados principales resumidos
- `SESION_CERRADA` / `PANEL_PRINCIPAL_ABIERTO`
- Accesos desde panel: `OPCIONES_PERFIL_ABIERTO`, `OPCIONES_CARGA_TRABAJO_ABIERTAS`, `PROYECTOS_ABIERTOS`, `INVESTIGADORES_ABIERTOS`, `MIS_PUBLICACIONES_ABIERTAS`, `PUBLICACIONES_ABIERTAS`, `CONVOCATORIAS_ABIERTAS`, `RECOMPENSAS_ABIERTAS`
- Subpantallas de detalle: `PUBLICACION_ABIERTA`, `MI_PUBLICACION_ABIERTA`, `CONVOCATORIA_ABIERTA`, `RECOMPENSA_ABIERTA`, `PROYECTO_ABIERTO`, `ENTREGABLES_ABIERTOS`, `ENTREGABLE_ABIERTO`, `INVESTIGADOR_ABIERTO`, `SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS`, `SOLICITUD_ELIMINACION_PERFIL_ABIERTA`

---

## Investigador — Diagrama de contexto

- **Actor**: Investigador  
- **Objetivo**: Visualizar la navegación del investigador (operación sobre información propia y acceso de consulta) y sus acciones disponibles.

<div align=center>

|![Diagrama de contexto: Investigador](https://raw.githubusercontent.com/beatriizorozco/25-26-idsw2-sdVC/develop/images/RUP/00-casos-uso/01-actores-casos-uso/diagrama-contexto-investigador.svg)|
|-|
|Código fuente: [especificacion.puml](/modelosUML/rup/00-casos-uso/01-actores-casos-uso/diagrama-contexto-investigador.puml)|


</div>
### Estados principales resumidos
- `SESION_CERRADA` / `PANEL_PRINCIPAL_ABIERTO`
- Accesos desde panel: `OPCIONES_PERFIL_ABIERTO`, `OPCIONES_CARGA_TRABAJO_ABIERTAS`, `PROYECTOS_ABIERTOS`, `INVESTIGADORES_ABIERTOS`, `MIS_PUBLICACIONES_ABIERTAS`, `PUBLICACIONES_ABIERTAS`, `RECOMPENSAS_ABIERTAS`
- Subpantallas de detalle: `PUBLICACION_ABIERTA`, `MI_PUBLICACION_ABIERTA`, `PROYECTO_ABIERTO`, `ENTREGABLES_ABIERTOS`, `ENTREGABLE_ABIERTO`, `RECOMPENSA_ABIERTA`, `INVESTIGADOR_ABIERTO`

---






