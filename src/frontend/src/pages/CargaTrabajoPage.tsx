import { ArrowLeft, BriefcaseBusiness, Check, ClipboardList, Search, Sparkles } from 'lucide-react'
import { useCallback, useEffect, useMemo, useState } from 'react'
import type { FormEvent } from 'react'
import {
  actualizarCargaTrabajoPersona,
  actualizarCargaTrabajoPropia,
  obtenerCargaTrabajoPropia,
  obtenerPanelCargaTrabajo,
} from '../services/api'
import type {
  CargaTrabajoPersona,
  CargaTrabajoUpdate,
  PanelCargaTrabajo,
  Rol,
} from '../types'

interface Props {
  rol: Rol
  onVolver: () => void
}

const cargaVacia: CargaTrabajoUpdate = {
  horasDocencia: 0,
  horasInvestigacion: 0,
  horasGestionAcademica: 0,
  observaciones: '',
}

export function CargaTrabajoPage({ rol, onVolver }: Props) {
  const [panel, setPanel] = useState<PanelCargaTrabajo | null>(null)
  const [cargaPropia, setCargaPropia] = useState<CargaTrabajoPersona | null>(null)
  const [seleccionada, setSeleccionada] = useState<CargaTrabajoPersona | null>(null)
  const [formulario, setFormulario] = useState<CargaTrabajoUpdate>(cargaVacia)
  const [criterio, setCriterio] = useState('')
  const [mensaje, setMensaje] = useState('')
  const [error, setError] = useState('')
  const [cargando, setCargando] = useState(true)
  const [guardando, setGuardando] = useState(false)

  const cargarCoordinador = useCallback(async (filtro = '') => {
    setCargando(true)
    try {
      setPanel(await obtenerPanelCargaTrabajo(filtro))
      setError('')
    } catch (cargaError) {
      setError(cargaError instanceof Error ? cargaError.message : 'No se pudo cargar la carga de trabajo.')
    } finally {
      setCargando(false)
    }
  }, [])

  const cargarInvestigador = useCallback(async () => {
    setCargando(true)
    try {
      const carga = await obtenerCargaTrabajoPropia()
      setCargaPropia(carga)
      setFormulario(desdeCarga(carga))
      setError('')
    } catch (cargaError) {
      setError(cargaError instanceof Error ? cargaError.message : 'No se pudo cargar la carga de trabajo.')
    } finally {
      setCargando(false)
    }
  }, [])

  useEffect(() => {
    if (rol === 'COORDINADOR') {
      void cargarCoordinador()
    } else {
      void cargarInvestigador()
    }
  }, [rol, cargarCoordinador, cargarInvestigador])

  const resumenCoordinador = useMemo(() => {
    const cargas = panel?.cargas ?? []
    return {
      totalPersonas: cargas.length,
      docentes: cargas.filter((carga) => carga.investigadorDocente).length,
      proyectosLibres: panel?.proyectosLibres.length ?? 0,
    }
  }, [panel])

  function editarCarga(carga: CargaTrabajoPersona) {
    setSeleccionada(carga)
    setFormulario(desdeCarga(carga))
    setMensaje('')
    setError('')
  }

  async function guardarCarga(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setGuardando(true)
    setError('')
    try {
      if (rol === 'COORDINADOR' && seleccionada) {
        const actualizada = await actualizarCargaTrabajoPersona(seleccionada.perfilId, formulario)
        setSeleccionada(actualizada)
        await cargarCoordinador(criterio)
      } else {
        const actualizada = await actualizarCargaTrabajoPropia(formulario)
        setCargaPropia(actualizada)
        setFormulario(desdeCarga(actualizada))
      }
      setMensaje('Carga de trabajo actualizada correctamente.')
    } catch (cargaError) {
      setError(cargaError instanceof Error ? cargaError.message : 'No se pudo guardar la carga de trabajo.')
    } finally {
      setGuardando(false)
    }
  }

  return (
    <main className="app-shell">
      <section className="workspace workload-workspace">
        <button className="link-button" type="button" onClick={onVolver}>
          <ArrowLeft size={17} />
          Volver al panel
        </button>

        <div className="workspace-heading">
          <p className="eyebrow">Carga de trabajo</p>
          <h1>{rol === 'COORDINADOR' ? 'Gestionar cargas' : 'Mi carga semanal'}</h1>
          <p>
            {rol === 'COORDINADOR'
              ? 'Revisa disponibilidad, sedes docentes y asignaciones recomendadas.'
              : 'Actualiza tus horas semanales y revisa tu margen de docencia si aplica por sede.'}
          </p>
        </div>

        {error && <p className="form-error">{error}</p>}
        {mensaje && <p className="form-success">{mensaje}</p>}
        {cargando && <p className="empty-state">Cargando carga de trabajo...</p>}

        {!cargando && rol === 'COORDINADOR' && panel && (
          <>
            <section className="workload-summary" aria-label="Resumen de carga">
              <Resumen label="Personas activas" value={resumenCoordinador.totalPersonas} />
              <Resumen label="Investigadores-docentes" value={resumenCoordinador.docentes} />
              <Resumen label="Proyectos libres" value={resumenCoordinador.proyectosLibres} />
            </section>

            <section className="detail-layout workload-layout">
              <article className="detail-panel">
                <div className="section-title">
                  <span className="action-icon"><BriefcaseBusiness size={20} /></span>
                  <h2>Cargas registradas</h2>
                </div>
                <div className="search-row">
                  <Search size={17} />
                  <input
                    value={criterio}
                    placeholder="Filtrar por persona, sede o linea"
                    onChange={(event) => setCriterio(event.target.value)}
                    onKeyDown={(event) => {
                      if (event.key === 'Enter') {
                        void cargarCoordinador(criterio)
                      }
                    }}
                  />
                  <button className="secondary-button" type="button" onClick={() => void cargarCoordinador(criterio)}>
                    Filtrar
                  </button>
                </div>
                <div className="workload-list">
                  {panel.cargas.map((carga) => (
                    <button key={carga.perfilId} className="workload-row" type="button" onClick={() => editarCarga(carga)}>
                      <span>
                        <strong>{carga.nombreCompleto || carga.usuario}</strong>
                        <small>{carga.sede} · {carga.investigadorDocente ? 'Investigador-docente' : 'Sin docencia por sede'}</small>
                      </span>
                      <span>{formatearHoras(carga.totalSemanal)} h</span>
                    </button>
                  ))}
                </div>
              </article>

              <aside className="detail-panel">
                <div className="section-title">
                  <span className="action-icon"><Sparkles size={20} /></span>
                  <h2>Sugerencias</h2>
                </div>
                <div className="suggestion-list">
                  {panel.sugerencias.map((sugerencia) => (
                    <article key={sugerencia.proyecto.codigo} className="suggestion-card">
                      <strong>{sugerencia.proyecto.nombre}</strong>
                      <small>{sugerencia.proyecto.sede} · {sugerencia.proyecto.area}</small>
                      {sugerencia.candidatos.length > 0 ? (
                        <ol>
                          {sugerencia.candidatos.map((candidato) => (
                            <li key={candidato.perfilId}>
                              {candidato.nombreCompleto} · {formatearHoras(candidato.totalSemanal)} h
                            </li>
                          ))}
                        </ol>
                      ) : (
                        <p className="empty-state">Sin candidatos docentes disponibles.</p>
                      )}
                    </article>
                  ))}
                </div>
              </aside>
            </section>

            {seleccionada && (
              <EditorCarga
                titulo={seleccionada.nombreCompleto || seleccionada.usuario}
                carga={seleccionada}
                formulario={formulario}
                guardando={guardando}
                onCambiar={setFormulario}
                onGuardar={guardarCarga}
                onCancelar={() => setSeleccionada(null)}
              />
            )}
          </>
        )}

        {!cargando && rol === 'INVESTIGADOR' && cargaPropia && (
          <section className="detail-layout workload-layout">
            <article className="detail-panel">
              <div className="section-title">
                <span className="action-icon"><ClipboardList size={20} /></span>
                <h2>Resumen semanal</h2>
              </div>
              <CargaDetalle carga={cargaPropia} />
            </article>
            <EditorCarga
              titulo="Editar carga"
              carga={cargaPropia}
              formulario={formulario}
              guardando={guardando}
              onCambiar={setFormulario}
              onGuardar={guardarCarga}
            />
          </section>
        )}
      </section>
    </main>
  )
}

