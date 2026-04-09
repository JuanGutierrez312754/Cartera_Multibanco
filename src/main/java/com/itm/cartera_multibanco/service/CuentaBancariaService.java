package com.itm.cartera_multibanco.service;

import java.math.BigDecimal;
import java.util.List;

import com.itm.cartera_multibanco.model.CuentaBancaria;

public interface CuentaBancariaService {
    List<CuentaBancaria> listarPorCedula(String cedula);
    void crear(CuentaBancaria cuenta);
    void actualizarSaldo(String cuenta, BigDecimal saldo);
    void eliminar(String cuenta);
}