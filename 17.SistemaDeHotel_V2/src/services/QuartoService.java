package services;

import entities.Quarto;
import enums.TipoQuarto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuartoService {

    private List<Quarto> quartos = new ArrayList<>();

    public QuartoService() {}

    public List<Quarto> listarQuartos() {return Collections.unmodifiableList(quartos);}

    public Quarto cadastrarQuarto(int numero, int capacidade, TipoQuarto tipoQuarto){

        validarNumero(numero);
        validarCapacidade(capacidade);
        validarTipoQuarto(tipoQuarto);

        Quarto quarto = new Quarto(numero, capacidade,tipoQuarto);
        quartos.add(quarto);
        return quarto;
    }

    public Quarto buscarQuartoPorId(int id){
        for(Quarto quarto : quartos){
            if(quarto.getId() == id){
                return quarto;
            }
        }
        return null;
    }

    public void excluirQuartoPorId(int id){

        Quarto quarto = buscarQuartoPorId(id);
        if(quarto == null){
            throw new IllegalArgumentException("Quarto não encontrado");
        }
        quartos.remove(quarto);
    }

    private void validarNumero(int numero) {
        if (numero <= 0) {throw new IllegalArgumentException("Número deve ser maior que zero.");}
        if (buscarQuartoPorId(numero) != null) {throw new IllegalArgumentException("Já existe um quarto com esse numero.");}
    }
    private void validarCapacidade(int capacidade) {

        if (capacidade <= 0) {throw new IllegalArgumentException("A capacidade deve ser maior que zero.");}
    }
    private void validarTipoQuarto(TipoQuarto tipoQuarto) {
        if (tipoQuarto == null) {throw new IllegalArgumentException("Tipo de quarto é obrigatório.");}
    }

    public Quarto buscarQuartoPorNumero(int numero){
        for(Quarto quarto : quartos){
            if(quarto.getNumero() == numero){
                return quarto;
            }
        }
        return null;
    }
}
