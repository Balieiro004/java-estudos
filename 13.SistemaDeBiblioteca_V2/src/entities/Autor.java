package entities;

public class Autor {

    private static int contador = 0;
    private int id;
    private String nome;


    public Autor(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return
                "\nId: " + id +
                "\nNome='" + nome;
    }
}
