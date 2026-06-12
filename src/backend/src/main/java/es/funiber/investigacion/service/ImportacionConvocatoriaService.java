package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.*;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.model.*;
import es.funiber.investigacion.repository.ConvocatoriaRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImportacionConvocatoriaService {
    private final RegistroImportadoresConvocatoria registro;
    private final ValidadorConvocatoria validador;
    private final ConvocatoriaRepository convocatorias;
    private final UsuarioRepository usuarios;
    private final PoliticaConvocatoria politica;
    public ImportacionConvocatoriaService(RegistroImportadoresConvocatoria registro, ValidadorConvocatoria validador,
            ConvocatoriaRepository convocatorias, UsuarioRepository usuarios, PoliticaConvocatoria politica) {
        this.registro = registro; this.validador = validador; this.convocatorias = convocatorias; this.usuarios = usuarios; this.politica = politica;
    }
    @Transactional(readOnly = true)
    public PrevisualizacionConvocatoriaResponse previsualizar(String nombreUsuario, FuenteConvocatoriaRequest fuente) {
        politica.exigirImportacion(usuario(nombreUsuario));
        DatosConvocatoria datos = registro.obtenerCompatible(fuente).extraer(fuente);
        validador.validar(datos);
        String mensaje = validador.yaIncorporada(datos.referenciaExterna())
                ? "Fuente validada. Confirma para actualizar la convocatoria ya incorporada."
                : "Fuente validada. Confirma para incorporar la convocatoria.";
        return new PrevisualizacionConvocatoriaResponse(DatosConvocatoriaRequest.desde(datos), mensaje);
    }
    @Transactional
    public ConvocatoriaResponse confirmar(String nombreUsuario, ConfirmarImportacionConvocatoriaRequest request) {
        Usuario actor = usuario(nombreUsuario);
        politica.exigirImportacion(actor);
        if (request == null || request.datos() == null) throw new IllegalArgumentException("No hay una previsualizacion para confirmar.");
        DatosConvocatoria datos = request.datos().aModelo();
        validador.validar(datos);
        Convocatoria convocatoria = convocatorias.findByReferenciaExterna(datos.referenciaExterna())
                .map(existente -> { existente.actualizarImportacion(datos, actor); return existente; })
                .orElseGet(() -> new Convocatoria(datos, actor));
        return ConvocatoriaResponse.desde(convocatorias.save(convocatoria));
    }
    private Usuario usuario(String nombre) { return usuarios.findByNombreUsuario(nombre).filter(Usuario::isActivo).orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el perfil solicitado.")); }
}
