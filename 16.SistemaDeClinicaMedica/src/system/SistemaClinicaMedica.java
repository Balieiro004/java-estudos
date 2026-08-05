package system;

import services.PacienteService;

public class SistemaClinicaMedica {

    PacienteService pacienteService;

    public SistemaClinicaMedica() {
        this.pacienteService = new PacienteService();
    }

    public PacienteService getPacienteService() {return pacienteService;}
}
