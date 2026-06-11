import {
  Archive,
  ArrowLeft,
  Check,
  FolderKanban,
  Download,
  FileText,
  Pencil,
  Plus,
  Search,
  UserMinus,
  UserPlus,
  X,
  Trash2,
  Upload,
} from 'lucide-react'
import { useCallback, useEffect, useState } from 'react'
import type { FormEvent } from 'react'
import {
  actualizarProyecto,
  agregarInvestigadorProyecto,
  archivarProyecto,
  crearProyecto,
  listarCandidatosProyecto,
  listarProyectos,
  obtenerProyecto,
  retirarInvestigadorProyecto,
  descargarArchivoProyecto,
  eliminarArchivoProyecto,
  listarArchivosProyecto,
  subirArchivoProyecto,
} from '../services/api'
import type { ArchivoProyecto, InvestigadorProyecto, Proyecto, ProyectoRequest, Rol } from '../types'
import { EntregablesProyecto } from '../components/EntregablesProyecto'

interface Props {
  rol: Rol
  onVolver: () => void
}

const formularioVacio: ProyectoRequest = {
  codigo: '',
  nombre: '',
  descripcion: '',
  area: '',
  sede: 'GLOBAL',
  estado: 'EN_CURSO',
  fechaInicio: '',
  fechaFin: '',
}

