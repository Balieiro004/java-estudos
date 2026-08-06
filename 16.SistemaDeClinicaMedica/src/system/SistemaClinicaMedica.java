package system;

import services.MedicoService;
import services.PacienteService;

public class SistemaClinicaMedica {

    PacienteService pacienteService;
    MedicoService medicoService;

    public SistemaClinicaMedica() {

        this.pacienteService = new PacienteService();
        this.medicoService = new MedicoService();
    }

    public PacienteService getPacienteService() {return pacienteService;}
    public MedicoService getMedicoService() {return medicoService;}
}
