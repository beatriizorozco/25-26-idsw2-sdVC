import { ArrowLeft, Check, Search, UserRound, UsersRound } from 'lucide-react'
import { useCallback, useEffect, useState } from 'react'
import type { FormEvent } from 'react'
import { crearInvestigador, listarInvestigadores, obtenerInvestigador } from '../services/api'
import type {
  InvestigadorCreateRequest,
  InvestigadorDetalle,
  InvestigadorResumen,
  Rol,
} from '../types'

interface Props {
  rol: Rol
  onVolver: () => void
}

const formularioVacio: InvestigadorCreateRequest = {
  usuario: '',
  nombreCompleto: '',
  email: '',
  sede: 'BARCELONA',
  unidad: '',
  lineaInvestigacion: '',
  biografia: '',
}

export function InvestigadoresPage({ rol, onVolver }: Props) {
  const [investigadores, setInvestigadores] = useState<InvestigadorResumen[]>([])
  const [seleccionado, setSeleccionado] = useState<InvestigadorDetalle | null>(null)
  const [criterio, setCriterio] = useState('')
  const [modo, setModo] = useState<'lista' | 'crear'>('lista')
  const [formulario, setFormulario] = useState<InvestigadorCreateRequest>(formularioVacio)
  const [error, setError] = useState('')
  const [mensaje, setMensaje] = useState('')
  const [cargando, setCargando] = useState(true)
  const [guardando, setGuardando] = useState(false)

  const cargarLista = useCallback(async (filtro = '') => {
    setCargando(true)
    try {
      const datos = await listarInvestigadores(filtro)
      setInvestigadores(datos)
      setSeleccionado((actual) => actual ? null : actual)
      setError('')
    } catch (listaError) {
      setError(mensajeError(listaError, 'No se pudieron cargar los investigadores.'))
    } finally {
      setCargando(false)
    }
  }, [])

  useEffect(() => {
    void cargarLista()
  }, [cargarLista])

  async function abrirDetalle(id: number) {
    try {
      setSeleccionado(await obtenerInvestigador(id))
      setModo('lista')
      setError('')
    } catch (detalleError) {
      setError(mensajeError(detalleError, 'No se pudo abrir el investigador.'))
    }
  }

  function prepararAlta() {
    setFormulario(formularioVacio)
    setModo('crear')
    setMensaje('')
    setError('')
  }

  async function guardar(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setGuardando(true)
    try {
      const creado = await crearInvestigador(formulario)
      setSeleccionado(creado)
      setModo('lista')
      await cargarLista(criterio)
      setMensaje(`Investigador creado correctamente. Contraseña temporal: ${formulario.usuario}123`)
      setError('')
    } catch (crearError) {
      setError(mensajeError(crearError, 'No se pudo crear el investigador.'))
    } finally {
      setGuardando(false)
    }
  }

  return (
    <main className="app-shell">
      <section className="workspace investigators-workspace">
        <button className="link-button" type="button" onClick={onVolver}>
          <ArrowLeft size={17} /> Volver al panel
        </button>

        <div className="workspace-heading">
          <p className="eyebrow">Investigadores</p>
          <h1>{rol === 'COORDINADOR' ? 'Gestionar investigadores' : 'Directorio de investigadores'}</h1>
          <p>
            {rol === 'COORDINADOR'
              ? 'Consulta el directorio activo y registra nuevos perfiles investigadores.'
              : 'Consulta perfiles activos y revisa su información investigadora visible.'}
          </p>
        </div>

        {error && <p className="form-error">{error}</p>}
        {mensaje && <p className="form-success">{mensaje}</p>}

        <section className="detail-layout investigators-layout">
          <article className="detail-panel">
            <div className="section-title investigators-title">
              <span className="action-icon"><UsersRound size={20} /></span>
              <h2>Directorio</h2>
              {rol === 'COORDINADOR' && (
                <button className="primary-button compact-button" type="button" onClick={prepararAlta}>
                  Nuevo
                </button>
              )}
            </div>

            <form className="search-row" onSubmit={(event) => { event.preventDefault(); void cargarLista(criterio) }}>
              <Search size={18} />
              <input
                value={criterio}
                onChange={(event) => setCriterio(event.target.value)}
                placeholder="Filtrar investigadores"
              />
              <button className="secondary-button" type="submit">Filtrar</button>
            </form>

            <div className="investigator-list">
              {cargando && <p className="empty-state">Cargando investigadores...</p>}
              {!cargando && investigadores.length === 0 && <p className="empty-state">No hay investigadores disponibles.</p>}
              {investigadores.map((investigador) => (
                <button
                  key={investigador.id}
                  className={`investigator-row ${seleccionado?.id === investigador.id ? 'selected' : ''}`}
                  type="button"
                  onClick={() => void abrirDetalle(investigador.id)}
                >
                  <span>
                    <strong>{investigador.nombreCompleto}</strong>
                    <small>{investigador.sede} · {investigador.lineaInvestigacion || 'Sin línea indicada'}</small>
                  </span>
                  <b>{investigador.investigadorDocente ? `${investigador.cargaSemanal} h` : investigador.sede}</b>
                </button>
              ))}
            </div>
          </article>

          <article className="detail-panel investigator-detail-panel">
            {modo === 'crear' && rol === 'COORDINADOR' && (
              <InvestigadorForm
                formulario={formulario}
                guardando={guardando}
                onChange={setFormulario}
                onCancelar={() => setModo('lista')}
                onGuardar={guardar}
              />
            )}

            {modo === 'lista' && !seleccionado && (
              <>
                <div className="section-title">
                  <span className="action-icon"><UserRound size={20} /></span>
                  <h2>Detalle</h2>
                </div>
                <p className="empty-state">Selecciona un investigador para consultar su perfil.</p>
              </>
            )}

            {modo === 'lista' && seleccionado && (
              <InvestigadorDetallePanel detalle={seleccionado} />
            )}
          </article>
        </section>
      </section>
    </main>
  )
}

