package entities;

public class Usuario {

    private static int contador = 0;
    private int id;
    private String nome;

    public Usuario(String nome) {
        contador++;
        this.id = contador;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario" +
                "\nid: " + id +
                "\nnome: " + nome +
                "\n=================";
    }
}
