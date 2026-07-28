package entities;

import enums.StatusMatricula;

import java.time.LocalDate;

public class Matricula {

    private static int contador;
    private int id;
    private Aluno aluno;
    private Plano plano;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    private StatusMatricula status;

    public Matricula() {}

    public Matricula(Aluno aluno, Plano plano, LocalDate dataInicio, LocalDate dataFim) {
        contador++;
        this.id = contador;
        this.aluno = aluno;
        this.plano = plano;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        ativa();
    }

    public void ativa(){this.status = StatusMatricula.ATIVA;}
    public void suspensa(){this.status = StatusMatricula.SUSPENSA;}
    public void cancelada(){this.status = StatusMatricula.CANCELADA;}
    public void encerrada(){this.status = StatusMatricula.ENCERRADA;}

    public int getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "========Matricula========" +
                "\nId: " + id +
                "\nAluno: " + aluno +
                "Plano: " + plano +
                "DataInicio: " + dataInicio +
                "\nDataFim: " + dataFim +
                "\nStatus: " + status;
    }
}
