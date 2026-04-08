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