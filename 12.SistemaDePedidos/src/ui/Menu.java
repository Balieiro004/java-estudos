package ui;

import entities.Cliente;
import system.SistemaPedidos;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private SistemaPedidos sistemaPedidos;
    private Scanner sc;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Menu(SistemaPedidos sistemaPedidos, Scanner sc) {
        this.sistemaPedidos = sistemaPedidos;
        this.sc = sc;
    }

    public void iniciar(){

        int opcao;
        do {
            System.out.println("===============MENU===============");

            System.out.println("1.Cadastrar Cliente");
            System.out.println("2.Listar Cliente");
            System.out.println("3.Buscar Cliente por Id");
            System.out.println("4.Excluir Cliente por Id");
            System.out.println("5.");
            System.out.println("6.");
            System.out.println("7.");
            System.out.println("8.");
            System.out.println("9.");
            System.out.println("0. Sair");

            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    buscarClientePorCpf();
                    break;
                case 4:
                    deletarClientePorId();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 0:
                    System.out.println("Saindo.....");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        }while (opcao != 0);
    }

    private void cadastrarCliente() {
        System.out.println("========Cadastrar Cliente========");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Cpf: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        try{
            Cliente cliente = sistemaPedidos.getClienteService().cadastrarCliente(nome, cpf, telefone);
            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println(cliente);
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarClientes(){

        List<Cliente> clientes = sistemaPedidos.getClienteService().listarClientes();

        if(clientes.isEmpty()){
            System.out.println("Lista vazia.");
        }else{
            for(Cliente cliente:clientes){
                System.out.println(cliente);
            }
        }
    }

    private void buscarClientePorCpf(){
        System.out.println("========Buscar Cliente por Id========");
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        Cliente cliente = sistemaPedidos.getClienteService().buscarClientePorId(id);

        if(cliente == null){
            System.out.println("Cliente não encontrado!");
        }else {
            System.out.println(cliente);
        }
    }
    private void deletarClientePorId(){
        System.out.println("========Deletar Cliente========");
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        try{
            sistemaPedidos.getClienteService().deletarClientePorId(id);
            System.out.println("Cliente deletado com sucesso!");
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
