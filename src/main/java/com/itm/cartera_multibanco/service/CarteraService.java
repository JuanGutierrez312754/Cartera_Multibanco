package com.itm.cartera_multibanco.service;

import com.itm.cartera_multibanco.model.Cartera;

public interface CarteraService {
    Cartera obtenerPorCedula(String cedula);
    void crear(Cartera cartera);
    void actualizarSaldo(String cedula, java.math.BigDecimal saldo);
}