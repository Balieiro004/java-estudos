package system;

import services.AlunoService;

public class SistemaAcademia {

    private AlunoService alunoService;

    public SistemaAcademia() {
        this.alunoService = new AlunoService();
    }

    public AlunoService getAlunoService() {return alunoService;}
}
