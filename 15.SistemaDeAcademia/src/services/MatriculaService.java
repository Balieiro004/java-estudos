package services;

import entities.Aluno;
import entities.Matricula;
import entities.Plano;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatriculaService {

    private AlunoService alunoService;
    private PlanoService planoService;
    private List<Matricula> matriculas = new ArrayList<>();

    public MatriculaService(AlunoService alunoService, PlanoService planoService) {
        this.alunoService = alunoService;
        this.planoService = planoService;
        carregarMatriculasMock();
    }

    public List<Matricula> getMatriculas() {return Collections.unmodifiableList(matriculas);}


    public Matricula criarMatricula(int idAluno, int idPlano, LocalDate dataInicio) {

        Aluno aluno = validarAluno(idAluno);
        Plano plano = validarPlano(idPlano);
        validarDatas(dataInicio);

        LocalDate dataFim = calculaDataFim(plano,dataInicio);

        Matricula matricula = new Matricula(aluno, plano, dataInicio, dataFim);
        matriculas.add(matricula);
        return matricula;
    }

    public Matricula buscarMatriculaPorId(int idMatricula) {
        for (Matricula matricula : matriculas) {
            if (matricula.getId() == idMatricula) {
                return matricula;
            }
        }
        return null;
    }

    public void cancelarMatriculaPorId(int idMatricula) {
        Matricula matricula = buscarMatriculaPorId(idMatricula);

        if (matricula == null) {
            throw new IllegalArgumentException("Matricula não encontrada");
        }

        matricula.cancelada();
    }

    public void suspenderMatriculaPorId(int idMatricula){
        Matricula matricula = buscarMatriculaPorId(idMatricula);

        if (matricula == null) {
            throw new IllegalArgumentException("Matricula não encontrada");
        }

        matricula.suspensa();
    }

    public void reativarMatriculaPorId(int idMatricula){
        Matricula matricula = buscarMatriculaPorId(idMatricula);

        if (matricula == null) {
            throw new IllegalArgumentException("Matricula não encontrada");
        }

        matricula.ativa();
    }

    public void encerrarMatriculaPorId(int idMatricula){
        Matricula matricula = buscarMatriculaPorId(idMatricula);

        if (matricula == null) {
            throw new IllegalArgumentException("Matricula não encontrada");
        }

        matricula.encerrada();
    }

    private Aluno validarAluno(int idAluno) {
        Aluno aluno = alunoService.buscarAlunoPorId(idAluno);

        if (aluno == null) {
            throw new IllegalArgumentException("Id de aluno inválido");
        }
        return aluno;
    }

    private Plano validarPlano(int idPlano) {
        Plano plano = planoService.buscarPlanoPorId(idPlano);
        if (plano == null) {
            throw new IllegalArgumentException("Plano inválido.");
        }
        return plano;
    }

    private void validarDatas(LocalDate dataInicio) {

        if(dataInicio == null) {
            throw new IllegalArgumentException("Data não pode ser vazia.");
        }
        if (dataInicio.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data não pode ser anterior à data de hoje.");
        }
    }

    private LocalDate calculaDataFim(Plano plano, LocalDate dataInicio) {
        return dataInicio.plusMonths(plano.getDuracaoMeses());
    }

    public void carregarMatriculasMock() {

        LocalDate hoje = LocalDate.now();

        criarMatricula(
                1,
                1,
                hoje
        );

        criarMatricula(
                2,
                2,
                hoje.plusDays(1)
        );

        criarMatricula(
                3,
                3,
                hoje.plusDays(2)
        );

        criarMatricula(
                4,
                4,
                hoje.plusDays(3)
        );

        criarMatricula(
                5,
                1,
                hoje.plusDays(4)
        );

        criarMatricula(
                6,
                2,
                hoje.plusDays(5)
        );
    }
}
