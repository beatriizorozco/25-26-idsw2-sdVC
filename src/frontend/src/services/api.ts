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

