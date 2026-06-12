import { useEffect, useState } from 'react'
import { ConfirmarCierreSesionModal } from './components/ConfirmarCierreSesionModal'
import { cerrarSesion, iniciarSesion, obtenerPanelPrincipal, obtenerSesion } from './services/api'
import { LoginPage } from './pages/LoginPage'
import { PanelPrincipalPage } from './pages/PanelPrincipalPage'
import { PerfilPage } from './pages/PerfilPage'
import { CargaTrabajoPage } from './pages/CargaTrabajoPage'
import { RecompensasPage } from './pages/RecompensasPage'
import { ProyectosPage } from './pages/ProyectosPage'
import { InvestigadoresPage } from './pages/InvestigadoresPage'
import { PublicacionesPage } from './pages/PublicacionesPage'
import { ConvocatoriasPage } from './pages/ConvocatoriasPage'
import type { PanelPrincipal, Sesion } from './types'

type Vista = 'panel' | 'perfil' | 'carga-trabajo' | 'recompensas' | 'proyectos' | 'investigadores' | 'publicaciones' | 'mis-publicaciones' | 'convocatorias'

export default function App() {
  const [sesion, setSesion] = useState<Sesion | null>(null)
  const [panel, setPanel] = useState<PanelPrincipal | null>(null)
  const [cargando, setCargando] = useState(true)
  const [confirmandoCierre, setConfirmandoCierre] = useState(false)
  const [cerrando, setCerrando] = useState(false)
  const [vista, setVista] = useState<Vista>('panel')

  useEffect(() => {
    async function recuperarSesion() {
      try {
        const sesionActual = await obtenerSesion()
        const panelActual = await obtenerPanelPrincipal()
        setSesion(sesionActual)
        setPanel(panelActual)
      } catch {
        setSesion(null)
        setPanel(null)
      } finally {
        setCargando(false)
      }
    }
    void recuperarSesion()
  }, [])

  async function acceder(usuario: string, contrasena: string) {
    const sesionActual = await iniciarSesion(usuario, contrasena)
    const panelActual = await obtenerPanelPrincipal()
    setSesion(sesionActual)
    setPanel(panelActual)
  }

  async function confirmarCierre() {
    setCerrando(true)
    try {
      await cerrarSesion()
      setSesion(null)
      setPanel(null)
      setVista('panel')
      setConfirmandoCierre(false)
    } finally {
      setCerrando(false)
    }
  }

  if (cargando) {
    return <main className="loading-screen">Cargando...</main>
  }

  if (!sesion || !panel) {
    return <LoginPage onLogin={acceder} />
  }

  return (
    <>
      {vista === 'panel' && (
        <PanelPrincipalPage
          sesion={sesion}
          panel={panel}
          onCerrarSesion={() => setConfirmandoCierre(true)}
          onAbrirModulo={(codigo) => {
            if (codigo === 'perfil') {
              setVista('perfil')
            }
            if (codigo === 'carga-trabajo') {
              setVista('carga-trabajo')
            }
            if (codigo === 'recompensas') {
              setVista('recompensas')
            }
            if (codigo === 'proyectos') {
              setVista('proyectos')
            }
            if (codigo === 'investigadores') {
              setVista('investigadores')
            }
            if (codigo === 'publicaciones' || codigo === 'mis-publicaciones') setVista(codigo)
            if (codigo === 'convocatorias') setVista('convocatorias')
          }}
        />
      )}
      {vista === 'perfil' && (
        <PerfilPage
          rol={sesion.rol}
          onVolver={() => setVista('panel')}
          onSesionCerrada={() => {
            setSesion(null)
            setPanel(null)
            setVista('panel')
          }}
        />
      )}
      {vista === 'carga-trabajo' && (
        <CargaTrabajoPage
          rol={sesion.rol}
          onVolver={() => setVista('panel')}
        />
      )}
      {vista === 'recompensas' && (
        <RecompensasPage
          rol={sesion.rol}
          onVolver={() => setVista('panel')}
        />
      )}
      {vista === 'proyectos' && (
        <ProyectosPage rol={sesion.rol} onVolver={() => setVista('panel')} />
      )}
      {vista === 'investigadores' && (
        <InvestigadoresPage rol={sesion.rol} onVolver={() => setVista('panel')} />
      )}
      {(vista === 'publicaciones' || vista === 'mis-publicaciones') && (
        <PublicacionesPage rol={sesion.rol} propias={vista === 'mis-publicaciones'} onVolver={() => setVista('panel')} />
      )}
      {vista === 'convocatorias' && (
        <ConvocatoriasPage onVolver={() => setVista('panel')} />
      )}
      {confirmandoCierre && (
        <ConfirmarCierreSesionModal
          procesando={cerrando}
          onCancelar={() => setConfirmandoCierre(false)}
          onConfirmar={() => void confirmarCierre()}
        />
      )}
    </>
  )
}

