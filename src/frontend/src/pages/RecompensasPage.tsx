import { ArrowLeft, Award, Check, Pencil, Plus, Search, Trash2, X } from 'lucide-react'
import { useCallback, useEffect, useState } from 'react'
import type { FormEvent } from 'react'
import {
  actualizarRecompensa,
  crearRecompensa,
  eliminarRecompensa,
  listarRecompensasGlobales,
  listarRecompensasPropias,
  obtenerBeneficiariosRecompensa,
  obtenerOpcionesCreacionRecompensa,
  obtenerRecompensaGlobal,
  obtenerRecompensaPropia,
  obtenerTiposRecompensa,
  prepararEdicionRecompensa,
} from '../services/api'
import type {
  BeneficiarioRecompensa,
  ProyectoRecompensa,
  Recompensa,
  RecompensaRequest,
  Rol,
  TipoRecompensa,
} from '../types'

interface Props {
  rol: Rol
  onVolver: () => void
}

const formularioVacio: RecompensaRequest = {
  beneficiarioId: 0,
  tipo: 'ECONOMICA',
  concepto: '',
  valor: 0,
}

export function RecompensasPage({ rol, onVolver }: Props) {
  const [recompensas, setRecompensas] = useState<Recompensa[]>([])
  const [seleccionada, setSeleccionada] = useState<Recompensa | null>(null)
  const [proyectos, setProyectos] = useState<ProyectoRecompensa[]>([])
  const [beneficiarios, setBeneficiarios] = useState<BeneficiarioRecompensa[]>([])
  const [tiposPermitidos, setTiposPermitidos] = useState<TipoRecompensa[]>(['ECONOMICA'])
  const [formulario, setFormulario] = useState<RecompensaRequest>(formularioVacio)
  const [modo, setModo] = useState<'lista' | 'crear' | 'editar'>('lista')
  const [criterio, setCriterio] = useState('')
  const [error, setError] = useState('')
  const [mensaje, setMensaje] = useState('')
  const [cargando, setCargando] = useState(true)
  const [guardando, setGuardando] = useState(false)
  const [confirmandoEliminar, setConfirmandoEliminar] = useState(false)

  const cargarLista = useCallback(async (filtro = '') => {
    setCargando(true)
    try {
      const lista = rol === 'COORDINADOR'
        ? await listarRecompensasGlobales(filtro)
        : await listarRecompensasPropias(filtro)
      setRecompensas(lista)
      setSeleccionada((actual) => lista.find((recompensa) => recompensa.id === actual?.id) ?? null)
      setError('')
    } catch (cargaError) {
      setError(mensajeError(cargaError, 'No se pudieron cargar las recompensas.'))
    } finally {
      setCargando(false)
    }
  }, [rol])

  useEffect(() => {
    void cargarLista()
  }, [cargarLista])

  async function abrirCreacion() {
    setError('')
    setMensaje('')
    try {
      const opciones = await obtenerOpcionesCreacionRecompensa()
      setProyectos(opciones.proyectos)
      setBeneficiarios([])
      setTiposPermitidos(['ECONOMICA'])
      setFormulario(formularioVacio)
      setModo('crear')
    } catch (cargaError) {
      setError(mensajeError(cargaError, 'No se pudo preparar la recompensa.'))
    }
  }

  async function seleccionarProyecto(proyectoId: number) {
    setFormulario({ ...formularioVacio, proyectoId })
    setTiposPermitidos(['ECONOMICA'])
    try {
      setBeneficiarios(proyectoId ? await obtenerBeneficiariosRecompensa(proyectoId) : [])
      setError('')
    } catch (cargaError) {
      setBeneficiarios([])
      setError(mensajeError(cargaError, 'No se pudieron cargar los beneficiarios elegibles.'))
    }
  }

  async function seleccionarBeneficiario(beneficiarioId: number) {
    if (!formulario.proyectoId || !beneficiarioId) {
      setTiposPermitidos(['ECONOMICA'])
      setFormulario({ ...formulario, beneficiarioId, tipo: 'ECONOMICA' })
      return
    }
    if (modo === 'editar') {
      const tipos = beneficiarios.find((beneficiario) => beneficiario.id === beneficiarioId)?.tiposPermitidos ?? []
      setTiposPermitidos(tipos)
      setFormulario({ ...formulario, beneficiarioId, tipo: tipos[0] ?? 'ECONOMICA' })
      return
    }
    try {
      const tipos = await obtenerTiposRecompensa(formulario.proyectoId, beneficiarioId)
      setTiposPermitidos(tipos)
      setFormulario({ ...formulario, beneficiarioId, tipo: tipos[0] ?? 'ECONOMICA' })
      setError('')
    } catch (cargaError) {
      setTiposPermitidos(['ECONOMICA'])
      setError(mensajeError(cargaError, 'No se pudieron cargar los tipos de recompensa permitidos.'))
    }
  }

  async function abrirDetalle(id: number) {
    try {
      const recompensa = rol === 'COORDINADOR'
        ? await obtenerRecompensaGlobal(id)
        : await obtenerRecompensaPropia(id)
      setSeleccionada(recompensa)
      setModo('lista')
      setError('')
    } catch (cargaError) {
      setError(mensajeError(cargaError, 'No se pudo abrir la recompensa.'))
    }
  }

  async function abrirEdicion() {
    if (!seleccionada) return
    setError('')
    try {
      const edicion = await prepararEdicionRecompensa(seleccionada.id)
      setBeneficiarios(edicion.beneficiarios)
      setTiposPermitidos(
        edicion.beneficiarios.find((beneficiario) => beneficiario.id === edicion.recompensa.beneficiarioId)
          ?.tiposPermitidos ?? ['ECONOMICA'],
      )
      setFormulario({
        proyectoId: edicion.recompensa.proyectoId,
        beneficiarioId: edicion.recompensa.beneficiarioId,
        tipo: edicion.recompensa.tipo,
        concepto: edicion.recompensa.concepto,
        valor: Number(edicion.recompensa.valor),
      })
      setModo('editar')
    } catch (cargaError) {
      setError(mensajeError(cargaError, 'No se pudo preparar la edicion.'))
    }
  }

  async function guardar(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setGuardando(true)
    setError('')
    try {
      const guardada = modo === 'crear'
        ? await crearRecompensa(formulario)
        : await actualizarRecompensa(seleccionada!.id, formulario)
      setMensaje(modo === 'crear' ? 'Recompensa creada correctamente.' : 'Recompensa actualizada correctamente.')
      setModo('lista')
      await cargarLista(criterio)
      setSeleccionada(guardada)
    } catch (cargaError) {
      setError(mensajeError(cargaError, 'No se pudo guardar la recompensa.'))
    } finally {
      setGuardando(false)
    }
  }

  async function confirmarEliminacion() {
    if (!seleccionada) return
    setGuardando(true)
    try {
      await eliminarRecompensa(seleccionada.id)
      setSeleccionada(null)
      setConfirmandoEliminar(false)
      setMensaje('Recompensa eliminada correctamente.')
      await cargarLista(criterio)
    } catch (cargaError) {
      setError(mensajeError(cargaError, 'No se pudo eliminar la recompensa.'))
    } finally {
      setGuardando(false)
    }
  }

  return (
    <main className="app-shell">
      <section className="workspace rewards-workspace">
        <button className="link-button" type="button" onClick={onVolver}>
          <ArrowLeft size={17} />
          Volver al panel
        </button>
        <div className="workspace-heading">
          <p className="eyebrow">Recompensas</p>
          <h1>{rol === 'COORDINADOR' ? 'Gestionar recompensas' : 'Mis recompensas'}</h1>
          <p>
            {rol === 'COORDINADOR'
              ? 'Reconoce la participacion en proyectos completados.'
              : 'Consulta los reconocimientos obtenidos por proyectos completados.'}
          </p>
        </div>

        {error && <p className="form-error">{error}</p>}
        {mensaje && <p className="form-success">{mensaje}</p>}

        <section className="detail-layout rewards-layout">
          <article className="detail-panel">
            <div className="section-title rewards-title">
              <span className="action-icon"><Award size={20} /></span>
              <h2>{rol === 'COORDINADOR' ? 'Recompensas registradas' : 'Recompensas recibidas'}</h2>
              {rol === 'COORDINADOR' && (
                <button className="primary-button compact-button" type="button" onClick={() => void abrirCreacion()}>
                  <Plus size={17} />
                  Nueva
                </button>
              )}
            </div>
            <div className="search-row">
              <Search size={17} />
              <input value={criterio} placeholder="Filtrar recompensas" onChange={(event) => setCriterio(event.target.value)} />
              <button className="secondary-button" type="button" onClick={() => void cargarLista(criterio)}>Filtrar</button>
            </div>
            <div className="reward-list">
              {cargando && <p className="empty-state">Cargando recompensas...</p>}
              {!cargando && recompensas.length === 0 && <p className="empty-state">No hay recompensas disponibles.</p>}
              {recompensas.map((recompensa) => (
                <button
                  key={recompensa.id}
                  className={`reward-row ${seleccionada?.id === recompensa.id ? 'selected' : ''}`}
                  type="button"
                  onClick={() => void abrirDetalle(recompensa.id)}
                >
                  <span>
                    <strong>{recompensa.proyectoNombre}</strong>
                    <small>{recompensa.beneficiarioNombre} · {recompensa.tipoEtiqueta}</small>
                  </span>
                  <b>{formatearValor(recompensa)}</b>
                </button>
              ))}
            </div>
          </article>

          {modo === 'lista' && <Detalle recompensa={seleccionada} rol={rol} onEditar={() => void abrirEdicion()} onEliminar={() => setConfirmandoEliminar(true)} />}
          {(modo === 'crear' || modo === 'editar') && (
            <Formulario
              modo={modo}
              proyectos={proyectos}
              beneficiarios={beneficiarios}
              tiposPermitidos={tiposPermitidos}
              proyectoSeleccionado={seleccionada}
              formulario={formulario}
              guardando={guardando}
              onSeleccionarProyecto={(id) => void seleccionarProyecto(id)}
              onSeleccionarBeneficiario={(id) => void seleccionarBeneficiario(id)}
              onCambiar={setFormulario}
              onGuardar={guardar}
              onCancelar={() => setModo('lista')}
            />
          )}
        </section>
      </section>

      {confirmandoEliminar && seleccionada && (
        <div className="modal-backdrop" role="presentation">
          <section className="modal" role="dialog" aria-modal="true" aria-labelledby="eliminar-recompensa">
            <button className="icon-button modal-close" type="button" onClick={() => setConfirmandoEliminar(false)} aria-label="Cerrar">
              <X size={18} />
            </button>
            <span className="modal-icon"><Trash2 size={20} /></span>
            <h2 id="eliminar-recompensa">Eliminar recompensa</h2>
            <p>Se eliminara la recompensa de {seleccionada.beneficiarioNombre}. El proyecto y su carga de trabajo no cambiaran.</p>
            <div className="modal-actions">
              <button className="secondary-button" type="button" onClick={() => setConfirmandoEliminar(false)}>Cancelar</button>
              <button className="danger-button" type="button" disabled={guardando} onClick={() => void confirmarEliminacion()}>
                <Trash2 size={17} />
                Eliminar
              </button>
            </div>
          </section>
        </div>
      )}
    </main>
  )
}

