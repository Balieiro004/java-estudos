package services;

import entities.Cliente;
import entities.Conta;
import enums.TipoConta;

import java.util.ArrayList;
import java.util.List;

public class ContaService {

    private ClienteService clienteService;
    List<Conta> contas = new ArrayList<>();

    public ContaService(ClienteService clienteService) {
        this.clienteService = clienteService;
        carregarContasMock();
    }

    public List<Conta> listaContas() {
        return contas;
    }

    public Conta cadastrarConta(String numeroConta, int idCliente, double saldo, TipoConta tipoConta){

        if(numeroConta == null || numeroConta.isBlank()){
            throw new IllegalArgumentException("Numero da conta é obrigatório.");
        }

        if (buscarContaPorNumeroConta(numeroConta) != null){
            throw new IllegalArgumentException("Já existe uma conta com esse numero.");
        }

        Cliente cliente = clienteService.buscarClientePorId(idCliente);

        if(cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        if(saldo < 0){
            throw new IllegalArgumentException("Saldo não pode ser negativo.");
        }

        if(tipoConta == null){
            throw new IllegalArgumentException("Tipo da conta é obrigatória.");
        }

        Conta conta = new Conta(numeroConta, cliente, saldo, tipoConta);
        contas.add(conta);
        return conta;
    }

    private Conta buscarContaPorNumeroConta(String numeroConta){
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equalsIgnoreCase(numeroConta)) {
                return conta;
            }
        }
        return null;
    }

    public void depositar(String numeroConta, double valor){

        Conta conta = validarContaParaMovimentacao(numeroConta, valor);

        conta.depositar(valor);
    }
    public void sacar(String numeroConta, double valor){

        Conta conta = validarContaParaMovimentacao(numeroConta, valor);

        validarSaldo(conta, valor);

        conta.sacar(valor);
    }

    public void transferir(String numeroContaOrigem, String numeroContaDestino, double valor){

        validarValor(valor);

        if(numeroContaOrigem.equalsIgnoreCase(numeroContaDestino)){
            throw new IllegalArgumentException("A conta de origem e destino devem ser diferentes");
        }

        sacar(numeroContaOrigem, valor);
        depositar(numeroContaDestino, valor);
    }

    public double consultarSaldo(String numeroConta){
        Conta conta = buscarContaPorNumeroConta(numeroConta);

        validarContaInexistente(conta);

        validarContaAtiva(conta);

        return conta.getSaldo();
    }

    public void encerrarConta(String numeroConta){
        Conta conta = buscarContaPorNumeroConta(numeroConta);

        validarContaInexistente(conta);

        if (conta.getSaldo() != 0){
            throw new IllegalArgumentException("Saldo precisa ser 0 para encerrar a conta.");
        }

        contas.remove(conta);
    }

    private Conta validarContaParaMovimentacao(String numeroConta, double valor){

        Conta conta = buscarContaPorNumeroConta(numeroConta);

        validarContaInexistente(conta);
        validarContaAtiva(conta);
        validarValor(valor);

        return conta;
    }

    private void validarContaAtiva(Conta conta) {
        if (!conta.isAtiva()) {
            throw new IllegalArgumentException("Conta inativa.");
        }
    }

    private void validarContaInexistente(Conta conta) {
        if (conta == null){
            throw new IllegalArgumentException("Conta inexistente.");
        }
    }

    private void validarValor(double valor) {

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }
    }

    private void validarSaldo(Conta conta, double valor) {

        if (conta.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }

    private void carregarContasMock() {

        cadastrarConta(
                "0001-0",
                1,
                1500.00,
                TipoConta.CORRENTE
        );

        cadastrarConta(
                "0002-0",
                2,
                2500.50,
                TipoConta.POUPANCA
        );

        cadastrarConta(
                "0003-0",
                3,
                800.75,
                TipoConta.CORRENTE
        );

        cadastrarConta(
                "0004-0",
                4,
                5000.00,
                TipoConta.POUPANCA
        );

        cadastrarConta(
                "0005-0",
                5,
                3200.90,
                TipoConta.CORRENTE
        );

        cadastrarConta(
                "0006-0",
                6,
                1200.00,
                TipoConta.POUPANCA
        );
    }
}
