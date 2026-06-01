import { useState } from 'react'

interface Props {
  compact?: boolean
}

export function BrandLogo({ compact = false }: Props) {
  const [logoDisponible, setLogoDisponible] = useState(true)

  return (
    <span className={`brand-logo${compact ? ' compact' : ''}`}>
      {logoDisponible && (
        <img
          src="/gipf-logo.png"
          alt="GIPF"
          onError={() => setLogoDisponible(false)}
        />
      )}
      {!logoDisponible && <span className="brand-logo-fallback">GIPF</span>}
    </span>
  )
}
