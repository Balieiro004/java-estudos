package system;

import services.ConsultaService;
import services.MedicoService;
import services.PacienteService;

public class SistemaClinicaMedica {

    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final ConsultaService consultaService;

    public SistemaClinicaMedica() {

        this.pacienteService = new PacienteService();
        this.medicoService = new MedicoService();
        this.consultaService = new ConsultaService(pacienteService, medicoService);
    }

    public PacienteService getPacienteService() {return pacienteService;}
    public MedicoService getMedicoService() {return medicoService;}
    public ConsultaService getConsultaService() {return consultaService;}
}
