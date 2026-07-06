package ui;

import entities.Cliente;
import system.SistemaLocadora;

import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    private SistemaLocadora sistema;

    public Menu(SistemaLocadora sistema) {
        this.sistema = sistema;
    }

    public void cadastrarCliente(){
        System.out.println("Cadastrando Cliente");

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Cpf: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        try{
            Cliente cliente = sistema.getClienteService().cadastrarCliente(nome,cpf,telefone);
            System.out.println("Cliente cadastrado com sucesso");
            System.out.println(cliente);

            System.out.println("Tecle ENTER para continuar");
            sc.nextLine();
        }catch(Exception e){
            System.out.println("erro: "+e.getMessage());

            System.out.println("Tecle ENTER para continuar");
            sc.nextLine();
        }
    }

    public void listarClientes(){
        List<Cliente> clientes = sistema.getClienteService().getClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
        System.out.println("Tecle ENTER para continuar");
        sc.nextLine();
    }

    public void buscarClientePorId(){
        System.out.println("Buscando Cliente por id");
        System.out.print("Id do Cliente: ");
        int id = sc.nextInt();

        Cliente cliente = sistema.getClienteService().buscarClientePorId(id);

        if(cliente != null){
            System.out.println(cliente);
        }else {
            System.out.println("Cliente não encontrado.");
        }

        sc.nextLine();
        System.out.println("Tecle ENTER para continuar");
        sc.nextLine();
    }

    public void deletarCliente(){
        System.out.println("Excluir Cliente");
        System.out.print("Id do Cliente: ");
        int id = sc.nextInt();

        try{
            sistema.getClienteService().deletarCliente(id);
            System.out.println("Cliente deletado com sucesso!");
        }catch(IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());
        }

        sc.nextLine();
        System.out.println("Tecle ENTER para continuar");
        sc.nextLine();

    }
}
