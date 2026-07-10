package ui;

import entities.Cliente;
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
            System.out.print("Opcão: ");
            int opcao = sc.nextInt();

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
                    break;
                }
                case 6: {
                    break;
                }
                case 7: {
                    break;
                }
                case 8: {
                    break;
                }
                case 9: {
                    break;
                }
                case 10: {
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

}
