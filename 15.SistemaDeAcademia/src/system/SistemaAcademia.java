package system;

import services.AlunoService;
import services.MatriculaService;
import services.PagamentoService;
import services.PlanoService;

public class SistemaAcademia {

    private AlunoService alunoService;
    private PlanoService planoService;
    private MatriculaService matriculaService;
    private PagamentoService pagamentoService;

    public SistemaAcademia() {
        this.alunoService = new AlunoService();
        this.planoService = new PlanoService();
        this.matriculaService = new MatriculaService(alunoService, planoService);
        this.pagamentoService = new PagamentoService(matriculaService);
    }

    public AlunoService getAlunoService() {return alunoService;}
    public PlanoService getPlanoService() {return planoService;}
    public MatriculaService getMatriculaService() {return matriculaService;}
    public PagamentoService getPagamentoService() {return pagamentoService;}
}
