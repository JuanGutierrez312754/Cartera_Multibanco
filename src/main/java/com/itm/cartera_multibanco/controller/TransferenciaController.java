package com.itm.cartera_multibanco.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itm.cartera_multibanco.service.TransferenciaService;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService service;

    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> transferir(
            @RequestParam String origen,
            @RequestParam String destino,
            @RequestParam BigDecimal monto) {

        String res = service.transferir(origen, destino, monto);
        return ResponseEntity.ok(res);
    }
}