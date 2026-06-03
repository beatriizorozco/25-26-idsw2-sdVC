import { ArrowLeft, Check, FileText, Search, Trash2, UserRound, X } from 'lucide-react'
import { useCallback, useEffect, useState } from 'react'
import type { FormEvent } from 'react'
import {
  actualizarPerfil,
  eliminarPerfilDesdeSolicitud,
  listarSolicitudesEliminacionPerfil,
  obtenerPerfil,
  obtenerSolicitudEliminacionPerfil,
  solicitarEliminacionPerfil,
} from '../services/api'
import type { Perfil, PerfilUpdate, Rol, SolicitudEliminacionPerfil } from '../types'

interface Props {
  rol: Rol
  onVolver: () => void
  onSesionCerrada: () => void
}

const perfilVacio: PerfilUpdate = {
  nombreCompleto: '',
  email: '',
  unidad: '',
  lineaInvestigacion: '',
  biografia: '',
}

export function PerfilPage({ rol, onVolver, onSesionCerrada }: Props) {
  const [perfil, setPerfil] = useState<Perfil | null>(null)
  const [formulario, setFormulario] = useState<PerfilUpdate>(perfilVacio)
  const [editando, setEditando] = useState(false)
  const [mensaje, setMensaje] = useState('')
  const [error, setError] = useState('')
  const [confirmandoEliminacion, setConfirmandoEliminacion] = useState(false)
  const [motivo, setMotivo] = useState('')
  const [solicitudes, setSolicitudes] = useState<SolicitudEliminacionPerfil[]>([])
  const [criterio, setCriterio] = useState('')
  const [solicitudActiva, setSolicitudActiva] = useState<SolicitudEliminacionPerfil | null>(null)
  const [procesando, setProcesando] = useState(false)

  const cargarPerfil = useCallback(async () => {
    try {
      const datos = await obtenerPerfil()
      setPerfil(datos)
      setFormulario({
        nombreCompleto: datos.nombreCompleto,
        email: datos.email,
        unidad: datos.unidad,
        lineaInvestigacion: datos.lineaInvestigacion,
        biografia: datos.biografia,
      })
    } catch (perfilError) {
      setError(perfilError instanceof Error ? perfilError.message : 'No se pudo cargar el perfil.')
    }
  }, [])

  const cargarSolicitudes = useCallback(async (filtro = '') => {
    try {
      setSolicitudes(await listarSolicitudesEliminacionPerfil(filtro))
    } catch {
      setSolicitudes([])
    }
  }, [])

  useEffect(() => {
    void cargarPerfil()
    if (rol === 'COORDINADOR') {
      void cargarSolicitudes()
    }
  }, [rol, cargarPerfil, cargarSolicitudes])

  async function guardarPerfil(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setProcesando(true)
    setError('')
    try {
      const actualizado = await actualizarPerfil(formulario)
      setPerfil(actualizado)
      setEditando(false)
      setMensaje('Perfil actualizado correctamente.')
    } catch (perfilError) {
      setError(perfilError instanceof Error ? perfilError.message : 'No se pudo actualizar el perfil.')
    } finally {
      setProcesando(false)
    }
  }

  async function confirmarSolicitudEliminacion() {
    setProcesando(true)
    setError('')
    try {
      await solicitarEliminacionPerfil(motivo)
      onSesionCerrada()
    } catch (solicitudError) {
      setError(solicitudError instanceof Error ? solicitudError.message : 'No se pudo registrar la solicitud.')
    } finally {
      setProcesando(false)
    }
  }

  async function abrirSolicitud(solicitud: SolicitudEliminacionPerfil) {
    setSolicitudActiva(await obtenerSolicitudEliminacionPerfil(solicitud.id))
  }

  async function confirmarEliminacionSolicitud() {
    if (!solicitudActiva) {
      return
    }
    setProcesando(true)
    try {
      await eliminarPerfilDesdeSolicitud(solicitudActiva.id)
      setSolicitudActiva(null)
      await cargarSolicitudes(criterio)
      setMensaje('Perfil eliminado correctamente.')
    } catch (solicitudError) {
      setError(solicitudError instanceof Error ? solicitudError.message : 'No se pudo eliminar el perfil.')
    } finally {
      setProcesando(false)
    }
  }

  return (
    <main className="app-shell">
      <section className="workspace">
        <button className="link-button" type="button" onClick={onVolver}>
          <ArrowLeft size={17} />
          Volver al panel
        </button>

        <div className="workspace-heading">
          <p className="eyebrow">Perfil</p>
          <h1>{perfil?.nombreCompleto ?? 'Perfil'}</h1>
          <p>Gestiona tus datos personales y las opciones de eliminación de cuenta.</p>
        </div>

        {error && <p className="form-error">{error}</p>}
        {mensaje && <p className="form-success">{mensaje}</p>}

        <section className="detail-layout">
          <article className="detail-panel">
            <div className="section-title">
              <span className="action-icon"><UserRound size={20} /></span>
              <h2>Datos del perfil</h2>
            </div>

            {!editando && perfil && (
              <>
                <dl className="data-list">
                  <dt>Usuario</dt>
                  <dd>{perfil.usuario}</dd>
                  <dt>Correo</dt>
                  <dd>{perfil.email || 'Sin correo indicado'}</dd>
                  <dt>Unidad</dt>
                  <dd>{perfil.unidad || 'Sin unidad indicada'}</dd>
                  <dt>Línea de investigación</dt>
                  <dd>{perfil.lineaInvestigacion || 'Sin línea indicada'}</dd>
                  <dt>Biografía</dt>
                  <dd>{perfil.biografia || 'Sin biografía indicada'}</dd>
                </dl>
                <div className="button-row">
                  <button className="primary-button" type="button" onClick={() => setEditando(true)}>
                    Editar perfil
                  </button>
                  <button className="secondary-button" type="button" onClick={() => setConfirmandoEliminacion(true)}>
                    Solicitar eliminación
                  </button>
                </div>
              </>
            )}

            {editando && (
              <form className="profile-form" onSubmit={guardarPerfil}>
                <label>
                  Nombre completo
                  <input value={formulario.nombreCompleto} onChange={(event) => setFormulario({ ...formulario, nombreCompleto: event.target.value })} />
                </label>
                <label>
                  Correo
                  <input value={formulario.email} onChange={(event) => setFormulario({ ...formulario, email: event.target.value })} />
                </label>
                <label>
                  Unidad
                  <input value={formulario.unidad} onChange={(event) => setFormulario({ ...formulario, unidad: event.target.value })} />
                </label>
                <label>
                  Línea de investigación
                  <input value={formulario.lineaInvestigacion} onChange={(event) => setFormulario({ ...formulario, lineaInvestigacion: event.target.value })} />
                </label>
                <label>
                  Biografía
                  <textarea value={formulario.biografia} onChange={(event) => setFormulario({ ...formulario, biografia: event.target.value })} rows={4} />
                </label>
                <div className="button-row">
                  <button className="primary-button" type="submit" disabled={procesando}>
                    <Check size={17} />
                    Guardar
                  </button>
                  <button className="secondary-button" type="button" onClick={() => setEditando(false)} disabled={procesando}>
                    Cancelar
                  </button>
                </div>
              </form>
            )}
          </article>

          {rol === 'COORDINADOR' && (
            <article className="detail-panel">
              <div className="section-title">
                <span className="action-icon"><FileText size={20} /></span>
                <h2>Solicitudes de eliminación</h2>
              </div>
              <div className="search-row">
                <Search size={17} />
                <input
                  value={criterio}
                  placeholder="Filtrar solicitudes"
                  onChange={(event) => setCriterio(event.target.value)}
                  onKeyDown={(event) => {
                    if (event.key === 'Enter') {
                      void cargarSolicitudes(criterio)
                    }
                  }}
                />
                <button className="secondary-button" type="button" onClick={() => void cargarSolicitudes(criterio)}>Filtrar</button>
              </div>
              <div className="request-list">
                {solicitudes.map((solicitud) => (
                  <button key={solicitud.id} className="request-row" type="button" onClick={() => void abrirSolicitud(solicitud)}>
                    <strong>{solicitud.nombreCompleto || solicitud.usuario}</strong>
                    <small>{solicitud.motivo}</small>
                  </button>
                ))}
                {!solicitudes.length && <p className="empty-state">No hay solicitudes pendientes.</p>}
              </div>
            </article>
          )}
        </section>
      </section>

      {confirmandoEliminacion && (
        <div className="modal-backdrop" role="presentation">
          <section className="modal" role="dialog" aria-modal="true" aria-labelledby="solicitud-eliminacion-titulo">
            <button className="icon-button modal-close" type="button" onClick={() => setConfirmandoEliminacion(false)} aria-label="Cerrar">
              <X size={18} />
            </button>
            <div className="modal-icon"><Trash2 size={22} /></div>
            <h2 id="solicitud-eliminacion-titulo">Solicitar eliminación</h2>
            <p>La solicitud cerrará tu sesión y quedará pendiente de revisión.</p>
            <label>
              Motivo
              <textarea value={motivo} onChange={(event) => setMotivo(event.target.value)} rows={4} />
            </label>
            <div className="modal-actions">
              <button className="secondary-button" type="button" onClick={() => setConfirmandoEliminacion(false)} disabled={procesando}>
                Cancelar
              </button>
              <button className="danger-button" type="button" onClick={() => void confirmarSolicitudEliminacion()} disabled={procesando || !motivo.trim()}>
                Confirmar
              </button>
            </div>
          </section>
        </div>
      )}

      {solicitudActiva && (
        <div className="modal-backdrop" role="presentation">
          <section className="modal" role="dialog" aria-modal="true" aria-labelledby="detalle-solicitud-titulo">
            <button className="icon-button modal-close" type="button" onClick={() => setSolicitudActiva(null)} aria-label="Cerrar">
              <X size={18} />
            </button>
            <div className="modal-icon"><Trash2 size={22} /></div>
            <h2 id="detalle-solicitud-titulo">Eliminar perfil</h2>
            <p>{solicitudActiva.nombreCompleto || solicitudActiva.usuario}</p>
            <p>{solicitudActiva.motivo}</p>
            <div className="modal-actions">
              <button className="secondary-button" type="button" onClick={() => setSolicitudActiva(null)} disabled={procesando}>
                Cancelar
              </button>
              <button className="danger-button" type="button" onClick={() => void confirmarEliminacionSolicitud()} disabled={procesando}>
                Eliminar perfil
              </button>
            </div>
          </section>
        </div>
      )}
    </main>
  )
}
