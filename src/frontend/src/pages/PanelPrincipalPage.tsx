import {
  Award,
  BookOpenText,
  BriefcaseBusiness,
  CalendarDays,
  ChevronRight,
  FolderKanban,
  LogOut,
  UserRound,
  UsersRound,
} from 'lucide-react'
import type { AccionPanel, PanelPrincipal, Sesion } from '../types'
import { BrandLogo } from '../components/BrandLogo'

interface Props {
  sesion: Sesion
  panel: PanelPrincipal
  onCerrarSesion: () => void
  onAbrirModulo: (codigo: string) => void
}

const icons = {
  perfil: UserRound,
  'carga-trabajo': BriefcaseBusiness,
  proyectos: FolderKanban,
  investigadores: UsersRound,
  'mis-publicaciones': BookOpenText,
  publicaciones: BookOpenText,
  convocatorias: CalendarDays,
  recompensas: Award,
}

function Accion({ accion, onAbrirModulo }: { accion: AccionPanel; onAbrirModulo: (codigo: string) => void }) {
  const Icon = icons[accion.codigo as keyof typeof icons] ?? ChevronRight
  return (
    <button className="action-row" type="button" title={`Abrir ${accion.etiqueta}`} onClick={() => onAbrirModulo(accion.codigo)}>
      <span className="action-icon"><Icon size={20} /></span>
      <span>
        <strong>{accion.etiqueta}</strong>
        <small>Acceso al módulo</small>
      </span>
      <ChevronRight className="action-chevron" size={19} />
    </button>
  )
}

export function PanelPrincipalPage({ sesion, panel, onCerrarSesion, onAbrirModulo }: Props) {
  const rol = sesion.rol === 'COORDINADOR' ? 'Coordinador' : 'Investigador'

  return (
    <main className="app-shell">
      <header className="app-header">
        <div className="brand-inline">
          <BrandLogo compact />
          <span>
            <strong>GIPF</strong>
            <small>Gestor de Investigación</small>
          </span>
        </div>
        <div className="header-actions">
          <div className="identity">
            <span className="avatar"><UserRound size={17} /></span>
            <span>
              <strong>{sesion.usuario}</strong>
              <small>{rol}</small>
            </span>
          </div>
          <button className="icon-button" type="button" onClick={onCerrarSesion} title="Cerrar sesión" aria-label="Cerrar sesión">
            <LogOut size={18} />
          </button>
        </div>
      </header>

      <section className="workspace">
        <div className="workspace-heading">
          <p className="eyebrow">Panel principal</p>
          <h1>Buenos días, {sesion.usuario}</h1>
          <p>Selecciona un área de trabajo para continuar.</p>
        </div>

        <section className="module-grid" aria-label="Áreas disponibles">
          {panel.acciones.map((accion) => (
            <Accion key={accion.codigo} accion={accion} onAbrirModulo={onAbrirModulo} />
          ))}
        </section>
      </section>
    </main>
  )
}
