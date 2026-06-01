package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.SesionResponse;
import es.funiber.investigacion.exception.SesionNoValidaException;
import es.funiber.investigacion.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class SesionService {

    private static final String ROLE_PREFIX = "ROLE_";

    public SesionResponse crearSesion(
            Usuario usuario,
            HttpServletRequest request,
            HttpServletResponse response) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                usuario.getNombreUsuario(),
                null,
                java.util.List.of(new SimpleGrantedAuthority(ROLE_PREFIX + usuario.getRol().name())));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        new HttpSessionSecurityContextRepository().saveContext(context, request, response);

        return new SesionResponse(usuario.getNombreUsuario(), usuario.getRol());
    }

    public SesionResponse obtenerSesionActual(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SesionNoValidaException();
        }

        String authority = authentication.getAuthorities().stream()
                .findFirst()
                .orElseThrow(SesionNoValidaException::new)
                .getAuthority();
        return new SesionResponse(
                authentication.getName(),
                es.funiber.investigacion.model.Rol.valueOf(authority.substring(ROLE_PREFIX.length())));
    }

    public void cerrarSesion(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
}

