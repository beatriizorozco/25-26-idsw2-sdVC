package es.funiber.investigacion.service;

import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.model.Publicacion;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PoliticaPublicacion {
    public void exigirGestion(Usuario actor, Publicacion publicacion) {
        if (actor.getRol() != Rol.COORDINADOR && !esAutor(actor, publicacion)) {
            throw new AccesoNoPermitidoException();
        }
    }
    public void exigirAutoria(Usuario actor, Publicacion publicacion) {
        if (!esAutor(actor, publicacion)) throw new AccesoNoPermitidoException();
    }
    private boolean esAutor(Usuario actor, Publicacion publicacion) {
        return publicacion.getAutor().getId().equals(actor.getId());
    }
}
