import type {
  CargaTrabajoPersona,
  CargaTrabajoUpdate,
  CsrfToken,
  PanelCargaTrabajo,
  PanelPrincipal,
  Perfil,
  PerfilUpdate,
  BeneficiarioRecompensa,
  OpcionesCreacionRecompensa,
  Recompensa,
  RecompensaEdicion,
  RecompensaRequest,
  TipoRecompensa,
  Sesion,
  SolicitudEliminacionPerfil,
  InvestigadorProyecto,
  Proyecto,
  ProyectoRequest,
  ArchivoProyecto,
  ArchivoEntregable,
  Entregable,
  EntregableRequest,
  InvestigadorCreateRequest,
  InvestigadorDetalle,
  InvestigadorResumen,
  Publicacion,
  PublicacionRequest,
  ArchivoPublicacion,
} from '../types'

const API_URL = import.meta.env.VITE_API_URL ?? '/api'

let csrfToken: CsrfToken | null = null

async function request<T>(path: string, options: RequestInit = {}): Promise<T> {
  const response = await fetch(`${API_URL}${path}`, {
    credentials: 'include',
    ...options,
    headers: {
      'Content-Type': 'application/json',
      ...options.headers,
    },
  })

  if (!response.ok) {
    const error = await response.json().catch(() => ({ mensaje: 'No se pudo completar la solicitud.' }))
    throw new Error(error.mensaje ?? 'No se pudo completar la solicitud.')
  }

  if (response.status === 204) {
    return undefined as T
  }

  return response.json() as Promise<T>
}

export async function obtenerTokenCsrf(): Promise<CsrfToken> {
  csrfToken = await request<CsrfToken>('/auth/csrf')
  return csrfToken
}

export async function iniciarSesion(usuario: string, contrasena: string): Promise<Sesion> {
  const token = await obtenerTokenCsrf()
  const sesion = await request<Sesion>('/auth/login', {
    method: 'POST',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify({ usuario, contrasena }),
  })
  await obtenerTokenCsrf()
  return sesion
}

export function obtenerSesion(): Promise<Sesion> {
  return request<Sesion>('/auth/me')
}

export function obtenerPanelPrincipal(): Promise<PanelPrincipal> {
  return request<PanelPrincipal>('/panel-principal')
}

export function obtenerPerfil(): Promise<Perfil> {
  return request<Perfil>('/perfil')
}

export async function actualizarPerfil(perfil: PerfilUpdate): Promise<Perfil> {
  const token = await obtenerTokenCsrf()
  return request<Perfil>('/perfil', {
    method: 'PATCH',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify(perfil),
  })
}

export async function solicitarEliminacionPerfil(motivo: string): Promise<SolicitudEliminacionPerfil> {
  const token = await obtenerTokenCsrf()
  const solicitud = await request<SolicitudEliminacionPerfil>('/perfil/solicitud-eliminacion', {
    method: 'POST',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify({ motivo }),
  })
  csrfToken = null
  return solicitud
}

export function listarSolicitudesEliminacionPerfil(criterio = ''): Promise<SolicitudEliminacionPerfil[]> {
  const query = criterio.trim() ? `?criterio=${encodeURIComponent(criterio.trim())}` : ''
  return request<SolicitudEliminacionPerfil[]>(`/solicitudes-eliminacion-perfil${query}`)
}

export function obtenerSolicitudEliminacionPerfil(id: number): Promise<SolicitudEliminacionPerfil> {
  return request<SolicitudEliminacionPerfil>(`/solicitudes-eliminacion-perfil/${id}`)
}

export async function eliminarPerfilDesdeSolicitud(id: number): Promise<void> {
  const token = await obtenerTokenCsrf()
  await request<void>(`/solicitudes-eliminacion-perfil/${id}/perfil`, {
    method: 'DELETE',
    headers: { [token.headerName]: token.token },
  })
}

export function obtenerPanelCargaTrabajo(criterio = ''): Promise<PanelCargaTrabajo> {
  const query = criterio.trim() ? `?criterio=${encodeURIComponent(criterio.trim())}` : ''
  return request<PanelCargaTrabajo>(`/carga-trabajo${query}`)
}

export function obtenerCargaTrabajoPropia(): Promise<CargaTrabajoPersona> {
  return request<CargaTrabajoPersona>('/carga-trabajo/me')
}

export function obtenerCargaTrabajoPersona(perfilId: number): Promise<CargaTrabajoPersona> {
  return request<CargaTrabajoPersona>(`/carga-trabajo/${perfilId}`)
}

