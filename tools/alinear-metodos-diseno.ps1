param(
    [string]$Raiz = (Resolve-Path (Join-Path $PSScriptRoot ".."))
)

$directorioDiseno = Get-ChildItem (Join-Path $Raiz "RUP") -Directory |
    Where-Object Name -Like "02-*o" |
    Select-Object -First 1
$diagramas = Join-Path $directorioDiseno.FullName "casos-uso"
$utf8 = [System.Text.UTF8Encoding]::new($false)

$mapa = @{
    "AuthService" = @{
        "validarCredenciales" = "autenticar"
    }
    "SesionService" = @{
        "exigirCoordinador" = "obtenerSesionActual"
        "exigirInvestigador" = "obtenerSesionActual"
    }
    "ConsultaConvocatoriaService" = @{
        "obtenerDetalle" = "obtener"
    }
    "PoliticaConvocatoria" = @{
        "permitirConsulta" = "exigirConsulta"
        "permitirImportacion" = "exigirImportacion"
    }
    "InvestigadorService" = @{
        "obtenerDetalle" = "obtener"
    }
    "PerfilService" = @{
        "editarPerfilPropio" = "actualizarPerfilPropio"
        "actualizarCampos" = "actualizar"
    }
    "CargaTrabajoService" = @{
        "obtenerPanelCargaTrabajo" = "obtenerPanelCoordinador"
        "obtenerCargaDePersona" = "obtenerCargaPorPerfil"
        "actualizarCargaDePersona" = "actualizarCargaPorPerfil"
        "validarHoras" = "validarCargaDocente"
        "actualizarCampos" = "actualizarCarga"
        "calcularMargenDocente" = "mapearCarga"
        "marcarDocenciaNoAplicable" = "mapearCarga"
    }
    "ProyectoService" = @{
        "listarVisiblesPropios" = "listar"
        "obtenerDetalleGlobal" = "obtener"
        "obtenerDetalleVisible" = "obtener"
        "obtenerCandidatos" = "listarCandidatos"
        "asociarInvestigador" = "agregarInvestigador"
        "desasignarInvestigador" = "retirarInvestigador"
        "prepararArchivado" = "archivar"
    }
    "EntregableService" = @{
        "listarPorProyecto" = "listar"
        "listarVisiblesPorProyecto" = "listar"
        "obtenerDetalle" = "obtener"
        "obtenerDetalleVisible" = "obtener"
        "crearPropio" = "crear"
        "actualizarPropio" = "actualizar"
        "retirarPropio" = "retirar"
        "aplicarCambiosYValidar" = "actualizar"
    }
    "PublicacionService" = @{
        "listarActivas" = "listar"
        "listarPropias" = "listar"
        "obtenerActiva" = "obtener"
        "obtenerPropia" = "obtener"
        "actualizarPropia" = "actualizar"
        "retirarPropia" = "retirar"
        "aplicarCambiosYValidar" = "actualizar"
    }
    "RecompensaService" = @{
        "anular" = "eliminar"
        "calcularTiposPermitidos" = "tiposPermitidos"
        "validarTipoSegunBeneficiario" = "validarDatos"
        "validarTipoYValor" = "validarDatos"
    }
    "ValidadorConvocatoria" = @{
        "revalidar" = "validar"
        "validarDuplicado" = "yaIncorporada"
        "validarEstructura" = "validar"
    }
    "ArchivoPublicacionRepository" = @{
        "agregarAdjuntosOpcionales" = "save"
        "buscarPorPublicacion" = "findByPublicacionIdOrderByFechaSubidaDesc"
        "guardarAdjuntosOpcionales" = "save"
    }
    "CargaTrabajoRepository" = @{
        "guardar" = "save"
        "obtenerCargaActual" = "findByUsuario"
        "obtenerCargas" = "findAll"
        "obtenerDetallePorPersona" = "findByUsuario"
        "obtenerPorPersona" = "findByUsuario"
        "obtenerPorUsuario" = "findByUsuario"
        "obtenerResumenGlobal" = "findAll"
        "recalcularPrioridadParaProyectosLibres" = "save"
        "sugerirInvestigadoresDocentesConMenorCarga" = "findAll"
    }
    "ConvocatoriaRepository" = @{
        "buscar" = "findAllByOrderByFechaCierreAsc"
        "existeReferenciaExterna" = "existsByReferenciaExterna"
        "guardarImportada" = "save"
        "obtenerDisponibles" = "findAllByOrderByFechaCierreAsc"
        "obtenerImportada" = "findById"
        "obtenerPorId" = "findById"
    }
    "EntregableRepository" = @{
        "buscarActivosPorProyecto" = "findByProyectoIdAndRetiradoOrderByFechaCreacionDesc"
        "guardar" = "save"
        "obtenerActivoPorId" = "findByIdAndRetirado"
        "obtenerActivoPorIdYAutor" = "findByIdAndRetirado"
        "obtenerPendientesAsignados" = "findAll"
        "obtenerPorProyecto" = "findByProyectoIdAndRetiradoOrderByFechaCreacionDesc"
        "obtenerVisiblesPorProyecto" = "findByProyectoIdAndRetiradoOrderByFechaCreacionDesc"
        "retirar" = "save"
    }
    "ProyectoRepository" = @{
        "archivar" = "save"
        "buscarActivos" = "findByArchivadoOrderByNombreAsc"
        "buscarActivosPorInvestigador" = "findDistinctByInvestigadoresIdOrderByNombreAsc"
        "buscarActivosVisiblesPorInvestigador" = "findDistinctByInvestigadoresIdOrderByNombreAsc"
        "guardar" = "save"
        "obtenerActivoPorId" = "findById"
        "obtenerActivoVisiblePorId" = "findById"
        "obtenerCompletado" = "findById"
        "obtenerCompletadosPendientesRecompensa" = "findByEstadoOrderByNombreAsc"
        "obtenerParticipantes" = "findById"
        "obtenerPorInvestigador" = "findDistinctByInvestigadoresIdOrderByNombreAsc"
        "obtenerProyectosLibres" = "findByEstadoOrderByNombreAsc"
        "obtenerResumenes" = "findAll"
        "obtenerResumenHistorico" = "findById"
        "registrarAsignacion" = "save"
        "registrarDesasignacion" = "save"
        "verificarParticipacion" = "findById"
    }
    "PublicacionRepository" = @{
        "buscarActivas" = "findByRetiradaOrderByFechaActualizacionDesc"
        "buscarActivasPorAutor" = "findByAutorAndRetiradaOrderByFechaActualizacionDesc"
        "guardar" = "save"
        "obtenerActivaPorId" = "findByIdAndRetirada"
        "retirar" = "save"
        "retirarPropia" = "save"
    }
    "RecompensaRepository" = @{
        "anular" = "save"
        "buscarDeProyectosCompletados" = "findAllByOrderByFechaCreacionDesc"
        "buscarPropiasDeProyectosCompletados" = "findByBeneficiarioOrderByFechaCreacionDesc"
        "existeDuplicado" = "existsByProyectoAndBeneficiarioAndTipo"
        "guardar" = "save"
        "obtenerPorId" = "findById"
        "obtenerPropiaPorId" = "findById"
    }
    "SolicitudEliminacionPerfilRepository" = @{
        "buscarPorCriterio" = "findByEstadoOrderByFechaCreacionDesc"
        "existePendiente" = "existsByUsuarioAndEstado"
        "guardar" = "save"
        "marcarResuelta" = "save"
        "obtenerPendientes" = "findByEstadoOrderByFechaCreacionDesc"
        "obtenerPorId" = "findById"
    }
    "ArchivoRepository" = @{
        "agregarVersionOpcional" = "save"
        "guardarOpcional" = "save"
        "obtenerPorEntregable" = "findByEntregableIdOrderByVersionDesc"
    }
    "InvestigadorRepository" = @{
        "obtenerAsociadoActivo" = "findById"
        "obtenerBeneficiario" = "findById"
        "obtenerCandidatoDisponible" = "findById"
        "obtenerElegible" = "findById"
        "obtenerElegiblesPorProyecto" = "findByRolAndActivoTrueOrderByNombreCompletoAsc"
        "obtenerEquipo" = "findByRolAndActivoTrueOrderByNombreCompletoAsc"
        "obtenerEquipoVisible" = "findByRolAndActivoTrueOrderByNombreCompletoAsc"
        "obtenerNoAsociados" = "findByRolAndActivoTrueOrderByNombreCompletoAsc"
        "obtenerResumenes" = "findByRolAndActivoTrueOrderByNombreCompletoAsc"
    }
    "PerfilRepository" = @{
        "desactivar" = "save"
        "guardar" = "save"
        "obtenerPorUsuario" = "findByNombreUsuario"
    }
    "PersonaRepository" = @{
        "obtenerInvestigadoresDocentesPorSede" = "findByRolAndActivoTrueOrderByNombreCompletoAsc"
        "obtenerPorId" = "findById"
    }
    "RespuestaRepository" = @{
        "buscarPorPublicacion" = "findByPublicacionIdOrderByFecha"
        "guardar" = "save"
    }
}

