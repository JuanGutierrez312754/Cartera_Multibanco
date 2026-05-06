package com.itm.cartera_multibanco.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.itm.cartera_multibanco.dao.CuentaBancariaDao;
import com.itm.cartera_multibanco.model.CuentaBancaria;
import com.itm.cartera_multibanco.service.impl.TransferenciaServiceImpl;

class TransferenciaServiceImplTest {

    @Mock
    private CuentaBancariaDao cuentaDao;

    @InjectMocks
    private TransferenciaServiceImpl transferenciaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void transferir_exitoso_retornaMensajeExito() {
        CuentaBancaria origen = new CuentaBancaria("C001", "123", 1, new BigDecimal("1000.00"));
        CuentaBancaria destino = new CuentaBancaria("C002", "456", 1, new BigDecimal("500.00"));

        when(cuentaDao.findByCuenta("C001")).thenReturn(origen);
        when(cuentaDao.findByCuenta("C002")).thenReturn(destino);

        String resultado = transferenciaService.transferir("C001", "C002", new BigDecimal("300.00"));

        assertEquals("Transferencia exitosa", resultado);
        verify(cuentaDao).updateSaldo("C001", new BigDecimal("700.00"));
        verify(cuentaDao).updateSaldo("C002", new BigDecimal("800.00"));
    }

    @Test
    void transferir_saldoInsuficiente_retornaMensajeError() {
        CuentaBancaria origen = new CuentaBancaria("C001", "123", 1, new BigDecimal("100.00"));
        CuentaBancaria destino = new CuentaBancaria("C002", "456", 1, new BigDecimal("500.00"));

        when(cuentaDao.findByCuenta("C001")).thenReturn(origen);
        when(cuentaDao.findByCuenta("C002")).thenReturn(destino);

        String resultado = transferenciaService.transferir("C001", "C002", new BigDecimal("300.00"));

        assertEquals("Saldo insuficiente", resultado);
        verify(cuentaDao, never()).updateSaldo(any(), any());
    }

    @Test
    void transferir_cuentaOrigenNoExiste_retornaMensajeError() {
        when(cuentaDao.findByCuenta("C999")).thenReturn(null);
        when(cuentaDao.findByCuenta("C002")).thenReturn(new CuentaBancaria());

        String resultado = transferenciaService.transferir("C999", "C002", new BigDecimal("100.00"));

        assertEquals("Cuenta no encontrada", resultado);
        verify(cuentaDao, never()).updateSaldo(any(), any());
    }

    @Test
    void transferir_cuentaDestinoNoExiste_retornaMensajeError() {
        when(cuentaDao.findByCuenta("C001")).thenReturn(new CuentaBancaria());
        when(cuentaDao.findByCuenta("C999")).thenReturn(null);

        String resultado = transferenciaService.transferir("C001", "C999", new BigDecimal("100.00"));

        assertEquals("Cuenta no encontrada", resultado);
        verify(cuentaDao, never()).updateSaldo(any(), any());
    }
}