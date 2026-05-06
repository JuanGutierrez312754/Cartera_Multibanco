package com.itm.cartera_multibanco.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.itm.cartera_multibanco.dao.CarteraDao;
import com.itm.cartera_multibanco.model.Cartera;
import com.itm.cartera_multibanco.service.impl.CarteraServiceImpl;

class CarteraServiceImplTest {

    @Mock
    private CarteraDao carteraDao;  // Se crea un Dao falso aqui para las pruebas

    @InjectMocks
    private CarteraServiceImpl carteraService;  // Acá se prueba la clase real

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Se inicializa el mockk
    }

    @Test
    void obtenerPorCedula_cuandoExiste_retornaCartera() {
        Cartera carteraEsperada = new Cartera("123", new BigDecimal("1000.00"));
        when(carteraDao.findByCedula("123")).thenReturn(carteraEsperada);

        Cartera resultado = carteraService.obtenerPorCedula("123");

        assertNotNull(resultado);
        assertEquals("123", resultado.getCedula());
        assertEquals(new BigDecimal("1000.00"), resultado.getSaldo());
    }

    @Test
    void obtenerPorCedula_cuandoNoExiste_retornaNull() {
        when(carteraDao.findByCedula("999")).thenReturn(null);

        Cartera resultado = carteraService.obtenerPorCedula("999");

        assertNull(resultado);
    }

    @Test
    void crear_guardaYRetornaCartera() {
        Cartera nueva = new Cartera("456", new BigDecimal("500.00"));
        when(carteraDao.save(nueva)).thenReturn(1);

        Cartera resultado = carteraService.crear(nueva);

        assertNotNull(resultado);
        assertEquals("456", resultado.getCedula());
        verify(carteraDao, times(1)).save(nueva);
    }

    @Test
    void eliminar_cuandoEliminaExitoso_retornaTrue() {
        when(carteraDao.delete("123")).thenReturn(1);

        boolean resultado = carteraService.eliminar("123");

        assertTrue(resultado);
    }

    @Test
    void eliminar_cuandoNoExiste_retornaFalse() {
        when(carteraDao.delete("999")).thenReturn(0);

        boolean resultado = carteraService.eliminar("999");

        assertFalse(resultado);
    }
}