$conceptuales = @{
    "PerfilService" = @("validarCambios", "validarSolicitud")
    "PoliticaPublicacion" = @(
        "alcancePropio", "permitirConsulta", "permitirConsultaGlobal",
        "permitirCrear", "permitirGestionGlobal", "permitirResponder"
    )
    "ProyectoService" = @(
        "aplicarCambiosYValidarFechas", "asignarCodigoCoordinadorYEstadoCreado",
        "evaluarCompatibilidadYRecomendarMenorCarga", "prepararCreacion",
        "prepararDesasignacion", "reevaluarCompatibilidadYDisponibilidad",
        "validarDatosYFechas"
    )
    "EntregableService" = @("validarDatosYArchivo", "validarDatosYAsignarAutor")
    "PublicacionService" = @("validarYAsignarAutor")
    "ConfirmarCierreSesionModal" = @("abrir", "cancelar", "confirmar")
    "PanelPrincipalPage" = @("cerrar", "confirmarCierre")
    "Spring Security" = @("obtenerTokenCsrf")
}

$clasesReales = @{
    "ArchivoRepository" = "ArchivoEntregableRepository"
    "InvestigadorRepository" = "UsuarioRepository"
    "PerfilRepository" = "UsuarioRepository"
    "PersonaRepository" = "UsuarioRepository"
    "RespuestaRepository" = "RespuestaPublicacionRepository"
    "SolicitudEliminacionPerfilService" = "PerfilService"
}

