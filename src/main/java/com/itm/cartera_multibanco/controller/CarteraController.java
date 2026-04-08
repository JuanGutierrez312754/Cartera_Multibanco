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
        carteraService.crear(cartera);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cartera creada");
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<?> actualizar(@PathVariable String cedula, @RequestBody Cartera cartera) {
        carteraService.actualizarSaldo(cedula, cartera.getSaldo());
        return ResponseEntity.ok("Saldo actualizado.");
    }
}