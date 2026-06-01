import { LogOut, X } from 'lucide-react'

interface Props {
  onCancelar: () => void
  onConfirmar: () => void
  procesando: boolean
}

export function ConfirmarCierreSesionModal({ onCancelar, onConfirmar, procesando }: Props) {
  return (
    <div className="modal-backdrop" role="presentation">
      <section className="modal" role="dialog" aria-modal="true" aria-labelledby="cerrar-sesion-titulo">
        <button className="icon-button modal-close" type="button" onClick={onCancelar} aria-label="Cerrar">
          <X size={18} />
        </button>
        <div className="modal-icon">
          <LogOut size={22} />
        </div>
        <h2 id="cerrar-sesion-titulo">Cerrar sesión</h2>
        <p>¿Deseas cerrar tu sesión actual?</p>
        <div className="modal-actions">
          <button className="secondary-button" type="button" onClick={onCancelar} disabled={procesando}>
            Cancelar
          </button>
          <button className="danger-button" type="button" onClick={onConfirmar} disabled={procesando}>
            <LogOut size={17} />
            {procesando ? 'Cerrando...' : 'Cerrar sesión'}
          </button>
        </div>
      </section>
    </div>
  )
}

