package entities;

public abstract class Pessoa {

    private static int contador;
    private int id;
    private String nome;
    private String cpf;

    public Pessoa(String nome, String cpf) {
        contador++;
        this.id = contador;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId() {return id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCpf() {return cpf;}

    public void setCpf(String cpf) {this.cpf = cpf;}


}
