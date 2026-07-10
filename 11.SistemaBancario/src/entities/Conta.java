package entities;

import enums.TipoConta;

public class Conta {

    private static int contador = 0;
    private int id;
    private String numeroConta;
    private Cliente cliente;
    private double saldo;
    private TipoConta tipoConta;
    private boolean ativa;

    public Conta(String numeroConta, Cliente cliente, double saldo, TipoConta tipoConta) {
        contador++;
        this.id = contador;
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.ativa = true;
    }

    public int getId() {
        return id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void sacar(double valor) {
        this.saldo -= valor;
    }

    public void depositar(double valor){
        this.saldo += valor;
    }

    @Override
    public String toString() {
        return "Conta" +
                "\nId: " + id +
                "\nNumeroConta: " + numeroConta +
                "\nCliente: " + cliente.getNome() +
                "\nSaldo: " + saldo +
                "\nTipoConta: " + tipoConta +
                "\nAtiva: " + ativa;
    }
}
