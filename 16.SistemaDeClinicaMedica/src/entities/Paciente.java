package entities;

public class Paciente  extends Pessoa{

    private String convenio;

    public Paciente(String nome, String cpf, String convenio) {
        super(nome, cpf);
        this.convenio = convenio;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    @Override
    public String toString() {
        return "=======Paciente======= " +
                "\nId: " + getId() +
                "\nNome: " + getNome() +
                "\nCpf: " + getCpf() +
                "\nConvenio='" + convenio;
    }
}
