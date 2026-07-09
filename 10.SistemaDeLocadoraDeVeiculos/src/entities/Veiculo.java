package entities;

import enums.CategoriaVeiculo;
import enums.StatusVeiculo;

public class Veiculo {

    private static int contador = 0;
    private int id;
    private String modelo;
    private String placa;
    private int ano;
    private double valorDiaria;
    private CategoriaVeiculo categoria;
    private StatusVeiculo status;

    public Veiculo(String modelo, String placa, int ano,double valorDiaria, CategoriaVeiculo categoria) {
        contador++;
        this.id = contador;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
        this.categoria = categoria;
        this.status = StatusVeiculo.DISPONIVEL;
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public CategoriaVeiculo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaVeiculo categoria) {
        this.categoria = categoria;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void alugar(){
        this.status = StatusVeiculo.ALUGADO;
    }

    public void devolver(){
        this.status = StatusVeiculo.DISPONIVEL;
    }

    public void emManutencao(){
        this.status = StatusVeiculo.MANUTENCAO;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @Override
    public String toString() {
        return "Veiculo" +
                "\nId: " + id +
                "\nModelo: " + modelo +
                "\nPlaca: " + placa +
                "\nAno: " + ano +
                "\nValor diaria: " + valorDiaria +
                "\nCategoria: " + categoria +
                "\nStatus:" + status;
    }
}
