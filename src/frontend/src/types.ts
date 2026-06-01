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

