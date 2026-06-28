package entities;

import entities.enuns.Especialidade;

import java.util.ArrayList;
import java.util.List;

public class SistemaChamados {

    private List<Chamado> chamados = new ArrayList<>();
    private List<Colaborador> colaboradores = new ArrayList<>();
    private List<Tecnico> tecnicos = new ArrayList<>();

    public List<Chamado> getChamados() {
        return chamados;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public String cadastrarChamado(String titulo, String descricao, Colaborador colaborador, Tecnico tecnicoResponsavel){
        if(titulo == null || titulo.isEmpty()){
            return "Titulo vazio";
        }
        if(descricao == null || descricao.isEmpty()){
            return "O chamado precisa ter uma descrição ele está vazio";
        }

        if(colaborador == null){
            return "Colaborador vazio";
        }
        if (tecnicoResponsavel == null){
            return "Tecnico vazio";
        }
        chamados.add(new Chamado(titulo, descricao, colaborador, tecnicoResponsavel));
        return "Chamado cadastrado com sucesso";
    }

    public String cadastrarColaborador(String nome, String email) {
        if(nome == null || nome.isEmpty() || email == null || email.isEmpty()) {
            return "Nome ou Email vazio";
        }

        if(buscarColaboradorPorEmail(email) != null) {
            return "Já existe colaborador com esse email";
        }

        colaboradores.add(new Colaborador(nome, email));
        return "Colaborador cadastrado com sucesso!";
    }

    public String cadastrarTecnico(String nome, String email, Especialidade especialidade) {
        if(nome == null || nome.isEmpty() || email == null || email.isEmpty()) {
            return "Nome ou Email vazio";
        }
        if(especialidade == null) {
            return "O especialidade vazio";
        }

        tecnicos.add(new Tecnico(nome, email, especialidade));
        return "Tecnico cadastrado com sucesso!";
    }

    private Colaborador buscarColaboradorPorEmail(String email) {
        for (Colaborador colaborador : colaboradores) {
            if(colaborador.getEmail().equals(email)) {
                return colaborador;
            }
        }
        return null;
    }

    public Colaborador buscarColaboradorPorId(int id) {
        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getId() == id) {
                return colaborador;
            }
        }
        return null;
    }

    public Tecnico buscarTecnicoPorId(int id) {
        for (Tecnico tecnico : tecnicos) {
            if (tecnico.getId() == id) {
                return tecnico;
            }
        }
        return null;
    }
}
