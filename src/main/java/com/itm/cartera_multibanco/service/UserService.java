package com.itm.cartera_multibanco.service;

import com.itm.cartera_multibanco.model.User;

import java.util.List;

public interface UserService {

    List<User> listarTodo();

    String crearUsuario(User usuario);

    User buscarPorCedula(String cedula);

    String actualizarUsuario(String cedula, User datosNuevos);

    String eliminarUsuario(String cedula);
}