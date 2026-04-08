@Service
public class CarteraServiceImpl implements CarteraService {

    private final CarteraDao carteraDao;

    public CarteraServiceImpl(CarteraDao carteraDao) {
        this.carteraDao = carteraDao;
    }

    @Override
    public Cartera obtenerPorCedula(String cedula) {
        return carteraDao.findByCedula(cedula);
    }

    @Override
    public void crear(Cartera cartera) {
        carteraDao.save(cartera);
    }

    @Override
    public void actualizarSaldo(String cedula, BigDecimal saldo) {
        carteraDao.updateSaldo(cedula, saldo);
    }
}