package com.universidad.egresados.controller;

import com.universidad.egresados.model.Rol;
import com.universidad.egresados.model.Usuario;
import com.universidad.egresados.service.RolService;
import com.universidad.egresados.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ViewController {
    private final UsuarioService usuarioService;
    private final RolService rolService;

    public ViewController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Vista 'login.html' en el directorio de plantillas
    }

    @GetMapping("/registro")
    public String showRegistroPage(Model model) {
        model.addAttribute("usuario", new Usuario());

        // Obtener la lista de roles de la base de datos
        List<Rol> roles = rolService.findAll();
        model.addAttribute("roles", roles);  // Pasar la lista de roles al modelo

        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        boolean registrado = usuarioService.registrarUsuario(usuario);
        if (!registrado) {
            model.addAttribute("error", "El usuario o email ya están en uso.");
            return "registro";
        }
        return "redirect:/login";
    }
}
