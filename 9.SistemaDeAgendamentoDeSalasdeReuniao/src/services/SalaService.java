package services;

import entities.Sala;

import java.util.ArrayList;
import java.util.List;

public class SalaService {

    private List<Sala> salas = new ArrayList<>();

    public SalaService() {
        carregarSalasMock();
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public Sala cadastrarSala(Sala sala) {
        if(sala.getNome() == null || sala.getNome().isBlank()) return null;

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

    public Sala buscarSalaPorId(int id) {
        for(Sala sala : salas){
            if(sala.getId() == id) return sala;
        }
        return null;
    }

    private void carregarSalasMock(){
        this.salas.add(new Sala("Sala Java",10));
        this.salas.add(new Sala("Sala Python",20));
        this.salas.add(new Sala("Sala C++",30));
        this.salas.add(new Sala("Sala C#",40));
    }

}
