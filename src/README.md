# Código fuente

## Aplicaciones

- [`backend`](backend/): API REST Java con Spring Boot.
- [`frontend`](frontend/): SPA React con TypeScript y Vite.

## Ejecución local

### Backend

```powershell
cd src/backend
.\mvnw.cmd spring-boot:run
```

### Frontend

```powershell
cd src/frontend
npm install
npm run dev
```

La interfaz queda disponible en `http://127.0.0.1:5173`.
