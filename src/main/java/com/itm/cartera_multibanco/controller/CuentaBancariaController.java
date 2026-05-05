package com.itm.cartera_multibanco.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itm.cartera_multibanco.model.CuentaBancaria;
import com.itm.cartera_multibanco.service.CuentaBancariaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaBancariaController {

    private final CuentaBancariaService service;

    public CuentaBancariaController(CuentaBancariaService service) {
        this.service = service;
    }

    @GetMapping("/{cedula}")
    public List<CuentaBancaria> listar(@PathVariable String cedula) {
        return service.listarPorCedula(cedula);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CuentaBancaria c) {
        CuentaBancaria creada = service.crear(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{cuenta}")
    public ResponseEntity<?> actualizar(@PathVariable String cuenta, @RequestBody CuentaBancaria c) {
        CuentaBancaria actualizada = service.actualizarSaldo(cuenta, c.getSaldo());
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{cuenta}")
    public ResponseEntity<?> eliminar(@PathVariable String cuenta) {
        boolean eliminado = service.eliminar(cuenta);
        if (!eliminado) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Cuenta eliminada.");
    }
}