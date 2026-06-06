# Código fuente

## Aplicaciones

- [`backend`](backend/): API REST Java con Spring Boot.
- [`frontend`](frontend/): SPA React con TypeScript y Vite.

## Ejecución local

```powershell
cd src/frontend
npm install
npm run dev
```

El comando comprueba el backend, lo inicia automáticamente si es necesario y
espera hasta que responda antes de arrancar Vite. La interfaz queda disponible
en `http://127.0.0.1:5173`.

Para arrancar únicamente el frontend durante una depuración aislada:

```powershell
npm run dev:frontend
```
