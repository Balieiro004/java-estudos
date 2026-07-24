package ui;

import entities.Cliente;
import entities.Quarto;
import entities.Reserva;
import enums.TipoQuarto;
import system.SistemaHotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            System.out.println("11.Criar Reserva");
            System.out.println("12.Listar Reservas");
            System.out.println("13.Finalizar Reserva");
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
                case 11:{
                    criarReserva();
                    break;
                }
                case 12:{
                    listarReservas();
                    break;
                }
                case 13:{
                    finalizarReserva();
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

    private void criarReserva(){
        System.out.println("========Criar Reserva========");
        System.out.print("Id Cliente: ");
        int idCliente = Integer.parseInt(sc.nextLine());

        System.out.print("Numero Quarto: ");
        int numeroQuarto = Integer.parseInt(sc.nextLine());

        System.out.print("Check In: ");
        LocalDate checkIn = LocalDate.parse(sc.nextLine(), formatter);

        System.out.print("Check Out Previsto: ");
        LocalDate checkOutPrevisto = LocalDate.parse(sc.nextLine(), formatter);

        System.out.print("Check Out Real: ");
        LocalDate checkOutReal = LocalDate.parse(sc.nextLine(), formatter);

        try{
            Reserva reserva = sistemaHotel.getReservaService().criarReserva(idCliente,numeroQuarto,checkIn,checkOutPrevisto,checkOutReal);
            System.out.println("Reserva Criada com sucesso!");
            System.out.println(reserva);
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarReservas(){
        System.out.println("========Listar Reservas========");
        List<Reserva> reservas = sistemaHotel.getReservaService().listarReservas();

        if(reservas.isEmpty()){
            System.out.println("Nenhum Reserva encontrada!");
        }else {
            reservas.forEach(System.out::println);
        }
    }

    private void finalizarReserva(){
        System.out.println("========Finalizar Reserva========");
        System.out.print("Id da Reserva: ");
        int idReserva = Integer.parseInt(sc.nextLine());

        System.out.print("Data de checkout Real: ");
        LocalDate dataDeCheckoutReal = LocalDate.parse(sc.nextLine(), formatter);


        try{
            Reserva reserva = sistemaHotel.getReservaService().finalizarReserva(idReserva, dataDeCheckoutReal);
            System.out.println("Reserva Finalizada com sucesso!");
            System.out.println("Total: R$ " + reserva.calcularTotal());
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
