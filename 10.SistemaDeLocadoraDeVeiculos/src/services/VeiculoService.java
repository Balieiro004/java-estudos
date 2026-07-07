package services;

import entities.Veiculo;
import enums.CategoriaVeiculo;
import enums.StatusVeiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoService {

    private List<Veiculo> veiculos = new ArrayList<>();

    public VeiculoService() {carregarClientesMock();}

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public  Veiculo cadastrarVeiculo(String modelo, String placa, int ano, double valorDiaria, CategoriaVeiculo categoria){

        if(modelo == null || modelo.isBlank()){
            throw new IllegalArgumentException("Modelo é obrigatório.");
        }

        if(placa == null || placa.isBlank()){
            throw new IllegalArgumentException("Placa é obrigatória");
        }

        if(ano < 1980 || ano > 9999){
            throw new IllegalArgumentException("Ano inválido");
        }

        if(categoria == null){
            throw new IllegalArgumentException("Categoria é obrigatório");
        }

        if(buscarVeiculoPorPlaca(placa) != null){
            throw new IllegalArgumentException("Já existe um veiculo cadastrado com essa placa.");
        }

        Veiculo veiculo = new Veiculo(modelo, placa, ano, valorDiaria, categoria);
        veiculos.add(veiculo);
        return veiculo;
    }

    public Veiculo buscarVeiculoPorPlaca(String placa){
        for (Veiculo veiculo : veiculos) {
            if(veiculo.getPlaca().equals(placa)){
                return veiculo;
            }
        }
        return null;
    }

    public Veiculo buscarVeiculoPorId(int id){
        for (Veiculo veiculo : veiculos) {
            if(veiculo.getId() == id){
                return veiculo;
            }
        }
        return null;
    }

    public void excluirVeiculoPorId(int id){
        Veiculo veiculo = buscarVeiculoPorId(id);

        if(veiculo == null){
            throw new IllegalArgumentException("Veiculo não encontrado");
        }
        veiculos.remove(veiculo);
    }

    public void excluirVeiculoPorPlaca(String placa){
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);

        if(veiculo == null){
            throw new IllegalArgumentException("Placa não encontrado");
        }
        veiculos.remove(veiculo);
    }

    private void carregarClientesMock(){
        cadastrarVeiculo(
                "Volkswagen Gol",
                "ABC1D23",
                2021,
                180,
                CategoriaVeiculo.HATCH
        );

        cadastrarVeiculo(
                "Toyota Corolla",
                "EFG4H56",
                2023,
                150,
                CategoriaVeiculo.SEDAN
        );

        cadastrarVeiculo(
                "Jeep Compass",
                "IJK7L89",
                2022,
                170,
                CategoriaVeiculo.SUV
        );

        cadastrarVeiculo(
                "Fiat Strada",
                "MNO1P23",
                2024,
                200,
                CategoriaVeiculo.PICAPE
        );

        cadastrarVeiculo(
                "Honda CG 160",
                "QRS4T56",
                2020,
                100,
                CategoriaVeiculo.MOTO
        );
    }

}
