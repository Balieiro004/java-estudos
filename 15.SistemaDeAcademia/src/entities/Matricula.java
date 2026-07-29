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

    public Matricula(Aluno aluno, Plano plano, LocalDate dataInicio, LocalDate dataFim) {
        contador++;
        this.id = contador;
        this.aluno = aluno;
        this.plano = plano;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        ativa();
    }

    public void ativa(){
        if (status == StatusMatricula.ATIVA) {
            throw new IllegalStateException("A matrícula já está ativa.");
        }

        this.status = StatusMatricula.ATIVA;
    }
    public void suspensa(){
        if (status == StatusMatricula.SUSPENSA) {
            throw new IllegalStateException("A matrícula já está suspensa.");
        }

        if (status == StatusMatricula.CANCELADA) {
            throw new IllegalStateException("Não é possível suspender uma matrícula cancelada.");
        }

        if (status == StatusMatricula.ENCERRADA) {
            throw new IllegalStateException("Não é possível suspender uma matrícula encerrada.");
        }
        this.status = StatusMatricula.SUSPENSA;
    }
    public void cancelada(){
        if (status == StatusMatricula.CANCELADA) {
            throw new IllegalStateException("A matrícula já está cancelada.");
        }

        if (status == StatusMatricula.ENCERRADA) {
            throw new IllegalStateException("Não é possível cancelar uma matrícula encerrada.");
        }
        this.status = StatusMatricula.CANCELADA;
    }
    public void encerrada(){
        if (status == StatusMatricula.ENCERRADA) {
            throw new IllegalStateException("A matrícula já está encerrada.");
        }

        if (status == StatusMatricula.CANCELADA) {
            throw new IllegalStateException("Não é possível encerrar uma matrícula cancelada.");
        }
        this.status = StatusMatricula.ENCERRADA;
    }

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
                "\nAluno: " + aluno.getNome() +
                "\nPlano: " + plano.getNome() +
                "\nDataInicio: " + dataInicio +
                "\nDataFim: " + dataFim +
                "\nStatus: " + status;
    }
}