function Detalle({ recompensa, rol, onEditar, onEliminar }: {
  recompensa: Recompensa | null
  rol: Rol
  onEditar: () => void
  onEliminar: () => void
}) {
  return (
    <aside className="detail-panel">
      <div className="section-title">
        <span className="action-icon"><Award size={20} /></span>
        <h2>Detalle</h2>
      </div>
      {!recompensa && <p className="empty-state">Selecciona una recompensa para consultar su detalle.</p>}
      {recompensa && (
        <>
          <dl className="data-list compact-list">
            <dt>Proyecto</dt><dd>{recompensa.proyectoCodigo} · {recompensa.proyectoNombre}</dd>
            <dt>Beneficiario</dt><dd>{recompensa.beneficiarioNombre}</dd>
            <dt>Tipo</dt><dd>{recompensa.tipoEtiqueta}</dd>
            <dt>Concepto</dt><dd>{recompensa.concepto}</dd>
            <dt>Valor</dt><dd>{formatearValor(recompensa)}</dd>
          </dl>
          {rol === 'COORDINADOR' && (
            <div className="button-row">
              <button className="primary-button" type="button" onClick={onEditar}><Pencil size={17} />Editar</button>
              <button className="danger-button" type="button" onClick={onEliminar}><Trash2 size={17} />Eliminar</button>
            </div>
          )}
        </>
      )}
    </aside>
  )
}

