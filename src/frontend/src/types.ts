export type Rol = 'COORDINADOR' | 'INVESTIGADOR'

export interface Sesion {
  usuario: string
  rol: Rol
}

export interface AccionPanel {
  codigo: string
  etiqueta: string
  ruta: string
}

export interface PanelPrincipal {
  rol: Rol
  acciones: AccionPanel[]
}

export interface CsrfToken {
  headerName: string
  token: string
}

export interface Perfil {
  id: number
  usuario: string
  rol: Rol
  nombreCompleto: string
  email: string
  unidad: string
  lineaInvestigacion: string
  biografia: string
}

export interface PerfilUpdate {
  nombreCompleto: string
  email: string
  unidad: string
  lineaInvestigacion: string
  biografia: string
}

export interface SolicitudEliminacionPerfil {
  id: number
  perfilId: number
  usuario: string
  nombreCompleto: string
  motivo: string
  estado: 'PENDIENTE' | 'RESUELTA'
  fechaCreacion: string
}

