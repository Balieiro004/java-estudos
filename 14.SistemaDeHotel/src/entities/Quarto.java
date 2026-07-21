package entities;

import enums.StatusQuarto;
import enums.TipoQuarto;

public class Quarto {

    private static int contador = 0;

    private int id;
    private int numero;
    private TipoQuarto tipo;
    private double valorDiaria;
    private StatusQuarto status;

    public Quarto(int numero, TipoQuarto tipo, double valorDiaria) {
        contador++;
        this.id = contador;
        this.numero = numero;
        this.tipo = tipo;
        this.valorDiaria = valorDiaria;
        disponivel();
    }

    public void disponivel(){this.status = StatusQuarto.DISPONIVEL;}
    public void ocupado(){this.status = StatusQuarto.OCUPADO;}
    public void manutencao(){this.status = StatusQuarto.MANUTENCAO;}

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void alterarTipo(TipoQuarto tipo) {this.tipo = tipo;}

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public StatusQuarto getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "========Quarto========" +
                "Id: " + id +
                "Numero: " + numero +
                "Tipo: " + tipo +
                "ValorDiaria: " + valorDiaria +
                "Status: " + status;
    }
}
