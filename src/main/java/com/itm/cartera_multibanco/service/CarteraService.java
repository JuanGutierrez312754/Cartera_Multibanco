package com.itm.cartera_multibanco.service;

import com.itm.cartera_multibanco.model.Cartera;
import java.math.BigDecimal;

public interface CarteraService {
    Cartera obtenerPorCedula(String cedula);
    Cartera crear(Cartera cartera);
    Cartera actualizarSaldo(String cedula, BigDecimal saldo);
    boolean eliminar(String cedula); 
}