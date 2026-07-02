package entities;

import java.util.ArrayList;
import java.util.List;

public class SistemaReservas {

    private List<Colaborador> colaboradores = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();
    private List<Sala> salas = new ArrayList<>();

    public SistemaReservas() {}

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public Sala cadastrarSala(Sala sala) {
        if(sala.getNome().isEmpty() || sala.getNome().isBlank()) return null;

        if(sala.getCapacidade() <= 0) return null;

        if(buscarSalaPorNome(sala.getNome()) != null) return null;

        salas.add(sala);
        return sala;
    }

    public String deletarSalaPorId(int idSala) {
        Sala sala = buscarSalaPorId(idSala);
        if(sala == null) return "Sala não encontrada";
        salas.remove(sala);
        return "Sala removida com sucesso";
    }

    private Sala buscarSalaPorNome(String nome) {
        for(Sala sala : salas) {
            if(sala.getNome().equalsIgnoreCase(nome)) return sala;
        }
        return null;
    }

    private Sala buscarSalaPorId(int id) {
        for(Sala sala : salas){
            if(sala.getId() == id) return sala;
        }
        return null;
    }
}
