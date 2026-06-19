package entities;

public class Colaborador extends Pessoa{

    public Colaborador(String nome, String email) {
        super(nome, email);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: Colaborador";
    }
}