function InvestigadorDetallePanel({ detalle }: { detalle: InvestigadorDetalle }) {
  return (
    <>
      <div className="section-title">
        <span className="action-icon"><UserRound size={20} /></span>
        <h2>Perfil investigador</h2>
      </div>
      <dl className="data-list">
        <dt>Usuario</dt><dd>{detalle.usuario}</dd>
        <dt>Correo</dt><dd>{detalle.email || 'Sin correo indicado'}</dd>
        <dt>Sede</dt><dd>{detalle.sede}</dd>
        <dt>Unidad</dt><dd>{detalle.unidad || 'Sin unidad indicada'}</dd>
        <dt>Línea de investigación</dt><dd>{detalle.lineaInvestigacion || 'Sin línea indicada'}</dd>
        <dt>Perfil docente</dt><dd>{detalle.investigadorDocente ? 'Investigador-docente' : 'Investigador sin docencia por sede'}</dd>
        <dt>Biografía</dt><dd>{detalle.biografia || 'Sin biografía indicada'}</dd>
      </dl>

      <h3 className="subsection-title">Proyectos asociados visibles</h3>
      <div className="project-list">
        {detalle.proyectos.length === 0 && <p className="empty-state">No hay proyectos visibles asociados.</p>}
        {detalle.proyectos.map((proyecto) => (
          <div className="project-row static-row" key={proyecto.id}>
            <span>
              <strong>{proyecto.nombre}</strong>
              <small>{proyecto.codigo} · {proyecto.sede || 'Sin sede'}</small>
            </span>
            <b>{proyecto.estado === 'COMPLETADO' ? 'Completado' : 'En curso'}</b>
          </div>
        ))}
      </div>
    </>
  )
}

function InvestigadorForm(props: {
  formulario: InvestigadorCreateRequest
  guardando: boolean
  onChange: (formulario: InvestigadorCreateRequest) => void
  onCancelar: () => void
  onGuardar: (event: FormEvent<HTMLFormElement>) => void
}) {
  const f = props.formulario
  const actualizar = (cambio: Partial<InvestigadorCreateRequest>) => props.onChange({ ...f, ...cambio })

  return (
    <form className="project-form" onSubmit={props.onGuardar}>
      <div className="section-title">
        <span className="action-icon"><UserRound size={20} /></span>
        <h2>Nuevo investigador</h2>
      </div>
      <label>Usuario<input value={f.usuario} maxLength={80} required onChange={(event) => actualizar({ usuario: event.target.value })} /></label>
      <label>Nombre completo<input value={f.nombreCompleto} maxLength={120} required onChange={(event) => actualizar({ nombreCompleto: event.target.value })} /></label>
      <label>Correo<input type="email" value={f.email} maxLength={120} required onChange={(event) => actualizar({ email: event.target.value })} /></label>
      <div className="form-grid">
        <label>
          Sede
          <select value={f.sede} onChange={(event) => actualizar({ sede: event.target.value })}>
            {['SANTANDER', 'BARCELONA', 'LIMA', 'BOGOTA', 'MEXICO'].map((sede) => <option key={sede}>{sede}</option>)}
          </select>
        </label>
        <label>Unidad<input value={f.unidad} maxLength={120} onChange={(event) => actualizar({ unidad: event.target.value })} /></label>
      </div>
      <label>Línea de investigación<input value={f.lineaInvestigacion} maxLength={160} onChange={(event) => actualizar({ lineaInvestigacion: event.target.value })} /></label>
      <label>Biografía<textarea value={f.biografia} maxLength={500} onChange={(event) => actualizar({ biografia: event.target.value })} /></label>
      <p className="field-hint">La contraseña temporal se generará como <strong>{f.usuario || 'usuario'}123</strong>.</p>
      <div className="form-actions">
        <button className="primary-button" type="submit" disabled={props.guardando}>
          <Check size={17} /> Guardar
        </button>
        <button className="secondary-button" type="button" onClick={props.onCancelar} disabled={props.guardando}>
          Cancelar
        </button>
      </div>
    </form>
  )
}

function mensajeError(error: unknown, respaldo: string) {
  return error instanceof Error ? error.message : respaldo
}
