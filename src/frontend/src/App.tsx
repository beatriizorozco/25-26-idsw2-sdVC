import { useEffect, useState } from 'react'
import { ConfirmarCierreSesionModal } from './components/ConfirmarCierreSesionModal'
import { cerrarSesion, iniciarSesion, obtenerPanelPrincipal, obtenerSesion } from './services/api'
import { LoginPage } from './pages/LoginPage'
import { PanelPrincipalPage } from './pages/PanelPrincipalPage'
import { PerfilPage } from './pages/PerfilPage'
import { CargaTrabajoPage } from './pages/CargaTrabajoPage'
import type { PanelPrincipal, Sesion } from './types'

type Vista = 'panel' | 'perfil' | 'carga-trabajo'

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

