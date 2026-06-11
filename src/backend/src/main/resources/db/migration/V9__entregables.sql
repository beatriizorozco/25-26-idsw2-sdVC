CREATE TABLE entregables (
    id BIGSERIAL PRIMARY KEY,
    proyecto_id BIGINT NOT NULL REFERENCES proyectos(id),
    titulo VARCHAR(180) NOT NULL,
    descripcion VARCHAR(1000),
    estado VARCHAR(30) NOT NULL DEFAULT 'PRESENTADO',
    autor_id BIGINT NOT NULL REFERENCES usuarios(id),
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    retirado BOOLEAN NOT NULL DEFAULT FALSE,
    fecha_retirada TIMESTAMP,
    retirado_por_id BIGINT REFERENCES usuarios(id),
    motivo_retirada VARCHAR(500)
);

CREATE TABLE archivos_entregable (
    id BIGSERIAL PRIMARY KEY,
    entregable_id BIGINT NOT NULL REFERENCES entregables(id),
    version INTEGER NOT NULL,
    nombre VARCHAR(240) NOT NULL,
    tipo_contenido VARCHAR(160) NOT NULL,
    tamano BIGINT NOT NULL,
    contenido BYTEA NOT NULL,
    subido_por_id BIGINT NOT NULL REFERENCES usuarios(id),
    fecha_subida TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_archivo_entregable_version UNIQUE (entregable_id, version)
);

CREATE INDEX idx_entregables_proyecto ON entregables(proyecto_id);
CREATE INDEX idx_entregables_autor ON entregables(autor_id);
CREATE INDEX idx_archivos_entregable ON archivos_entregable(entregable_id);
