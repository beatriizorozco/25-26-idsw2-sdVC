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

export type TipoRecompensa = 'ECONOMICA' | 'REDUCCION_DOCENTE'

export interface Recompensa {
  id: number
  proyectoId: number
  proyectoCodigo: string
  proyectoNombre: string
  beneficiarioId: number
  beneficiario: string
  beneficiarioNombre: string
  tipo: TipoRecompensa
  tipoEtiqueta: string
  concepto: string
  valor: number
  fechaCreacion: string
}

export interface RecompensaRequest {
  proyectoId?: number
  beneficiarioId: number
  tipo: TipoRecompensa
  concepto: string
  valor: number
}

export interface ProyectoRecompensa {
  id: number
  codigo: string
  nombre: string
}

export interface BeneficiarioRecompensa {
  id: number
  usuario: string
  nombreCompleto: string
  sede: string
  investigadorDocente: boolean
  tiposPermitidos: TipoRecompensa[]
}

export interface OpcionesCreacionRecompensa {
  proyectos: ProyectoRecompensa[]
}

export interface RecompensaEdicion {
  recompensa: Recompensa
  beneficiarios: BeneficiarioRecompensa[]
}