export function ProyectosPage({ rol, onVolver }: Props) {
  const [proyectos, setProyectos] = useState<Proyecto[]>([])
  const [seleccionado, setSeleccionado] = useState<Proyecto | null>(null)
  const [formulario, setFormulario] = useState<ProyectoRequest>(formularioVacio)
  const [modo, setModo] = useState<'lista' | 'crear' | 'editar'>('lista')
  const [criterio, setCriterio] = useState('')
  const [archivados, setArchivados] = useState(false)
  const [candidatos, setCandidatos] = useState<InvestigadorProyecto[]>([])
  const [candidatoId, setCandidatoId] = useState(0)
  const [confirmacion, setConfirmacion] = useState<'archivar' | 'retirar' | null>(null)
  const [investigadorRetirar, setInvestigadorRetirar] = useState<InvestigadorProyecto | null>(null)
  const [motivo, setMotivo] = useState('')
  const [error, setError] = useState('')
  const [mensaje, setMensaje] = useState('')
  const [cargando, setCargando] = useState(true)
  const [guardando, setGuardando] = useState(false)

  const cargarLista = useCallback(async (filtro: string, historico: boolean) => {
    setCargando(true)
    try {
      const lista = await listarProyectos(filtro, rol === 'COORDINADOR' && historico)
      setProyectos(lista)
      setSeleccionado((actual) => lista.find((proyecto) => proyecto.id === actual?.id) ?? null)
      setError('')
    } catch (cargaError) {
      setError(mensajeError(cargaError, 'No se pudieron cargar los proyectos.'))
    } finally {
      setCargando(false)
    }
  }, [rol])

  useEffect(() => {
    void cargarLista('', false)
  }, [cargarLista])

  async function abrirDetalle(id: number) {
    try {
      setSeleccionado(await obtenerProyecto(id))
      setModo('lista')
      setError('')
    } catch (cargaError) {
      setError(mensajeError(cargaError, 'No se pudo abrir el proyecto.'))
    }
  }

  function abrirCreacion() {
    setFormulario(formularioVacio)
    setModo('crear')
    setMensaje('')
    setError('')
  }

  function abrirEdicion() {
    if (!seleccionado) return
    setFormulario({
      codigo: seleccionado.codigo,
      nombre: seleccionado.nombre,
      descripcion: seleccionado.descripcion ?? '',
      area: seleccionado.area ?? '',
      sede: normalizarSede(seleccionado.sede),
      estado: seleccionado.estado,
      fechaInicio: seleccionado.fechaInicio ?? '',
      fechaFin: seleccionado.fechaFin ?? '',
    })
    setModo('editar')
    setError('')
  }

  async function guardar(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setGuardando(true)
    try {
      const guardado = modo === 'crear'
        ? await crearProyecto(formulario)
        : await actualizarProyecto(seleccionado!.id, formulario)
      setMensaje(guardado.archivado
        ? 'Proyecto completado y archivado correctamente.'
        : modo === 'crear' ? 'Proyecto creado correctamente.' : 'Proyecto actualizado correctamente.')
      setModo('lista')
      await cargarLista(criterio, archivados)
      setSeleccionado(guardado)
      setError('')
    } catch (guardarError) {
      setError(mensajeError(guardarError, 'No se pudo guardar el proyecto.'))
    } finally {
      setGuardando(false)
    }
  }

  async function prepararAsignacion() {
    if (!seleccionado) return
    try {
      const disponibles = await listarCandidatosProyecto(seleccionado.id)
      setCandidatos(disponibles)
      setCandidatoId(disponibles[0]?.id ?? 0)
      setError('')
    } catch (cargaError) {
      setError(mensajeError(cargaError, 'No se pudieron cargar los investigadores disponibles.'))
    }
  }

  async function asignar() {
    if (!seleccionado || !candidatoId) return
    setGuardando(true)
    try {
      const actualizado = await agregarInvestigadorProyecto(seleccionado.id, candidatoId)
      setSeleccionado(actualizado)
      setCandidatos([])
      setCandidatoId(0)
      setMensaje('Investigador agregado correctamente.')
      await cargarLista(criterio, archivados)
    } catch (asignacionError) {
      setError(mensajeError(asignacionError, 'No se pudo agregar el investigador.'))
    } finally {
      setGuardando(false)
    }
  }

  async function confirmarAccion() {
    if (!seleccionado || !motivo.trim()) return
    setGuardando(true)
    try {
      if (confirmacion === 'archivar') {
        await archivarProyecto(seleccionado.id, motivo)
        setSeleccionado(null)
        setMensaje('Proyecto archivado correctamente.')
      }
      if (confirmacion === 'retirar' && investigadorRetirar) {
        const actualizado = await retirarInvestigadorProyecto(seleccionado.id, investigadorRetirar.id, motivo)
        setSeleccionado(actualizado)
        setMensaje('Investigador desasignado; su participación queda en el histórico.')
      }
      setConfirmacion(null)
      setInvestigadorRetirar(null)
      setMotivo('')
      await cargarLista(criterio, archivados)
      setError('')
    } catch (accionError) {
      setError(mensajeError(accionError, 'No se pudo completar la operación.'))
    } finally {
      setGuardando(false)
    }
  }

  function cambiarHistorico(valor: boolean) {
    setArchivados(valor)
    setSeleccionado(null)
    void cargarLista(criterio, valor)
  }

  return (
    <main className="app-shell">
      <section className="workspace projects-workspace">
        <button className="link-button" type="button" onClick={onVolver}>
          <ArrowLeft size={17} /> Volver al panel
        </button>
        <div className="workspace-heading">
          <p className="eyebrow">Proyectos</p>
          <h1>{rol === 'COORDINADOR' ? 'Gestionar proyectos' : 'Mis proyectos'}</h1>
          <p>{rol === 'COORDINADOR' ? 'Coordina proyectos y conserva su histórico.' : 'Consulta los proyectos en los que participas.'}</p>
        </div>

        {error && <p className="form-error">{error}</p>}
        {mensaje && <p className="form-success">{mensaje}</p>}

        <section className="detail-layout projects-layout">
          <article className="detail-panel">
            <div className="section-title projects-title">
              <span className="action-icon"><FolderKanban size={20} /></span>
              <h2>{archivados ? 'Histórico de proyectos' : 'Proyectos activos'}</h2>
              {rol === 'COORDINADOR' && !archivados && (
                <button className="primary-button compact-button" type="button" onClick={abrirCreacion}>
                  <Plus size={17} /> Nuevo
                </button>
              )}
            </div>

            {rol === 'COORDINADOR' && (
              <div className="segmented-control" aria-label="Ámbito de proyectos">
                <button className={!archivados ? 'selected' : ''} type="button" onClick={() => cambiarHistorico(false)}>Activos</button>
                <button className={archivados ? 'selected' : ''} type="button" onClick={() => cambiarHistorico(true)}>Archivados</button>
              </div>
            )}

            <form className="search-row" onSubmit={(event) => { event.preventDefault(); void cargarLista(criterio, archivados) }}>
              <Search size={18} />
              <input value={criterio} onChange={(event) => setCriterio(event.target.value)} placeholder="Filtrar proyectos" />
              <button className="secondary-button" type="submit">Filtrar</button>
            </form>

            <div className="project-list">
              {cargando && <p className="empty-state">Cargando proyectos...</p>}
              {!cargando && proyectos.length === 0 && <p className="empty-state">No hay proyectos disponibles.</p>}
              {proyectos.map((proyecto) => (
                <button
                  className={`project-row ${seleccionado?.id === proyecto.id ? 'selected' : ''}`}
                  key={proyecto.id}
                  type="button"
                  onClick={() => void abrirDetalle(proyecto.id)}
                >
                  <span><strong>{proyecto.nombre}</strong><small>{proyecto.codigo} · {proyecto.area || 'Sin área'}</small></span>
                  <b>{etiquetaEstado(proyecto.estado)}</b>
                </button>
              ))}
            </div>
          </article>

          <article className="detail-panel project-detail">
            {modo !== 'lista' && (
              <ProyectoForm
                formulario={formulario}
                modo={modo}
                guardando={guardando}
                onChange={setFormulario}
                onCancelar={() => setModo('lista')}
                onGuardar={guardar}
              />
            )}
            {modo === 'lista' && !seleccionado && (
              <>
                <div className="section-title"><span className="action-icon"><FolderKanban size={20} /></span><h2>Detalle</h2></div>
                <p className="empty-state">Selecciona un proyecto para consultar su detalle.</p>
              </>
            )}
            {modo === 'lista' && seleccionado && (
              <ProyectoDetalle
                proyecto={seleccionado}
                rol={rol}
                candidatos={candidatos}
                candidatoId={candidatoId}
                guardando={guardando}
                onEditar={abrirEdicion}
                onPrepararAsignacion={() => void prepararAsignacion()}
                onCandidato={setCandidatoId}
                onAsignar={() => void asignar()}
                onCancelarAsignacion={() => setCandidatos([])}
                onArchivar={() => setConfirmacion('archivar')}
                onRetirar={(investigador) => { setInvestigadorRetirar(investigador); setConfirmacion('retirar') }}
              />
            )}
          </article>
        </section>
      </section>

      {confirmacion && (
        <div className="modal-backdrop">
          <section className="modal">
            <span className="modal-icon">{confirmacion === 'archivar' ? <Archive /> : <UserMinus />}</span>
            <button className="icon-button modal-close" type="button" onClick={() => setConfirmacion(null)}><X size={18} /></button>
            <h2>{confirmacion === 'archivar' ? 'Archivar proyecto' : 'Desasignar investigador'}</h2>
            <p>La información no se eliminará: quedará conservada en el histórico.</p>
            <label>Motivo<textarea value={motivo} onChange={(event) => setMotivo(event.target.value)} required /></label>
            <div className="modal-actions">
              <button className="secondary-button" type="button" onClick={() => setConfirmacion(null)}>Cancelar</button>
              <button className="danger-button" type="button" disabled={!motivo.trim() || guardando} onClick={() => void confirmarAccion()}>
                <Archive size={17} /> Confirmar
              </button>
            </div>
          </section>
        </div>
      )}
    </main>
  )
}

