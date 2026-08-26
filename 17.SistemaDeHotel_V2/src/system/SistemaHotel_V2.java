package system;

import services.HospedeService;
import services.QuartoService;
import services.ReservaService;

public class SistemaHotel_V2 {

    private final HospedeService hospedeService;
    private final QuartoService quartoService;
    private final ReservaService reservaService;

    public SistemaHotel_V2() {
        this.hospedeService = new HospedeService();
        this.quartoService = new QuartoService();
        this.reservaService = new ReservaService(hospedeService, quartoService);
    }

    public HospedeService getHospedeService() {return hospedeService;}

    public QuartoService getQuartoService() {return quartoService;}

    public ReservaService getReservaService() {return reservaService;}
}
