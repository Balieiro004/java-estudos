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

    public Chamado cadastrarChamado(String titulo, String descricao, Colaborador colaborador, Tecnico tecnicoResponsavel){
        if(titulo == null || titulo.isEmpty()){
            return null;
        }
        if(descricao == null || descricao.isEmpty()){
            return null;
        }

        if(colaborador == null){
            return null;
        }
        if (tecnicoResponsavel == null){
            return null;
        }

        Chamado chamado = new Chamado(titulo, descricao, colaborador, tecnicoResponsavel);
        chamados.add(chamado);
        return chamado;
    }

    public Colaborador cadastrarColaborador(String nome, String email) {
        if(nome == null || nome.isEmpty() || email == null || email.isEmpty()) {
            return null;
        }

        if(buscarColaboradorPorEmail(email) != null) {
            return null;
        }

        Colaborador colaborador = new Colaborador(nome, email);
        colaboradores.add(colaborador);
        return colaborador;

    }

    public Tecnico cadastrarTecnico(String nome, String email, Especialidade especialidade) {
        if(nome == null || nome.isEmpty() || email == null || email.isEmpty()) {
            return null;
        }
        if(especialidade == null) {
            return null;
        }

        if(buscarTecnicoPorEmail(email) != null) {
            return null;
        }

        Tecnico tecnico = new Tecnico(nome, email, especialidade);
        tecnicos.add(tecnico);
        return tecnico;
    }

    private Colaborador buscarColaboradorPorEmail(String email) {
        for (Colaborador colaborador : colaboradores) {
            if(colaborador.getEmail().equals(email)) {
                return colaborador;
            }
        }
        return null;
    }

    private Tecnico buscarTecnicoPorEmail(String email) {
        for (Tecnico tecnico : tecnicos) {
            if(tecnico.getEmail().equals(email)) {
                return tecnico;
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

    public Chamado buscarChamadoPorId(int id) {
        for (Chamado chamado : chamados) {
            if (chamado.getId() == id) {
                return chamado;
            }
        }
        return null;
    }

    public String removerChamadoPorId(int id) {
        Chamado chamado = buscarChamadoPorId(id);

        if(chamado == null) return "Chamado não encontrado";

        chamados.remove(chamado);
        return "Chamado removido com sucesso";
    }
}
