package com.itm.cartera_multibanco.service.impl;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

import com.itm.cartera_multibanco.dao.CarteraDao;
import com.itm.cartera_multibanco.model.Cartera;
import com.itm.cartera_multibanco.service.CarteraService;

@Service
public class CarteraServiceImpl implements CarteraService {

    private final CarteraDao carteraDao;

    public CarteraServiceImpl(CarteraDao carteraDao) {
        this.carteraDao = carteraDao;
    }

    @Override
    public Cartera obtenerPorCedula(String cedula) {
        return carteraDao.findByCedula(cedula);
    }

    @Override
    public Cartera crear(Cartera cartera) {
        carteraDao.save(cartera);
        return cartera;
    }

    @Override
    public Cartera actualizarSaldo(String cedula, BigDecimal saldo) {
        carteraDao.updateSaldo(cedula, saldo);
        return carteraDao.findByCedula(cedula);   // retorna el estado actualizado
    }

    @Override
    public boolean eliminar(String cedula) {
        return carteraDao.delete(cedula) > 0;
    }
}