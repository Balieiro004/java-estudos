package services;

import entities.Plano;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanoService {

    private List<Plano> planos = new ArrayList<>();

    public PlanoService() {carregarPlanosMock();}

    public List<Plano> listarPlanos() {return Collections.unmodifiableList(planos);}

    public void cadastrarPlano(String nome, int duracaoMeses, double valorMensal){

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome Obrigatório.");
        }

        if (duracaoMeses < 1 || duracaoMeses > 12) {
            throw new IllegalArgumentException("Opção de meses Invalida.");
        }

        if (valorMensal < 0) {
            throw new IllegalArgumentException("Valor mensal Invalida.");
        }

        Plano plano = new Plano(nome, duracaoMeses, valorMensal);
        planos.add(plano);
    }

    public Plano buscarPlanoPorId(int id){
        for (Plano plano : planos) {
            if (plano.getId() == id) {
                return plano;
            }
        }
        return null;
    }

    public void carregarPlanosMock() {
        cadastrarPlano(
                "Mensal",
                1,
                99.90
        );

        cadastrarPlano(
                "Trimestral",
                3,
                89.90
        );

        cadastrarPlano(
                "Semestral",
                6,
                79.90
        );

        cadastrarPlano(
                "Anual",
                12,
                69.90
        );
    }
}