function Resumen({ label, value }: { label: string; value: number }) {
  return (
    <article>
      <strong>{value}</strong>
      <span>{label}</span>
    </article>
  )
}

function EditorCarga({
  titulo,
  carga,
  formulario,
  guardando,
  onCambiar,
  onGuardar,
  onCancelar,
}: {
  titulo: string
  carga: CargaTrabajoPersona
  formulario: CargaTrabajoUpdate
  guardando: boolean
  onCambiar: (formulario: CargaTrabajoUpdate) => void
  onGuardar: (event: FormEvent<HTMLFormElement>) => void
  onCancelar?: () => void
}) {
  return (
    <article className="detail-panel">
      <div className="section-title">
        <span className="action-icon"><BriefcaseBusiness size={20} /></span>
        <h2>{titulo}</h2>
      </div>
      <CargaDetalle carga={{ ...carga, ...formulario, totalSemanal: totalFormulario(formulario) }} />
      <form className="profile-form workload-form" onSubmit={onGuardar}>
        <label>
          Horas de docencia
          <input type="number" min="0" max={carga.investigadorDocente ? 16 : undefined} step="0.5" value={formulario.horasDocencia} onChange={(event) => onCambiar({ ...formulario, horasDocencia: Number(event.target.value) })} />
          {carga.investigadorDocente && <small className="field-hint">Máximo 16 horas semanales.</small>}
        </label>
        <label>
          Horas de investigacion
          <input type="number" min="0" step="0.5" value={formulario.horasInvestigacion} onChange={(event) => onCambiar({ ...formulario, horasInvestigacion: Number(event.target.value) })} />
        </label>
        <label>
          Horas de gestion academica
          <input type="number" min="0" step="0.5" value={formulario.horasGestionAcademica} onChange={(event) => onCambiar({ ...formulario, horasGestionAcademica: Number(event.target.value) })} />
        </label>
        <label>
          Observaciones
          <textarea value={formulario.observaciones} rows={3} onChange={(event) => onCambiar({ ...formulario, observaciones: event.target.value })} />
        </label>
        <div className="button-row">
          <button className="primary-button" type="submit" disabled={guardando}>
            <Check size={17} />
            Guardar
          </button>
          {onCancelar && (
            <button className="secondary-button" type="button" onClick={onCancelar} disabled={guardando}>
              Cancelar
            </button>
          )}
        </div>
      </form>
    </article>
  )
}

