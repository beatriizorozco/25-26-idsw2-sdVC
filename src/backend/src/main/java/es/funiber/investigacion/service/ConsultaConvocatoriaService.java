package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.ConvocatoriaResponse;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.ConvocatoriaRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsultaConvocatoriaService {
    private final ConvocatoriaRepository convocatorias;
    private final UsuarioRepository usuarios;
    private final PoliticaConvocatoria politica;
    public ConsultaConvocatoriaService(ConvocatoriaRepository convocatorias, UsuarioRepository usuarios, PoliticaConvocatoria politica) {
        this.convocatorias = convocatorias; this.usuarios = usuarios; this.politica = politica;
    }
    @Transactional(readOnly = true)
    public List<ConvocatoriaResponse> listar(String nombreUsuario, String texto, String area, String estado) {
        politica.exigirConsulta(usuario(nombreUsuario));
        String fTexto = normalizar(texto), fArea = normalizar(area), fEstado = normalizar(estado);
        return convocatorias.findAllByOrderByFechaCierreAsc().stream()
                .filter(c -> fTexto.isBlank() || normalizar(c.getTitulo()).contains(fTexto)
                        || normalizar(c.getEntidadConvocante()).contains(fTexto)
                        || normalizar(c.getDescripcion()).contains(fTexto))
                .filter(c -> fArea.isBlank() || normalizar(c.getArea()).contains(fArea))
                .filter(c -> fEstado.isBlank() || normalizar(c.getEstado()).equals(fEstado))
                .map(ConvocatoriaResponse::desde).toList();
    }
    @Transactional(readOnly = true)
    public ConvocatoriaResponse obtener(String nombreUsuario, Long id) {
        politica.exigirConsulta(usuario(nombreUsuario));
        return ConvocatoriaResponse.desde(convocatorias.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro la convocatoria solicitada.")));
    }
    private Usuario usuario(String nombre) { return usuarios.findByNombreUsuario(nombre).filter(Usuario::isActivo).orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el perfil solicitado.")); }
    private String normalizar(String texto) { return texto == null ? "" : texto.trim().toLowerCase(); }
}