$ajustesFirmas = @{
    "Auth -> Auth: autenticar(usuario, contrasena, activo)" =
        "note right of Auth: Responsabilidad interna: validar credenciales y estado activo"
    "Service -> ProyectoRepo: findById(proyectoId, investigador)" =
        "Service -> ProyectoRepo: findById(proyectoId)"
    "Service -> Repo: findById(id, investigadorId)" =
        "Service -> Repo: findById(id)"
    "Service -> Repo: findById(investigadorId, id)" =
        "Service -> Repo: findById(id)"
    "Service -> ProyectoRepo: findById(proyectoId, criterio)" =
        "Service -> ProyectoRepo: findById(proyectoId)"
    "Service -> InvestigadorRepo: findById(id, investigadorId)" =
        "Service -> InvestigadorRepo: findById(investigadorId)"
    "Service -> InvestigadorRepo: findById(proyectoId, investigadorId)" =
        "Service -> InvestigadorRepo: findById(investigadorId)"
    "Service -> InvestigadorRepo: findById(datos.proyectoId, datos.investigadorId)" =
        "Service -> InvestigadorRepo: findById(datos.investigadorId)"
    "Service -> InvestigadorRepo: findById(recompensa.proyectoId, datos.investigadorId)" =
        "Service -> InvestigadorRepo: findById(datos.investigadorId)"
    "Service -> InvestigadorRepo: findByRolAndActivoTrueOrderByNombreCompletoAsc(proyectoId)" =
        "Service -> InvestigadorRepo: findByRolAndActivoTrueOrderByNombreCompletoAsc(Rol.INVESTIGADOR)"
    "Service -> InvestigadorRepo: findByRolAndActivoTrueOrderByNombreCompletoAsc(recompensa.proyectoId)" =
        "Service -> InvestigadorRepo: findByRolAndActivoTrueOrderByNombreCompletoAsc(Rol.INVESTIGADOR)"
}

$modificados = 0
foreach ($diagrama in Get-ChildItem $diagramas -Recurse -Filter secuencia.puml) {
    $lineas = [IO.File]::ReadAllLines($diagrama.FullName, $utf8)
    $alias = @{}
    foreach ($linea in $lineas) {
        if ($linea -match '^\s*participant\s+"?(?<etiqueta>[^"\\]+)(?:\\n[^"]*)?"?\s+as\s+(?<alias>\w+)') {
            $alias[$matches.alias] = $matches.etiqueta.Trim()
        }
    }

    $cambio = $false
    $nuevas = foreach ($linea in $lineas) {
        if ($linea -match '^(?<prefijo>\s*participant\s+")(?<clase>[^"\\]+)(?<resto>(?:\\n[^"]*)?"\s+as\s+\w+.*)$') {
            $claseParticipante = $matches.clase.Trim()
            if ($clasesReales.ContainsKey($claseParticipante)) {
                $cambio = $true
                "$($matches.prefijo)$($clasesReales[$claseParticipante])$($matches.resto)"
                continue
            }
        }
        if ($ajustesFirmas.ContainsKey($linea.Trim())) {
            $cambio = $true
            "$($linea.Substring(0, $linea.Length - $linea.TrimStart().Length))$($ajustesFirmas[$linea.Trim()])"
            continue
        }
        if ($linea -match '^(?<prefijo>\s*\w+\s*(?:-+>|\.{2}>|-->)\s*(?<destino>\w+)\s*:\s*)(?<metodo>\w+)(?<resto>\s*\(.*)$') {
            $destino = $matches.destino
            $metodo = $matches.metodo
            if ($alias.ContainsKey($destino)) {
                $clase = $alias[$destino]
                if ($mapa.ContainsKey($clase) -and $mapa[$clase].ContainsKey($metodo)) {
                    $cambio = $true
                    "$($matches.prefijo)$($mapa[$clase][$metodo])$($matches.resto)"
                    continue
                }
                if ($conceptuales.ContainsKey($clase) -and $metodo -in $conceptuales[$clase]) {
                    $cambio = $true
                    "note right of ${destino}: Responsabilidad interna: $metodo"
                    continue
                }
                if ($metodo.EndsWith("Response")) {
                    $cambio = $true
                    "$($matches.prefijo)$metodo"
                    continue
                }
            }
        }
        $linea
    }

    if ($cambio) {
        [IO.File]::WriteAllLines($diagrama.FullName, $nuevas, $utf8)
        $modificados++
    }
}

Write-Host "Diagramas alineados: $modificados"
