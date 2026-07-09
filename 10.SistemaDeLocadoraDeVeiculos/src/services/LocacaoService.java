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

        carregarLocacoesMock();
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

        veiculo.alugar();
        Locacao locacao = new Locacao(cliente, veiculo, dataRetirada, dataPrevistaDevolucao, veiculo.getValorDiaria());
        locacoes.add(locacao);
        return locacao;

    }

    public Locacao buscarLocacaoPorId(int id){
        for(Locacao locacao : locacoes){
            if(locacao.getId() == id){
                return locacao;
            }
        }
        return null;
    }

    public void devolverVeiculoPorId(int id){
        Locacao locacao = buscarLocacaoPorId(id);

        if(locacao == null){
            throw new IllegalArgumentException("Locação não encontrada.");
        }

        if(locacao.getDataDevolucao() != null){
            throw new IllegalArgumentException("Este veículo já foi devolvido.");
        }

        locacao.setDataDevolucao(LocalDate.now());

        locacao.getVeiculo().devolver();
    }

    public void cancelarLocacaoPorId(int id){
        Locacao locacao = buscarLocacaoPorId(id);

        if(locacao == null){
            throw new IllegalArgumentException("Locação não encontrada.");
        }

        if(locacao.getDataDevolucao() != null){
            locacao.getVeiculo().devolver();
        }

        locacoes.remove(locacao);
    }

    private void carregarLocacoesMock() {
        criarLocacao(1, 1,
                LocalDate.of(2026, 7, 10),
                LocalDate.of(2026, 7, 15));

        criarLocacao(2, 2,
                LocalDate.of(2026, 7, 11),
                LocalDate.of(2026, 7, 16));

        criarLocacao(3, 3,
                LocalDate.of(2026, 7, 12),
                LocalDate.of(2026, 7, 18));

        criarLocacao(4, 4,
                LocalDate.of(2026, 7, 13),
                LocalDate.of(2026, 7, 20));

        criarLocacao(5, 5,
                LocalDate.of(2026, 7, 14),
                LocalDate.of(2026, 7, 21));
    }
}
