package entities;


import enums.Especialidades;

public class Medico extends Pessoa{

    private String crm;
    private Especialidades especialidade;

    private double valorConsulta;

    public Medico(String nome, String cpf, String crm, Especialidades especialidade, double valorConsulta) {
        super(nome, cpf);

        if (valorConsulta < 0) {throw new IllegalArgumentException("O valor da consulta não pode ser negativo.");}
        this.crm = crm;
        this.especialidade = especialidade;
        this.valorConsulta = valorConsulta;
    }


    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidades getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidades especialidade) {this.especialidade = especialidade;}

    public double getValorConsulta() {return valorConsulta;}

    @Override
    public String toString() {
        return "=======Medico=======" +
                "\nId: " + getId() +
                "\nNome: " + getNome() +
                "\nCpf: " + getCpf() +
                "\ncrm: " + crm +
                "\nespecialidade: " + especialidade;
    }
}