export async function actualizarCargaTrabajoPropia(carga: CargaTrabajoUpdate): Promise<CargaTrabajoPersona> {
  const token = await obtenerTokenCsrf()
  return request<CargaTrabajoPersona>('/carga-trabajo/me', {
    method: 'PATCH',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify(carga),
  })
}

export async function actualizarCargaTrabajoPersona(
  perfilId: number,
  carga: CargaTrabajoUpdate,
): Promise<CargaTrabajoPersona> {
  const token = await obtenerTokenCsrf()
  return request<CargaTrabajoPersona>(`/carga-trabajo/${perfilId}`, {
    method: 'PATCH',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify(carga),
  })
}

export async function cerrarSesion(): Promise<void> {
  const token = await obtenerTokenCsrf()
  await request<void>('/auth/logout', {
    method: 'POST',
    headers: { [token.headerName]: token.token },
  })
  csrfToken = null
}

export function listarRecompensasGlobales(criterio = ''): Promise<Recompensa[]> {
  const query = criterio.trim() ? `?criterio=${encodeURIComponent(criterio.trim())}` : ''
  return request<Recompensa[]>(`/recompensas${query}`)
}

export function listarRecompensasPropias(criterio = ''): Promise<Recompensa[]> {
  const query = criterio.trim() ? `?criterio=${encodeURIComponent(criterio.trim())}` : ''
  return request<Recompensa[]>(`/recompensas/me${query}`)
}

export function obtenerRecompensaGlobal(id: number): Promise<Recompensa> {
  return request<Recompensa>(`/recompensas/${id}`)
}

export function obtenerRecompensaPropia(id: number): Promise<Recompensa> {
  return request<Recompensa>(`/recompensas/me/${id}`)
}

export function obtenerOpcionesCreacionRecompensa(): Promise<OpcionesCreacionRecompensa> {
  return request<OpcionesCreacionRecompensa>('/recompensas/opciones-creacion')
}

export function obtenerBeneficiariosRecompensa(proyectoId: number): Promise<BeneficiarioRecompensa[]> {
  return request<BeneficiarioRecompensa[]>(`/recompensas/opciones-creacion/${proyectoId}/beneficiarios`)
}

export function obtenerTiposRecompensa(proyectoId: number, beneficiarioId: number): Promise<TipoRecompensa[]> {
  return request<TipoRecompensa[]>(`/recompensas/opciones-creacion/${proyectoId}/beneficiarios/${beneficiarioId}/tipos`)
}

export function prepararEdicionRecompensa(id: number): Promise<RecompensaEdicion> {
  return request<RecompensaEdicion>(`/recompensas/${id}/edicion`)
}

export async function crearRecompensa(datos: RecompensaRequest): Promise<Recompensa> {
  const token = await obtenerTokenCsrf()
  return request<Recompensa>('/recompensas', {
    method: 'POST',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify(datos),
  })
}

export async function actualizarRecompensa(id: number, datos: RecompensaRequest): Promise<Recompensa> {
  const token = await obtenerTokenCsrf()
  return request<Recompensa>(`/recompensas/${id}`, {
    method: 'PATCH',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify(datos),
  })
}

export async function eliminarRecompensa(id: number): Promise<void> {
  const token = await obtenerTokenCsrf()
  await request<void>(`/recompensas/${id}`, {
    method: 'DELETE',
    headers: { [token.headerName]: token.token },
  })
}

export function listarProyectos(criterio = '', archivados = false): Promise<Proyecto[]> {
  const query = new URLSearchParams()
  if (criterio.trim()) query.set('criterio', criterio.trim())
  if (archivados) query.set('archivados', 'true')
  const suffix = query.size ? `?${query.toString()}` : ''
  return request<Proyecto[]>(`/proyectos${suffix}`)
}

export function obtenerProyecto(id: number): Promise<Proyecto> {
  return request<Proyecto>(`/proyectos/${id}`)
}

export async function crearProyecto(datos: ProyectoRequest): Promise<Proyecto> {
  const token = await obtenerTokenCsrf()
  return request<Proyecto>('/proyectos', {
    method: 'POST',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify(datos),
  })
}

export async function actualizarProyecto(id: number, datos: ProyectoRequest): Promise<Proyecto> {
  const token = await obtenerTokenCsrf()
  return request<Proyecto>(`/proyectos/${id}`, {
    method: 'PATCH',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify(datos),
  })
}

