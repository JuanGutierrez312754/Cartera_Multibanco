package com.itm.cartera_multibanco.controller;

import com.itm.cartera_multibanco.model.User;
import com.itm.cartera_multibanco.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios") // Esta será la URL base: http://localhost:8080/usuarios
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> obtenerUsuarios() {
        return userService.listarTodo();
    }

    @PostMapping
    public String guardarUsuario(@Valid @RequestBody User usuario) {
        return userService.crearUsuario(usuario);
    }

    // 3. Endpoint para actualizar (PUT)
    @PutMapping("/{cedula}")
    public String actualizar(@PathVariable String cedula, @Valid @RequestBody User usuario) {
        return userService.actualizarUsuario(cedula, usuario);
    }

    // 4. Endpoint para eliminar (DELETE)
    @DeleteMapping("/{cedula}")
    public String eliminar(@PathVariable String cedula) {
        return userService.eliminarUsuario(cedula);
    }
}