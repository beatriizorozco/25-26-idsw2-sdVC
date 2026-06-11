import { ArrowLeft, Check, Download, FileText, MessageSquare, Pencil, Plus, Search, Trash2, X } from 'lucide-react'
import { useCallback, useEffect, useState } from 'react'
import type { FormEvent } from 'react'
import { actualizarPublicacion, crearPublicacion, descargarArchivoPublicacion, listarPublicaciones, obtenerPublicacion, responderPublicacion, retirarPublicacion } from '../services/api'
import type { Publicacion, PublicacionRequest, Rol } from '../types'

interface Props { rol: Rol; propias: boolean; onVolver: () => void }
const vacio: PublicacionRequest = { titulo: '', contenido: '' }

export function PublicacionesPage({ rol, propias, onVolver }: Props) {
  const [lista, setLista] = useState<Publicacion[]>([])
  const [seleccionada, setSeleccionada] = useState<Publicacion | null>(null)
  const [criterio, setCriterio] = useState('')
  const [modo, setModo] = useState<'lista' | 'crear' | 'editar'>('lista')
  const [formulario, setFormulario] = useState(vacio)
  const [archivo, setArchivo] = useState<File | null>(null)
  const [respuesta, setRespuesta] = useState('')
  const [motivo, setMotivo] = useState('')
  const [confirmando, setConfirmando] = useState(false)
  const [mensaje, setMensaje] = useState('')
  const [error, setError] = useState('')
  const [guardando, setGuardando] = useState(false)

  const cargar = useCallback(async (filtro = '') => {
    try {
      const datos = await listarPublicaciones(filtro, propias)
      setLista(datos)
      setSeleccionada((actual) => datos.find((p) => p.id === actual?.id) ?? null)
      setError('')
    } catch (e) { setError(mensajeError(e, 'No se pudieron cargar las publicaciones.')) }
  }, [propias])

  useEffect(() => { setSeleccionada(null); setModo('lista'); void cargar() }, [cargar])

  async function abrir(id: number) {
    try { setSeleccionada(await obtenerPublicacion(id, propias)); setModo('lista'); setError('') }
    catch (e) { setError(mensajeError(e, 'No se pudo abrir la publicación.')) }
  }
  function crear() { setFormulario(vacio); setArchivo(null); setModo('crear') }
  function editar() {
    if (!seleccionada) return
    setFormulario({ titulo: seleccionada.titulo, contenido: seleccionada.contenido }); setArchivo(null); setModo('editar')
  }
  async function guardar(event: FormEvent) {
    event.preventDefault(); setGuardando(true)
    try {
      const guardada = modo === 'crear' ? await crearPublicacion(formulario, archivo) : await actualizarPublicacion(seleccionada!.id, formulario, archivo)
      setMensaje(modo === 'crear' ? 'Publicación creada correctamente.' : 'Publicación actualizada correctamente.')
      setModo('lista'); await cargar(criterio); setSeleccionada(guardada); setError('')
    } catch (e) { setError(mensajeError(e, 'No se pudo guardar la publicación.')) }
    finally { setGuardando(false) }
  }
  async function responder() {
    if (!seleccionada || !respuesta.trim()) return
    setGuardando(true)
    try { setSeleccionada(await responderPublicacion(seleccionada.id, respuesta)); setRespuesta(''); setMensaje('Respuesta publicada correctamente.') }
    catch (e) { setError(mensajeError(e, 'No se pudo publicar la respuesta.')) }
    finally { setGuardando(false) }
  }
  async function retirar() {
    if (!seleccionada || !motivo.trim()) return
    setGuardando(true)
    try { await retirarPublicacion(seleccionada.id, motivo); setConfirmando(false); setSeleccionada(null); setMotivo(''); setMensaje('Publicación retirada y conservada en el histórico.'); await cargar(criterio) }
    catch (e) { setError(mensajeError(e, 'No se pudo retirar la publicación.')) }
    finally { setGuardando(false) }
  }
  const puedeGestionar = seleccionada && (rol === 'COORDINADOR' || seleccionada.propia)

  return <main className="app-shell"><section className="workspace publications-workspace">
    <button className="link-button" type="button" onClick={onVolver}><ArrowLeft size={17} /> Volver al panel</button>
    <div className="workspace-heading"><p className="eyebrow">Publicaciones</p><h1>{propias ? 'Mis publicaciones' : 'Publicaciones'}</h1><p>{propias ? 'Comparte y gestiona tus aportaciones.' : 'Consulta y participa en la actividad investigadora.'}</p></div>
    {error && <p className="form-error">{error}</p>}{mensaje && <p className="form-success">{mensaje}</p>}
    <section className="detail-layout publications-layout">
      <article className="detail-panel">
        <div className="section-title publications-title"><span className="action-icon"><FileText size={20} /></span><h2>{propias ? 'Publicaciones propias' : 'Publicaciones activas'}</h2>
          {propias && <button className="primary-button compact-button" type="button" onClick={crear}><Plus size={17} /> Nueva</button>}</div>
        <form className="search-row" onSubmit={(e) => { e.preventDefault(); void cargar(criterio) }}><Search size={18}/><input value={criterio} onChange={(e) => setCriterio(e.target.value)} placeholder="Filtrar publicaciones"/><button className="secondary-button">Filtrar</button></form>
        <div className="publication-list">{lista.length === 0 && <p className="empty-state">No hay publicaciones disponibles.</p>}{lista.map((p) =>
          <button className={`publication-row ${seleccionada?.id === p.id ? 'selected' : ''}`} key={p.id} type="button" onClick={() => void abrir(p.id)}>
            <span><strong>{p.titulo}</strong><small>{p.autorNombre} · {formatearFecha(p.fechaActualizacion)}</small></span><MessageSquare size={17}/></button>)}</div>
      </article>
      <article className="detail-panel publication-detail">
        {modo !== 'lista' && <form className="publication-form" onSubmit={guardar}><div className="section-title"><span className="action-icon"><FileText size={20}/></span><h2>{modo === 'crear' ? 'Nueva publicación' : 'Editar publicación'}</h2></div>
          <label>Título<input required maxLength={180} value={formulario.titulo} onChange={(e) => setFormulario({...formulario, titulo:e.target.value})}/></label>
          <label>Contenido<textarea required maxLength={4000} value={formulario.contenido} onChange={(e) => setFormulario({...formulario, contenido:e.target.value})}/></label>
          <label>Adjunto opcional<input type="file" onChange={(e) => setArchivo(e.target.files?.[0] ?? null)}/></label>
          <div className="form-actions"><button className="primary-button" disabled={guardando}><Check size={17}/> Guardar</button><button className="secondary-button" type="button" onClick={() => setModo('lista')}><X size={17}/> Cancelar</button></div></form>}
        {modo === 'lista' && !seleccionada && <><div className="section-title"><span className="action-icon"><FileText size={20}/></span><h2>Detalle</h2></div><p className="empty-state">Selecciona una publicación para consultar su detalle.</p></>}
        {modo === 'lista' && seleccionada && <><div className="section-title"><span className="action-icon"><FileText size={20}/></span><h2>{seleccionada.titulo}</h2></div>
          <p className="publication-meta">{seleccionada.autorNombre} · {formatearFecha(seleccionada.fechaCreacion)}</p><p className="publication-content">{seleccionada.contenido}</p>
          <section className="publication-files"><h3>Archivos adjuntos</h3>{seleccionada.archivos.length === 0 && <p className="empty-state">Sin archivos adjuntos.</p>}{seleccionada.archivos.map((a) => <button className="version-row" key={a.id} type="button" onClick={() => void descargarArchivoPublicacion(seleccionada.id,a)}><Download size={16}/>{a.nombre}</button>)}</section>
          <section className="responses-section"><h3>Respuestas</h3>{seleccionada.respuestas.map((r) => <div className="response-row" key={r.id}><strong>{r.autorNombre}</strong><p>{r.contenido}</p><small>{formatearFecha(r.fecha)}</small></div>)}
            <label>Responder<textarea value={respuesta} maxLength={1500} onChange={(e) => setRespuesta(e.target.value)}/></label><button className="secondary-button" disabled={!respuesta.trim() || guardando} type="button" onClick={() => void responder()}><MessageSquare size={16}/> Publicar respuesta</button></section>
          {puedeGestionar && <div className="form-actions"><button className="primary-button" type="button" onClick={editar}><Pencil size={16}/> Editar</button><button className="danger-button" type="button" onClick={() => setConfirmando(true)}><Trash2 size={16}/> Retirar</button></div>}</>}
      </article>
    </section>
    {confirmando && <div className="modal-backdrop"><section className="modal"><span className="modal-icon"><Trash2/></span><button className="icon-button modal-close" onClick={() => setConfirmando(false)}><X size={18}/></button><h2>Retirar publicación</h2><p>La autoría, respuestas y archivos se conservarán.</p><label>Motivo<textarea value={motivo} onChange={(e)=>setMotivo(e.target.value)}/></label><div className="modal-actions"><button className="secondary-button" onClick={()=>setConfirmando(false)}>Cancelar</button><button className="danger-button" disabled={!motivo.trim()||guardando} onClick={()=>void retirar()}>Retirar</button></div></section></div>}
  </section></main>
}
function formatearFecha(valor:string){ return new Intl.DateTimeFormat('es-ES',{dateStyle:'medium'}).format(new Date(valor)) }
function mensajeError(error:unknown,respaldo:string){ return error instanceof Error ? error.message : respaldo }