function ProyectoDetalle(props: {
  proyecto: Proyecto
  rol: Rol
  candidatos: InvestigadorProyecto[]
  candidatoId: number
  guardando: boolean
  onEditar: () => void
  onPrepararAsignacion: () => void
  onCandidato: (id: number) => void
  onAsignar: () => void
  onCancelarAsignacion: () => void
  onArchivar: () => void
  onRetirar: (investigador: InvestigadorProyecto) => void
}) {
  const { proyecto } = props
  const [archivos, setArchivos] = useState<ArchivoProyecto[]>([])
  const [archivoSubida, setArchivoSubida] = useState<File | null>(null)
  const [procesandoArchivo, setProcesandoArchivo] = useState(false)
  const [errorArchivo, setErrorArchivo] = useState('')

  const cargarArchivos = useCallback(async () => {
    try {
      setArchivos(await listarArchivosProyecto(proyecto.id))
      setErrorArchivo('')
    } catch (error) {
      setErrorArchivo(mensajeError(error, 'No se pudieron cargar los archivos.'))
    }
  }, [proyecto.id])

  useEffect(() => {
    void cargarArchivos()
  }, [cargarArchivos])

  async function subirArchivo() {
    if (!archivoSubida) return
    setProcesandoArchivo(true)
    try {
      await subirArchivoProyecto(proyecto.id, archivoSubida)
      setArchivoSubida(null)
      await cargarArchivos()
    } catch (error) {
      setErrorArchivo(mensajeError(error, 'No se pudo subir el archivo.'))
    } finally {
      setProcesandoArchivo(false)
    }
  }

  async function eliminarArchivo(archivo: ArchivoProyecto) {
    if (!window.confirm(`¿Eliminar el archivo "${archivo.nombre}"?`)) return
    setProcesandoArchivo(true)
    try {
      await eliminarArchivoProyecto(proyecto.id, archivo.id)
      await cargarArchivos()
    } catch (error) {
      setErrorArchivo(mensajeError(error, 'No se pudo eliminar el archivo.'))
    } finally {
      setProcesandoArchivo(false)
    }
  }

  return (
    <>
      <div className="section-title"><span className="action-icon"><FolderKanban size={20} /></span><h2>Detalle</h2></div>
      <dl className="data-list">
        <dt>Código</dt><dd>{proyecto.codigo}</dd>
        <dt>Estado</dt><dd>{etiquetaEstado(proyecto.estado)}</dd>
        <dt>Sede</dt><dd>{proyecto.sede || 'Sin sede'}</dd>
        <dt>Área</dt><dd>{proyecto.area || 'Sin área'}</dd>
        <dt>Descripción</dt><dd>{proyecto.descripcion || 'Sin descripción'}</dd>
        {proyecto.archivado && <><dt>Motivo de archivo</dt><dd>{proyecto.motivoArchivado}</dd></>}
      </dl>
      <h3 className="subsection-title">Investigadores activos</h3>
      <div className="participant-list">
        {proyecto.investigadores.length === 0 && <p className="empty-state">Sin investigadores asignados.</p>}
        {proyecto.investigadores.map((investigador) => (
          <div className="participant-row" key={investigador.id}>
            <span><strong>{investigador.nombreCompleto}</strong><small>{investigador.sede}</small></span>
            {props.rol === 'COORDINADOR' && !proyecto.archivado && (
              <button className="icon-button danger-icon" title="Desasignar investigador" type="button" onClick={() => props.onRetirar(investigador)}>
                <UserMinus size={17} />
              </button>
            )}
          </div>
        ))}
      </div>
      <section className="project-resource-section">
        <div className="section-title resource-title">
          <span className="action-icon"><FileText size={20} /></span>
          <div>
            <h2>Documentación del proyecto</h2>
            <p>Material de consulta y archivos de apoyo compartidos.</p>
          </div>
        </div>
        {errorArchivo && <p className="form-error">{errorArchivo}</p>}
        {!proyecto.archivado && (
          <div className="file-upload-row">
            <input
              type="file"
              aria-label="Seleccionar documento del proyecto"
              onChange={(event) => setArchivoSubida(event.target.files?.[0] ?? null)}
            />
            <button className="secondary-button" type="button" disabled={!archivoSubida || procesandoArchivo} onClick={() => void subirArchivo()}>
              <Upload size={17} /> Subir
            </button>
          </div>
        )}
        <div className="project-files">
          {archivos.length === 0 && <p className="empty-state">No hay documentación registrada.</p>}
          {archivos.map((archivo) => (
            <div className="project-file-row" key={archivo.id}>
              <FileText size={18} />
              <span>
                <strong>{archivo.nombre}</strong>
                <small>{formatearTamano(archivo.tamano)} · {archivo.subidoPor}</small>
              </span>
              <button className="icon-button" title="Descargar documento" type="button" onClick={() => void descargarArchivoProyecto(proyecto.id, archivo)}>
                <Download size={17} />
              </button>
              {props.rol === 'COORDINADOR' && !proyecto.archivado && (
                <button className="icon-button danger-icon" title="Eliminar documento" type="button" disabled={procesandoArchivo} onClick={() => void eliminarArchivo(archivo)}>
                  <Trash2 size={17} />
                </button>
              )}
            </div>
          ))}
        </div>
      </section>
      <EntregablesProyecto proyectoId={proyecto.id} proyectoArchivado={proyecto.archivado} rol={props.rol} />
      {props.rol === 'COORDINADOR' && !proyecto.archivado && (
        <>
          {props.candidatos.length > 0 && (
            <div className="assignment-row">
              <select value={props.candidatoId} onChange={(event) => props.onCandidato(Number(event.target.value))}>
                {props.candidatos.map((candidato) => <option key={candidato.id} value={candidato.id}>{candidato.nombreCompleto} · {candidato.sede} · {candidato.cargaSemanal ?? 0} h</option>)}
              </select>
              <button className="primary-button compact-button" type="button" disabled={props.guardando} onClick={props.onAsignar}><Check size={17} /> Agregar</button>
              <button className="icon-button" type="button" onClick={props.onCancelarAsignacion}><X size={17} /></button>
            </div>
          )}
          <div className="form-actions project-main-actions">
            <button className="primary-button" type="button" onClick={props.onEditar}><Pencil size={17} /> Editar</button>
            <button className="secondary-button" type="button" onClick={props.onPrepararAsignacion}><UserPlus size={17} /> Agregar investigador</button>
            <button className="danger-button" type="button" onClick={props.onArchivar}><Archive size={17} /> Archivar</button>
          </div>
        </>
      )}
    </>
  )
}

