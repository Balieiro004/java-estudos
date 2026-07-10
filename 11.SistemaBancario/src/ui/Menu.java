package ui;

import entities.Cliente;
import entities.Conta;
import enums.TipoConta;
import system.SistemaBanco;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private SistemaBanco sistemaBanco;
    private Scanner sc;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Menu(SistemaBanco sistemaBanco, Scanner sc) {
        this.sistemaBanco = sistemaBanco;
        this.sc = sc;
    }

    public void iniciar(){
        boolean executando = true;

        while(executando){
            System.out.println("========Menu========");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Buscar Cliente por Id");
            System.out.println("4 - Exluir Cliente por Id");
            System.out.println("5 - Cadastrar uma Conta");
            System.out.println("6 - Listar Contas");
            System.out.println("7 - Depositar");
            System.out.println("8 - Sacar");
            System.out.println("9 - Transferir");
            System.out.println("10 - Consultar Saldo");
            System.out.println("11 - Encerrar conta");
            System.out.println("12 - Sair");
            System.out.print("Opcão: ");
            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao){
                case 1: {
                    cadastrarCliente();
                    break;
                }
                case 2: {
                    buscarClientes();
                    break;
                }
                case 3: {
                    buscarClientePorId();
                    break;
                }
                case 4: {
                    excluirClientePorId();
                    break;
                }
                case 5: {
                    cadastrarConta();
                    break;
                }
                case 6: {
                    listaContas();
                    break;
                }
                case 7: {
                    depositar();
                    break;
                }
                case 8: {
                    sacar();
                    break;
                }
                case 9: {
                    transferir();
                    break;
                }
                case 10: {
                    consultarSaldo();
                    break;
                }
                case 11:{
                    encerrarConta();
                    break;
                }
                case 12: {
                    executando = false;
                    System.out.println("Saindo....");
                    break;
                }
                default: {
                    System.out.println("Opcão Inválida");
                }
            }
        }
    }

    private void cadastrarCliente(){
        System.out.println("Cadastrar Cliente");
        System.out.print("Nome: ");
        String nome = sc.next();
        System.out.print("CPF: ");
        String cpf = sc.next();
        System.out.print("Telefone: ");
        String telefone = sc.next();

        try{
            Cliente cliente = sistemaBanco.getClienteService().cadastrarCliente(nome,cpf,telefone);
            System.out.println("Cliente cadastrado com sucesso");
            System.out.println(cliente);
        }catch(IllegalArgumentException e){
            System.out.println("Erro " + e.getMessage());
        }
    }

    private void buscarClientes(){
        List<Cliente> clienteList = sistemaBanco.getClienteService().buscarClientes();

        if(clienteList.isEmpty()){
            System.out.println("Nenhum cliente encontrado");
        }else {
            for(Cliente cliente : clienteList){
                System.out.println(cliente);
            }
        }
    }

    private void buscarClientePorId(){
        System.out.println("Buscar Cliente por Id");
        System.out.print("Id: ");
        int id = sc.nextInt();

        Cliente cliente = sistemaBanco.getClienteService().buscarClientePorId(id);
        if(cliente == null){
            System.out.println("Nenhum cliente encontrado");

        }else {
            System.out.println(cliente);
        }
    }

    private void excluirClientePorId(){
        System.out.println("Excluir Cliente por Id");
        System.out.print("Id: ");
        int id = sc.nextInt();

        try{
            sistemaBanco.getClienteService().excluirClientePorId(id);
            System.out.println("Cliente excluido com sucesso");
        }catch(IllegalArgumentException e){
            System.out.println("Erro " + e.getMessage());
        }
    }

    private void cadastrarConta(){
        System.out.println("Cadastrar Conta");

        System.out.print("Numero da Conta: ");
        String numeroConta = sc.nextLine();

        System.out.print("Id do Cliente: ");
        int idCliente = Integer.parseInt(sc.nextLine());

        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(sc.nextLine());

        System.out.println("Tipos de conta:");
        System.out.println("1 - Corrente");
        System.out.println("2 - Poupança");
        System.out.println("3 - Salário");

        System.out.print("Escolha o tipo de conta: ");
        int opcaoTipoConta = Integer.parseInt(sc.nextLine());

        TipoConta tipoConta;

        switch (opcaoTipoConta) {
            case 1: {
                tipoConta = TipoConta.CORRENTE;
                break;
            }
            case 2: {
                tipoConta = TipoConta.POUPANCA;
                break;
            }
            case 3:{
                tipoConta = TipoConta.SALARIO;
                break;
            }
            default: {
                System.out.println("Tipo de conta inválido.");
                return;
            }
        }

        try{
            Conta conta = sistemaBanco.getContaService().cadastrarConta(numeroConta, idCliente, saldo, tipoConta);
            System.out.println(conta);
            System.out.println("Conta Cadastrada com sucesso");
        }catch(IllegalArgumentException e){
            System.out.println("Erro " + e.getMessage());
        }
    }

    private void listaContas(){
        List<Conta> contas = sistemaBanco.getContaService().listaContas();
        if(contas.isEmpty()){
            System.out.println("Nenhum conta encontrado");
        }else  {
            for(Conta conta : contas){
                System.out.println(conta);
            }
        }
    }

    private void depositar(){

        try{
            String numeroConta = lerNumeroConta("Digite o numero da Conta: ");
            double valor = lerValor("Valor para depósito: ");

            sistemaBanco.getContaService().depositar(numeroConta, valor);

            System.out.println("Depósito realizado com sucesso");
        }catch(IllegalArgumentException e){
            System.out.println("Erro " + e.getMessage());
        }
    }

    private void sacar(){

        try{
            String numeroConta = lerNumeroConta("Digite o numero da Conta: ");
            double valor = lerValor("Valor para sacar: ");

            sistemaBanco.getContaService().sacar(numeroConta, valor);

            System.out.println("Saque realizado com sucesso");
        }catch(IllegalArgumentException e){
            System.out.println("Erro " + e.getMessage());
        }
    }

    private void transferir(){
        System.out.println("Transferência");

        String contaOrigem = lerNumeroConta("Conta origem: ");
        String contaDestino = lerNumeroConta("Conta destino: ");
        double valor = lerValor("Valor para transferir: ");

        try{
            sistemaBanco.getContaService().transferir(contaOrigem, contaDestino, valor);
            System.out.println("Transferencia realizada com sucesso");
        }catch(IllegalArgumentException e){
            System.out.println("Erro " + e.getMessage());
        }
    }

    private void consultarSaldo(){
        System.out.println("Consultando Saldo");

        String numeroConta = lerNumeroConta("Digite o numero da Conta: ");

        try{
            double saldo = sistemaBanco.getContaService().consultarSaldo(numeroConta);

            System.out.printf("Saldo atual: R$ %.2f%n", saldo);
        }catch(IllegalArgumentException e){
            System.out.println("Erro " + e.getMessage());
        }
    }

    private void encerrarConta(){
        System.out.println("Encerrando Conta");
        String numeroConta = lerNumeroConta("Digite o numero da Conta: ");
        try{
            sistemaBanco.getContaService().encerrarConta(numeroConta);
            System.out.println("Conta encerrada com sucesso");
        }catch(IllegalArgumentException e){
            System.out.println("Erro " + e.getMessage());
        }
    }
    private String lerNumeroConta(String mensagem){
        System.out.print(mensagem);
        return sc.nextLine();
    }

    private double lerValor(String mensagem){
        System.out.print(mensagem);
        return Double.parseDouble(sc.nextLine());
    }
}