function Formulario({ modo, proyectos, beneficiarios, tiposPermitidos, proyectoSeleccionado, formulario, guardando, onSeleccionarProyecto, onSeleccionarBeneficiario, onCambiar, onGuardar, onCancelar }: {
  modo: 'crear' | 'editar'
  proyectos: ProyectoRecompensa[]
  beneficiarios: BeneficiarioRecompensa[]
  tiposPermitidos: TipoRecompensa[]
  proyectoSeleccionado: Recompensa | null
  formulario: RecompensaRequest
  guardando: boolean
  onSeleccionarProyecto: (id: number) => void
  onSeleccionarBeneficiario: (id: number) => void
  onCambiar: (datos: RecompensaRequest) => void
  onGuardar: (event: FormEvent<HTMLFormElement>) => void
  onCancelar: () => void
}) {
  return (
    <aside className="detail-panel">
      <div className="section-title">
        <span className="action-icon"><Award size={20} /></span>
        <h2>{modo === 'crear' ? 'Nueva recompensa' : 'Editar recompensa'}</h2>
      </div>
      {modo === 'crear' && proyectos.length === 0 && <p className="empty-state">No hay proyectos completados disponibles.</p>}
      <form className="profile-form" onSubmit={onGuardar}>
        <label>
          Proyecto completado
          {modo === 'crear' ? (
            <select value={formulario.proyectoId ?? 0} required onChange={(event) => onSeleccionarProyecto(Number(event.target.value))}>
              <option value={0}>Selecciona un proyecto</option>
              {proyectos.map((proyecto) => <option key={proyecto.id} value={proyecto.id}>{proyecto.codigo} · {proyecto.nombre}</option>)}
            </select>
          ) : (
            <input value={proyectoSeleccionado ? `${proyectoSeleccionado.proyectoCodigo} - ${proyectoSeleccionado.proyectoNombre}` : ''} disabled />
          )}
        </label>
        <label>
          Beneficiario elegible
          <select value={formulario.beneficiarioId} required onChange={(event) => onSeleccionarBeneficiario(Number(event.target.value))}>
            <option value={0}>Selecciona un beneficiario</option>
            {beneficiarios.map((beneficiario) => <option key={beneficiario.id} value={beneficiario.id}>{beneficiario.nombreCompleto} · {beneficiario.sede}</option>)}
          </select>
        </label>
        <label>
          Tipo
          <select value={formulario.tipo} onChange={(event) => onCambiar({ ...formulario, tipo: event.target.value as TipoRecompensa })}>
            {tiposPermitidos.map((tipo) => <option key={tipo} value={tipo}>{etiquetaTipo(tipo)}</option>)}
          </select>
        </label>
        <label>
          Concepto
          <textarea required maxLength={240} value={formulario.concepto} onChange={(event) => onCambiar({ ...formulario, concepto: event.target.value })} />
        </label>
        <label>
          {formulario.tipo === 'ECONOMICA' ? 'Importe economico' : 'Horas de reduccion docente'}
          <input type="number" required min="0.01" step="0.01" value={formulario.valor} onChange={(event) => onCambiar({ ...formulario, valor: Number(event.target.value) })} />
        </label>
        <div className="button-row">
          <button className="primary-button" type="submit" disabled={guardando || !formulario.proyectoId || !formulario.beneficiarioId}>
            <Check size={17} />Guardar
          </button>
          <button className="secondary-button" type="button" disabled={guardando} onClick={onCancelar}>Cancelar</button>
        </div>
      </form>
    </aside>
  )
}

function formatearValor(recompensa: Recompensa) {
  return recompensa.tipo === 'ECONOMICA'
    ? Number(recompensa.valor).toLocaleString('es-ES', { style: 'currency', currency: 'EUR' })
    : `${Number(recompensa.valor).toLocaleString('es-ES')} h`
}

function etiquetaTipo(tipo: TipoRecompensa) {
  return tipo === 'ECONOMICA' ? 'Economica' : 'Reduccion docente'
}

function mensajeError(error: unknown, respaldo: string) {
  return error instanceof Error ? error.message : respaldo
}
