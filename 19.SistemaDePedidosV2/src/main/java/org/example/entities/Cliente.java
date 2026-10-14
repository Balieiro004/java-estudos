package org.example.entities;

public class Cliente {

    private String nome;
    private String email;

    public Cliente(String nome, String email) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email do cliente não pode ser vazio.");
        }

        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void alterarNome(String nome){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void alterarEmail(String email){
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email do cliente não pode ser vazio.");
        }
        this.email = email;
    }

}
