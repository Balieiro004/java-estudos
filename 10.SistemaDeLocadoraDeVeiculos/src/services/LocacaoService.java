package services;

import entities.Cliente;
import entities.Locacao;
import entities.Veiculo;
import enums.StatusVeiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocacaoService {

    ClienteService clienteService;
    VeiculoService veiculoService;

    private List<Locacao> locacoes = new ArrayList<>();

    public LocacaoService(ClienteService clienteService, VeiculoService veiculoService) {
        this.clienteService = clienteService;
        this.veiculoService = veiculoService;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public Locacao criarLocacao(int idCliente, int idVeiculo, LocalDate dataRetirada, LocalDate dataPrevistaDevolucao){

        Cliente cliente = clienteService.buscarClientePorId(idCliente);
        Veiculo veiculo = veiculoService.buscarVeiculoPorId(idVeiculo);

        if(cliente == null){
            throw new IllegalArgumentException("Cliente inexistente.");
        }
        if(veiculo == null){
            throw new IllegalArgumentException("Veiculo inexistente.");
        }

        if (veiculo.getStatus() != StatusVeiculo.DISPONIVEL) {
            throw new IllegalArgumentException("Veiculo Indisponivel no momento.");
        }

        if (dataRetirada.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Data inválida para retirada.");
        }

        if(dataPrevistaDevolucao.isBefore(dataRetirada) || dataPrevistaDevolucao.isEqual(dataRetirada)){
            throw new IllegalArgumentException("Data para devolução precisa ser depois da data de retirada");
        }
        return new Locacao(cliente, veiculo, dataRetirada, dataPrevistaDevolucao, veiculo.getValorDiaria());

    }

}
