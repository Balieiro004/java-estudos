import entities.Sala;
import entities.SistemaReservas;

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

            System.out.print("Opção: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:{
                    System.out.print("Digite o nome do Sala: ");
                    String nome = sc.next();
                    System.out.print("Digite o capacidade do Sala: ");
                    int capacidade = sc.nextInt();
                    Sala sala = new Sala(nome, capacidade);

                    sala = sistemaReservas.cadastrarSala(sala);

                    if(sala != null) {
                        System.out.println("Sala Cadastrada com sucesso!");
                        System.out.println(sala);
                    }else{
                        System.out.println("Erro ao cadastrar Sala!");
                    }

                    break;
                }
                case 2:{
                    List<Sala> salas = sistemaReservas.getSalas();

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

                    System.out.println(sistemaReservas.deletarSalaPorId(idSala));
                    break;
                }
                case 4:{
                    executando = true;
                    break;
                }
            }
        }

        sc.close();
    }
}