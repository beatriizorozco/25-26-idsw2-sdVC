package es.funiber.investigacion.service;

import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PoliticaConvocatoria {
    public void exigirConsulta(Usuario actor) { exigirCoordinador(actor); }
    public void exigirImportacion(Usuario actor) { exigirCoordinador(actor); }
    private void exigirCoordinador(Usuario actor) {
        if (actor.getRol() != Rol.COORDINADOR) throw new AccesoNoPermitidoException();
    }
}
