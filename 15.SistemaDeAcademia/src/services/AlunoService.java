package services;

import entities.Aluno;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AlunoService {

    private List<Aluno> alunos = new ArrayList<>();

    public AlunoService() {
        carregarAlunosMock();
    }

    public List<Aluno> listarAlunos() {
        return Collections.unmodifiableList(alunos);
    }

    public Aluno cadastrarAluno(String nome, String cpf, String telefone, String email){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("Cpf é obrigatório.");
        }
        if(buscarAlunoPorCpf(cpf) != null){
            throw new IllegalArgumentException("Já existe um aluno com esse CPF.");
        }
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email é obrigatório.");
        }
        if(buscarAlunoPorEmail(email) != null) {
            throw new IllegalArgumentException("Já existe um aluno com esse email.");
        }

        Aluno aluno = new Aluno(nome, cpf, telefone, email);
        alunos.add(aluno);
        return aluno;
    }

    public Aluno buscarAlunoPorId(int id){
        for(Aluno aluno : alunos){
            if(aluno.getId() == id){
                return aluno;
            }
        }
        return null;
    }

    public void  excluirAlunoPorId(int id){
        Aluno aluno = buscarAlunoPorId(id);
        if(aluno == null){
            throw new IllegalArgumentException("Aluno não encontrado.");
        }
        alunos.remove(aluno);
    }

    private Aluno buscarAlunoPorCpf(String cpf){
        for(Aluno aluno : alunos){
            if(aluno.getCpf().equals(cpf)){
                return aluno;
            }
        }
        return null;
    }
    private Aluno buscarAlunoPorEmail(String email){
        for(Aluno aluno : alunos){
            if(aluno.getEmail().equalsIgnoreCase(email)){
                return aluno;
            }
        }
        return null;
    }

    public void carregarAlunosMock() {
        cadastrarAluno(
                "João Silva",
                "12345678901",
                "11999990001",
                "joao.silva@email.com"
        );

        cadastrarAluno(
                "Maria Oliveira",
                "23456789012",
                "11999990002",
                "maria.oliveira@email.com"
        );

        cadastrarAluno(
                "Pedro Santos",
                "34567890123",
                "11999990003",
                "pedro.santos@email.com"
        );

        cadastrarAluno(
                "Ana Costa",
                "45678901234",
                "11999990004",
                "ana.costa@email.com"
        );

        cadastrarAluno(
                "Carlos Pereira",
                "56789012345",
                "11999990005",
                "carlos.pereira@email.com"
        );

        cadastrarAluno(
                "Fernanda Lima",
                "67890123456",
                "11999990006",
                "fernanda.lima@email.com"
        );
    }
}
