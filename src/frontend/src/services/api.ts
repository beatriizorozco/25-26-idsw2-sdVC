import type { CsrfToken, PanelPrincipal, Sesion } from '../types'

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

async function tokenCsrf(): Promise<CsrfToken> {
  return csrfToken ?? obtenerTokenCsrf()
}

export async function iniciarSesion(usuario: string, contrasena: string): Promise<Sesion> {
  const token = await tokenCsrf()
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

export async function cerrarSesion(): Promise<void> {
  const token = await tokenCsrf()
  await request<void>('/auth/logout', {
    method: 'POST',
    headers: { [token.headerName]: token.token },
  })
  csrfToken = null
}

