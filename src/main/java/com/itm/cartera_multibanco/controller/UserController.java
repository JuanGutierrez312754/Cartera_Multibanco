package com.itm.cartera_multibanco.controller;

import com.itm.cartera_multibanco.model.User;
import com.itm.cartera_multibanco.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> obtenerUsuarios() {
        return userService.listarTodo();
    }

    @GetMapping("/{cedula}")
    public User buscarPorCedula(@PathVariable String cedula) {
        return userService.buscarPorCedula(cedula);
    }

    @PostMapping
    public String guardarUsuario(@Valid @RequestBody User usuario) {
        return userService.crearUsuario(usuario);
    }

    @PutMapping("/{cedula}")
    public String actualizar(@PathVariable String cedula, @Valid @RequestBody User usuario) {
        return userService.actualizarUsuario(cedula, usuario);
    }

    @DeleteMapping("/{cedula}")
    public String eliminar(@PathVariable String cedula) {
        return userService.eliminarUsuario(cedula);
    }
}