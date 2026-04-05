package com.itm.cartera_multibanco.dao;

import com.itm.cartera_multibanco.model.Cartera;
import java.math.BigDecimal;

public interface CarteraDao {
    Cartera findByCedula(String cedula);
    int save(Cartera cartera);
    int updateSaldo(String cedula, BigDecimal saldo);
}