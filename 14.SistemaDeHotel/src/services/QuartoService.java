package services;

import entities.Quarto;
import enums.StatusQuarto;
import enums.TipoQuarto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuartoService {

    private List<Quarto> quartos = new ArrayList<Quarto>();

    public QuartoService() {carregarQuartosMock();}

    public List<Quarto> listarQuartos() {
        return Collections.unmodifiableList(quartos);
    }

    public Quarto cadastrarQuarto(int numero, TipoQuarto tipoQuarto, double valorDiaria){

        if (numero <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }

        if(tipoQuarto == null){
            throw new IllegalArgumentException("Tipo de quarto inválido");
        }

        if(valorDiaria <= 0){
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }

        Quarto quarto = new Quarto(numero, tipoQuarto, valorDiaria);
        quartos.add(quarto);
        return quarto;
    }

    public Quarto buscarQuartoPorNumero(int numeroQuarto){
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numeroQuarto) {
                return quarto;
            }
        }
        return null;
    }

    public Quarto buscarQuartoPorId(int id){
        for (Quarto quarto : quartos) {
            if (quarto.getId() == id) {
                return quarto;
            }
        }
        return null;
    }

    public Quarto colocarQuartoEmManutencaoPorNumeroDoQuarto(int numeroQuarto){
        Quarto quarto = buscarQuartoPorNumero(numeroQuarto);

        if(quarto == null){
            throw new IllegalArgumentException("Quarto não encontrado.");
        }

        if(quarto.getStatus() == StatusQuarto.OCUPADO){
            throw new IllegalArgumentException("Este quarto está sendo usado no momento.");
        }
        quarto.manutencao();
        return quarto;
    }

    public void liberarQuartoPorNumero(int numeroQuarto){
        Quarto quarto = buscarQuartoPorNumero(numeroQuarto);

        if(quarto == null){
            throw new IllegalArgumentException("Quarto não encontrado.");
        }

        quarto.disponivel();

    }
    public void carregarQuartosMock() {
        cadastrarQuarto(
                101,
                TipoQuarto.SIMPLES,
                180.00
        );

        cadastrarQuarto(
                102,
                TipoQuarto.DUPLO,
                280.00
        );

        cadastrarQuarto(
                201,
                TipoQuarto.LUXO,
                450.00
        );

        cadastrarQuarto(
                202,
                TipoQuarto.SUITE,
                650.00
        );

        cadastrarQuarto(
                301,
                TipoQuarto.SIMPLES,
                190.00
        );

        cadastrarQuarto(
                302,
                TipoQuarto.DUPLO,
                300.00
        );
    }
}
