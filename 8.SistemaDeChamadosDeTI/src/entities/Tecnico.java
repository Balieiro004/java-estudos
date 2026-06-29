package entities;

import entities.enuns.Especialidade;

public class Tecnico extends Pessoa {

    private Especialidade especialidade;

    public Tecnico(String nome, String email, Especialidade especialidade) {
        super(nome, email);
        this.especialidade = especialidade;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "========================\n" +
                super.toString() +
                "\nEspecialidade: " + especialidade +
                "\nTipo: Técnico";
    }
}
