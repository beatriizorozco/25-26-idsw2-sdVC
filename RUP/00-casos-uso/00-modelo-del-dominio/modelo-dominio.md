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

# Modelo del dominio

## Objetivo

#### El objetivo de este proyecto es diseñar un solo sistema en donde:
#### 1. Se pueda llevar una gestión de los proyectos asociados con los investigadores, en donde entre otras cosas se podrán revisar los entregables de un proyecto.
#### 2. Estén registradas las diferentes entidades de la red de Funiber (investigador, coordinador, etc), los cuales podrán interactuar entre ellos.


## Vocabulario del proyecto

#### En base a nuestra primera reunión definimos los términos clave del proyecto y los explicamos, a continuación los términos mas importantes del [vocabulario](/documents/vocabulario.md)

---

###  Estados de un proyecto

| **Término** | **Definición** | **Notas / Ejemplo** |
|--------------|----------------|----------------------|
| **Entidad convocante** | Organización o institución que emite una convocatoria. | Puede ser la Unión Europea, el Ministerio de Ciencia, el Gobierno de Cantabria, etc. |
| **Convocatoria** | Anuncio oficial de una oportunidad de financiación o participación en un proyecto de investigación. | Ejemplo: “Convocatoria Horizonte Europa 2025”. |
| **Propuesta** | Siguiente fase de una convocatoria, en donde se concretan los detalles de la misma | Ejemplo: Investigación sobre enfermedad rara.|
| **Proyecto** | Última fase de una convocatoria, en donde se tienen unos objetivos, un grupo de investigadores/docentes, una fecha de entrega y una documentación del mismo| ----- |

---

###  Usuarios y Roles

| **Término** | **Definición** | **Notas / Ejemplo** |
|--------------|----------------|----------------------|
| **Investigador / Docente** | Usuario registrado que busca, participa o coordina proyectos de investigación. | Incluye personal académico afiliado a FUNIBER. |
| **Perfil de investigador** | Conjunto de datos que describen la especialización, intereses, experiencia y criterios de elegibilidad del usuario. | Se utiliza para filtrar y recomendar convocatorias. |
| **Coordinador** | Rol o proceso encargado de revisar que la información (convocatorias, proyectos, documentación) sea correcta antes de su publicación. | Puede ser un rol administrativo dentro del sistema. |
| **Antena** | Rol o proceso encargado de estar al tanto de nuevas convocatorias | Puede ser un rol administrativo dentro del sistema (coordinador y antena están en el mismo nivel de permisos dentro del sistema)|

---



## Diagramas de clases

#### A continuación el diagrama de clases principal del proyecto, en donde se resalta que la entidad principal es proyecto.

<div align=center>

|![](https://raw.githubusercontent.com/beatriizorozco/25-26-idsw2-sdVC/develop/images/RUP/00-casos-uso/00-modelo-del-dominio/00-diagrama-clases/diagrama-clases-proyecto-red.svg)|
|:-:|
|**[código](/modelosUML/rup/00-casos-uso/00-modelo-del-dominio/00-diagrama-clases/diagrama-clases-proyecto-red.puml)**| 

</div>

#### De este diagrama elaboramos dos subdiagramas, los cuales reflejan mejor las necesidades de nuestro sistema y en donde cada uno tiene un contexto muy diferenciado del otro, los cuales son: la gestión de proyectos para el primer diagrama y la red de investigadores para el segundo diagrama.


<div align=center>

|![](https://raw.githubusercontent.com/beatriizorozco/25-26-idsw2-sdVC/develop/images/RUP/00-casos-uso/00-modelo-del-dominio/00-diagrama-clases/diagrama-clases-proyecto.svg)|![](https://raw.githubusercontent.com/beatriizorozco/25-26-idsw2-sdVC/develop/images/RUP/00-casos-uso/00-modelo-del-dominio/00-diagrama-clases/diagrama-clases-red.svg)|
|:-:|:-:|
|**[código](/modelosUML/rup/00-casos-uso/00-modelo-del-dominio/00-diagrama-clases/diagrama-clases-proyecto.puml)**| **[código](/modelosUML/rup/00-casos-uso/00-modelo-del-dominio/00-diagrama-clases/diagrama-clases-red.puml)**|

</div>

#### De estos dos diagramas elaboramos los siguientes diagramas de estados de las entidades principales de cada uno de ellos, es decir proyecto e investigador/docente, sin olvidarnos de los dos diferentes contextos que puede tener cada entidad.



## Diagramas de estado

#### En los siguientes diagramas de estado se modelan los diferentes comportamientos que puede tener un proyecto.

<div align=center>

|:-:|:-:|

</div>
#### En los siguientes diagramas de estado se modelan los diferentes comportamientos que puede tener un investigador.

<div align=center>

|:-:|:-:|

</div>

## Diagrama de objetos

#### En el siguiente diagrama se implementa una instancia del sistema de gestión de proyectos, en donde hay varios investigadores participando en un proyecto en donde destacan varios aspectos del proyecto como los entregables o las recompensas del mismo hacia los investigadores.

<div align=center>

|:-:|

</div>

#### En el siguiente diagrama se implementa una instancia del sistema de red de investigadores, en donde de nuevo hay varios investigadores participando en un proyecto pero en este caso destacan aspectos relacionados con los investigadores como los perfiles de los mismos.

<div align=center>

|:-:|

</div>
#### Por último el diagrama de objetos unificado, basado en el diagrama de clases de la red de investigadores + gestión de proyectos


<div align=center>

|:-:|

</div>
