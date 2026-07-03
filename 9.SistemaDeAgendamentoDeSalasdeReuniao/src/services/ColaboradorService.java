package services;

import entities.Colaborador;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorService {

    private List<Colaborador> colaboradores = new ArrayList<>();

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public Colaborador cadastrarColaborador(Colaborador colaborador) {
        if(colaborador.getNome() == null || colaborador.getNome().isBlank()) return  null;

        if(colaborador.getEmail() == null || colaborador.getEmail().isBlank()) return  null;

        if (buscarColaboradorPorEmail(colaborador.getEmail()) != null) return null;

        if(buscarColaboradorPorNome(colaborador.getNome()) != null) return null;

        colaboradores.add(colaborador);
        System.out.println(colaborador.getNome() + "Teste");
        return colaborador;
    }

    public String deletarColaboradorPorId(int idColaborador) {
        Colaborador colaborador = buscarColaboradorPorId(idColaborador);
        if(colaborador == null) return "Colaborador Não encontrado";
        colaboradores.remove(colaborador);
        return "Colaborador removido com sucesso";
    }

    private Colaborador buscarColaboradorPorNome(String nome) {
        for (Colaborador colaborador: colaboradores) {
            if(colaborador.getNome().equalsIgnoreCase(nome)) return colaborador;
        }
        return null;
    }
    private Colaborador buscarColaboradorPorEmail(String email) {
        for(Colaborador colaborador : colaboradores) {
            if(colaborador.getEmail().equalsIgnoreCase(email)) return colaborador;
        }
        return null;
    }

    private Colaborador buscarColaboradorPorId(int id) {
        for(Colaborador colaborador: colaboradores) {
            if(colaborador.getId() == id) return colaborador;
        }
        return null;
    }
}
