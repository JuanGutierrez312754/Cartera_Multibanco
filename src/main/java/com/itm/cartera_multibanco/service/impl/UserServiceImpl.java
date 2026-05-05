package com.itm.cartera_multibanco.service.impl;

import com.itm.cartera_multibanco.model.User;
import com.itm.cartera_multibanco.repository.UserRepository;
import com.itm.cartera_multibanco.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listarTodo() {
        return userRepository.findAll();
    }

    @Override
    public String crearUsuario(User usuario) {
        if (userRepository.existsByCedula(usuario.getCedula())) {
            return "Error: La cédula ya está registrada.";
        }

        if (userRepository.existsByUsername(usuario.getUsername())) {
            return "Error: El username ya está registrado.";
        }

        userRepository.save(usuario);
        return "Usuario creado con éxito.";
    }

    @Override
    public User buscarPorCedula(String cedula) {
        return userRepository.findById(cedula).orElse(null);
    }

    @Override
    public String actualizarUsuario(String cedula, User datosNuevos) {
        Optional<User> usuarioOptional = userRepository.findById(cedula);

        if (usuarioOptional.isEmpty()) {
            return "Error: El usuario con cédula " + cedula + " no existe.";
        }

        Optional<User> usuarioConMismoUsername = userRepository.findByUsername(datosNuevos.getUsername());

        if (usuarioConMismoUsername.isPresent()
                && !usuarioConMismoUsername.get().getCedula().equals(cedula)) {
            return "Error: El username ya está registrado.";
        }

        User usuarioActual = usuarioOptional.get();
        usuarioActual.setNombreCompleto(datosNuevos.getNombreCompleto());
        usuarioActual.setUsername(datosNuevos.getUsername());
        usuarioActual.setClave(datosNuevos.getClave());

        userRepository.save(usuarioActual);
        return "Usuario actualizado correctamente.";
    }

    @Override
    public String eliminarUsuario(String cedula) {
        if (!userRepository.existsById(cedula)) {
            return "Error: No se puede eliminar, el usuario no existe.";
        }

        userRepository.deleteById(cedula);
        return "Usuario eliminado con éxito.";
    }
}