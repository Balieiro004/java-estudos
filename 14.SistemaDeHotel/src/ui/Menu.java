package ui;

import entities.Cliente;
import system.SistemaHotel;

import java.time.format.DateTimeFormatter;
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
            System.out.println("0.Sair");
            System.out.print("Opcão: ");
            int opcao = Integer.parseInt(sc.nextLine());

            switch(opcao){
                case 1:{
                    cadastrarCliente();
                    break;
                }
                case 2:{
                    break;
                }
                case 3:{
                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    break;
                }
                case 6:{
                    break;
                }
                case 7:{
                    break;
                }
                case 8:{
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
}