export async function archivarProyecto(id: number, motivo: string): Promise<Proyecto> {
  const token = await obtenerTokenCsrf()
  return request<Proyecto>(`/proyectos/${id}/archivado`, {
    method: 'PATCH',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify({ motivo }),
  })
}

export function listarCandidatosProyecto(id: number): Promise<InvestigadorProyecto[]> {
  return request<InvestigadorProyecto[]>(`/proyectos/${id}/candidatos`)
}

export async function agregarInvestigadorProyecto(id: number, investigadorId: number): Promise<Proyecto> {
  const token = await obtenerTokenCsrf()
  return request<Proyecto>(`/proyectos/${id}/investigadores/${investigadorId}`, {
    method: 'POST',
    headers: { [token.headerName]: token.token },
  })
}

export async function retirarInvestigadorProyecto(
  id: number,
  investigadorId: number,
  motivo: string,
): Promise<Proyecto> {
  const token = await obtenerTokenCsrf()
  return request<Proyecto>(`/proyectos/${id}/investigadores/${investigadorId}/desasignado`, {
    method: 'PATCH',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify({ motivo }),
  })
}

export function listarArchivosProyecto(proyectoId: number): Promise<ArchivoProyecto[]> {
  return request<ArchivoProyecto[]>(`/proyectos/${proyectoId}/archivos`)
}

export async function subirArchivoProyecto(proyectoId: number, archivo: File): Promise<ArchivoProyecto> {
  const token = await obtenerTokenCsrf()
  const datos = new FormData()
  datos.append('archivo', archivo)
  const response = await fetch(`${API_URL}/proyectos/${proyectoId}/archivos`, {
    method: 'POST',
    credentials: 'include',
    headers: { [token.headerName]: token.token },
    body: datos,
  })
  if (!response.ok) {
    const error = await response.json().catch(() => ({ mensaje: 'No se pudo subir el archivo.' }))
    throw new Error(error.mensaje ?? 'No se pudo subir el archivo.')
  }
  return response.json() as Promise<ArchivoProyecto>
}

export async function descargarArchivoProyecto(proyectoId: number, archivo: ArchivoProyecto): Promise<void> {
  const response = await fetch(`${API_URL}/proyectos/${proyectoId}/archivos/${archivo.id}`, {
    credentials: 'include',
  })
  if (!response.ok) throw new Error('No se pudo descargar el archivo.')
  const enlace = document.createElement('a')
  enlace.href = URL.createObjectURL(await response.blob())
  enlace.download = archivo.nombre
  enlace.click()
  URL.revokeObjectURL(enlace.href)
}

export async function eliminarArchivoProyecto(proyectoId: number, archivoId: number): Promise<void> {
  const token = await obtenerTokenCsrf()
  await request<void>(`/proyectos/${proyectoId}/archivos/${archivoId}`, {
    method: 'DELETE',
    headers: { [token.headerName]: token.token },
  })
}

export function listarEntregables(proyectoId: number): Promise<Entregable[]> {
  return request<Entregable[]>(`/proyectos/${proyectoId}/entregables`)
}

export function obtenerEntregable(id: number): Promise<Entregable> {
  return request<Entregable>(`/entregables/${id}`)
}

async function enviarEntregable(
  path: string,
  method: 'POST' | 'PATCH',
  datos: EntregableRequest,
  archivo?: File | null,
): Promise<Entregable> {
  const token = await obtenerTokenCsrf()
  const cuerpo = new FormData()
  cuerpo.append('datos', new Blob([JSON.stringify(datos)], { type: 'application/json' }))
  if (archivo) cuerpo.append('archivo', archivo)
  const response = await fetch(`${API_URL}${path}`, {
    method,
    credentials: 'include',
    headers: { [token.headerName]: token.token },
    body: cuerpo,
  })
  if (!response.ok) {
    const error = await response.json().catch(() => ({ mensaje: 'No se pudo guardar el entregable.' }))
    throw new Error(error.mensaje ?? 'No se pudo guardar el entregable.')
  }
  return response.json() as Promise<Entregable>
}

export function crearEntregable(
  proyectoId: number,
  datos: EntregableRequest,
  archivo?: File | null,
): Promise<Entregable> {
  return enviarEntregable(`/proyectos/${proyectoId}/entregables`, 'POST', datos, archivo)
}

export function actualizarEntregable(
  id: number,
  datos: EntregableRequest,
  archivo?: File | null,
): Promise<Entregable> {
  return enviarEntregable(`/entregables/${id}`, 'PATCH', datos, archivo)
}

