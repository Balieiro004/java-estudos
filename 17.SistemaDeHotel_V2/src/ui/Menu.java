package ui;

import entities.Hospede;
import entities.Quarto;
import entities.Reserva;
import enums.TipoQuarto;
import system.SistemaHotel_V2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner sc;
    private SistemaHotel_V2 sistemaHotel;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

    public Menu(SistemaHotel_V2 sistemaHotel, Scanner sc) {
        this.sistemaHotel = sistemaHotel;
        this.sc = sc;
    }

    public void iniciar() {

        boolean executando = true;

        while (executando) {
            System.out.println("========MENU PRINCIPAL========");
            System.out.println("1.Cadastrar Hospede");
            System.out.println("2.Listar Hospedes");
            System.out.println("3.Buscar Hospede");
            System.out.println("4.Excluir Hospede");

            System.out.println("5.Cadastrar Quarto");
            System.out.println("6.Listar Quartos");
            System.out.println("7.Buscar Quarto");
            System.out.println("8.Excluir Quarto");

            System.out.println("9.Criar Reserva");
            System.out.println("10.Listar Reservas");
            System.out.println("11.Buscar Reserva");
            System.out.println("12.Calculo Hospedagem");
            System.out.println("13.Confirmar Reserva");
            System.out.println("14.Cancelar Reserva");
            System.out.println("15.Iniciar Hospedagem");
            System.out.println("16.Realizar CheckOut");
            System.out.println("17.Listar Reserva Hospede");
            System.out.println("18.Listar Reserva Quarto");
            System.out.println("19.Listar Quartos Disponiveis");
            System.out.println("20.Listar Reservas Ativas");
            System.out.println("21.Listar Reservas Finalizadas");
            System.out.println("22.Listar Reservas por Período");

            System.out.println("0.Sair");
            System.out.print("Opção: ");
            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:{
                    cadastrarHospede();
                    break;
                }
                case 2:{
                    listarHospedes();
                    break;
                }
                case 3:{
                    buscarHospedePorId();
                    break;
                }
                case 4:{
                    excluirHospede();
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
                    buscarQuartoPorId();
                    break;
                }
                case 8:{
                    excluirQuartoPorId();
                    break;
                }
                case 9:{
                    criarReserva();
                    break;
                }
                case 10:{
                    listarReservas();
                    break;
                }
                case 11:{
                    buscarReservaPorId();
                    break;
                }
                case 12:{
                    calculoHospedagemPorId();
                    break;
                }
                case 13:{
                    confirmarReserva();
                    break;
                }
                case 14:{
                    cancelarReserva();
                    break;
                }
                case 15:{
                    iniciarHospedagem();
                    break;
                }
                case 16:{
                    realizarCheckOut();
                    break;
                }
                case 17:{
                    listarReservaHospedePorId();
                    break;
                }
                case 18:{
                    listarReservaQuartoPorNumero();
                    break;
                }
                case 19:{
                    listarQuartosDisponiveis();
                    break;
                }
                case 20:{
                    listarReservasAtiva();
                    break;
                }
                case 21:{
                    listarReservasFinalizadas();
                    break;
                }
                case 22:{
                    listarReservasPorPeriodo();
                    break;
                }
                case 0:{
                    executando = false;
                    System.out.println("Saindo.....");
                    break;
                } default:{
                    System.out.println("Opção inválida.");
                }
            }
        }
    }

    private void cadastrarHospede() {
        System.out.println("========Cadastro de Hospede========");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.println("Email: ");
        String email = sc.nextLine();

        try {
            Hospede hospede = sistemaHotel.getHospedeService().cadastrarHospede(nome, cpf, telefone, email);
            System.out.println("Hospede Cadastrado com sucesso!");
            System.out.println(hospede);
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void listarHospedes(){
        System.out.println("========Lista de Hospedes========");
        List<Hospede> listaHospede = sistemaHotel.getHospedeService().listarHospedes();

        if(listaHospede.isEmpty()){
            System.out.println("Nenhum Hospede cadastrado para listar.");
        }else {
            for(Hospede hospede : listaHospede){
                System.out.println(hospede);
            }
        }

    }
    private void buscarHospedePorId(){
        System.out.println("========Buscando Hospede========");

        int idHospede = lerIdHospede();
        try{
            Hospede hospede = sistemaHotel.getHospedeService().buscarHospedePorId(idHospede);
            if(hospede == null){
                System.out.println("Nenhum Hospede encontrado com esse id.");
                return;
            }
            System.out.println(hospede);

        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void excluirHospede(){
        System.out.println("========Excluir Hospede========");
        int idHospede = lerIdHospede();

        try {
            sistemaHotel.getHospedeService().excluirHospede(idHospede);
            System.out.println("Hospede Excluido com sucesso!");
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarQuarto(){
        System.out.println("========Cadastro de Quarto========");

        System.out.print("Numero: ");
        int numeroQuarto = Integer.parseInt(sc.nextLine());

        System.out.print("Capacidade: ");
        int capacidade = Integer.parseInt(sc.nextLine());

        System.out.println("Tipo de quarto: ");
        System.out.println("1.SIMPLES");
        System.out.println("2.DUPLO");
        System.out.println("3.LUXO");
        System.out.println("4.SUITE");
        System.out.print("Opção: ");
        int opcao = Integer.parseInt(sc.nextLine());

        TipoQuarto tipoQuarto;
        switch (opcao){
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
                System.out.println("Tipo de quarto Inválido");
                return;
            }
        }

        try{
            Quarto quarto = sistemaHotel.getQuartoService().cadastrarQuarto(numeroQuarto, capacidade, tipoQuarto);
            System.out.println("Quarto Cadastrado com sucesso!");
            System.out.println(quarto);
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void listarQuartos(){
        System.out.println("========Listar Quartos========");

        List<Quarto> listaQuartos = sistemaHotel.getQuartoService().listarQuartos();
        if(listaQuartos.isEmpty()){
            System.out.println("Nenhum Quarto cadastrado para listar.");
        }else {
            listaQuartos.forEach(System.out::println);
        }
    }
    private void buscarQuartoPorId(){
        System.out.println("========Buscando Quarto========");

        int idQuarto = lerIdQuarto();

        Quarto quarto = sistemaHotel.getQuartoService().buscarQuartoPorId(idQuarto);

        System.out.println(quarto == null ? "Nenhum quarto encontrato com esse ID." : quarto.toString());
    }
    private void excluirQuartoPorId(){
        System.out.println("========Excluir Quarto========");

        int idQuarto = lerIdQuarto();

        try{
            sistemaHotel.getQuartoService().excluirQuartoPorId(idQuarto);
            System.out.println("Quarto Excluido com sucesso!");
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void criarReserva(){

        System.out.println("========Criar Reserva========");

        int idHospede = lerIdHospede();
        int idQuarto = lerNumeroDoQuarto();
        LocalDate dataCheckIn = lerDataCheckIn();
        LocalDate dataCheckOut = lerDataCheckOut();

        System.out.print("Quantidade de hospedes: ");
        int quantidadeDeHospede = Integer.parseInt(sc.nextLine());

        System.out.print("Valor Diaria: R$ ");
        double valorDiaria = Double.parseDouble(sc.nextLine());

        try{
            Reserva reserva = sistemaHotel.getReservaService().criarReserva(idHospede, idQuarto, dataCheckIn, dataCheckOut, quantidadeDeHospede, valorDiaria);
            System.out.println("Reserva Criado com sucesso!");
            System.out.println(reserva);
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void listarReservas(){
        System.out.println("========Listar Reservas========");

        List<Reserva> listaReserva = sistemaHotel.getReservaService().listarReservas();
        if(listaReserva.isEmpty()){
            System.out.println("Nenhum Reserva encontrado!");
        }else  {
            listaReserva.forEach(System.out::println);
        }
    }
    private void buscarReservaPorId() {
        System.out.println("========Buscando Reserva========");

        int idReserva = lerIdReserva();

        Reserva reserva = sistemaHotel.getReservaService().buscarReservaPorId(idReserva);

        if (reserva != null) {
            System.out.println(reserva);
        } else {
            System.out.println("Nenhuma reserva encontrada!");
        }
    }
    private void calculoHospedagemPorId(){
        System.out.println("========Calculando Hospedagem========");

        int idReserva = lerIdReserva();

        try{
            double valorHospedagem = sistemaHotel.getReservaService().calcularHospedagemPorId(idReserva);
            System.out.println("Hospedagem Calculado com sucesso!");
            System.out.println("O valor a ser pago é: " + valorHospedagem);

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

    }
    private void confirmarReserva(){
        System.out.println("========Confirmar Reserva========");
        int idReserva = lerIdReserva();

        try{
            sistemaHotel.getReservaService().confirmarReserva(idReserva);
            System.out.println("Reserva Confirado com sucesso!");
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void cancelarReserva(){
        System.out.println("========Cancelar Reserva========");
        int idReserva = lerIdReserva();

        try{
            sistemaHotel.getReservaService().cancelarReserva(idReserva);
            System.out.println("Reserva Cancelado com sucesso!");
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void iniciarHospedagem(){
        System.out.println("========Iniciar Hospedagem========");

        int idReserva = lerIdReserva();
        LocalDate dataCheckIn = lerDataCheckIn();

        try{
            sistemaHotel.getReservaService().realizarCheckIn(idReserva, dataCheckIn);
            System.out.println("Hospedagem Iniciado com sucesso!");
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void realizarCheckOut(){
        System.out.println("========Realizar Check Out========");
        int idReserva = lerIdReserva();
        LocalDate dataCheckOut = lerDataCheckOut();

        try{
            sistemaHotel.getReservaService().realizarCheckOut(idReserva, dataCheckOut);
            System.out.println("Reserva finalizada com sucesso!");
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void listarReservaHospedePorId(){
        System.out.println("========Listar Reserva Hospede========");
        int idHospede = lerIdHospede();

        try{
            List<Reserva> reservasHospede = sistemaHotel.getReservaService().listarReservasHospede(idHospede);
            if(reservasHospede.isEmpty()){
                System.out.println("Nenhum Reserva encontrada!");
            }else   {
                reservasHospede.forEach(System.out::println);
            }
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void listarReservaQuartoPorNumero(){
        System.out.println("========Listar Reserva Quarto========");
        int numeroQuarto = lerNumeroDoQuarto();

        try{
            List<Reserva> listaReserva = sistemaHotel.getReservaService().listarReservasQuarto(numeroQuarto);
            if(listaReserva.isEmpty()){
                System.out.println("Nenhum Reserva encontrada!");
            }else    {
                listaReserva.forEach(System.out::println);
            }
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void listarQuartosDisponiveis(){
        System.out.println("========Listar Quartos Disponiveis========");
        List<Quarto> listaQuartos = sistemaHotel.getReservaService().listarQuartosDisponiveis();
        if(listaQuartos.isEmpty()){
            System.out.println("Nenhum Quarto encontrado!");
        }else     {
            listaQuartos.forEach(System.out::println);
        }
    }
    private void listarReservasAtiva(){
        System.out.println("========Listar Reservas Ativa========");
        List<Reserva> listaReservas = sistemaHotel.getReservaService().listarReservasAtivas();
        if(listaReservas.isEmpty()){
            System.out.println("Nenhum Reserva encontrada!");
        }else {
            listaReservas.forEach(System.out::println);
        }
    }
    private void listarReservasFinalizadas(){
        System.out.println("========Listar Reservas Finalizadas========");
        List<Reserva> listarReservas = sistemaHotel.getReservaService().listarReservasFinalizadas();
        if(listarReservas.isEmpty()){
            System.out.println("Nenhum Reserva encontrada!");
        }else  {
            listarReservas.forEach(System.out::println);
        }
    }
    private void listarReservasPorPeriodo(){
        System.out.println("========Listar Reservas por Periodo========");

        System.out.print("Data Inicio: ");
        LocalDate dataInicio = LocalDate.parse(sc.next(), formatter);

        System.out.print("Data Fim: ");
        LocalDate dataFim = LocalDate.parse(sc.next(), formatter);

        try{
            List<Reserva> listaReservaPorPeriodo = sistemaHotel.getReservaService().listarReservas(dataInicio, dataFim);
            if(listaReservaPorPeriodo.isEmpty()){
                System.out.println("Nenhum Reserva encontrada!");
            }else     {
                listaReservaPorPeriodo.forEach(System.out::println);
            }
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

    }


    private int lerIdHospede(){
        System.out.print("Id Hospede: ");
        return Integer.parseInt(sc.nextLine());
    }

    private int lerIdQuarto(){
        System.out.print("Id Quarto: ");
        return Integer.parseInt(sc.nextLine());
    }

    private int lerNumeroDoQuarto(){
        System.out.print("Numero Quarto: ");
        return Integer.parseInt(sc.nextLine());
    }

    private LocalDate lerDataCheckIn(){
        System.out.print("Data do checkin: ");
        return LocalDate.parse(sc.nextLine(), formatter);
    }

    private LocalDate lerDataCheckOut(){
        System.out.print("Data do checkout: ");
        return LocalDate.parse(sc.nextLine(), formatter);
    }

    private int lerIdReserva(){
        System.out.print("Id Reserva: ");
        return Integer.parseInt(sc.nextLine());
    }
}
