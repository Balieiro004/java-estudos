package entities;


import enums.Especialidades;

public class Medico extends Pessoa{

    private String crm;
    private Especialidades especialidade;

    public Medico(String nome, String cpf, String crm, Especialidades especialidade) {
        super(nome, cpf);
        this.crm = crm;
        this.especialidade = especialidade;
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

    public void clinicoGeral(){this.especialidade = Especialidades.CLINICO_GERAL;}
    public void cardiologia(){this.especialidade = Especialidades.CARDIOLOGIA;}
    public void pediatria(){this.especialidade = Especialidades.PEDIATRIA;}
    public void ortopedia(){this.especialidade = Especialidades.ORTOPEDIA;}
    public void dermatologia(){this.especialidade = Especialidades.DERMATOLOGIA;}
    public void psiquiatria(){this.especialidade = Especialidades.PSIQUIATRIA;}

    @Override
    public String toString() {
        return "=======Medico=======" +
                "\nId: " + getId() +
                "\nNome: " + getNome() +
                "\nCpf: " + getCpf() +
                "crm='" + crm +
                ", especialidade=" + especialidade;
    }
}
