package com.itm.cartera_multibanco.service.impl;

import com.itm.cartera_multibanco.dao.CuentaBancariaDao;
import com.itm.cartera_multibanco.model.CuentaBancaria;
import com.itm.cartera_multibanco.service.CuentaBancariaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    private final CuentaBancariaDao cuentaDao;

    public CuentaBancariaServiceImpl(CuentaBancariaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    @Override
    public List<CuentaBancaria> listarPorCedula(String cedula) {
        return cuentaDao.findByCedula(cedula);
    }

    @Override
    public void crear(CuentaBancaria cuenta) {
        cuentaDao.save(cuenta);
    }

    @Override
    public void actualizarSaldo(String numeroCuenta, BigDecimal saldo) {
        cuentaDao.updateSaldo(numeroCuenta, saldo);
    }

    @Override
    public void eliminar(String numeroCuenta) {
        cuentaDao.delete(numeroCuenta);
    }
}