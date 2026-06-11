import { Check, Download, FileCheck2, Pencil, Plus, Trash2, X } from 'lucide-react'
import { useCallback, useEffect, useState } from 'react'
import type { FormEvent } from 'react'
import {
  actualizarEntregable,
  crearEntregable,
  descargarArchivoEntregable,
  listarEntregables,
  retirarEntregable,
} from '../services/api'
import type { Entregable, EntregableRequest, Rol } from '../types'

interface Props {
  proyectoId: number
  proyectoArchivado: boolean
  rol: Rol
}

const vacio: EntregableRequest = { titulo: '', descripcion: '', estado: 'PRESENTADO' }

export function EntregablesProyecto({ proyectoId, proyectoArchivado, rol }: Props) {
  const [entregables, setEntregables] = useState<Entregable[]>([])
  const [seleccionado, setSeleccionado] = useState<Entregable | null>(null)
  const [formulario, setFormulario] = useState<EntregableRequest>(vacio)
  const [archivo, setArchivo] = useState<File | null>(null)
  const [modo, setModo] = useState<'lista' | 'crear' | 'editar'>('lista')
  const [motivo, setMotivo] = useState('')
  const [confirmando, setConfirmando] = useState(false)
  const [mensaje, setMensaje] = useState('')
  const [error, setError] = useState('')
  const [guardando, setGuardando] = useState(false)

  const cargar = useCallback(async () => {
    try {
      const lista = await listarEntregables(proyectoId)
      setEntregables(lista)
      setSeleccionado((actual) => lista.find((item) => item.id === actual?.id) ?? null)
      setError('')
    } catch (cargaError) {
      setError(mensajeError(cargaError, 'No se pudieron cargar los entregables.'))
    }
  }, [proyectoId])

  useEffect(() => {
    setSeleccionado(null)
    setModo('lista')
    void cargar()
  }, [cargar])

  function abrirCreacion() {
    setFormulario(vacio)
    setArchivo(null)
    setModo('crear')
  }

  function abrirEdicion() {
    if (!seleccionado) return
    setFormulario({
      titulo: seleccionado.titulo,
      descripcion: seleccionado.descripcion ?? '',
      estado: seleccionado.estado,
    })
    setArchivo(null)
    setModo('editar')
  }

  async function guardar(event: FormEvent) {
    event.preventDefault()
    setGuardando(true)
    try {
      const guardado = modo === 'crear'
        ? await crearEntregable(proyectoId, formulario, archivo)
        : await actualizarEntregable(seleccionado!.id, formulario, archivo)
      setMensaje(modo === 'crear' ? 'Entregable creado correctamente.' : 'Entregable actualizado correctamente.')
      setModo('lista')
      await cargar()
      setSeleccionado(guardado)
    } catch (guardarError) {
      setError(mensajeError(guardarError, 'No se pudo guardar el entregable.'))
    } finally {
      setGuardando(false)
    }
  }

  async function retirar() {
    if (!seleccionado || !motivo.trim()) return
    setGuardando(true)
    try {
      await retirarEntregable(seleccionado.id, motivo)
      setConfirmando(false)
      setMotivo('')
      setSeleccionado(null)
      setMensaje('Entregable retirado y conservado en el historico.')
      await cargar()
    } catch (retiradaError) {
      setError(mensajeError(retiradaError, 'No se pudo retirar el entregable.'))
    } finally {
      setGuardando(false)
    }
  }

  const puedeGestionar = seleccionado && (rol === 'COORDINADOR' || seleccionado.propio)

  return (
    <section className="deliverables-section">
      <div className="section-title deliverables-title">
        <span className="action-icon"><FileCheck2 size={20} /></span>
        <h2>Entregables</h2>
        {!proyectoArchivado && modo === 'lista' && (
          <button className="secondary-button compact-button" type="button" onClick={abrirCreacion}>
            <Plus size={16} /> Nuevo
          </button>
        )}
      </div>
      {error && <p className="form-error">{error}</p>}
      {mensaje && <p className="form-success">{mensaje}</p>}

      {modo !== 'lista' && (
        <form className="deliverable-form" onSubmit={guardar}>
          <label>Titulo<input required maxLength={180} value={formulario.titulo} onChange={(event) => setFormulario({ ...formulario, titulo: event.target.value })} /></label>
          <label>Descripcion<textarea maxLength={1000} value={formulario.descripcion} onChange={(event) => setFormulario({ ...formulario, descripcion: event.target.value })} /></label>
          <label>Estado<select value={formulario.estado} onChange={(event) => setFormulario({ ...formulario, estado: event.target.value })}><option value="PRESENTADO">Presentado</option><option value="EN_REVISION">En revision</option><option value="ACEPTADO">Aceptado</option></select></label>
          <label>{modo === 'crear' ? 'Archivo inicial opcional' : 'Nueva version opcional'}<input type="file" onChange={(event) => setArchivo(event.target.files?.[0] ?? null)} /></label>
          <div className="form-actions">
            <button className="primary-button" disabled={guardando} type="submit"><Check size={16} /> Guardar</button>
            <button className="secondary-button" type="button" onClick={() => setModo('lista')}><X size={16} /> Cancelar</button>
          </div>
        </form>
      )}

      {modo === 'lista' && (
        <div className="deliverables-layout">
          <div className="deliverable-list">
            {entregables.length === 0 && <p className="empty-state">No hay entregables registrados.</p>}
            {entregables.map((entregable) => (
              <button className={`deliverable-row ${seleccionado?.id === entregable.id ? 'selected' : ''}`} key={entregable.id} type="button" onClick={() => setSeleccionado(entregable)}>
                <span><strong>{entregable.titulo}</strong><small>{entregable.autorNombre} · {etiquetaEstado(entregable.estado)}</small></span>
                <b>{entregable.archivos.length} v.</b>
              </button>
            ))}
          </div>
          {seleccionado && (
            <div className="deliverable-detail">
              <p>{seleccionado.descripcion || 'Sin descripcion.'}</p>
              <div className="version-list">
                {seleccionado.archivos.length === 0 && <small className="empty-state">Sin archivos.</small>}
                {seleccionado.archivos.map((version) => (
                  <button className="version-row" key={version.id} type="button" onClick={() => void descargarArchivoEntregable(seleccionado.id, version)}>
                    <Download size={15} /><span>v{version.version} · {version.nombre}</span>
                  </button>
                ))}
              </div>
              {puedeGestionar && !proyectoArchivado && (
                <div className="form-actions">
                  <button className="secondary-button" type="button" onClick={abrirEdicion}><Pencil size={16} /> Editar</button>
                  <button className="danger-button" type="button" onClick={() => setConfirmando(true)}><Trash2 size={16} /> Retirar</button>
                </div>
              )}
            </div>
          )}
        </div>
      )}

      {confirmando && (
        <div className="modal-backdrop">
          <section className="modal">
            <span className="modal-icon"><Trash2 /></span>
            <button className="icon-button modal-close" type="button" onClick={() => setConfirmando(false)}><X size={18} /></button>
            <h2>Retirar entregable</h2>
            <p>Se conservaran la autoria, los archivos y todas sus versiones.</p>
            <label>Motivo<textarea value={motivo} onChange={(event) => setMotivo(event.target.value)} /></label>
            <div className="modal-actions">
              <button className="secondary-button" type="button" onClick={() => setConfirmando(false)}>Cancelar</button>
              <button className="danger-button" disabled={!motivo.trim() || guardando} type="button" onClick={() => void retirar()}>Retirar</button>
            </div>
          </section>
        </div>
      )}
    </section>
  )
}

function etiquetaEstado(estado: string) {
  return estado.replace(/_/g, ' ').toLowerCase()
}

function mensajeError(error: unknown, respaldo: string) {
  return error instanceof Error ? error.message : respaldo
}
