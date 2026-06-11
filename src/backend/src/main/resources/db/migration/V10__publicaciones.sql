CREATE TABLE publicaciones (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(180) NOT NULL,
    contenido VARCHAR(4000) NOT NULL,
    autor_id BIGINT NOT NULL REFERENCES usuarios(id),
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    retirada BOOLEAN NOT NULL DEFAULT FALSE,
    fecha_retirada TIMESTAMP,
    retirado_por_id BIGINT REFERENCES usuarios(id),
    motivo_retirada VARCHAR(500)
);

CREATE TABLE respuestas_publicacion (
    id BIGSERIAL PRIMARY KEY,
    publicacion_id BIGINT NOT NULL REFERENCES publicaciones(id),
    autor_id BIGINT NOT NULL REFERENCES usuarios(id),
    contenido VARCHAR(1500) NOT NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE archivos_publicacion (
    id BIGSERIAL PRIMARY KEY,
    publicacion_id BIGINT NOT NULL REFERENCES publicaciones(id),
    nombre VARCHAR(240) NOT NULL,
    tipo_contenido VARCHAR(160) NOT NULL,
    tamano BIGINT NOT NULL,
    contenido BYTEA NOT NULL,
    subido_por_id BIGINT NOT NULL REFERENCES usuarios(id),
    fecha_subida TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_publicaciones_autor ON publicaciones(autor_id);
CREATE INDEX idx_publicaciones_retirada ON publicaciones(retirada);
CREATE INDEX idx_respuestas_publicacion ON respuestas_publicacion(publicacion_id);
CREATE INDEX idx_archivos_publicacion ON archivos_publicacion(publicacion_id);
