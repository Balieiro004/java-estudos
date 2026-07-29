package ui;

import entities.Aluno;
import system.SistemaAcademia;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner sc;
    private SistemaAcademia sistemaAcademia;
    DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Menu(SistemaAcademia sistemaAcademia, Scanner sc) {
        this.sistemaAcademia = sistemaAcademia;
        this.sc = sc;
    }

    public void iniciar(){

        int opcao = 0;
        do {
            System.out.println("========Iniciando Menu========");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Listar Alunos");
            System.out.println("3. Buscar Aluno por Id");
            System.out.println("4. Excluir Aluno por Id");
            System.out.println("5. ");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:{
                    cadastrarAluno();
                    break;
                }
                case 2:{
                    listarAlunos();
                    break;
                }
                case 3:{
                    buscarAlunoPorId();
                    break;
                }
                case 4:{
                    excluirAlunoPorId();
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
                case 9:{
                    break;
                }
                case 0:{
                    System.out.println("Saindo.....");
                    break;
                }
                default:{
                    System.out.println("Opção inválida.");
                }
            }
        }while (opcao != 0);
    }

    private void cadastrarAluno(){

        System.out.println("========Cadastro de Aluno========");
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Cpf: ");
        String cpf = sc.nextLine();

        System.out.println("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        try{
            Aluno aluno = sistemaAcademia.getAlunoService().cadastrarAluno(nome,cpf,telefone,email);
            System.out.println("Aluno Cadastrado com sucesso!");
            System.out.println(aluno);
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarAlunos(){
        List<Aluno> alunos = sistemaAcademia.getAlunoService().listarAlunos();

        if(alunos.isEmpty()){
            System.out.println("Nenhum Aluno encontrado!");
        }else {
            alunos.forEach(System.out::println);
        }
    }

    private void buscarAlunoPorId(){
        System.out.println("========Buscar Aluno por Id========");
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        Aluno aluno = sistemaAcademia.getAlunoService().buscarAlunoPorId(id);
        if(aluno == null){
            System.out.println("Nenhum Aluno encontrado!");
        }else  {
            System.out.println(aluno);
        }
    }

    private void excluirAlunoPorId(){
        System.out.println("========Excluir Aluno por Id========");
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        try{
            sistemaAcademia.getAlunoService().excluirAlunoPorId(id);
            System.out.println("Aluno Excluido com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
