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
    private final PoliticaConvocatoria politica;
    private final AccesoUsuarioService accesoUsuarios;
    public ConsultaConvocatoriaService(ConvocatoriaRepository convocatorias, PoliticaConvocatoria politica, AccesoUsuarioService accesoUsuarios) {
        this.convocatorias = convocatorias; this.politica = politica; this.accesoUsuarios = accesoUsuarios;
    }
    @Transactional(readOnly = true)
    public List<ConvocatoriaResponse> listar(String nombreUsuario, String texto, String area, String estado) {
        politica.exigirConsulta(accesoUsuarios.buscarActivo(nombreUsuario));
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
        politica.exigirConsulta(accesoUsuarios.buscarActivo(nombreUsuario));
        return ConvocatoriaResponse.desde(convocatorias.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro la convocatoria solicitada.")));
    }
    private String normalizar(String texto) { return texto == null ? "" : texto.trim().toLowerCase(); }
}
