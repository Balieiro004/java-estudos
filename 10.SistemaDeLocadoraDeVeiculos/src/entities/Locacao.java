package entities;

import java.time.LocalDate;

public class Locacao {

    private static int contador = 0;
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;

    private LocalDate dataRetirada;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    private double valorDiaria;

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataPrevistaDevolucao, double valorDiaria) {
        contador++;
        this.id=contador;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.valorDiaria = valorDiaria;
        this.dataDevolucao = null;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @Override
    public String toString() {
        return "Locacao" +
                "\nId: " + id +
                "\nCliente: " + cliente.getNome() +
                "\nVeiculo: " + veiculo.getModelo() +
                "\nDataRetirada: " + dataRetirada +
                "\nDataPrevistaDevolucao: " + dataPrevistaDevolucao +
                "\nDataDevolucao: " + dataDevolucao +
                "\nValorDiaria: " + valorDiaria;
    }
}
