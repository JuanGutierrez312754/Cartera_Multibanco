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
        service.crear(c);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cuenta creada");
    }

    @PutMapping("/{cuenta}")
    public ResponseEntity<?> actualizar(@PathVariable String cuenta, @RequestBody CuentaBancaria c) {
        service.actualizarSaldo(cuenta, c.getSaldo());
        return ResponseEntity.ok("Saldo actualizado");
    }

    @DeleteMapping("/{cuenta}")
    public ResponseEntity<?> eliminar(@PathVariable String cuenta) {
        service.eliminar(cuenta);
        return ResponseEntity.ok("Cuenta eliminada");
    }
}