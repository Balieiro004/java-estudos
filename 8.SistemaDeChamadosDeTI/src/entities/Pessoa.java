package entities;

public abstract class Pessoa {

    private static int contador;
    private int id;
    private String nome;
    private String email;

    public Pessoa(String nome, String email) {
        contador++;
        this.id = contador;
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa " +
                "\nid: " + id +
                "\nnome: " + nome +
                "\nemail: " + email;
    }
}
