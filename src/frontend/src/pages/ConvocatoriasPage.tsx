import { ArrowLeft, CalendarDays, Check, FileUp, Plus, Search, X } from 'lucide-react'
import { useCallback, useEffect, useState } from 'react'
import type { FormEvent } from 'react'
import { confirmarImportacionConvocatoria, listarConvocatorias, obtenerConvocatoria, previsualizarConvocatoria } from '../services/api'
import type { Convocatoria, FuenteConvocatoria, PrevisualizacionConvocatoria } from '../types'

interface Props { onVolver: () => void }
const fuenteInicial: FuenteConvocatoria = { tipo: 'ENLACE', referencia: '' }

export function ConvocatoriasPage({ onVolver }: Props) {
  const [lista, setLista] = useState<Convocatoria[]>([])
  const [seleccionada, setSeleccionada] = useState<Convocatoria | null>(null)
  const [texto, setTexto] = useState('')
  const [area, setArea] = useState('')
  const [estado, setEstado] = useState('')
  const [modo, setModo] = useState<'lista' | 'importar'>('lista')
  const [fuente, setFuente] = useState<FuenteConvocatoria>(fuenteInicial)
  const [previsualizacion, setPrevisualizacion] = useState<PrevisualizacionConvocatoria | null>(null)
  const [procesando, setProcesando] = useState(false)
  const [error, setError] = useState('')
  const [mensaje, setMensaje] = useState('')

  const cargar = useCallback(async () => {
    try {
      const datos = await listarConvocatorias(texto, area, estado)
      setLista(datos)
      setSeleccionada((actual) => datos.find((c) => c.id === actual?.id) ?? null)
      setError('')
    } catch (e) { setError(mensajeError(e, 'No se pudieron cargar las convocatorias.')) }
  }, [texto, area, estado])

  useEffect(() => { void cargar() }, []) // La carga inicial no debe repetirse mientras se escribe el filtro.

  async function abrir(id: number) {
    try { setSeleccionada(await obtenerConvocatoria(id)); setModo('lista'); setError('') }
    catch (e) { setError(mensajeError(e, 'No se pudo abrir la convocatoria.')) }
  }
  function iniciarImportacion() {
    setFuente(fuenteInicial); setPrevisualizacion(null); setModo('importar'); setSeleccionada(null); setError(''); setMensaje('')
  }
  async function seleccionarArchivo(archivo?: File) {
    if (!archivo) { setFuente({ tipo: 'ARCHIVO', referencia: '' }); return }
    const contenido = archivo.name.toLowerCase().endsWith('.json') ? await archivo.text() : undefined
    setFuente({ tipo: 'ARCHIVO', referencia: archivo.name, contenido })
  }
  async function previsualizar(event: FormEvent) {
    event.preventDefault(); setProcesando(true)
    try { setPrevisualizacion(await previsualizarConvocatoria(fuente)); setError('') }
    catch (e) { setError(mensajeError(e, 'No se pudo previsualizar la convocatoria.')) }
    finally { setProcesando(false) }
  }
  async function confirmar() {
    if (!previsualizacion) return
    setProcesando(true)
    try {
      const guardada = await confirmarImportacionConvocatoria(previsualizacion.datos)
      setMensaje('Convocatoria importada correctamente.'); setModo('lista'); setPrevisualizacion(null)
      await cargar(); setSeleccionada(guardada); setError('')
    } catch (e) { setError(mensajeError(e, 'No se pudo importar la convocatoria.')) }
    finally { setProcesando(false) }
  }

  return <main className="app-shell"><section className="workspace convocatorias-workspace">
    <button className="link-button" type="button" onClick={onVolver}><ArrowLeft size={17}/> Volver al panel</button>
    <div className="workspace-heading"><p className="eyebrow">Convocatorias</p><h1>Seguimiento de convocatorias</h1><p>Consulta oportunidades externas e incorpora nuevas fuentes al seguimiento interno.</p></div>
    {error && <p className="form-error">{error}</p>}{mensaje && <p className="form-success">{mensaje}</p>}
    <section className="detail-layout convocatorias-layout">
      <article className="detail-panel">
        <div className="section-title convocatorias-title"><span className="action-icon"><CalendarDays size={20}/></span><h2>Convocatorias disponibles</h2>
        </div>
        <form className="convocatoria-filters" onSubmit={(e) => { e.preventDefault(); void cargar() }}>
          <label>Buscar<input value={texto} onChange={(e) => setTexto(e.target.value)} placeholder="Título, entidad o descripción"/></label>
          <label>Área<input value={area} onChange={(e) => setArea(e.target.value)} placeholder="Todas las áreas"/></label>
          <label>Estado<select value={estado} onChange={(e) => setEstado(e.target.value)}><option value="">Todos</option><option value="ABIERTA">Abierta</option><option value="PROXIMA">Próxima</option><option value="CERRADA">Cerrada</option></select></label>
          <button className="secondary-button"><Search size={17}/> Filtrar</button>
        </form>
        <div className="convocatoria-list">{lista.length === 0 && <p className="empty-state">No hay convocatorias que coincidan con los filtros.</p>}{lista.map((c) =>
          <button className={`convocatoria-row ${seleccionada?.id === c.id ? 'selected' : ''}`} key={c.id} type="button" onClick={() => void abrir(c.id)}>
            <span><strong>{c.titulo}</strong><small>{c.entidadConvocante} · {c.area}</small></span><b>{c.estado}</b></button>)}</div>
      </article>
      <article className="detail-panel convocatoria-detail">
        {modo === 'importar' && !previsualizacion && <form className="convocatoria-form" onSubmit={previsualizar}>
          <div className="section-title"><span className="action-icon"><FileUp size={20}/></span><h2>Importar convocatoria</h2></div>
          <label>Tipo de fuente<select value={fuente.tipo} onChange={(e) => setFuente({ tipo: e.target.value as FuenteConvocatoria['tipo'], referencia: '' })}><option value="ENLACE">Enlace externo</option><option value="ARCHIVO">Archivo</option></select></label>
          {fuente.tipo === 'ENLACE' ? <label>Enlace<input type="url" required value={fuente.referencia} onChange={(e) => setFuente({...fuente, referencia:e.target.value})} placeholder="https://entidad.org/convocatoria"/></label>
            : <label>Archivo PDF, DOC, XLS o JSON<input type="file" required accept=".pdf,.doc,.docx,.xls,.xlsx,.json" onChange={(e) => void seleccionarArchivo(e.target.files?.[0])}/><span className="field-hint">Los archivos JSON permiten extraer todos los campos; los demás conservan la referencia para revisión.</span></label>}
          <div className="form-actions"><button className="primary-button" disabled={procesando || !fuente.referencia}><FileUp size={17}/> Previsualizar</button><button className="secondary-button" type="button" onClick={() => setModo('lista')}><X size={17}/> Cancelar</button></div>
        </form>}
        {modo === 'importar' && previsualizacion && <><div className="section-title"><span className="action-icon"><FileUp size={20}/></span><h2>Confirmar importación</h2></div><p className="preview-message">{previsualizacion.mensaje}</p><Datos convocatoria={previsualizacion.datos}/>
          <div className="form-actions"><button className="primary-button" disabled={procesando} type="button" onClick={() => void confirmar()}><Check size={17}/> Confirmar</button><button className="secondary-button" type="button" onClick={() => { setPrevisualizacion(null); setModo('lista') }}><X size={17}/> Cancelar</button></div></>}
        {modo === 'lista' && !seleccionada && <><div className="section-title"><span className="action-icon"><CalendarDays size={20}/></span><h2>Detalle</h2></div><p className="empty-state">Selecciona una convocatoria para consultar su información.</p></>}
        {modo === 'lista' && seleccionada && <><div className="section-title"><span className="action-icon"><CalendarDays size={20}/></span><h2>{seleccionada.titulo}</h2></div><Datos convocatoria={seleccionada}/>
          <div className="form-actions"><button className="primary-button" type="button" onClick={iniciarImportacion}><Plus size={17}/> Importar convocatoria</button></div></>}
      </article>
    </section>
  </section></main>
}

function Datos({ convocatoria: c }: { convocatoria: Convocatoria | PrevisualizacionConvocatoria['datos'] }) {
  return <dl className="data-list convocatoria-data"><dt>Entidad</dt><dd>{c.entidadConvocante}</dd><dt>Área</dt><dd>{c.area}</dd><dt>Estado</dt><dd>{c.estado}</dd><dt>Apertura</dt><dd>{fecha(c.fechaApertura)}</dd><dt>Cierre</dt><dd>{fecha(c.fechaCierre)}</dd><dt>Descripción</dt><dd>{c.descripcion}</dd><dt>Requisitos</dt><dd>{c.requisitos}</dd><dt>Criterios</dt><dd>{c.criteriosEvaluacion}</dd><dt>Dotación</dt><dd>{c.dotacion}</dd><dt>Contacto</dt><dd>{c.contacto}</dd><dt>Fuente</dt><dd>{c.referenciaExterna}</dd></dl>
}
function fecha(valor?: string) { return valor ? new Intl.DateTimeFormat('es-ES', { dateStyle: 'medium' }).format(new Date(`${valor}T00:00:00`)) : 'Sin fecha indicada' }
function mensajeError(error: unknown, respaldo: string) { return error instanceof Error ? error.message : respaldo }
