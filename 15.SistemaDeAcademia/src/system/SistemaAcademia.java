package system;

import services.AlunoService;
import services.PlanoService;

public class SistemaAcademia {

    private AlunoService alunoService;
    private PlanoService planoService;

    public SistemaAcademia() {
        this.alunoService = new AlunoService();
        this.planoService = new PlanoService();
    }

    public AlunoService getAlunoService() {return alunoService;}
    public PlanoService getPlanoService() {return planoService;}
}