function CargaDetalle({ carga }: { carga: CargaTrabajoPersona }) {
  const margen = carga.investigadorDocente ? Math.max(0, 16 - Number(carga.horasDocencia)) : 0
  return (
    <dl className="data-list compact-list">
      <dt>Sede</dt>
      <dd>{carga.sede}</dd>
      <dt>Tipo</dt>
      <dd>{carga.investigadorDocente ? 'Investigador-docente' : 'Investigador sin docencia por sede'}</dd>
      <dt>Total semanal</dt>
      <dd>{formatearHoras(carga.totalSemanal)} h</dd>
      <dt>Docencia</dt>
      <dd>{formatearHoras(carga.horasDocencia)} h</dd>
      <dt>Margen docente</dt>
      <dd>{carga.investigadorDocente ? `${formatearHoras(margen)} h disponibles` : 'No aplica'}</dd>
      <dt>Límite docente</dt>
      <dd>{carga.investigadorDocente ? '16 h semanales' : 'No aplica por sede'}</dd>
    </dl>
  )
}

function desdeCarga(carga: CargaTrabajoPersona): CargaTrabajoUpdate {
  return {
    horasDocencia: Number(carga.horasDocencia),
    horasInvestigacion: Number(carga.horasInvestigacion),
    horasGestionAcademica: Number(carga.horasGestionAcademica),
    observaciones: carga.observaciones ?? '',
  }
}

function totalFormulario(carga: CargaTrabajoUpdate) {
  return Number(carga.horasDocencia) + Number(carga.horasInvestigacion) + Number(carga.horasGestionAcademica)
}

function formatearHoras(valor: number) {
  return Number(valor).toLocaleString('es-ES', { maximumFractionDigits: 1 })
}
