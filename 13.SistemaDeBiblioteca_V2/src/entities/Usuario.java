package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {

    private static int contador = 0;
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario(String nome, String cpf, String telefone, String email) {
        contador++;
        this.id = contador;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Emprestimo> getEmprestimos() {return Collections.unmodifiableList(emprestimos); }

    public void adicionarEmprestimo(Emprestimo emprestimo){
        emprestimos.add(emprestimo);
    }

    public boolean possuiEmprestimoAtrasado() {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao() == null && emprestimo.getDataPrevistaDevolucao().isBefore(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
    }

    // TODO: Implementar controle de multas pendentes.

    @Override
    public String toString() {
        return "========Usuario========" +
                "\nId: " + id +
                "\nNome: " + nome +
                "\nCpf: " + cpf +
                "\nTelefone: " + telefone +
                "\nEmail: " + email;
    }
}
