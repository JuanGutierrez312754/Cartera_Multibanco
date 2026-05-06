package com.itm.cartera_multibanco.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.itm.cartera_multibanco.dao.CuentaBancariaDao;
import com.itm.cartera_multibanco.model.CuentaBancaria;
import com.itm.cartera_multibanco.service.impl.CuentaBancariaServiceImpl;

class CuentaBancariaServiceImplTest {

    @Mock
    private CuentaBancariaDao cuentaDao;

    @InjectMocks
    private CuentaBancariaServiceImpl cuentaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarPorCedula_retornaListaDeCuentas() {
        List<CuentaBancaria> cuentas = Arrays.asList(
            new CuentaBancaria("C001", "123", 1, new BigDecimal("1000.00")),
            new CuentaBancaria("C002", "123", 2, new BigDecimal("2000.00"))
        );
        when(cuentaDao.findByCedula("123")).thenReturn(cuentas);

        List<CuentaBancaria> resultado = cuentaService.listarPorCedula("123");

        assertEquals(2, resultado.size());
        verify(cuentaDao, times(1)).findByCedula("123");
    }

    @Test
    void crear_guardaYRetornaCuenta() {
        CuentaBancaria nueva = new CuentaBancaria("C001", "123", 1, new BigDecimal("500.00"));
        when(cuentaDao.save(nueva)).thenReturn(1);

        CuentaBancaria resultado = cuentaService.crear(nueva);

        assertNotNull(resultado);
        assertEquals("C001", resultado.getCuenta());
        verify(cuentaDao, times(1)).save(nueva);
    }

    @Test
    void actualizarSaldo_retornaCuentaActualizada() {
        CuentaBancaria actualizada = new CuentaBancaria("C001", "123", 1, new BigDecimal("800.00"));
        when(cuentaDao.findByCuenta("C001")).thenReturn(actualizada);

        CuentaBancaria resultado = cuentaService.actualizarSaldo("C001", new BigDecimal("800.00"));

        assertNotNull(resultado);
        assertEquals(new BigDecimal("800.00"), resultado.getSaldo());
        verify(cuentaDao, times(1)).updateSaldo("C001", new BigDecimal("800.00"));
    }

    @Test
    void eliminar_cuandoEliminaExitoso_retornaTrue() {
        when(cuentaDao.delete("C001")).thenReturn(1);

        boolean resultado = cuentaService.eliminar("C001");

        assertTrue(resultado);
    }

    @Test
    void eliminar_cuandoNoExiste_retornaFalse() {
        when(cuentaDao.delete("C999")).thenReturn(0);

        boolean resultado = cuentaService.eliminar("C999");

        assertFalse(resultado);
    }
}