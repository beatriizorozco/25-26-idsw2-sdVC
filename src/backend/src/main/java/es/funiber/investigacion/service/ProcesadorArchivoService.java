package es.funiber.investigacion.service;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProcesadorArchivoService {

    private static final long TAMANO_MAXIMO = 15L * 1024 * 1024;

    public ContenidoArchivo procesarObligatorio(MultipartFile fichero) {
        if (fichero == null || fichero.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionarse un archivo.");
        }
        return procesar(fichero);
    }

    public ContenidoArchivo procesar(MultipartFile fichero) {
        if (fichero.getSize() > TAMANO_MAXIMO) {
            throw new IllegalArgumentException("El archivo no puede superar los 15 MB.");
        }
        try {
            return new ContenidoArchivo(
                    limpiarNombre(fichero.getOriginalFilename()),
                    fichero.getContentType() == null ? "application/octet-stream" : fichero.getContentType(),
                    fichero.getBytes());
        } catch (IOException exception) {
            throw new IllegalArgumentException("No se pudo leer el archivo seleccionado.");
        }
    }

    private String limpiarNombre(String original) {
        String nombre = original == null ? "archivo" : original.replace("\\", "/");
        nombre = nombre.substring(nombre.lastIndexOf('/') + 1).trim();
        return nombre.isBlank() ? "archivo" : nombre.substring(0, Math.min(nombre.length(), 240));
    }
}
