package com.itm.cartera_multibanco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itm.cartera_multibanco.model.Cartera;
import com.itm.cartera_multibanco.service.CarteraService;

@RestController
@RequestMapping("/cartera")
public class CarteraController {

    private final CarteraService carteraService;

    public CarteraController(CarteraService carteraService) {
        this.carteraService = carteraService;
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> obtener(@PathVariable String cedula) {
        Cartera c = carteraService.obtenerPorCedula(cedula);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cartera cartera) {
        Cartera creada = carteraService.crear(cartera);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<?> actualizar(@PathVariable String cedula, @RequestBody Cartera cartera) {
        Cartera actualizada = carteraService.actualizarSaldo(cedula, cartera.getSaldo());
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<?> eliminar(@PathVariable String cedula) {
        boolean eliminado = carteraService.eliminar(cedula);
        if (!eliminado) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Cartera eliminada.");
    }
}