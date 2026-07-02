package entities;

public class Sala {

    private static int contador;
    private int id;
    private String nome;
    private int capacidade;

    public Sala(String nome, int capacidade) {
        contador++;
        this.id = contador;
        this.nome = nome;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return "Sala" +
                "\nId: " + id +
                "\nNome: " + nome +
                "\nCapacidade: " + capacidade;
    }
}
