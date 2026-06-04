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

export interface CargaTrabajoPersona {
  perfilId: number
  usuario: string
  nombreCompleto: string
  rol: Rol
  sede: string
  investigadorDocente: boolean
  horasDocencia: number
  horasInvestigacion: number
  horasGestionAcademica: number
  totalSemanal: number
  margenDocente: number
  observaciones: string
}

export interface CargaTrabajoUpdate {
  horasDocencia: number
  horasInvestigacion: number
  horasGestionAcademica: number
  observaciones: string
}

export interface ProyectoLibre {
  codigo: string
  nombre: string
  sede: string
  area: string
}

export interface SugerenciaAsignacion {
  proyecto: ProyectoLibre
  candidatos: CargaTrabajoPersona[]
}

export interface PanelCargaTrabajo {
  maximoDocenteSemanal: number
  cargas: CargaTrabajoPersona[]
  proyectosLibres: ProyectoLibre[]
  sugerencias: SugerenciaAsignacion[]
}

