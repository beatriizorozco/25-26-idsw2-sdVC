import { FormEvent, useState } from 'react'
import { ArrowRight, LockKeyhole, UserRound } from 'lucide-react'
import { BrandLogo } from '../components/BrandLogo'

interface Props {
  onLogin: (usuario: string, contrasena: string) => Promise<void>
}

export function LoginPage({ onLogin }: Props) {
  const [usuario, setUsuario] = useState('')
  const [contrasena, setContrasena] = useState('')
  const [error, setError] = useState('')
  const [procesando, setProcesando] = useState(false)

  async function enviar(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setError('')
    setProcesando(true)
    try {
      await onLogin(usuario, contrasena)
    } catch (loginError) {
      setError(loginError instanceof Error ? loginError.message : 'No se pudo iniciar sesión.')
    } finally {
      setProcesando(false)
    }
  }

  return (
    <main className="login-layout">
      <section className="login-intro">
        <BrandLogo />
        <p className="eyebrow">Plataforma interna</p>
        <h1>Gestor de Investigación</h1>
        <p className="intro-copy">
          Gestión coordinada de proyectos, publicaciones y actividad investigadora de FUNIBER.
        </p>
      </section>

      <section className="login-panel" aria-labelledby="login-title">
        <div>
          <p className="eyebrow">Acceso privado</p>
          <h2 id="login-title">Iniciar sesión</h2>
          <p className="panel-copy">Introduce tus credenciales corporativas.</p>
        </div>

        <form onSubmit={enviar}>
          <label>
            Usuario
            <span className="input-wrapper">
              <UserRound size={18} />
              <input
                autoComplete="username"
                value={usuario}
                onChange={(event) => setUsuario(event.target.value)}
                placeholder="nombre.apellido"
                required
              />
            </span>
          </label>
          <label>
            Contraseña
            <span className="input-wrapper">
              <LockKeyhole size={18} />
              <input
                autoComplete="current-password"
                type="password"
                value={contrasena}
                onChange={(event) => setContrasena(event.target.value)}
                placeholder="••••••••"
                required
              />
            </span>
          </label>

          {error && <p className="form-error" role="alert">{error}</p>}

          <button className="primary-button login-button" type="submit" disabled={procesando}>
            {procesando ? 'Validando...' : 'Acceder'}
            <ArrowRight size={18} />
          </button>
        </form>
      </section>
    </main>
  )
}
