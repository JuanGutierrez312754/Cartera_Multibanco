package com.itm.cartera_multibanco.service;

import com.itm.cartera_multibanco.model.Banco;

import java.util.List;

public interface BancoService {

    List<Banco> listarTodo();

    Banco buscarPorId(Integer id);

    Banco crearBanco(Banco banco);

    Banco actualizarBanco(Integer id, Banco datosNuevos);

    String eliminarBanco(Integer id);
}