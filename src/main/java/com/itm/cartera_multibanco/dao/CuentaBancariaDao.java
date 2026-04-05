package com.itm.cartera_multibanco.dao;

import com.itm.cartera_multibanco.model.CuentaBancaria;
import java.math.BigDecimal;
import java.util.List;

public interface CuentaBancariaDao {
    List<CuentaBancaria> findByCedula(String cedula);
    CuentaBancaria findByCuenta(String cuenta);
    int save(CuentaBancaria cuentaBancaria);
    int updateSaldo(String cuenta, BigDecimal saldo);
    int delete(String cuenta);
}