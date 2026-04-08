public interface CuentaBancariaService {
    List<CuentaBancaria> listarPorCedula(String cedula);
    void crear(CuentaBancaria cuenta);
    void actualizarSaldo(String cuenta, BigDecimal saldo);
    void eliminar(String cuenta);
}