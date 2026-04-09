package com.itm.cartera_multibanco.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itm.cartera_multibanco.dao.CuentaBancariaDao;
import com.itm.cartera_multibanco.model.CuentaBancaria;
import com.itm.cartera_multibanco.service.CuentaBancariaService;

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
    public void actualizarSaldo(String cuenta, BigDecimal saldo) {
        cuentaDao.updateSaldo(cuenta, saldo);
    }

    @Override
    public void eliminar(String cuenta) {
        cuentaDao.delete(cuenta);
    }
}