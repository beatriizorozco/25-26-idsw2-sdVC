package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.CsrfTokenResponse;
import es.funiber.investigacion.dto.LoginRequest;
import es.funiber.investigacion.dto.SesionResponse;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.service.AuthService;
import es.funiber.investigacion.service.SesionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final SesionService sesionService;

    public AuthController(AuthService authService, SesionService sesionService) {
        this.authService = authService;
        this.sesionService = sesionService;
    }

    @GetMapping("/csrf")
    public CsrfTokenResponse obtenerTokenCsrf(CsrfToken csrfToken) {
        return new CsrfTokenResponse(csrfToken.getHeaderName(), csrfToken.getToken());
    }

    @PostMapping("/login")
    public SesionResponse iniciarSesion(
            @Valid @RequestBody LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response) {
        Usuario usuario = authService.autenticar(loginRequest.usuario(), loginRequest.contrasena());
        return sesionService.crearSesion(usuario, request, response);
    }

    @GetMapping("/me")
    public SesionResponse obtenerSesion(Authentication authentication) {
        return sesionService.obtenerSesionActual(authentication);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cerrarSesion(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        sesionService.cerrarSesion(request, response, authentication);
    }
}

