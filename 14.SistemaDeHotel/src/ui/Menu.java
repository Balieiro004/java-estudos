package ui;

import entities.Cliente;
import entities.Quarto;
import enums.TipoQuarto;
import system.SistemaHotel;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    SistemaHotel sistemaHotel;
    Scanner sc;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Menu(SistemaHotel sistemaHotel, Scanner sc) {
        this.sistemaHotel = sistemaHotel;
        this.sc = sc;
    }

    public void iniciar(){

        boolean executando = true;

        while(executando){
            System.out.println("========Menu========");
            System.out.println("1.Cadastrar Cliente");
            System.out.println("2.Listar Clientes");
            System.out.println("3.Buscar Cliente Por Id");
            System.out.println("4.Excluir Cliente Por Id");
            System.out.println("5.Cadastrar Quarto");
            System.out.println("6.Listar Quartos");
            System.out.println("7.Buscar Quarto por Numero");
            System.out.println("8.Buscar Quarto Por Id");
            System.out.println("9.Colocar Quarto Em Manutencao por numero");
            System.out.println("10.Liberar Quarto Por Numero");
            System.out.println("0.Sair");
            System.out.print("Opcão: ");
            int opcao = Integer.parseInt(sc.nextLine());

            switch(opcao){
                case 1:{
                    cadastrarCliente();
                    break;
                }
                case 2:{
                    listarClientes();
                    break;
                }
                case 3:{
                    buscarClientePorId();
                    break;
                }
                case 4:{
                    excluirClientePorId();
                    break;
                }
                case 5:{
                    cadastrarQuarto();
                    break;
                }
                case 6:{
                    listarQuartos();
                    break;
                }
                case 7:{
                    buscarQuartoPorNumero();
                    break;
                }
                case 8:{
                    buscarQuartoPorId();
                    break;
                }
                case 9:{
                    colocarQuartoEmManutencaoPorNumeroDoQuarto();
                    break;
                }
                case 10:{
                    liberarQuartoPorNumero();
                    break;
                }
                case 0:{
                    System.out.println("Saindo....");
                    executando = false;
                    break;
                }
                default:{
                    System.out.println("Opção inválida.");
                }
            }
        }
    }

    private void cadastrarCliente(){
        System.out.println("========Cadastrar Cliente========");
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        try{
            Cliente cliente = sistemaHotel.getClienteService().cadastrarCliente(nome, cpf, telefone, email);
            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println(cliente);
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarClientes(){
        System.out.println("========Lista de Clientes========");

        List<Cliente> clientes = sistemaHotel.getClienteService().listarClientes();

        if(clientes.isEmpty()){
            System.out.println("Nenhum cliente encontrado!");
        }else {
            for(Cliente cliente : clientes){
                System.out.println(cliente);
            }
        }
    }

    private void buscarClientePorId(){
        System.out.println("========Buscar Cliente por Id========");

        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        Cliente cliente = sistemaHotel.getClienteService().buscarClientePorId(id);

        if(cliente == null){
            System.out.println("Nenhum cliente encontrado!");
        }else{
            System.out.println(cliente);
        }
    }

    private void excluirClientePorId(){
        System.out.println("========Excluir Cliente por Id========");

        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        try{
            sistemaHotel.getClienteService().excluirClientePorId(id);
            System.out.println("Cliente excluido com sucesso!");
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarQuarto(){
        System.out.println("========Cadastrar Quarto========");

        System.out.print("Numero do Quarto: ");
        int numero = Integer.parseInt(sc.nextLine());

        System.out.println("1.Simples");
        System.out.println("2.Duplo");
        System.out.println("3.Luxo");
        System.out.println("4.Suite");

        System.out.print("Tipo do Quarto: ");
        int opcaoQuarto = Integer.parseInt(sc.nextLine());

        TipoQuarto tipoQuarto;
        switch (opcaoQuarto){
            case 1:{
                tipoQuarto = TipoQuarto.SIMPLES;
                break;
            }
            case 2:{
                tipoQuarto = TipoQuarto.DUPLO;
                break;
            }
            case 3:{
                tipoQuarto = TipoQuarto.LUXO;
                break;
            }
            case 4:{
                tipoQuarto = TipoQuarto.SUITE;
                break;
            }
            default:{
                tipoQuarto = null;
            }
        }

        System.out.print("Valor da diaria: R$ ");
        double valorDiaria = Double.parseDouble(sc.nextLine());

        try{
            Quarto quarto = sistemaHotel.getQuartoService().cadastrarQuarto(numero,tipoQuarto, valorDiaria);
            System.out.println("Quarto Cadastrado com sucesso!");
            System.out.println(quarto);
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarQuartos(){
        System.out.println("========Listar Quartos========");
        List<Quarto> quartos = sistemaHotel.getQuartoService().listarQuartos();

        if(quartos.isEmpty()){
            System.out.println("Nenhum quarto encontrado!");
        }else  {
            quartos.forEach(System.out::println);
        }
    }

    private void buscarQuartoPorNumero(){
        System.out.println("========Buscar Quarto por Numero========");

        System.out.print("Numero do Quarto: ");
        int numeroQuarto = Integer.parseInt(sc.nextLine());

        Quarto quarto = sistemaHotel.getQuartoService().buscarQuartoPorNumero(numeroQuarto);

        System.out.println(quarto == null ? "Nenhum quarto encontrado!" : quarto);
    }

    private void buscarQuartoPorId(){
        System.out.println("========Buscar Quarto por Id========");
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        Quarto quarto = sistemaHotel.getQuartoService().buscarQuartoPorId(id);

        System.out.println(quarto == null ? "Nenhum quarto encontrado!" : quarto);
    }

    private void colocarQuartoEmManutencaoPorNumeroDoQuarto(){
        System.out.println("========Colocar Quarto em Manutencao========");
        System.out.print("Numero do Quarto: ");
        int numeroQuarto = Integer.parseInt(sc.nextLine());

        try{
            Quarto quarto = sistemaHotel.getQuartoService().colocarQuartoEmManutencaoPorNumeroDoQuarto(numeroQuarto);
            System.out.println("Quarto Colocado em manutenção!");
            System.out.println(quarto);
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void liberarQuartoPorNumero(){
        System.out.println("========Liberar Quarto por Numero========");

        System.out.print("Numero do Quarto: ");
        int numeroQuarto = Integer.parseInt(sc.nextLine());

        try{
            sistemaHotel.getQuartoService().liberarQuartoPorNumero(numeroQuarto);
            System.out.println("Quarto Liberado com sucesso!");
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }


}
