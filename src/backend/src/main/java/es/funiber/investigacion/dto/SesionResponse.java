package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Rol;

public record SesionResponse(String usuario, Rol rol) {
}

