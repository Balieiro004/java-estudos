package entities;

import services.ColaboradorService;
import services.ReservaService;
import services.SalaService;

public class SistemaReservas {

    private SalaService salaService;
    private ColaboradorService colaboradorService;
    private ReservaService reservaService;

    public SistemaReservas() {

        this.salaService = new SalaService();
        this.colaboradorService = new ColaboradorService();

        this.reservaService = new ReservaService(colaboradorService,salaService);
    }

    public SalaService getSalaService() {return salaService;}

    public ColaboradorService getColaboradorService() {
        return colaboradorService;
    }

    public ReservaService getReservaService() {return reservaService;}
}
