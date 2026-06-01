import { useEffect, useState } from 'react'
import { ConfirmarCierreSesionModal } from './components/ConfirmarCierreSesionModal'
import { cerrarSesion, iniciarSesion, obtenerPanelPrincipal, obtenerSesion } from './services/api'
import { LoginPage } from './pages/LoginPage'
import { PanelPrincipalPage } from './pages/PanelPrincipalPage'
import type { PanelPrincipal, Sesion } from './types'

export default function App() {
  const [sesion, setSesion] = useState<Sesion | null>(null)
  const [panel, setPanel] = useState<PanelPrincipal | null>(null)
  const [cargando, setCargando] = useState(true)
  const [confirmandoCierre, setConfirmandoCierre] = useState(false)
  const [cerrando, setCerrando] = useState(false)

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
      <PanelPrincipalPage sesion={sesion} panel={panel} onCerrarSesion={() => setConfirmandoCierre(true)} />
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

