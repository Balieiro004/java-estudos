package system;

import services.AlunoService;
import services.MatriculaService;
import services.PlanoService;

public class SistemaAcademia {

    private AlunoService alunoService;
    private PlanoService planoService;
    private MatriculaService matriculaService;

    public SistemaAcademia() {
        this.alunoService = new AlunoService();
        this.planoService = new PlanoService();
        this.matriculaService = new MatriculaService(alunoService, planoService);
    }

    public AlunoService getAlunoService() {return alunoService;}
    public PlanoService getPlanoService() {return planoService;}
    public MatriculaService getMatriculaService() {return matriculaService;}
}