function ProyectoForm(props: {
  formulario: ProyectoRequest
  modo: 'crear' | 'editar'
  guardando: boolean
  onChange: (formulario: ProyectoRequest) => void
  onCancelar: () => void
  onGuardar: (event: FormEvent<HTMLFormElement>) => void
}) {
  const f = props.formulario
  const actualizar = (cambio: Partial<ProyectoRequest>) => props.onChange({ ...f, ...cambio })
  return (
    <form className="project-form" onSubmit={props.onGuardar}>
      <div className="section-title"><span className="action-icon"><FolderKanban size={20} /></span><h2>{props.modo === 'crear' ? 'Nuevo proyecto' : 'Editar proyecto'}</h2></div>
      <label>Código<input value={f.codigo} maxLength={40} required onChange={(e) => actualizar({ codigo: e.target.value })} /></label>
      <label>Nombre<input value={f.nombre} maxLength={180} required onChange={(e) => actualizar({ nombre: e.target.value })} /></label>
      <label>Descripción<textarea value={f.descripcion} maxLength={1000} onChange={(e) => actualizar({ descripcion: e.target.value })} /></label>
      <label>Área<input value={f.area} maxLength={120} onChange={(e) => actualizar({ area: e.target.value })} /></label>
      <div className="form-grid">
        <label>Sede<select value={f.sede} onChange={(e) => actualizar({ sede: e.target.value })}>{['GLOBAL', 'SANTANDER', 'BARCELONA', 'LIMA', 'BOGOTA', 'MEXICO'].map((sede) => <option key={sede}>{sede}</option>)}</select></label>
        <label>Estado<select value={f.estado} onChange={(e) => actualizar({ estado: e.target.value as ProyectoRequest['estado'] })}><option value="EN_CURSO">En curso</option><option value="COMPLETADO">Completado y archivar</option></select></label>
        <label>Fecha de inicio<input type="date" value={f.fechaInicio} onChange={(e) => actualizar({ fechaInicio: e.target.value })} /></label>
        <label>Fecha de fin<input type="date" value={f.fechaFin} onChange={(e) => actualizar({ fechaFin: e.target.value })} /></label>
      </div>
      <div className="form-actions">
        <button className="primary-button" disabled={props.guardando} type="submit"><Check size={17} /> Guardar</button>
        <button className="secondary-button" type="button" onClick={props.onCancelar}>Cancelar</button>
      </div>
    </form>
  )
}

function etiquetaEstado(estado: Proyecto['estado']) {
  return estado === 'COMPLETADO' ? 'Completado' : 'En curso'
}

function normalizarSede(sede: string) {
  return sede.normalize('NFD').replace(/[\u0300-\u036f]/g, '').toUpperCase()
}

function mensajeError(error: unknown, respaldo: string) {
  return error instanceof Error ? error.message : respaldo
}

function formatearTamano(bytes: number) {
  if (bytes < 1024) return `${bytes} B`
  if (bytes < 1024 * 1024) return `${Math.round(bytes / 1024)} KB`
  return `${(bytes / (1024 * 1024)).toFixed(1)} MB`
}
