package com.itm.cartera_multibanco.controller;

import com.itm.cartera_multibanco.model.Banco;
import com.itm.cartera_multibanco.service.BancoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bancos")
public class BancoController {

    private final BancoService bancoService;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @GetMapping
    public ResponseEntity<?> listarBancos() {
        return ResponseEntity.ok(bancoService.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Banco banco = bancoService.buscarPorId(id);

        if (banco == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(banco);
    }

    @PostMapping
    public ResponseEntity<?> crearBanco(@Valid @RequestBody Banco banco) {
        Banco bancoCreado = bancoService.crearBanco(banco);
        return ResponseEntity.status(HttpStatus.CREATED).body(bancoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarBanco(@PathVariable Integer id, @Valid @RequestBody Banco banco) {
        Banco bancoActualizado = bancoService.actualizarBanco(id, banco);

        if (bancoActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bancoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarBanco(@PathVariable Integer id) {
        return ResponseEntity.ok(bancoService.eliminarBanco(id));
    }
}