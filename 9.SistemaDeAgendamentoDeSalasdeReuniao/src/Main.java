import entities.Colaborador;
import entities.Reserva;
import entities.Sala;
import entities.SistemaReservas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaReservas sistemaReservas = new SistemaReservas();
        boolean executando = true;


        while (executando) {

            System.out.println("MENU");
            System.out.println("1 - Cadastrar Sala");
            System.out.println("2 - Listar Salas");
            System.out.println("3 - Deletar Sala");
            System.out.println("4 - Cadastrar Colaborador");
            System.out.println("5 - Listar Colaboradores");
            System.out.println("6 - Deletar Colaborador");
            System.out.println("7 - Criar Reserva");
            System.out.println("8 - Listar Reservas");

            System.out.print("Opção: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:{
                    System.out.print("Digite o nome do Sala: ");
                    String nome = sc.next();
                    System.out.print("Digite o capacidade do Sala: ");
                    int capacidade = sc.nextInt();
                    Sala sala = new Sala(nome, capacidade);

                    sala = sistemaReservas.getSalaService().cadastrarSala(sala);

                    if(sala != null) {
                        System.out.println("Sala Cadastrada com sucesso!");
                        System.out.println(sala);
                    }else{
                        System.out.println("Erro ao cadastrar Sala!");
                    }

                    break;
                }
                case 2:{
                    List<Sala> salas = sistemaReservas.getSalaService().getSalas();

                    for(Sala sala : salas){
                        System.out.println(sala);
                        System.out.println("=====================");
                    }
                    break;
                }
                case 3:{
                    System.out.println("Deletar Sala");
                    System.out.print("Informe o id da sala: ");
                    int idSala = sc.nextInt();

                    System.out.println(sistemaReservas.getSalaService().deletarSalaPorId(idSala));
                    break;
                }
                case 4:{
                    System.out.println("Cadastrar Colaborador");
                    System.out.print("Nome: ");
                    String nome = sc.next();
                    System.out.print("Email: ");
                    String email = sc.next();

                    Colaborador colaborador = new Colaborador(nome, email);

                    colaborador = sistemaReservas.getColaboradorService().cadastrarColaborador(colaborador);
                    if(colaborador != null) {
                        System.out.println("Colaborador Cadastrado com sucesso!");
                        System.out.println(colaborador);
                    }else {
                        System.out.println("Erro ao cadastrar Colaborador!");
                    }

                    sc.nextLine();
                    System.out.println("========================\nDe um ENTER para continuar.");
                    sc.nextLine();
                    break;
                }
                case 5:{
                    List<Colaborador> colaboradores = sistemaReservas.getColaboradorService().getColaboradores();

                    if (colaboradores.isEmpty()) {
                        System.out.println("Lista vazia!");
                        break;
                    }

                    for(Colaborador colaborador:colaboradores){
                        System.out.println(colaborador);
                    }

                    break;

                }
                case 6:{
                    System.out.println("Deletar Colaborador");
                    Colaborador colaborador = obterColaborador(sc, sistemaReservas);

                    if(colaborador == null) {break;}

                    System.out.println(sistemaReservas.getColaboradorService().deletarColaboradorPorId(colaborador.getId()));
                    break;

                }
                case 7:{
                    System.out.println("Criar Reserva");

                    Colaborador colaborador = obterColaborador(sc, sistemaReservas);
                    if(colaborador == null) {break;}

                    Sala sala = obterSala(sc, sistemaReservas);
                    if(sala == null) {break;}

                    System.out.print("Data (dd/MM/yyyy): ");
                    LocalDate data = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    System.out.print("Hora Inicio (HH:mm): ");
                    LocalTime horaInicio = LocalTime.parse(sc.next(), DateTimeFormatter.ofPattern("HH:mm"));

                    System.out.print("Hora Fim (HH:mm): ");
                    LocalTime horaFim = LocalTime.parse(sc.next(), DateTimeFormatter.ofPattern("HH:mm"));

                    Reserva reserva = sistemaReservas.getReservaService().criarReserva(colaborador, sala, data, horaInicio, horaFim);

                    if(reserva == null) {
                        System.out.println("Não foi possivel criar a Reserva!");
                    }else {
                        System.out.println("Reserva Criada com sucesso!");
                        System.out.println(reserva);
                    }

                    break;

                }
                case 8:{
                    executando = false;
                    break;

                }
            }
        }
        sc.close();
    }

    private static Colaborador obterColaborador(Scanner sc, SistemaReservas sistemaReservas) {
        System.out.print("Informe o id do colaborador: ");
        int id = sc.nextInt();


        Colaborador colaborador = sistemaReservas
                .getColaboradorService()
                .buscarColaboradorPorId(id);

        if (colaborador == null) {
            System.out.println("Colaborador não encontrado!");
        }

        return colaborador;
    }

    private static Sala obterSala(Scanner sc, SistemaReservas sistemaReservas) {
        System.out.print("Informe o id do sala: ");
        int id = sc.nextInt();

        Sala sala = sistemaReservas.getSalaService().buscarSalaPorId(id);

        if (sala == null) {
            System.out.println("Sala nao encontrada!");
        }
        return sala;
    }
}

