UPDATE recompensas
SET valor = 4
WHERE tipo = 'REDUCCION_DOCENTE'
  AND (valor < 4 OR valor > 16 OR MOD(valor, 4) <> 0);

ALTER TABLE recompensas
    ADD CONSTRAINT ck_recompensa_reduccion_docente_asignaturas
    CHECK (
        tipo <> 'REDUCCION_DOCENTE'
        OR (valor BETWEEN 4 AND 16 AND MOD(valor, 4) = 0)
    );
