package com.itm.cartera_multibanco.service;

import java.math.BigDecimal;

public interface TransferenciaService {
    String transferir(String cuentaOrigen, String cuentaDestino, BigDecimal monto);
}