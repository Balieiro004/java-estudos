package entities;

import enums.FormaPagamento;

import java.time.LocalDate;

public class Pagamento {

    private static int contador;
    private int id;
    private Matricula matricula;
    private LocalDate dataPagamento;
    private double valorPagamento;
    private FormaPagamento formaPagamento;

    public Pagamento(Matricula matricula, LocalDate dataPagamento, double valorPagamento, FormaPagamento formaPagamento) {
        contador++;
        this.id=contador;
        this.matricula = matricula;
        this.dataPagamento = dataPagamento;
        this.valorPagamento = valorPagamento;
        this.formaPagamento = formaPagamento;
    }
    public int getId() {
        return id;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    @Override
    public String toString() {
        return "========Pagamento========" +
                "\nId=" + id +
                "\nMatricula: " + matricula.getAluno().getNome() +
                "\nData Pagamento: " + dataPagamento +
                "\nValor Pagamento: " + valorPagamento +
                "\nForma Pagamento: " + formaPagamento;
    }
}
