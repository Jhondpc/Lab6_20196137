package com.example.lab6_sol.controller;

import com.example.lab6_sol.entity.Usuario;
import com.example.lab6_sol.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public String listaUsuarios(Model model){
        List<Usuario> estudiantes = usuarioRepository.findByRolid(5);
        model.addAttribute("estudiantes", estudiantes);
        return "lista_usuarios";
    }

    @GetMapping("/new")
    public String nuevoEstudiante(@ModelAttribute("usuario") Usuario usuario,
                                  Model model){
        return "edit_new_estudiante";
    }

    @GetMapping("/edit")
    public String editarEstudiante(@ModelAttribute("usuario") Usuario usuario,
                                  @RequestParam("idUsuario") int idUsuario,
                                  Model model){
        Optional<Usuario> optShipper = usuarioRepository.findById(idUsuario);

        if (optShipper.isPresent()) {
            usuario = optShipper.get();
            model.addAttribute("usuario", usuario);
            return "edit_new_estudiante";
        } else {
            return "redirect:/estudiante/lista";
        }
    }

    @PostMapping("/save")
    public String guardarEstudiante(@ModelAttribute("usuario") @Valid Usuario usuario,
                                    BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            Optional<Usuario> optShipper = usuarioRepository.findById(usuario.getId());

            if (optShipper.isPresent()) {
                usuario = optShipper.get();
                model.addAttribute("usuario", usuario);
                return "edit_new_estudiante";
            } else {
                return "redirect:/estudiante/lista";
            }
        }
        usuarioRepository.guardarEstudiante(usuario.getNombres(),usuario.getApellidos(),usuario.getDni(),usuario.getEdad(),usuario.getCorreo());
        return "redirect:/estudiante/lista";
    }

}
