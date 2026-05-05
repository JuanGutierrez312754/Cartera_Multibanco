package com.itm.cartera_multibanco.service;

import java.math.BigDecimal;
import java.util.List;

import com.itm.cartera_multibanco.model.CuentaBancaria;

public interface CuentaBancariaService {
    List<CuentaBancaria> listarPorCedula(String cedula);
    CuentaBancaria crear(CuentaBancaria cuenta); 
    CuentaBancaria actualizarSaldo(String cuenta, BigDecimal saldo);
    boolean eliminar(String cuenta);
}