package com.itm.cartera_multibanco.service.impl;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

import com.itm.cartera_multibanco.dao.CuentaBancariaDao;
import com.itm.cartera_multibanco.model.CuentaBancaria;
import com.itm.cartera_multibanco.service.TransferenciaService;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private final CuentaBancariaDao cuentaDao;

    public TransferenciaServiceImpl(CuentaBancariaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    @Override
    public String transferir(String origen, String destino, BigDecimal monto) {

        CuentaBancaria c1 = cuentaDao.findByCuenta(origen);
        CuentaBancaria c2 = cuentaDao.findByCuenta(destino);

        if (c1 == null || c2 == null) {
            return "Cuenta no encontrada";
        }

        if (c1.getSaldo().compareTo(monto) < 0) {
            return "Saldo insuficiente";
        }

        c1.setSaldo(c1.getSaldo().subtract(monto));
        c2.setSaldo(c2.getSaldo().add(monto));

        cuentaDao.updateSaldo(origen, c1.getSaldo());
        cuentaDao.updateSaldo(destino, c2.getSaldo());

        return "Transferencia exitosa";
    }
}