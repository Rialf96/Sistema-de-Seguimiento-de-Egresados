package com.universidad.egresados.controller;

import com.universidad.egresados.model.Usuario;
import com.universidad.egresados.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilController {

    private final UsuarioService usuarioService;

    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model, Authentication authentication) {
        String username = authentication.getName(); // obtiene el nombre del usuario logueado
        Usuario usuario = usuarioService.obtenerUsuarioPorUsername(username).orElse(null);

        if (usuario == null) {
            return "redirect:/login?error"; // si no encuentra el usuario, redirige al login
        }

        // Adaptar los atributos al HTML
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuario.nombreCompleto", usuario.getNombre());
        model.addAttribute("usuario.nombreUsuario", usuario.getUsername());
        model.addAttribute("usuario.email", usuario.getEmail());
        model.addAttribute("usuario.fotografia", "/img/default-user.png"); // cambia esto si usas fotos reales

        return "perfil"; // thymeleaf buscará "perfil.html"
    }
}