export async function retirarEntregable(id: number, motivo: string): Promise<Entregable> {
  const token = await obtenerTokenCsrf()
  return request<Entregable>(`/entregables/${id}/retirada`, {
    method: 'PATCH',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify({ motivo }),
  })
}

export async function descargarArchivoEntregable(entregableId: number, archivo: ArchivoEntregable): Promise<void> {
  const response = await fetch(`${API_URL}/entregables/${entregableId}/archivos/${archivo.id}`, {
    credentials: 'include',
  })
  if (!response.ok) throw new Error('No se pudo descargar la version.')
  const enlace = document.createElement('a')
  enlace.href = URL.createObjectURL(await response.blob())
  enlace.download = archivo.nombre
  enlace.click()
  URL.revokeObjectURL(enlace.href)
}

export function listarInvestigadores(criterio = '', proyectoId?: number): Promise<InvestigadorResumen[]> {
  const query = new URLSearchParams()
  if (criterio.trim()) query.set('criterio', criterio.trim())
  if (proyectoId) query.set('proyectoId', String(proyectoId))
  const suffix = query.size ? `?${query.toString()}` : ''
  return request<InvestigadorResumen[]>(`/investigadores${suffix}`)
}

export function obtenerInvestigador(id: number, proyectoId?: number): Promise<InvestigadorDetalle> {
  const suffix = proyectoId ? `?proyectoId=${proyectoId}` : ''
  return request<InvestigadorDetalle>(`/investigadores/${id}${suffix}`)
}

export async function crearInvestigador(datos: InvestigadorCreateRequest): Promise<InvestigadorDetalle> {
  const token = await obtenerTokenCsrf()
  return request<InvestigadorDetalle>('/investigadores', {
    method: 'POST',
    headers: { [token.headerName]: token.token },
    body: JSON.stringify(datos),
  })
}

export function listarPublicaciones(criterio = '', propias = false): Promise<Publicacion[]> {
  const query = criterio.trim() ? `?criterio=${encodeURIComponent(criterio.trim())}` : ''
  return request<Publicacion[]>(`/publicaciones${propias ? '/me' : ''}${query}`)
}

export function obtenerPublicacion(id: number, propia = false): Promise<Publicacion> {
  return request<Publicacion>(`/publicaciones${propia ? '/me' : ''}/${id}`)
}

async function enviarPublicacion(path: string, method: 'POST' | 'PATCH', datos: PublicacionRequest, archivo?: File | null) {
  const token = await obtenerTokenCsrf()
  const cuerpo = new FormData()
  cuerpo.append('datos', new Blob([JSON.stringify(datos)], { type: 'application/json' }))
  if (archivo) cuerpo.append('archivo', archivo)
  const response = await fetch(`${API_URL}${path}`, { method, credentials: 'include', headers: { [token.headerName]: token.token }, body: cuerpo })
  if (!response.ok) {
    const error = await response.json().catch(() => ({ mensaje: 'No se pudo guardar la publicación.' }))
    throw new Error(error.mensaje ?? 'No se pudo guardar la publicación.')
  }
  return response.json() as Promise<Publicacion>
}

export function crearPublicacion(datos: PublicacionRequest, archivo?: File | null) {
  return enviarPublicacion('/publicaciones', 'POST', datos, archivo)
}

export function actualizarPublicacion(id: number, datos: PublicacionRequest, archivo?: File | null) {
  return enviarPublicacion(`/publicaciones/${id}`, 'PATCH', datos, archivo)
}

export async function responderPublicacion(id: number, contenido: string): Promise<Publicacion> {
  const token = await obtenerTokenCsrf()
  return request<Publicacion>(`/publicaciones/${id}/respuestas`, {
    method: 'POST', headers: { [token.headerName]: token.token }, body: JSON.stringify({ contenido }),
  })
}

export async function retirarPublicacion(id: number, motivo: string): Promise<void> {
  const token = await obtenerTokenCsrf()
  await request<void>(`/publicaciones/${id}`, {
    method: 'DELETE', headers: { [token.headerName]: token.token }, body: JSON.stringify({ motivo }),
  })
}

export async function descargarArchivoPublicacion(publicacionId: number, archivo: ArchivoPublicacion): Promise<void> {
  const response = await fetch(`${API_URL}/publicaciones/${publicacionId}/archivos/${archivo.id}`, { credentials: 'include' })
  if (!response.ok) throw new Error('No se pudo descargar el archivo.')
  const enlace = document.createElement('a')
  enlace.href = URL.createObjectURL(await response.blob())
  enlace.download = archivo.nombre
  enlace.click()
  URL.revokeObjectURL(enlace.href)
}

